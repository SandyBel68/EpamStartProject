package dao;

import entities.Room;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoomDAOImplTest {

    private static RoomDAOImpl roomDAO;
    private static Room room115;

    @BeforeAll
    public static void init() {
        roomDAO = RoomDAOImpl.getInstance();
        room115 = new Room(115, 1, "0", "0", "10", "15");
    }

    @Test
    public void addRoomTest(){
        roomDAO.addRoom(room115);
        List<Room> rooms = roomDAO.getAllRooms();
        assertTrue(rooms.contains(room115));
    }

    @Test
    public void getAllRoomsTest() {
        List<Room> rooms = roomDAO.getAllRooms();
        assertTrue(rooms.size() > 0);
    }

    @Test
    public void getRoomsByFloorTest() { List<Room> rooms = roomDAO.getRoomsByFloor(1);
       assertTrue(rooms.contains(room115));
    }

    @Test
    public void removeRoomByIdTest() {
        roomDAO.removeRoomById(115);
        List<Room> rooms = roomDAO.getAllRooms();
        assertFalse(rooms.contains(room115));
    }
}
