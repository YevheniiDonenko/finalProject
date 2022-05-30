package servlet;

import dao.ExhibitionDao;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdateExhibitionServlet", value = "/UpdateExhibitionServlet")
public class UpdateExhibitionServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(UpdateExhibitionServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.debug("Command starts");

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String theme = request.getParameter("theme");
        String description = request.getParameter("description");
        String start = request.getParameter("start");
        String finish = request.getParameter("finish");
        String hallId = request.getParameter("hall_id");
        String price = request.getParameter("price");

        ExhibitionDao.getInstance().updateExhibition(id, name, theme, description, start, finish, price, hallId);
        response.sendRedirect(getServletContext().getContextPath() + "/exhibitionservlet");

        log.trace("Exhibition added. Redirected to exhibitionservlet");
        log.debug("Command finished");

    }
}
