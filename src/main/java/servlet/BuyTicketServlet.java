package servlet;

import dao.OrderDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import org.apache.log4j.Logger;

@WebServlet(name = "BuyTicketServlet", value = "/ticketBuy")
public class BuyTicketServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(BuyTicketServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Command starts");
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("user_id");
        String exhibitionId = request.getParameter("id");
        String quantity = request.getParameter("quantity");

        OrderDao.getInstance().setOrder(userId, exhibitionId, quantity);

        response.sendRedirect(getServletContext().getContextPath() + "/exhibitions.jsp");
        log.trace("Redirected to exhibitions.jsp");
        log.debug("Command finished");
    }
}
