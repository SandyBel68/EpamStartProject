package visitorLocation;

import dao.RoomDAO;
import dao.impl.RoomDAOImpl;
import entities.Room;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;



import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static service.GetVisitorLocationService.getVisitorLocation;


public class VisitorLocationTest {
    private static RoomDAO roomDAO;

    @BeforeAll
    public static void init() {
        roomDAO = RoomDAOImpl.getInstance();
    }


    @Test
    public void getVisitorLocation1() throws SQLException {
        long x1 = 200;
        long y1 = 200;
        List<Room> rooms = roomDAO.getAllByFloor(1);
        assertTrue(getVisitorLocation(rooms, x1, y1) == 101);
    }
    @Test
    public void getVisitorLocation2() throws SQLException {
        long x1 = 1200;
        long y1 = 200;
        List<Room> rooms = roomDAO.getAllByFloor(1);
        assertTrue(getVisitorLocation(rooms, x1, y1) == 103);
    }

    @Test
    public void getVisitorLocation3() throws SQLException {
        long x1 = 1300;
        long y1 = 700;
        List<Room> rooms = roomDAO.getAllByFloor(1);
        assertTrue(getVisitorLocation(rooms, x1, y1) == null);
    }





    ////////////////////////////////////////

}
