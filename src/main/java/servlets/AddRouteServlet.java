package servlets;

import entities.Visitor;
import service.VisitorService;
import service.WriterToMoveTrackerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddRouteServlet", value = "/addRoute")
public class AddRouteServlet extends HttpServlet {
    private static VisitorService visitorService = VisitorService.getInstance();
    private static WriterToMoveTrackerService writerToMoveTrackerService = WriterToMoveTrackerService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Visitor> allVisitors = visitorService.getAllVisitors();
        req.setAttribute("allVisitors", allVisitors);
        req.getRequestDispatcher("/addRoute.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        String visitorName = req.getParameter("visitorName");
        writerToMoveTrackerService.addVisitorInMoveTracker(visitorName, 1, "Zastavskaya 22");
        req.getRequestDispatcher("/addedRoute.jsp").forward(req, resp);
    }
}
