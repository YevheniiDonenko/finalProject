package filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "NotLoginFilter")
public class NotLoginFilter implements Filter {

    private static final Logger log = Logger.getLogger(NotLoginFilter.class);

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
            if (role != null) {
                log.debug("Filter finished");
                chain.doFilter(request, response);
                return;
            }
            log.trace("Not login. Redirect to login.jsp");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } catch (NullPointerException e) {
            log.trace("Not login. Redirect to login.jsp");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

    }
}
