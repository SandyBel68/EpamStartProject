package servlets;

import movetracker.StatisticsService;
import room.Room;
import room.RoomService;
import visitor.Visitor;
import visitor.VisitorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RoomServlet", value = "/room")
public class RoomServlet extends HttpServlet {
    private static RoomService roomService = RoomService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        List<Room> allRooms = roomService.getAllRooms();
        req.setAttribute("allRooms", allRooms);
        req.getRequestDispatcher("/room.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        Integer roomNumber = Integer.valueOf(req.getParameter("roomNumber"));
        req.setAttribute("roomNumber", roomNumber);
        getServletContext().getRequestDispatcher("/roomReport").include(req,resp);
    }
}
