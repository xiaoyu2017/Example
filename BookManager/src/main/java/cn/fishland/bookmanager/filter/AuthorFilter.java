package cn.fishland.bookmanager.filter;

import cn.fishland.bookmanager.bean.pojo.User;
import cn.fishland.bookmanager.tool.WebTool;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 权限监视，所有servlet访问都需要权限验证
 *
 * @author xiaoyu
 * @version 1.0
 */
public class AuthorFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("AuthorFilter init...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //将request和response强转成http协议的
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        //解决post请求提交参数的乱码问题
        req.setCharacterEncoding("UTF-8");
        //统一设置响应编码
        resp.setContentType("text/html;charset=UTF-8");

        // 是否为放行url
        if (WebTool.letGo(req.getRequestURI())) {
            filterChain.doFilter(req, resp);
            return;
        }

        // 权限校验
        if (noPermission(req)) {
            resp.sendRedirect(WebTool.LOGIN_VIEW);
            return;
        }

        filterChain.doFilter(req, resp);
    }

    private boolean noPermission(HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("user");
        return user == null || user.getId() == null || user.getId() <= 0 || !user.getStatus();
    }

    @Override
    public void destroy() {
        System.out.println("AuthorFilter destroy...");
    }
}
