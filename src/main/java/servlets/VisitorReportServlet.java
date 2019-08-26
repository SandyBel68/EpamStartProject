package servlets;

import lombok.extern.log4j.Log4j2;
import service.StatisticsService;
import common.ReportDocument;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Log4j2
@WebServlet(name = "VisitorReport", value = "/visitorReport")
public class VisitorReportServlet extends HttpServlet {
    private static StatisticsService visitorStatistics = StatisticsService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<ReportDocument> listByVisitor = null;
        try {
            listByVisitor = visitorStatistics.getListByVisitorReport(String.valueOf(req.getAttribute("visitorName")));
        } catch (SQLException | NullPointerException | NumberFormatException e) {
            log.error(e.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
        req.setAttribute("listByVisitor",listByVisitor);
        req.getRequestDispatcher("/visitorReport.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        doGet(req, resp);
    }
}
