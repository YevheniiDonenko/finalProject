package servlet;

import dao.ExhibitionDao;
import models.ExhibitionData;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ExhibitionServlet", value = "/exhibitionservlet")
public class ExhibitionServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(ExhibitionServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Command starts");
        String order;

        try {
            order = request.getParameter("order");
        } catch (NullPointerException e) {
            order = "default";
        }
        if (order == null) {
            order = "default";
        }

        ExhibitionDao exhibitionDao = ExhibitionDao.getInstance();
        List<ExhibitionData> list;
        HttpSession session = request.getSession();

        switch (order) {
            case "name":
                list = exhibitionDao.getAllExhibitionsOrderByName();
                break;
            case "price":
                list = exhibitionDao.getAllExhibitionsOrderByPrice();
                break;
            case "pricefilter":
                String start = request.getParameter("start");
                String finish = request.getParameter("finish");
                list = exhibitionDao.getAllExhibitionsBetweenPrice(start, finish);
                break;
            default:
                list = exhibitionDao.getAllExhibitions();
        }

        session.setAttribute("exhibitions", list);
        response.sendRedirect(getServletContext().getContextPath() + "/exhibitions.jsp");

        log.trace("Redirected to exhibitions.jsp");
        log.debug("Command finished");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
