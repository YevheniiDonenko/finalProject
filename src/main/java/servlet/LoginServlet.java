package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.UserDao;
import models.UserData;
import org.apache.log4j.Logger;

@WebServlet(name = "LoginServlet", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet implements Serializable {

    private static final Logger log = Logger.getLogger(LoginServlet.class);

    private static final long serialVersionUID = 1L;

    private UserDao userDao;
    {
        userDao = UserDao.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(getServletContext().getContextPath() + "/login.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        log.debug("Command starts");

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();

        Cookie cookie = new Cookie("JSESSIONID", session.getId());
        cookie.setMaxAge(Integer.MAX_VALUE);
        response.addCookie(cookie);

        UserData user = userDao.getUserByLogin(login);

        if (login == null || login.isEmpty() || password == null || password.isEmpty()) {
            response.sendRedirect(getServletContext().getContextPath() + "/login");
        } else {
            PrintWriter pw = response.getWriter();
            try {
                if (user.getPassword().equals(password) && user.getLogin().equals(login)) {
                    session.setAttribute("user", user);
                    session.setAttribute("user_id", user.getId());
                    request.setAttribute("role", user.getRole());
                    session.setAttribute("role", user.getRole());
                    session.setAttribute("errorLogin", false);
                response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
                log.trace("Redirected to index.jsp");
                } else {
                    session.setAttribute("errorLogin", true);
                    response.sendRedirect(getServletContext().getContextPath() + "/login");
                    log.trace("Redirected to login.jsp");

                }
            } catch (NullPointerException e) {
                session.setAttribute("errorLogin", true);
                response.sendRedirect(getServletContext().getContextPath() + "/login");
                log.trace("Redirected to login.jsp");
            }
        }

        log.debug("Command finished");
    }

}
