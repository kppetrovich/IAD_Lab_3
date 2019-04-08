package filter;

import bean.UserBean;

import javax.faces.bean.ManagedProperty;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "authFilter", urlPatterns = {"/main.xhtml"})
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        ServletContext context = req.getServletContext();
        UserBean userBean = (UserBean) context.getAttribute("userBean");
        if (!userBean.getUsersMap().containsKey(session.getId())) {
            resp.sendRedirect(req.getContextPath() + "/index.xhtml");
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
