package servlet;

import dao.HallDao;
import dao.OrderDao;
import dao.UserDao;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteUserServlet", value = "/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(DeleteUserServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.debug("Command starts");

        String id = request.getParameter("id");
        OrderDao.getInstance().deleteOrderByUserId(id);
        UserDao.getInstance().deleteUser(id);
        response.sendRedirect(getServletContext().getContextPath() + "/users.jsp");

        log.trace("Redirected to users.jsp");
        log.debug("Command finished");
    }
}
