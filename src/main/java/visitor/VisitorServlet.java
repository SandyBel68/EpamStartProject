package visitor;

import movetracker.MoveTracker;
import movetracker.StatisticsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "VisitorServlet", value = "/visitor")
public class VisitorServlet extends HttpServlet {
    private static VisitorService visitorService = VisitorService.getInstance();
    private static StatisticsService visitorStatistics = StatisticsService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        List<Visitor> allVisitors = visitorService.getAllVisitors();
        req.setAttribute("listOfVisitors", allVisitors);
        req.getRequestDispatcher("visitor.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        String visitorName = req.getParameter("visitorName");
        req.setAttribute("visitorName", visitorName);
        getServletContext().getRequestDispatcher("/visitorReport").include(req,resp);
    }
}
