/**
 * Copyright (c) 2015-2016, Michael Yang 杨福海 (fuhai999@gmail.com).
 *
 * Licensed under the GNU Lesser General Public License (LGPL) ,Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.sxfei.app.modules.sapress.web.admin;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sxfei.app.core.utils.StringUtil;
import cn.sxfei.app.core.utils.StringUtils;
import cn.sxfei.app.modules.sapress.comm.AjaxResult;
import cn.sxfei.app.modules.sapress.comm.SapressController;
import cn.sxfei.app.modules.sapress.interfaces.base.dto.PostQueryDTO;
import cn.sxfei.app.modules.sapress.interfaces.base.service.admin.IAdminPostsService;
import cn.sxfei.app.modules.sapress.services.admin.MenuInitService;
import cn.sxfei.app.modules.sapress.services.admin.TermsInitService;
import cn.sxfei.app.modules.sapress.services.admin.po.TermRelationshipsPO;
import cn.sxfei.app.modules.sapress.services.admin.po.TermTaxonomyPO;
import cn.sxfei.app.modules.sapress.services.admin.po.TermsPO;
import cn.sxfei.app.modules.sapress.web.admin.dto.Taxonomy;
import cn.sxfei.app.modules.sapress.web.admin.dto.TplModule;
import cn.sxfei.app.modules.sapress.web.admin.dto.TplTaxonomyType;
import cn.sxfei.app.plugins.jfinal.activerecord.Page;
import cn.sxfei.app.plugins.jfinal.activerecord.tx.Tx;
import cn.sxfei.app.plugins.jfinal.aop.Before;

import com.google.common.collect.Lists;

/**
 * 后台分类管理
 * 
 * @author songxianfei@gmail.com
 * @time 2016-08-12
 */
@Controller
@RequestMapping("/admin/taxonomy")
// @RouterMapping(url = "/admin/taxonomy", viewPath = "/WEB-INF/admin/taxonomy")
public class _TermsController extends SapressController {
    @Autowired
    private IAdminPostsService adminPostsService;

    @RequestMapping({ "/index", "/", "" })
    public String index() {
        String moduleName = getPara("m", "post");// post
        String type = getPara("t");// category
        String pageNo = getPara("page");
        TplModule module = MenuInitService.getModule(moduleName);
        TplTaxonomyType tplTaxonomyType = module.getTaxonomyTypeByType(type);
        BigInteger id = getParaToBigInteger("id");
        PostQueryDTO dto = new PostQueryDTO();
        List<String> typeList = Lists.newArrayList();
        typeList.add(type);
        dto.setTypeList(typeList);
        dto.setPageSize(Integer.MAX_VALUE);
        if (StringUtils.isNotBlank(pageNo)) {
            dto.setPageNo(Integer.parseInt(pageNo));
        }
        List<Taxonomy> taxonomys = adminPostsService.selectTaxonomyList(dto);
        if (id != null) {
            TermsPO termsPO = TermsPO.dao.findById(id);
            TermTaxonomyPO termTaxonomyPO = TermTaxonomyPO.dao.findFirstByWhere("where term_id=? and taxonomy=? ", termsPO.getTermId(),
                    type);
            Taxonomy taxonomy = new Taxonomy();
            taxonomy.setId(String.valueOf(termsPO.getTermId()));
            taxonomy.setTitle(termsPO.getName());
            taxonomy.setParentId(termTaxonomyPO.getParent());
            taxonomy.setSlug(termsPO.getSlug());
            taxonomy.setText(termTaxonomyPO.getDescription());
            setAttr("taxonomy", taxonomy);
            // Content content =
            // ContentQuery.me().findFirstByModuleAndObjectId(Consts.MODULE_MENU,
            // taxonomy.getId());
            // if (content != null) {
            // setAttr("addToMenuSelete", "checked=\"checked\"");
            // }
        }

        if (id != null && taxonomys != null) {
            // ModelSorter.removeTreeBranch(taxonomys, id);
        }

        if (TplTaxonomyType.TYPE_SELECT.equals(tplTaxonomyType.getFormType())) {
            dto.setPageSize(Integer.MAX_VALUE);
        } else if (TplTaxonomyType.TYPE_INPUT.equals(tplTaxonomyType.getFormType())) {

        }
        List<Taxonomy> taxonomyList = adminPostsService.selectTaxonomyList(dto);
        Page<Taxonomy> result = new Page<Taxonomy>();
        result.setTotalRow(taxonomys.size());
        result.setPageSize(dto.getPageSize());
        result.setPageNumber(dto.getPageNo());
        result.setList(taxonomyList);
        setAttr("page", result);
        setAttr("module", module);
        setAttr("type", tplTaxonomyType);
        setAttr("taxonomys", taxonomys);
        return "admin/taxonomy/index";
    }

    @RequestMapping("/save")
    @ResponseBody
    @Before(Tx.class)
    public AjaxResult save(Taxonomy arg) {
        Taxonomy m = arg.getTaxonomy();
        if (StringUtils.isBlank(m.getTitle())) {
            return renderAjaxResultForError("名称不能为空！");
        }

        if (StringUtils.isBlank(m.getSlug())) {
            return renderAjaxResultForError("别名不能为空！");
        }

        List<TermsPO> termsPOs = TermsPO.dao.findByWhere("where slug=?", m.getSlug());
        if (termsPOs.size() > 1) {
            return renderAjaxResultForError("别名已经存在！");
        } else if (termsPOs.size() == 1) {
            TermsPO dbTaxonomy = termsPOs.get(0);
            if (m.getId() != null && dbTaxonomy != null && !m.getId().equals(String.valueOf(dbTaxonomy.getTermId()))) {
                return renderAjaxResultForError("别名已经存在！");
            }
        }
        TermsPO termsPO = new TermsPO();
        termsPO.setName(m.getTitle());
        termsPO.setSlug(m.getSlug());

        if (StringUtil.isBlank(m.getId())) {
            termsPO.save();
            TermTaxonomyPO taxonomyPO = new TermTaxonomyPO();
            taxonomyPO.setCount(0l);
            taxonomyPO.setDescription(m.getText());
            taxonomyPO.setParent(m.getParent_id());
            taxonomyPO.setTermId(termsPO.getTermId());
            taxonomyPO.setTaxonomy(m.getType());
            taxonomyPO.save();
        } else {
            termsPO.setTermId(m.getId());
            termsPO.update();
            TermTaxonomyPO taxonomyPO = TermTaxonomyPO.dao.findFirstByWhere("where term_id=? and taxonomy=? ", termsPO.getTermId(),
                    m.getType());
            taxonomyPO.setDescription(m.getText());
            taxonomyPO.setParent(m.getParent_id());
            taxonomyPO.update();
        }
        TermsInitService.reInit();
        return renderAjaxResultForSuccess("ok");
    }

    @RequestMapping("/delete")
    @ResponseBody
    @Before(Tx.class)
    public AjaxResult delete() {
        BigInteger id = getParaToBigInteger("id");
        TermsPO.dao.deleteById(id);
        TermTaxonomyPO termTaxonomyPO = TermTaxonomyPO.dao.findFirstByWhere("where term_id=?", id);
        TermTaxonomyPO.dao.deleteByWhere("where term_id=?", id);
        TermRelationshipsPO.dao.deleteByWhere("where term_taxonomy_id=?", termTaxonomyPO.getTermTaxonomyId());
        return renderAjaxResultForSuccess();
    }

    @RequestMapping("/set_layer")
    public String set_layer() {
        String moduleName = getPara("m", "post");// post
        String type = getPara("t");// category
        TplModule module = MenuInitService.getModule(moduleName);
        TplTaxonomyType tplTaxonomyType = module.getTaxonomyTypeByType(type);
        tplTaxonomyType.setMetadatas(module.getMetadatas());
        BigInteger id = getParaToBigInteger("id");
        TermsPO termsPO = TermsPO.dao.findById(id);
        TermTaxonomyPO termTaxonomyPO = TermTaxonomyPO.dao.findFirstByWhere("where term_id=? and taxonomy=? ", termsPO.getTermId(), type);
        Taxonomy taxonomy = new Taxonomy();
        taxonomy.setId(String.valueOf(termsPO.getTermId()));
        taxonomy.setTitle(termsPO.getName());
        taxonomy.setParentId(termTaxonomyPO.getParent());
        taxonomy.setSlug(termsPO.getSlug());
        taxonomy.setText(termTaxonomyPO.getDescription());
        setAttr("taxonomy", taxonomy);
        setAttr("type", tplTaxonomyType);
        return "admin/taxonomy/set_layer";
    }
}