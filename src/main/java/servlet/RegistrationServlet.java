package servlet;

import dao.UserDao;
import models.UserData;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RegistrationServlet", value = "/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(RegistrationServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       log.debug("Command starts");

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String name = request.getParameter("password");
        String email = request.getParameter("email");

        HttpSession session = request.getSession();
        session.setAttribute("used_login", false);
        session.setAttribute("used_email", false);

        UserData userData = UserDao.getInstance().getUserByLogin(login);
        if (userData != null) {
            session.setAttribute("used_login", true);
            response.sendRedirect(getServletContext().getContextPath() + "/regPage.jsp");
            log.trace("Fail try. Redirected to regPage.jsp");
            return;
        }

        userData = UserDao.getInstance().getUserByEmail(email);

        if (userData != null) {
            session.setAttribute("used_email", true);
            response.sendRedirect(getServletContext().getContextPath() + "/regPage.jsp");
            log.trace("Fail try. Redirected to regPage.jsp");
            return;
        }

        UserDao.getInstance().setUser(login, password, name, email);
        response.sendRedirect(getServletContext().getContextPath() + "/login.jsp");
        log.trace("Successful registration. Redirected to login.jsp");
        log.debug("Command finished");
    }
}
