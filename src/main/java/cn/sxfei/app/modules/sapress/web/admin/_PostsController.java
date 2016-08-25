package cn.sxfei.app.modules.sapress.web.admin;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sxfei.app.core.base.constant.Constants;
import cn.sxfei.app.core.base.dto.QueryDTO;
import cn.sxfei.app.core.base.dto.ResultPageDTO;
import cn.sxfei.app.core.spring.PropertiesHelper;
import cn.sxfei.app.core.utils.DateUtils;
import cn.sxfei.app.core.utils.StringUtil;
import cn.sxfei.app.core.utils.StringUtils;
import cn.sxfei.app.modules.sapress.comm.AjaxResult;
import cn.sxfei.app.modules.sapress.comm.SapressController;
import cn.sxfei.app.modules.sapress.interfaces.base.constants.SaConstant;
import cn.sxfei.app.modules.sapress.interfaces.base.dto.PostQueryDTO;
import cn.sxfei.app.modules.sapress.interfaces.base.dto.UserDTO;
import cn.sxfei.app.modules.sapress.interfaces.base.entity.Posts;
import cn.sxfei.app.modules.sapress.interfaces.base.entity.Terms;
import cn.sxfei.app.modules.sapress.interfaces.base.enums.ModuleTypeEnum;
import cn.sxfei.app.modules.sapress.interfaces.base.enums.PostStatusEnum;
import cn.sxfei.app.modules.sapress.interfaces.base.enums.PostTypeEnum;
import cn.sxfei.app.modules.sapress.interfaces.base.service.IPostsService;
import cn.sxfei.app.modules.sapress.interfaces.base.service.admin.IAdminPostsService;
import cn.sxfei.app.modules.sapress.services.admin.MenuInitService;
import cn.sxfei.app.modules.sapress.services.admin.TermsInitService;
import cn.sxfei.app.modules.sapress.services.admin.po.PostMetaPO;
import cn.sxfei.app.modules.sapress.services.admin.po.PostsPO;
import cn.sxfei.app.modules.sapress.services.admin.po.TermRelationshipsPO;
import cn.sxfei.app.modules.sapress.services.admin.po.TermTaxonomyPO;
import cn.sxfei.app.modules.sapress.services.admin.po.TermsPO;
import cn.sxfei.app.modules.sapress.web.admin.dto.Content;
import cn.sxfei.app.modules.sapress.web.admin.dto.ContentDTO;
import cn.sxfei.app.modules.sapress.web.admin.dto.Taxonomy;
import cn.sxfei.app.modules.sapress.web.admin.dto.TplModule;
import cn.sxfei.app.modules.sapress.web.admin.dto.TplTaxonomyType;
import cn.sxfei.app.plugins.jfinal.activerecord.Page;
import cn.sxfei.app.plugins.jfinal.activerecord.tx.Tx;
import cn.sxfei.app.plugins.jfinal.aop.Before;

import com.google.common.collect.Lists;

/**
 * 后台文章管理
 * 
 * @author songxianfei@gmail.com
 * @time 2016-08-06
 */
@Controller
@RequestMapping("/admin/content")
// @RouterMapping(url = "/admin/content", viewPath = "/WEB-INF/admin/content")
public class _PostsController extends SapressController {
    @Autowired
    private IAdminPostsService adminPostsService;
    @Autowired
    private IPostsService postsService;

    @RequestMapping({ "/index", "/", "" })
    // ?s=sxf&post_status=all&post_type=post&action=-1&m=0&cat=0&paged=1&action2=-1
    // s=sxf&post_status=all&post_type=post&m=201607&cat=2&filter_action=筛选&paged=1
    public String index() {
        // content?m=article&p=article&c=list
        PostQueryDTO queryDto = new PostQueryDTO();
        queryDto.setKeyword(getPara("k"));
        queryDto.setPostDate(getPara(""));
        String status = getPara("s");
        List<String> statusList = Lists.newArrayList();
        if (StringUtils.isNullString(status)) {
            statusList.add(PostStatusEnum.PUBLISH.getName());
            statusList.add(PostStatusEnum.DRAFT.getName());
            // statusList.add(PostStatusEnum.PRIVATE.getName());
            // statusList.add(PostStatusEnum.PENDING.getName());
            // statusList.add(PostStatusEnum.TRASH.getName());
        } else {
            if ("delete".equals(status)) {
                statusList.add(PostStatusEnum.TRASH.getName());
            } else if ("normal".equals(status)) {
                statusList.add(PostStatusEnum.PUBLISH.getName());
            } else {
                statusList.add(status);
            }
        }
        queryDto.setStatusList(statusList);
        queryDto.setPostType(getPara("m", "post"));// post_type

        // SELECT post_status, COUNT( * ) AS num_posts FROM jbpress_posts WHERE
        // post_type = 'post' GROUP BY post_status";
        List<PostsPO> postlist = PostsPO.dao.findByWhereAndColumns(" where post_type = ? GROUP BY post_status",
                "post_status, COUNT( * ) AS id  ", queryDto.getPostType());
        String trash = "0";
        String draft = "0";
        String publish = "0";
        for (PostsPO po : postlist) {
            if (PostStatusEnum.TRASH.getName().equals(po.getPostStatus())) {
                trash = po.getId();
            }
            if (PostStatusEnum.DRAFT.getName().equals(po.getPostStatus())) {
                draft = po.getId();
            }
            if (PostStatusEnum.PUBLISH.getName().equals(po.getPostStatus())) {
                publish = po.getId();
            }
        }
        setAttr("delete_count", trash);
        setAttr("draft_count", draft);
        setAttr("normal_count", publish);
        setAttr("count", Integer.parseInt(draft) + Integer.parseInt(publish));
        ResultPageDTO<Posts> page = adminPostsService.getPostsByPage(queryDto);
        Map<Long, List<Terms>> termMap = postsService.getTermByIds(page.getIdList());
        List<ContentDTO> contentList = Lists.newArrayList();
        for (Posts post : page.getList()) {
            ContentDTO dto = new ContentDTO();
            dto.setCommentCount(String.valueOf(post.getCommentCount()));
            dto.setCreated(DateUtils.format(post.getPostDate(), DateUtils.DATE_PATTERN_YYYYMMDD));
            dto.setId(String.valueOf(post.getId()));
            dto.setStatus(post.getPostStatus());
            dto.setTitle(post.getPostTitle());
            dto.setUrl(post.getGuid());
            dto.setUsername(PropertiesHelper.getProperty(Constants.USER_KEY + post.getPostAuthor()));
            List<Terms> termsList = termMap.get(post.getId());
            dto.setTermsList(termsList);
            contentList.add(0, dto);
        }
        Page<ContentDTO> result = new Page<ContentDTO>();
        result.setTotalRow(Integer.parseInt(draft) + Integer.parseInt(publish));
        result.setPageSize(queryDto.getPageSize());
        result.setPageNumber(queryDto.getPageNo());
        result.setList(contentList);
        setAttr("page", result);

        setAttr("delete_count", trash);
        setAttr("draft_count", draft);
        setAttr("normal_count", publish);
        setAttr("count", Integer.parseInt(draft) + Integer.parseInt(publish));
        TplModule module = MenuInitService.getModule(queryDto.getPostType());
        setAttr("module", module);
        QueryDTO query = new QueryDTO();
        query.setIdList(page.getIdList());
        List<Map> mapList = adminPostsService.getPostTermTaxonomy(query);
        HashMap<String, List<Taxonomy>> _taxonomyMap = new HashMap<String, List<Taxonomy>>();
        List<TplTaxonomyType> types = module.getTaxonomyTypes();
        if (!CollectionUtils.isEmpty(types)) {
            for (TplTaxonomyType type : types) {
                List<Taxonomy> taxonomys = Lists.newArrayList();
                int i = 0;
                for (Map map : mapList) {
                    if (type.getName().equals(map.get("taxonomy"))) {
                        Taxonomy taxonomy = new Taxonomy();
                        taxonomy.setId(String.valueOf(map.get("term_taxonomy_id")));
                        if (i == 0) {
                            taxonomy.set_selected("selected=\"selected\"");
                        }
                        i++;
                        // taxonomy.setLayerString(String.valueOf(map.get("layerString")));
                        taxonomy.setTitle(String.valueOf(map.get("name")));
                        taxonomys.add(taxonomy);
                    }
                }
                if (taxonomys.size() > 0) {
                    _taxonomyMap.put(type.getTitle(), taxonomys);
                }
            }
        }
        // setAttr("_taxonomyMap", _taxonomyMap);

        return "admin/content/index";
    }

    @RequestMapping(value = "/trash")
    @ResponseBody
    public AjaxResult trash() {
        PostsPO posts = PostsPO.dao.findById(getPara("id"));
        if (posts != null) {
            UserDTO userDto = getLoginUser();
            if (!"1".equals(userDto.getId())) {
                if (!userDto.getId().equals(posts.getPostAuthor())) {
                    return renderAjaxResultForError("只能把自己写的文章扔进垃圾箱!");
                }
            }
            posts.setPostStatus(PostStatusEnum.TRASH.getName());
            posts.update();
            return renderAjaxResultForSuccess("success");
        }
        return renderAjaxResultForError("文章已删除或不存在!");

    }

    @RequestMapping("/restore")
    @ResponseBody
    public AjaxResult restore() {
        BigInteger id = getParaToBigInteger("id");
        PostsPO c = PostsPO.dao.findById(id);
        if (c != null) {
            c.setPostStatus(PostStatusEnum.PUBLISH.getName());
            c.setPostModified(DateUtils.format(new Date()));
            c.update();
            return renderAjaxResultForSuccess("success");
        }
        return renderAjaxResultForError("restore error!");

    }

    @RequestMapping("/draft")
    @ResponseBody
    public AjaxResult draft() {
        PostsPO posts = PostsPO.dao.findById(getPara("id"));
        if (posts != null) {
            UserDTO userDto = getLoginUser();
            if (!"1".equals(userDto.getId())) {
                if (!userDto.getId().equals(posts.getPostAuthor())) {
                    return renderAjaxResultForError("只能把自己写的文章扔进草稿箱!");
                }
            }
            posts.setPostStatus(PostStatusEnum.DRAFT.getName());
            posts.update();
            return renderAjaxResultForSuccess("success");
        }
        return renderAjaxResultForError("trash error!");

    }

    @RequestMapping("/batchDelete")
    @ResponseBody
    public AjaxResult batchDelete() {
        String[] ids = getParas("dataItem");
        for (int i = 0; i < ids.length; i++) {
            PostsPO.dao.deleteById(ids[i]);
            PostMetaPO.dao.deleteById(ids[i]);
            TermRelationshipsPO.dao.deleteByWhere("where object_id=?", ids[i]);
        }
        return renderAjaxResultForSuccess("success");
    }

    @RequestMapping("/edit")
    public String edit() {
        String postType = getPara("p");
        TplModule module = MenuInitService.getModule(postType);
        String moduleName = module.getName();
        setAttr("module", module);

        String id = getPara("id");
        PostsPO posts = PostsPO.dao.findById(id);
        List<PostMetaPO> postMetaList = PostMetaPO.dao.findByWhere("where post_id=?  ", id);
        PostMetaPO postMeta = null;
        for (PostMetaPO po : postMetaList) {
            if (SaConstant.POST_META_THUMBNAIL.equals(po.getMetaValue())) {
                postMeta = po;
            }
        }
        if (posts != null) {
            ContentDTO content = new ContentDTO();
            if (null != postMeta) {
                content.setThumbnail(postMeta.getMetaValue());
            }
            content.setTitle(posts.getPostTitle());
            content.setText(posts.getPostContent());
            content.setSlug(posts.getGuid());
            content.setId(posts.getId());
            content.setModule(moduleName);
            content.setCreated((PropertiesHelper.getProperty(Constants.USER_KEY + posts.getPostAuthor())));
            List<Long> idList = Lists.newArrayList();
            idList.add(Long.valueOf(content.getId()));
            QueryDTO dto = new QueryDTO();
            dto.setIdList(idList);
            List<Map> mapList = adminPostsService.getPostTermTaxonomy(dto);
            List<Taxonomy> list = Lists.newArrayList();
            for (Map map : mapList) {
                Taxonomy taxonomy = new Taxonomy();
                taxonomy.setId(String.valueOf(map.get("term_id")));
                taxonomy.setTitle(String.valueOf(map.get("name")));
                taxonomy.setType(String.valueOf(map.get("taxonomy")));
                list.add(taxonomy);
            }
            content.setTaxonomyList(list);
            setAttr("content", content);
        }

        String editor = getCookie("_editor", "tinymce");
        String[] editors = editor.split("#");
        editor = editors[editors.length - 1];
        setAttr("_editor", editor);
        setAttr("urlPreffix", "");
        setAttr("urlSuffix", "");

        if (ModuleTypeEnum.PAGE.getName().equals(moduleName)) {
            setAttr("slugDisplay", " style=\"display: none\"");
        } else {
            setAttr("slugDisplay", " style=\"display: display\"");
        }
        // setAttr("taxonomyBox", new TaxonomyBox());
        return "admin/content/edit";
    }

    @RequestMapping("/save")
    @ResponseBody
    @Before(Tx.class)
    public AjaxResult save(Content arg) {
        ContentDTO content = arg.getContent();
        arg.set_category(request.getParameterValues("_category"));
        arg.set_tag(request.getParameter("_tag"));
        if (StringUtils.isBlank(content.getTitle())) {
            return renderAjaxResultForError("内容标题不能为空！");
        }
        String slug = content.getSlug();
        if (StringUtils.isBlank(slug)) {
            slug = content.getTitle();
        }
        if (slug != null) {
            if (StringUtils.isNum(slug)) {
                slug = "c" + slug; // slug不能为全是数字,随便添加一个字母，c代表content好了
            } else {
                slug = slug.replaceAll("(\\s+)|(\\.+)|(。+)|(…+)|[\\$,，？\\-?、；;:!]", "_");
                slug = slug.replaceAll("(?!_)\\pP|\\pS", "");
            }
            content.setSlug(slug);
        }
        /*
         * List<PostsPO> tempList = PostsPO.dao.findByWhere("where guid=? ",
         * slug); if (tempList.size() > 1) { return
         * renderAjaxResultForError("该URL已存在：" + slug); } for (PostsPO po :
         * tempList) { if (!po.getId().equals(content.getId())) { return
         * renderAjaxResultForError("该URL已存在：" + slug); } }
         */
        String text = content.getText();
        text = StringUtils.fileterStr(text);
        // 开始保存
        PostsPO post = new PostsPO();
        UserDTO userDto = getLoginUser();
        if (StringUtils.isNotEmpty(content.getId())) {
            post = PostsPO.dao.findById(content.getId());
            if (!"1".equals(userDto.getId())) {
                if (!userDto.getId().equals(post.getPostAuthor())) {
                    return renderAjaxResultForError("只能编辑自己写的文章!");
                }
            }
        }
        post.setPostName(arg.getUsername());

        post.setPostContent(content.getText());
        post.setPostTitle(content.getTitle());
        post.setPostModified(DateUtils.format(new Date()));
        post.setPostModifiedGmt(DateUtils.format(new Date()));
        post.setPostType(content.getModule());
        post.setPostStatus(PostStatusEnum.PUBLISH.getName());
        post.setPostAuthor(userDto.getId());
        // ----------
        post.setPostExcerpt("");
        post.setToPing("");
        post.setPinged("");
        post.setPostContentFiltered("");
        if (StringUtils.isNotEmpty(content.getId())) {
            post.setGuid("/?p=" + post.getId());
            post.update();
            // 保存文章结束
            if (!StringUtils.isNullString(content.getThumbnail())) {
                PostMetaPO postMetaPO = PostMetaPO.dao.findFirstByWhere("where meta_key = ? and post_id = ?",
                        SaConstant.POST_META_THUMBNAIL, post.getId());
                if (null == postMetaPO) {
                    postMetaPO = new PostMetaPO();

                }
                postMetaPO.setMetaKey(SaConstant.POST_META_THUMBNAIL);
                postMetaPO.setMetaValue(content.getThumbnail());
                postMetaPO.setPostId(BigInteger.valueOf(Long.parseLong(post.getId())));
                if (StringUtils.isNullString(postMetaPO.getMetaId())) {
                    postMetaPO.save();
                } else {
                    postMetaPO.update();
                }
            }
        } else {
            post.setPostDate(DateUtils.format(new Date()));
            post.setPostDateGmt(DateUtils.format(new Date()));
            post.save();
            String id = post.getId();
            post = new PostsPO();
            post.setId(id);
            post.setGuid("/?p=" + post.getId());
            post.update();
            // 保存文章结束
            if (!StringUtils.isNullString(content.getThumbnail())) {
                PostMetaPO postMetaPO = new PostMetaPO();
                postMetaPO.setMetaKey(SaConstant.POST_META_THUMBNAIL);
                postMetaPO.setMetaValue(content.getThumbnail());
                postMetaPO.setPostId(BigInteger.valueOf(Long.parseLong(post.getId())));
                postMetaPO.save();
            }
        }

        // 保存分类
        String[] categorys = arg.get_category();
        if (null != categorys && categorys.length > 0) {
            saveCategorys(categorys, post);
        }
        // 保存标签信息
        String tag = arg.get_tag();
        saveTag(tag, post);
        AjaxResult ar = new AjaxResult();
        ar.setErrorCode(0);
        ar.setData(content.getId());
        return renderAjaxResult("save ok", 0, content.getId());
    }

    private void saveCategorys(String[] categorys, PostsPO post) {
        StringBuffer termTaxonomyIdsSb = new StringBuffer("''");
        List<TermRelationshipsPO> relationshipsList = TermsInitService.termRelationshipsTermsObjectId.get(post.getId()
                + PostTypeEnum.CATEGORY.getName());
        if (null != relationshipsList) {
            for (TermRelationshipsPO po : relationshipsList) {
                termTaxonomyIdsSb.append(",'").append(po.getTermTaxonomyId()).append("'");
            }
        }
        // 删除所有关系
        TermRelationshipsPO.dao.deleteByWhere("where object_id=? and term_taxonomy_id in (" + termTaxonomyIdsSb.toString() + ")",
                post.getId());
        TermsInitService.reInit();
        for (int i = 0; i < categorys.length; i++) {
            TermRelationshipsPO relationships = new TermRelationshipsPO();
            relationships.setObjectId(post.getId());
            TermTaxonomyPO termTaxonomyPO = TermsInitService.termTaxonomyTerms.get(Long.valueOf(categorys[i]));
            relationships.setTermTaxonomyId(termTaxonomyPO.getTermId());
            relationships.setTermOrder(0);
            relationships.save();
        }
        TermsInitService.reInit();
    }

    private void saveTag(String tag, PostsPO post) {
        if (StringUtil.isNullOrEmpty(tag)) {
            return;
        }
        String[] tags = tag.split(",");
        for (int i = 0; i < tags.length; i++) {
            TermRelationshipsPO relationships = new TermRelationshipsPO();
            relationships.setObjectId(post.getId());
            TermsPO termsPO = TermsInitService.mapTerms.get(tags[i]);
            if (null == termsPO) {
                termsPO = new TermsPO();
                termsPO.setName(tags[i]);
                termsPO.setSlug(tags[i]);
                termsPO.setTermGroup(Long.parseLong("0"));
                termsPO.save();
                TermTaxonomyPO termTaxonomyPO = new TermTaxonomyPO();
                termTaxonomyPO.setTermId(termsPO.getTermId());
                termTaxonomyPO.setTermTaxonomyId(termsPO.getTermId());
                termTaxonomyPO.setTaxonomy("post_tag");
                termTaxonomyPO.setParent("0");
                termTaxonomyPO.setCount(Long.parseLong("1"));
                termTaxonomyPO.setDescription("post_tag");
                termTaxonomyPO.save();
                TermRelationshipsPO termRelationshipsPO = new TermRelationshipsPO();
                termRelationshipsPO.setTermTaxonomyId(termTaxonomyPO.getTermTaxonomyId());
                termRelationshipsPO.setObjectId(post.getId());
                termRelationshipsPO.setTermOrder(0);
                termRelationshipsPO.save();
            } else {
                TermTaxonomyPO termTaxonomyPO = TermsInitService.termTaxonomyTerms.get(termsPO.getTermId());
                if (null == termTaxonomyPO) {
                    termTaxonomyPO = new TermTaxonomyPO();
                    termTaxonomyPO.setTermId(termsPO.getTermId());
                    termTaxonomyPO.setTermTaxonomyId(termsPO.getTermId());
                    termTaxonomyPO.setTaxonomy("post_tag");
                    termTaxonomyPO.setParent("0");
                    termTaxonomyPO.setCount(Long.parseLong("1"));
                    termTaxonomyPO.save();
                }
                TermRelationshipsPO termRelationshipsPO = TermsInitService.termRelationshipsTerms.get(termTaxonomyPO.getTermTaxonomyId()
                        + "_" + post.getId());
                if (null == termRelationshipsPO) {
                    termRelationshipsPO = new TermRelationshipsPO();
                    termRelationshipsPO.setTermTaxonomyId(termTaxonomyPO.getTermTaxonomyId());
                    termRelationshipsPO.setObjectId(post.getId());
                    termRelationshipsPO.setTermOrder(0);
                    termRelationshipsPO.save();
                }
            }
        }
        TermsInitService.reInit();
    }

    @RequestMapping({ "/changeEditor/markdown", "/changeEditor/tinymce" })
    @ResponseBody
    public AjaxResult changeEditor() {
        String[] urls = request.getRequestURI().split("/");
        String name = urls[urls.length - 1];
        setCookie("_editor", name);
        return renderAjaxResultForSuccess();
    }
}
