package cn.sxfei.app.modules.sapress.web.admin;

import java.util.List;

import org.apache.tools.ant.types.resources.comparators.Content;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.sxfei.app.core.base.constant.Constants;
import cn.sxfei.app.core.utils.StringUtil;
import cn.sxfei.app.modules.sapress.comm.SapressController;
import cn.sxfei.app.modules.sapress.comm.util.CookieUtils;
import cn.sxfei.app.modules.sapress.interfaces.base.entity.Comments;
import cn.sxfei.app.modules.sapress.services.base.po.UserPO;
import cn.sxfei.app.modules.sapress.web.admin.dto.TplModule;

import com.google.common.collect.Lists;

/**
 * 后台管理登录及主界面
 * 
 * @author songxianfei@gmail.com
 * @time 2016-08-06
 */
@Controller
@RequestMapping("/admin")
// @RouterMapping(url = "/admin", viewPath = "/WEB-INF/admin")
// @RouterNotAllowConvert
public class _AdminController extends SapressController {
    @RequestMapping({ "/index", "/", "" })
    // @Before(ActionCacheClearInterceptor.class)
    public String index() {
        List<TplModule> moduleList = Lists.newArrayList();// MenuInitService.moduleList;
        setAttr("modules", moduleList);
        List<Content> contents = Lists.newArrayList();
        setAttr("contents", contents);
        List<Comments> commentList = Lists.newArrayList();
        setAttr("comments", commentList);
        return ("admin/index");
    }

    @RequestMapping("/login")
    @ResponseBody
    // @Clear(AdminInterceptor.class)
    public Object login() {
        String username = getPara("username");
        String password = getPara("password");
        if (null == username && null == password) {
            return new ModelAndView("admin/login");
        }
        if (StringUtil.isBlank(username)) {
            return renderAjaxResultForError("用户名不能为空");
        }
        if (StringUtil.isBlank(password)) {
            return renderAjaxResultForError("密码不能为空");
        }
        UserPO user = UserPO.dao.findFirstByWhere(" where user_login=?", username);
        if (null == user) {
            return renderAjaxResultForError("没有该用户");
        }
        UserPO userTemp = UserPO.dao.findFirst("select MD5(?) as user_pass ", password + Constants.USER_PASSWORD_SALT);
        if (userTemp.getUserPass().equals(user.getUserPass())) {
            CookieUtils.put(response, Constants.COOKIE_LOGINED_USER, user.getId().toString());
            return renderAjaxResultForSuccess("登陆成功");
        }
        return renderAjaxResultForError("密码错误");

    }

    @RequestMapping("/logout")
    // @Before(UCodeInterceptor.class)
    public String logout() {
        CookieUtils.remove(response);
        return ("admin/login");
    }
}
