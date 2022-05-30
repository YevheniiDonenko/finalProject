package servlet;

import dao.ExhibitionDao;
import dao.OrderDao;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteExhibitionServlet", value = "/DeleteExhibitionServlet")
public class DeleteExhibitionServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(DeleteExhibitionServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.debug("Command starts");

        String id = request.getParameter("id");
        OrderDao.getInstance().deleteOrderByExhibitionId(id);
        ExhibitionDao.getInstance().deleteExhibition(id);
        response.sendRedirect(getServletContext().getContextPath() + "/exhibitionservlet");

        log.trace("Redirected to exhibitions.jsp");
        log.debug("Command finished");
    }
}
