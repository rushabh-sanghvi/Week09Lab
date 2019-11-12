package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Role;
import models.User;
import services.UserService;

/**
 *
 * @author 794473
 */
public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest hsr = (HttpServletRequest) request;
        HttpSession session = hsr.getSession();

        String email = (String) session.getAttribute("email");

        UserService us = new UserService();

        try {
            User u = us.get(email);
            Role r = u.getRole();

            String roleName = r.getRoleName();

            if (!roleName.contains("admin")) {
                HttpServletResponse hsre = (HttpServletResponse) response;
                hsre.sendRedirect("home");
                return;
            }
        } catch (Exception ex) {

        }

   chain.doFilter(request, response);
    }

    public void destroy() {

    }

    public void init(FilterConfig filterConfig) {

    }
}
