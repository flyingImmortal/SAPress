package cn.sxfei.app.modules.sapress.web.admin.freemarker;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.sxfei.app.core.base.dto.QueryDTO;
import cn.sxfei.app.modules.sapress.interfaces.base.dto.PostQueryDTO;
import cn.sxfei.app.modules.sapress.interfaces.base.service.admin.IAdminPostsService;
import cn.sxfei.app.modules.sapress.web.admin.dto.ContentDTO;
import cn.sxfei.app.modules.sapress.web.admin.dto.Taxonomy;
import cn.sxfei.app.modules.sapress.web.admin.dto.TplTaxonomyType;

import com.google.common.collect.Lists;

public class TaxonomyBox extends JFunction {
    @Autowired
    private IAdminPostsService adminPostsService;
    private TplTaxonomyType taxonomyType;
    private ContentDTO content;
    private List<String> contentTaxonomyList = Lists.newArrayList();

    @Override
    public Object onExec() {
        init();
        return doExec();
    }

    private void init() {

        this.taxonomyType = (TplTaxonomyType) get(0);
        this.content = (ContentDTO) get(1);
        contentTaxonomyList = Lists.newArrayList();
        if (content != null) {
            List<Long> idList = Lists.newArrayList();
            idList.add(Long.valueOf(content.getId()));
            QueryDTO dto = new QueryDTO();
            dto.setIdList(idList);
            List<Map> mapList = adminPostsService.getPostTermTaxonomy(dto);
            for (Map map : mapList) {
                if (taxonomyType.getName().equals(map.get("taxonomy"))) {
                    contentTaxonomyList.add(String.valueOf(map.get("term_id")));
                }
            }
        } 
    }

    private Object doExec() {

        // String moduleName = taxonomyType.getModule().getName();
        // String txType = taxonomyType.getName();
        List<Taxonomy> list = Lists.newArrayList();
        PostQueryDTO dto = new PostQueryDTO();
        List<String> typeList = Lists.newArrayList();
        typeList.add("category");
        typeList.add("post_tag");
        dto.setTypeList(typeList);
        dto.setPageSize(Integer.MAX_VALUE);
        List<Map> mapList = adminPostsService.selectTermAndTaxonomy(dto);
        for (Map map : mapList) {
            if (taxonomyType.getName().equals(map.get("taxonomy")) && "0".equals(String.valueOf(map.get("parent")))) {
                Taxonomy taxonomy = new Taxonomy();
                taxonomy.setId(String.valueOf(map.get("term_id")));
                taxonomy.setTitle(String.valueOf(map.get("name")));
                taxonomy.setType(String.valueOf(map.get("taxonomy")));
                List<Taxonomy> childList = Lists.newArrayList();
                for (Map map1 : mapList) {
                    String parent = String.valueOf(map1.get("parent"));
                    if (taxonomyType.getName().equals(map1.get("taxonomy")) && parent.equals(taxonomy.getId())) {
                        Taxonomy taxonomyTemp = new Taxonomy();
                        taxonomyTemp.setId(String.valueOf(map1.get("term_id")));
                        taxonomyTemp.setTitle(String.valueOf(map1.get("name")));
                        taxonomyTemp.setType(String.valueOf(map1.get("taxonomy")));
                        childList.add(taxonomyTemp);
                    }
                }
                taxonomy.setChildList(childList);
                list.add(taxonomy);
            }
        }
        StringBuilder htmlBuilder = new StringBuilder();
        if (list != null && list.size() > 0) {
            doBuilder(list, htmlBuilder);
        }
        return htmlBuilder.toString();
    }

    private void doBuilder(List<Taxonomy> list, StringBuilder htmlBuilder) {
        htmlBuilder.append("<ul>");
        for (Taxonomy taxonomy : list) {

            boolean checked = contentTaxonomyList != null && contentTaxonomyList.contains(taxonomy.getId());
            String html = "<li ><label><input  name=\"_%s\" value=\"%s\" %s type=\"checkbox\"/>%s</label></li>";
            htmlBuilder.append(String.format(html, taxonomyType.getName(), taxonomy.getId(), checked ? "checked=\"checked\"" : "",
                    taxonomy.getTitle()));

            if (taxonomy.getChildList() != null && taxonomy.getChildList().size() > 0) {
                doBuilder(taxonomy.getChildList(), htmlBuilder);
            }
        }
        htmlBuilder.append("</ul>");
    }

}
