package cn.sxfei.app.modules.sapress.interfaces.base.service.admin;

import java.util.List;
import java.util.Map;

import cn.sxfei.app.core.base.dto.QueryDTO;
import cn.sxfei.app.core.base.dto.ResultPageDTO;
import cn.sxfei.app.modules.sapress.interfaces.base.dto.PostQueryDTO;
import cn.sxfei.app.modules.sapress.web.admin.dto.Taxonomy;

public interface IAdminPostsService {

    public ResultPageDTO getPostsByPage(PostQueryDTO queryDTO);

    public List<Map> getPostTermTaxonomy(QueryDTO queryDto);

    public List<Map> selectTermAndTaxonomy(PostQueryDTO queryDto);

    /**
     * 按条件查询分类信息
     * 
     * @param queryDto
     * @return
     */
    public List<Taxonomy> selectTaxonomyList(PostQueryDTO queryDto);
}
