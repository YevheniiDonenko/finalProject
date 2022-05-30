package servlet;

import dao.HallDao;
import dao.UserDao;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdateUserServlet", value = "/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(UpdateUserServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.debug("Command starts");

        String id = request.getParameter("id");
        String role = request.getParameter("role");

        UserDao.getInstance().updateUser(id, role);
        response.sendRedirect(getServletContext().getContextPath() + "/users.jsp");

        log.trace("Exhibition added. Redirected to users.jsp");
        log.debug("Command finished");
    }
}
