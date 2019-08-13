package dao;

import room.Room;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import room.RoomDAO;
import room.RoomDAOImpl;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoomDAOImplTest {

    private static RoomDAO roomDAO;
    private static Room room115;

    @BeforeAll
    public static void init() {
        roomDAO = RoomDAOImpl.getInstance();
        room115 = new Room(115,1, 1, "0", "0", "10", "15");
    }

    @Test
    public void addRoomTest() throws SQLException {
        roomDAO.addRoom(room115);
        List<Room> rooms = roomDAO.getAllRooms();
        assertTrue(rooms.contains(room115));
    }

    @Test
    public void getAllRoomsTest() throws SQLException {
        List<Room> rooms = roomDAO.getAllRooms();
        assertTrue(rooms.size() > 0);
    }

    @Test
    public void getRoomsByFloorTest() throws SQLException { List<Room> rooms = roomDAO.getRoomsByFloor(1, 1);
       assertTrue(rooms.contains(room115));
    }

    @Test
    public void removeRoomByIdTest() throws SQLException {
        roomDAO.removeRoomById(115, 1);
        List<Room> rooms = roomDAO.getAllRooms();
        assertFalse(rooms.contains(room115));
    }
}
