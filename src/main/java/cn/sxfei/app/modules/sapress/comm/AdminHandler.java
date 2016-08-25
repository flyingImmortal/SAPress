package cn.sxfei.app.modules.sapress.comm;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.sxfei.app.core.base.constant.Constants;
import cn.sxfei.app.modules.sapress.comm.util.EncryptUtils;
import cn.sxfei.app.modules.sapress.interfaces.base.dto.UserDTO;
import cn.sxfei.app.modules.sapress.services.admin.MenuInitService;
import cn.sxfei.app.modules.sapress.web.admin.dto.MenuGroup;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 后端管理员界面拦截器
 * 
 * @author
 */
public class AdminHandler extends HandlerInterceptorAdapter {

    final Logger logger = LoggerFactory.getLogger(getClass());

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = true;
        String target = request.getRequestURI();
        logger.info("来拦截了一次"+target);
        String cpath = request.getContextPath();
        if (!target.startsWith(cpath + "/admin")) {
            return flag;
        }
        request.setAttribute("c", request.getParameter("c"));
        request.setAttribute("p",  request.getParameter("p"));
        request.setAttribute("m",  request.getParameter("m"));
        request.setAttribute("t",  request.getParameter("t"));
        request.setAttribute("s",  request.getParameter("s"));
        request.setAttribute("k",  request.getParameter("k"));
        request.setAttribute("page", request.getParameter("page"));

        request.setAttribute("_request", request);
        request.setAttribute("CPATH", cpath);
        request.setAttribute("SPATH", cpath + "/static");
        if (target.startsWith(cpath + "/admin/login")) {
            return flag;
        }
        if (target.startsWith(cpath + "/admin/logout")) {
            return flag;
        }
        UserDTO user = PressServiceHelper.getSessionUser(request);
        if (user == null) {
            // response.sendRedirect(cpath + "/admin/login.html");
            request.getRequestDispatcher("/admin/login.html").forward(request, response);
            return false;
        }
        user.setUsername(user.getUserNicename());
        request.setAttribute(Constants.ATTR_USER, user);
        request.setAttribute("ucode", EncryptUtils.generateUcode(user.getId(), EncryptUtils.salt()));
        request.setAttribute("_menu_html", generateHtml());
        
        return flag;
    }

    /**
     * freemwork公共拦截器处理
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {

    }

    public String generateHtml() {
        LinkedList<MenuGroup> menuGroups = MenuInitService.menuGroups;
        StringBuilder htmlBuilder = new StringBuilder();
        for (MenuGroup group : menuGroups) {
            htmlBuilder.append(group.generateHtml());
        }
        return htmlBuilder.toString();
    }
}
