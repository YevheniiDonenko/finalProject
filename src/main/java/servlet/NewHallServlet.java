package servlet;

import dao.ExhibitionDao;
import dao.HallDao;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "NewHallServlet", value = "/NewHallServlet")
public class NewHallServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(NewHallServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.debug("Command starts");

        String name = request.getParameter("name");

        HallDao.getInstance().setHall(name);

        response.sendRedirect(getServletContext().getContextPath() + "/halls.jsp");

        log.trace("Redirected to halls.jsp");
        log.debug("Command finished");

    }
}
