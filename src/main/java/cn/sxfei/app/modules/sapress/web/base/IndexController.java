package cn.sxfei.app.modules.sapress.web.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sxfei.app.core.base.BaseController;
import cn.sxfei.app.core.base.dto.QueryDTO;
import cn.sxfei.app.core.base.dto.ResultDTO;
import cn.sxfei.app.modules.sapress.interfaces.base.dto.PostDTO;
import cn.sxfei.app.modules.sapress.interfaces.base.service.IPostsService;

import com.google.common.collect.Lists;
import com.mysql.jdbc.StringUtils;

/**
 * @author sxfei 2016-07-10
 */
@Controller
@RequestMapping("")
public class IndexController extends BaseController {
    @Autowired
    private IPostsService postsService;

    /**
     * 主界面日志列表查询
     * 
     * @param pageNo
     * @return
     */
    @RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
    public void getLastList() {
        String p = getPara("p");
        if (!StringUtils.isNullOrEmpty(p)) {
            render("/press/#/contentArchives/" + p);
            return;
        }
        String author = getPara("author");
        if (!StringUtils.isNullOrEmpty(author)) {
            render("/press/#/contentArchives/" + author);
            return;
        }
        render("/press");
    }

}
