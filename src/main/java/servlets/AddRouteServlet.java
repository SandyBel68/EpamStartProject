package servlets;

import entities.Building;
import entities.Floor;
import entities.Visitor;
import service.BuildingService;
import service.FloorService;
import service.RoomService;
import service.VisitorService;
import service.WriterToMoveTrackerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AddRouteServlet", value = "/addRoute")
public class AddRouteServlet extends HttpServlet {
    private static VisitorService visitorService = VisitorService.getInstance();
    private static WriterToMoveTrackerService writerToMoveTrackerService = WriterToMoveTrackerService.getInstance();
    private static BuildingService buildingService = BuildingService.getInstance();
    private static FloorService floorService = FloorService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        List<Visitor> allVisitors = visitorService.getAllVisitors();
        List<Building> allBuildings = buildingService.getAllBuildings();
        List<Floor> allFloors = floorService.getAllFloors();
        req.setAttribute("allVisitors", allVisitors);
        req.setAttribute("allBuildings", allBuildings);
        req.setAttribute("allFloors", allFloors);
        req.getRequestDispatcher("/addRoute.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        String visitorName = req.getParameter("visitorName");
        String address = req.getParameter("address");
        Integer numberFloor = Integer.valueOf(req.getParameter("numberFloor"));
            writerToMoveTrackerService.addVisitorInMoveTracker(visitorName, numberFloor, address);
        req.getRequestDispatcher("/addedRoute.jsp").forward(req, resp);
    }
}
