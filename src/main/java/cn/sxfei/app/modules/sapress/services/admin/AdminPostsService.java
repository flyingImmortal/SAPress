package cn.sxfei.app.modules.sapress.services.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sxfei.app.core.base.dto.QueryDTO;
import cn.sxfei.app.core.base.dto.ResultPageDTO;
import cn.sxfei.app.core.utils.StringUtil;
import cn.sxfei.app.modules.sapress.interfaces.base.dto.PostQueryDTO;
import cn.sxfei.app.modules.sapress.interfaces.base.entity.Posts;
import cn.sxfei.app.modules.sapress.interfaces.base.service.IPostsService;
import cn.sxfei.app.modules.sapress.interfaces.base.service.admin.IAdminPostsService;
import cn.sxfei.app.modules.sapress.services.admin.po.PostsPO;
import cn.sxfei.app.modules.sapress.services.base.dao.IPostsDAO;
import cn.sxfei.app.modules.sapress.services.base.dao.ITermsDAO;
import cn.sxfei.app.modules.sapress.web.admin.dto.Taxonomy;

import com.google.common.collect.Lists;

@Service
public class AdminPostsService implements IAdminPostsService {
    @Autowired
    private IPostsService postsService;
    @Autowired
    private IPostsDAO postsDAO;
    @Autowired
    private ITermsDAO termsDAO;

    // 查询分页记录
    // private String selectByPage =
    // "SELECT SQL_CALC_FOUND_ROWS  jbpress_posts.ID FROM jbpress_posts  WHERE 1=1  AND jbpress_posts.post_type = 'post' AND (jbpress_posts.post_status = 'publish' OR jbpress_posts.post_status = 'future' OR jbpress_posts.post_status = 'draft' OR jbpress_posts.post_status = 'pending' OR jbpress_posts.post_status = 'private')  ORDER BY jbpress_posts.post_date DESC LIMIT 0, 20";
    // 查分页总记录数
    // private String selectCount = "SELECT FOUND_ROWS()";
    // 查询文章详情
    // private String selectInfo =
    // "SELECT jbpress_posts.* FROM jbpress_posts WHERE ID IN (8,4,1)";

    public ResultPageDTO<Posts> getPostsByPage(PostQueryDTO queryDto) {
        String postDate = queryDto.getPostDate();
        if (StringUtil.isNotBlank(postDate)) {
            queryDto.setYear(postDate.substring(0, 4));
            queryDto.setMonth(postDate.substring(4, 6));
        }
        ResultPageDTO<Posts> page = new ResultPageDTO<Posts>();
        List<Long> idList = postsDAO.selectByPage(queryDto);
        //PostsPO postsPO = PostsPO.dao.findFirst("SELECT FOUND_ROWS() as id");
        //page.setTotal(Integer.valueOf(postsPO.getId() + ""));
        List<Posts> postDtoList = postsService.getByIds(idList);
        page.setList(postDtoList);
        page.setIdList(idList);
        return page;
    }

    // 查询文章分类信息
    // private String selectPostTerm =
    // "SELECT t.*, tt.*, tr.object_id FROM jbpress_terms AS t " +
    // "INNER JOIN jbpress_term_taxonomy AS tt "
    // +
    // "ON tt.term_id = t.term_id INNER JOIN jbpress_term_relationships AS tr "
    // + "ON tr.term_taxonomy_id = tt.term_taxonomy_id  "
    // + "WHERE tt.taxonomy IN ('category', 'post_tag', 'post_format') " +
    // "AND tr.object_id IN (1, 4, 8) " + "ORDER BY t.name ASC";
    public List<Map> getPostTermTaxonomy(QueryDTO queryDto) {
        return termsDAO.selectTermByPostId(queryDto);
    }

    // 查询文章附加信息
    String selectPostmeta = "SELECT post_id, meta_key, meta_value FROM " + "jbpress_postmeta " + "WHERE post_id IN (1,4,8) "
            + "ORDER BY meta_id ASC";

    // 查询文章的评论信息
    String selectPostComment = "SELECT comment_post_ID, COUNT(comment_ID) as num_comments FROM jbpress_comments "
            + "WHERE comment_post_ID IN ( '8', '4', '1' ) AND comment_approved = '0' " + "GROUP BY comment_post_ID";

    // 查询我的总文章数
    String myPostsCount = "SELECT COUNT( 1 ) " + "FROM jbpress_posts " + "WHERE post_type = 'post' "
            + "AND post_status NOT IN ( 'trash','auto-draft','inherit' ) " + "AND post_author = 1 ";
    // 按状态统计所有文章
    String startPostByStatus = "SELECT post_status, COUNT( * ) AS num_posts FROM jbpress_posts WHERE post_type = 'post' GROUP BY post_status";

    public List<PostsPO> statPostByStatus(String postType) {
        List<PostsPO> postsPOList = PostsPO.dao.findByWhereAndColumns(" where post_type = ? GROUP BY post_status",
                "post_status, COUNT( * ) AS num_posts", postType);
        return postsPOList;
    }

    // 统计已审核或未审核的评论
    String commonts = "SELECT comment_approved, COUNT( * ) AS total " + "FROM jbpress_comments GROUP BY comment_approved";

    // 统计所有文章所在年月
    String selectPostYear = "SELECT DISTINCT YEAR( post_date ) AS year, MONTH( post_date ) AS month " + "FROM jbpress_posts "
            + "WHERE post_type = 'post' " + "AND post_status != 'auto-draft' AND post_status != 'trash' " + "ORDER BY post_date DESC";

    // 查询所有分类信息
    String selectTerm = "SELECT  t.*, tt.* FROM jbpress_terms AS t  INNER JOIN jbpress_term_taxonomy AS tt "
            + "ON t.term_id = tt.term_id WHERE tt.taxonomy IN ('category') " + "ORDER BY t.name ASC ";

    public List<Map> selectTermAndTaxonomy(PostQueryDTO queryDto) {
        return termsDAO.selectTermAndTaxonomy(queryDto);
    }

    public List<Taxonomy> selectTaxonomyList(PostQueryDTO queryDto) {
        List<Map> mapList = termsDAO.selectTermAndTaxonomy(queryDto);
        List<Taxonomy> taxonomys = Lists.newArrayList();
        for (Map map : mapList) {
            Taxonomy taxonomy = new Taxonomy();
            taxonomy.setId(String.valueOf(map.get("term_id")));
            taxonomy.setTitle(String.valueOf(map.get("name")));
            taxonomy.setSlug(String.valueOf(map.get("slug")));
            taxonomy.setText(String.valueOf(map.get("description")));
            taxonomy.setContent_count(String.valueOf(map.get("count")));
            taxonomy.setParentId(String.valueOf(map.get("parent")));
            taxonomys.add(taxonomy);
        }
        return taxonomys;
    }

    // 查询所有分类附加信息
    String selectTermmeta = "SELECT term_id, meta_key, meta_value FROM jbpress_termmeta " + "WHERE term_id IN (2,3,1) ORDER BY meta_id ASC";

    // 查询所有用户信息
    String selectUserAll = "SELECT jbpress_users.ID,jbpress_users.user_login,jbpress_users.display_name FROM jbpress_users INNER JOIN jbpress_usermeta ON ( jbpress_users.ID = jbpress_usermeta.user_id ) WHERE 1=1 AND ( "
            + "( jbpress_usermeta.meta_key = 'jbpress_user_level' AND CAST(jbpress_usermeta.meta_value AS CHAR) != '0' ) "
            + " ) ORDER BY display_name ASC ";
    // 按分类统计所有文章数
    String startPostCount = "SELECT  t.term_id, tt.parent, tt.count, tt.taxonomy FROM jbpress_terms AS t  INNER JOIN jbpress_term_taxonomy AS tt ON t.term_id = tt.term_id WHERE tt.taxonomy IN ('category') AND tt.count > 0 ORDER BY tt.count DESC LIMIT 10";
    // 查询所有分类信息
    String startTermAll = "SELECT  t.*, tt.* FROM jbpress_terms AS t  INNER JOIN jbpress_term_taxonomy AS tt ON t.term_id = tt.term_id WHERE tt.taxonomy IN ('category') ORDER BY t.name ASC";

}
