package servlet;

import dao.ExhibitionDao;
import dao.HallDao;
import dao.OrderDao;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteHallServlet", value = "/DeleteHallServlet")
public class DeleteHallServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(DeleteHallServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.debug("Command starts");

        String id = request.getParameter("id");
        HallDao.getInstance().deleteHall(id);
        response.sendRedirect(getServletContext().getContextPath() + "/halls.jsp");
        System.out.println("deleted");
        log.trace("Redirected to halls.jsp");
        log.debug("Command finished");
    }
}
