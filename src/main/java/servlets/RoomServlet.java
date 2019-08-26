package servlets;

import entities.Room;
import lombok.extern.log4j.Log4j2;
import service.RoomService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Log4j2
@WebServlet(name = "RoomServlet", value = "/room")
public class RoomServlet extends HttpServlet {
    private static RoomService roomService = RoomService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        List<Room> allRooms = null;
        try {
            allRooms = roomService.getAllRooms();
        } catch (SQLException | NullPointerException | NumberFormatException e) {
            log.error(e.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
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
