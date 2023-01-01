package cn.fishland.bookmanager.controller;

import cn.fishland.bookmanager.bean.pojo.User;
import cn.fishland.bookmanager.bean.vo.ReturnData;
import cn.fishland.bookmanager.service.UserService;
import cn.fishland.bookmanager.service.impl.UserServiceImpl;
import cn.fishland.bookmanager.tool.WebTool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户相关API
 *
 * @author xiaoyu
 * @version 1.0
 */
public class UserServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] mapping = WebTool.handlerMapping(req.getRequestURI());
        switch (mapping[3]) {
            case "login":
                login(req, resp);
                break;
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String code = req.getParameter("code");

        String sessionCode = (String) req.getSession().getAttribute("code");

        ReturnData data = new ReturnData("OK");

        // 校验验证码
        if (WebTool.isBlank(sessionCode) || WebTool.isBlank(code) || !sessionCode.equalsIgnoreCase(code)) {
            data.setMessage("验证码错误！");
            req.setAttribute("result", data);
            req.getRequestDispatcher("/WEB-INF/views/page/login.jsp").forward(req, resp);
            return;
        }

        // 校验账户密码
        User user;
        if ((user = userService.login(new User(name, password))) != null) {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/");
            return;
        }

        data.setMessage("账户密码错误！");
        req.setAttribute("result", data);
        req.getRequestDispatcher("/WEB-INF/views/page/login.jsp").forward(req, resp);
    }
}
