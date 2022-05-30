package filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter")
public class LoginFilter implements Filter {

    private static final Logger log = Logger.getLogger(LoginFilter.class);

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

       log.debug("Filter starts");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);
        try {
            String role = (String) session.getAttribute("role");
            if (role == null) {
                log.trace("Filter finished");
                chain.doFilter(request, response);
                return;
            }
            log.trace("Filter finished. User already registered. Forward to profile.jsp");
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        } catch (NullPointerException e) {
            log.trace("Filter finished");
            chain.doFilter(request, response);
        }
    }
}
