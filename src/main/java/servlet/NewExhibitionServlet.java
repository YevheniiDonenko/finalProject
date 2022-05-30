package servlet;

import dao.ExhibitionDao;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "NewExhibitionServlet", value = "/new-exhibition")
public class NewExhibitionServlet extends HttpServlet {

    private final static Logger log = Logger.getLogger(NewExhibitionServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.debug("Command starts");

        String name = request.getParameter("name");
        String theme = request.getParameter("theme");
        String description = request.getParameter("description");
        String start = changeDate(request.getParameter("start"));
        String finish = changeDate(request.getParameter("finish"));
        String price = request.getParameter("price");
        String hall_id = request.getParameter("hall_id");

        ExhibitionDao.getInstance().setExhibition(name, theme,  description, start, finish, price, hall_id);

        response.sendRedirect(getServletContext().getContextPath() + "/exhibitionservlet");

        log.trace("Redirected to exhibitionservlet");
        log.debug("Command finished");


    }
    private String changeDate(String date) {
        return date.substring(0, 10) + " " + date.substring(11, 16) + ":00";
    }


}
