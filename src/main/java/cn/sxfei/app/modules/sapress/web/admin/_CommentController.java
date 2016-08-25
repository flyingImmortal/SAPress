package cn.sxfei.app.modules.sapress.web.admin;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sxfei.app.core.base.dto.ResultPageDTO;
import cn.sxfei.app.core.utils.DateUtils;
import cn.sxfei.app.core.utils.StringUtil;
import cn.sxfei.app.core.utils.StringUtils;
import cn.sxfei.app.modules.sapress.comm.AjaxResult;
import cn.sxfei.app.modules.sapress.comm.PressServiceHelper;
import cn.sxfei.app.modules.sapress.comm.SapressController;
import cn.sxfei.app.modules.sapress.interfaces.base.dto.CommentQueryDTO;
import cn.sxfei.app.modules.sapress.interfaces.base.dto.UserDTO;
import cn.sxfei.app.modules.sapress.interfaces.base.entity.Comments;
import cn.sxfei.app.modules.sapress.interfaces.base.enums.CommentStatusEnum;
import cn.sxfei.app.modules.sapress.interfaces.base.service.admin.IAdminCommentsService;
import cn.sxfei.app.modules.sapress.services.admin.MenuInitService;
import cn.sxfei.app.modules.sapress.services.admin.po.CommentsPO;
import cn.sxfei.app.modules.sapress.services.admin.po.PostsPO;
import cn.sxfei.app.modules.sapress.web.admin.dto.Comment;
import cn.sxfei.app.modules.sapress.web.admin.dto.TplModule;
import cn.sxfei.app.plugins.jfinal.activerecord.Page;

import com.google.common.collect.Lists;

/**
 * 后台评论管理
 * 
 * @author songxianfei@gmail.com
 * @time 2016-08-12
 */
@Controller
@RequestMapping("/admin/comment")
// @RouterMapping(url = "/admin/comment", viewPath = "/WEB-INF/admin/comment")
public class _CommentController extends SapressController {
    @Autowired
    IAdminCommentsService adminCommentsService;

    @RequestMapping({ "/index", "/", "" })
    public String index() {
        String moduleName = getPara("m", "post");// post
        String pageNo = getPara("page");
        TplModule module = MenuInitService.getModule(moduleName);
        setAttr("module", module);
        List<CommentsPO> commentsList = CommentsPO.dao.findByWhereAndColumns("GROUP BY comment_approved",
                "comment_approved, COUNT( * ) AS commentId  ");
        String trash = "0";
        String draft = "0";
        String publish = "0";
        for (CommentsPO po : commentsList) {
            if (CommentStatusEnum.TRASH.getName().equals(po.getCommentApproved())) {
                trash = po.getCommentId();
            }
            // 待审核
            if (CommentStatusEnum.PENDING.getName().equals(po.getCommentApproved())) {
                draft = po.getCommentId();
            }
            // 已批准
            if (CommentStatusEnum.APPROVED.getName().equals(po.getCommentApproved())) {
                publish = po.getCommentId();
            }
        }
        setAttr("delete_count", trash);
        setAttr("draft_count", draft);
        setAttr("normal_count", publish);
        Page<Comment> result = new Page<Comment>();
        CommentQueryDTO queryDto = new CommentQueryDTO();
        List<String> statusList = Lists.newArrayList();
        statusList.add("0");
        statusList.add("1");
        queryDto.setStatusList(statusList);
        if (StringUtil.isNotBlank(pageNo)) {
            queryDto.setPageNo(Integer.valueOf(pageNo));
        }
        queryDto.setSortkey("comment_date_gmt DESC,comment_ID desc ");
        ResultPageDTO<Comments> page = adminCommentsService.getCommentsForPage(queryDto);
        List<Comment> commentList = Lists.newArrayList();
        for (Comments comments : page.getList()) {
            Comment comment = new Comment();
            comment.setId(String.valueOf(comments.getCommentId()));
            comment.setStatus(comments.getCommentApproved());
            comment.setUsername(comments.getCommentAuthor());
            comment.setText(comments.getCommentContent());
            comment.setCreated(DateUtils.format(comments.getCommentDateGmt()));
            Long parentId = comments.getCommentParent();
            if (parentId > 0) {

            }
            commentList.add(comment);
        }
        result.setPageSize(queryDto.getPageSize());
        result.setPageNumber(queryDto.getPageNo());
        result.setList(commentList);
        setAttr("page", result);
        setAttr("count", page.getTotal());
        return "admin/comment/index";
    }

    @RequestMapping("/reply_layer")
    public String reply_layer() {
        BigInteger id = getParaToBigInteger("id");
        CommentsPO comments = CommentsPO.dao.findById(id);
        Comment comment = new Comment();
        comment.setId(String.valueOf(comments.getCommentId()));
        comment.setStatus(comments.getCommentApproved());
        comment.setUsername(comments.getCommentAuthor());
        comment.setText(comments.getCommentContent());
        comment.setCreated(DateUtils.format(comments.getCommentDateGmt()));

        comment.setParent_id(comments.getCommentId());
        comment.setAuthor(comments.getCommentAuthor());
        comment.setContent_module(getPara("m"));
        PostsPO postsPO = PostsPO.dao.findById(comments.getCommentPostId());
        if (null != postsPO) {
            comment.setContent_title(postsPO.getPostTitle());
            comment.setContentUrl(postsPO.getGuid());
            comment.setContent_id(postsPO.getId());
        }
        setAttr("comment", comment);
        return "admin/comment/reply_layer";
    }

    @RequestMapping("/reply")
    @ResponseBody
    public AjaxResult reply(Comment arg) {
        Comment comment = arg.getComment();
        CommentsPO commentsPO = new CommentsPO();
        commentsPO.setCommentParent(comment.getParent_id());
        commentsPO.setCommentDateGmt(new Date());
        commentsPO.setCommentPostId(comment.getContent_id());
        UserDTO user = PressServiceHelper.getSessionUser(request);

        commentsPO.setCommentAuthor(user.getUserNicename());
        commentsPO.setCommentAuthorUrl(user.getUserEmail());
        commentsPO.setCommentDate(new Date());
        commentsPO.setCommentAuthorIp(getIP());
        commentsPO.setCommentAgent(getAgent());
        // 过滤数据
        String text = StringUtils.fileterStr(comment.getText());
        commentsPO.setCommentContent(text);
        commentsPO.save();
        return renderAjaxResultForSuccess();
    }
}
