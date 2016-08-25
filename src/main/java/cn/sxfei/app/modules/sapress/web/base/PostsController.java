package cn.sxfei.app.modules.sapress.web.base;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sxfei.app.core.base.BaseController;
import cn.sxfei.app.core.base.dto.QueryDTO;
import cn.sxfei.app.core.base.dto.ResultDTO;
import cn.sxfei.app.core.utils.CollectionUtils;
import cn.sxfei.app.core.utils.DateUtils;
import cn.sxfei.app.core.utils.StringUtil;
import cn.sxfei.app.modules.sapress.interfaces.base.dto.CommentQueryDTO;
import cn.sxfei.app.modules.sapress.interfaces.base.dto.PostDTO;
import cn.sxfei.app.modules.sapress.interfaces.base.entity.Comments;
import cn.sxfei.app.modules.sapress.interfaces.base.service.ICommentsService;
import cn.sxfei.app.modules.sapress.interfaces.base.service.IPostsService;

import com.google.common.collect.Lists;

/**
 * @author sxfei 2016-07-10
 */
@Controller
@RequestMapping("/press/posts")
public class PostsController extends BaseController {
    @Autowired
    private IPostsService postsService;
    private Logger log = LoggerFactory.getLogger(PostsController.class);
    @Autowired
    private ICommentsService commentsService;

    /**
     * 主界面日志列表查询
     * 
     * @param pageNo
     * @return
     */
    @RequestMapping(value = "/last/{pageNo}", method = RequestMethod.GET)
    @ResponseBody
    public ResultDTO getLastList(@PathVariable("pageNo")
    Integer pageNo) {
        QueryDTO dto = new QueryDTO();
        dto.setPageNo(pageNo);
        dto.setPageSize(5);
        long timer = System.currentTimeMillis();
        List<Long> idList = postsService.getLastPosts(dto);
        log.error("主界面日志查询ID耗时" + (System.currentTimeMillis() - timer));
        timer = System.currentTimeMillis();
        List<PostDTO> result = postsService.getResultByIds(idList);
        for (PostDTO postDto : result) {
            String content = postDto.getContentArticle();
            if (content == null) {
                continue;
            }
            // String article = StringUtil.delHTMLTag(content);
            // if (article.length() > 150) {
            // content = getArticle(content, article, 1);
            // postDto.setContentArticle(content);
            // }
        }
        log.error("主界面日志查询详情耗时" + (System.currentTimeMillis() - timer));
        return success(result);
    }

    private String getArticle(String content, String article, int i) {
        String temp = article.substring(149, 150);
        int index = content.indexOf(temp);
        if (index < 150) {
            getArticle(content, article, i++);
        } else {
            return article.substring(0, index);
        }
        return content;
    }

    /**
     * 浏览最多的文章列表
     * 
     * @param pageNo
     * @return
     */
    @RequestMapping(value = "/hot", method = RequestMethod.GET)
    @ResponseBody
    public ResultDTO getHotList() {
        List<Long> idList = postsService.getHotPosts();
        List<PostDTO> result = postsService.getResultByIds(idList);
        return success(result);
    }

    /**
     * 查询随机文章列表
     * 
     * @param pageNo
     * @return
     */
    @RequestMapping(value = "/random", method = RequestMethod.GET)
    @ResponseBody
    public ResultDTO getRandomList() {
        List<Long> idList = Lists.newArrayList();
        for (int i = 0; i < 5; i++) {
            Long temp = postsService.getRandomPosts();
            idList.add(temp);
        }
        List<PostDTO> result = postsService.getResultByIds(idList);
        return success(result);
    }

    /**
     * 文章详情
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResultDTO getPosts(@PathVariable("id")
    Long id) {
        List<Long> idList = Lists.newArrayList();
        idList.add(id);
        List<PostDTO> result = postsService.getResultByIds(idList);
        PostDTO dto = result.get(0);
        return success(dto);
    }

    /**
     * 上一篇
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/prev/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResultDTO getPrevPosts(@PathVariable("id")
    Long id) {
        List<PostDTO> result = postsService.getPrevById(id);
        PostDTO dto = result.get(0);
        return success(dto);
    }

    /**
     * 下一篇
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/next/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResultDTO getNextPosts(@PathVariable("id")
    Long id) {
        List<PostDTO> result = postsService.getNextById(id);
        PostDTO dto = result.get(0);
        return success(dto);
    }

    /**
     * 获取相关文章
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/relate/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResultDTO getRelatePosts(@PathVariable("id")
    Long id) {
        List<PostDTO> result = postsService.getRelatePosts(id);
        return success(result);
    }
}
