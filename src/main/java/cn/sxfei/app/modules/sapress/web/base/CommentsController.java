package cn.sxfei.app.modules.sapress.web.base;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;

import cn.sxfei.app.core.base.BaseController;
import cn.sxfei.app.core.base.dto.ResultDTO;
import cn.sxfei.app.core.utils.CollectionUtils;
import cn.sxfei.app.core.utils.DateUtils;
import cn.sxfei.app.modules.sapress.interfaces.base.dto.CommentQueryDTO;
import cn.sxfei.app.modules.sapress.interfaces.base.entity.Comments;
import cn.sxfei.app.modules.sapress.interfaces.base.service.ICommentsService;
import cn.sxfei.app.modules.sapress.services.admin.po.CommentsPO;
import cn.sxfei.app.modules.sapress.services.admin.po.PostsPO;
import cn.sxfei.app.modules.sapress.web.admin.dto.Comment;

/**
 * @author sxfei 2016-07-16
 */
@Controller
@RequestMapping("/press/comments")
public class CommentsController extends BaseController {
    @Autowired
    private ICommentsService commentsService;

    /**
     * 某文章评论列表查询
     * 
     * @param pageNo
     * @return
     */
    @RequestMapping(value = "/last/{postId}/{pageNo}", method = RequestMethod.GET)
    @ResponseBody
    public ResultDTO getLastList(@PathVariable("postId")
    String postId, @PathVariable("pageNo")
    Integer pageNo) {
        // 查询评论
        CommentQueryDTO commentQueryDTO = new CommentQueryDTO();
        List<String> statusList = Lists.newArrayList();
        statusList.add("1");
        commentQueryDTO.setStatusList(statusList);
        commentQueryDTO.setPostId(postId);
        commentQueryDTO.setPageSize(5);
        commentQueryDTO.setSortkey("comment_date_gmt DESC");
        List<Long> idList = commentsService.selectForPage(commentQueryDTO);
        List<Comments> commentResult = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(idList)) {
            commentResult = commentsService.getByIds(idList);
            for (Comments comm : commentResult) {
                comm.setCommentDateStr(DateUtils.format(comm.getCommentDate(), "yyyy年MM月dd日"));
            }
        }

        // List<Long> idList = commentsService.getLastComments();
        // List<Comments> result = commentsService.getByIds(idList);
        return success(commentResult);
    }

    /**
     * 所有文章的最新评论
     * 
     * @param pageNo
     * @return
     */
    @RequestMapping(value = "/allLast", method = RequestMethod.GET)
    @ResponseBody
    public ResultDTO getAllList() {
        List<Long> idList = commentsService.getAllLastComments();
        List<Comments> result = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(idList)) {
            result = commentsService.getByIds(idList);
        }
        return success(result);
    }

    @RequestMapping(value = "/{commentId}", method = RequestMethod.GET)
    @ResponseBody
    public ResultDTO getComment(@PathVariable("commentId")
    Long commentId) {
        Comments comments = commentsService.getByPrimaryKey(commentId);
        return success(comments);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ResponseBody
    public ResultDTO add() {

        CommentsPO commentsPo = new CommentsPO();
        commentsPo.setCommentPostId(getPara("postId"));
        commentsPo.setCommentAuthor("游客");
        // commentsPo.setCommentAuthorEmail(commentAuthorEmail);
        commentsPo.setCommentAuthorIp(getIP());
        commentsPo.setCommentContent(getPara("text"));
        commentsPo.setCommentDate(new Date());
        commentsPo.setCommentDateGmt(new Date());
        commentsPo.setCommentType("trackback");// 评论类型(pingback/普通)
        // commentsPo.setCommentParent(commentParent);
        commentsPo.setCommentAgent(getAgent());
        commentsPo.setCommentApproved("0");
        commentsPo.save();
        // commentsService.insert(comments);
        PostsPO posts = PostsPO.dao.findById(commentsPo.getCommentPostId());
        int count = posts.getCommentCount().intValue() + 1;
        posts.setCommentCount(String.valueOf(count));
        posts.update();
        return success();
    }
}
