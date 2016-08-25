package cn.sxfei.app.modules.sapress.interfaces.base.service.admin;

import cn.sxfei.app.core.base.dto.ResultPageDTO;
import cn.sxfei.app.modules.sapress.interfaces.base.dto.CommentQueryDTO;
import cn.sxfei.app.modules.sapress.interfaces.base.entity.Comments;

public interface IAdminCommentsService {

    public ResultPageDTO<Comments> getCommentsForPage(CommentQueryDTO queryDto);

}
