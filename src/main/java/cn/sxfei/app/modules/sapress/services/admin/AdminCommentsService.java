package cn.sxfei.app.modules.sapress.services.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sxfei.app.core.base.BaseService;
import cn.sxfei.app.core.base.dto.QueryDTO;
import cn.sxfei.app.core.base.dto.ResultPageDTO;
import cn.sxfei.app.modules.sapress.interfaces.base.dto.CommentQueryDTO;
import cn.sxfei.app.modules.sapress.interfaces.base.entity.Comments;
import cn.sxfei.app.modules.sapress.interfaces.base.service.admin.IAdminCommentsService;
import cn.sxfei.app.modules.sapress.services.admin.po.CommentsPO;
import cn.sxfei.app.modules.sapress.services.base.dao.ICommentsDAO;

/**
 * 评论信息
 * 
 * @author sxfei 2016-08-12
 */
@Service
public class AdminCommentsService extends BaseService<Comments, Long> implements IAdminCommentsService {
    @Autowired
    private ICommentsDAO commentsDAO;

    public ResultPageDTO<Comments> getCommentsForPage(CommentQueryDTO queryDto) {
        ResultPageDTO<Comments> page = new ResultPageDTO<Comments>();
        List<Long> idList = commentsDAO.selectForPage(queryDto);
        CommentsPO commentsPO = CommentsPO.dao.findFirst("SELECT FOUND_ROWS() as commentId");
        page.setTotal(Integer.valueOf(commentsPO.getCommentId() + ""));
        QueryDTO dto = new QueryDTO();
        dto.setIdList(idList);
        List<Comments> postDtoList = commentsDAO.selectByIds(dto);
        page.setList(postDtoList);
        page.setIdList(idList);
        return page;
    }

}
