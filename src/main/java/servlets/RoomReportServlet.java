package servlets;

import service.StatisticsService;
import common.ReportDocument;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RoomReport", value = "/roomReport")
public class RoomReportServlet extends HttpServlet {
    private static StatisticsService roomStatistics = StatisticsService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<ReportDocument> listByRoom = roomStatistics.getListByRoomReport((Integer)(req.getAttribute("roomNumber")));
        req.setAttribute("listByRoom", listByRoom);
        req.getRequestDispatcher("/roomReport.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        doGet(req, resp);
    }
}
