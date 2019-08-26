package servlets;

import common.ReportDocument;
import service.StatisticsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "VisitorReport", value = "/visitorReport")
public class VisitorReportServlet extends HttpServlet {
    private static StatisticsService visitorStatistics = StatisticsService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<ReportDocument> listByVisitor = visitorStatistics.getListByVisitorReport(String.valueOf(req.getAttribute("visitorName")));
        req.setAttribute("listByVisitor",listByVisitor);
        req.getRequestDispatcher("/visitorReport.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        doGet(req, resp);
    }
}
