package filters;

import com.sun.deploy.net.HttpRequest;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;

@WebFilter(filterName = "AccessFilter")
public class AccessFilter implements Filter {

    private static final Logger log = Logger.getLogger(AccessFilter.class);

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
            if (session.getAttribute("role").equals("admin")) {
                log.debug("filter finished");
                chain.doFilter(request, response);
            }
            else {
                log.trace("User is not admin. Forward to accessDenied.jsp");
                request.getRequestDispatcher("accessDenied.jsp").forward(request, response);
            }
        } catch (NullPointerException e) {
            log.trace("User is not admin. Forward to accessDenied.jsp");
            request.getRequestDispatcher("accessDenied.jsp").forward(request, response);
        }

    }
}
