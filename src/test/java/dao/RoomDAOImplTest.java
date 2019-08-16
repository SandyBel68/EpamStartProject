package dao;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import room.Room;
import room.RoomDAO;
import room.RoomDAOImpl;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoomDAOImplTest {

    private static RoomDAO roomDAO;
    private static Room room115;

    @BeforeAll
    public static void init() {
        roomDAO = RoomDAOImpl.getInstance();
        room115 = new Room(115, 28, "0", "0", "10", "15");
    }

    @Test
    public void addRoomTest() throws SQLException {
        Integer id = roomDAO.add(room115);
        assertTrue(id > 0);
    }

    @Test
    public void getAllByFloorTest() throws SQLException {
        List<Room> rooms = roomDAO.getAllByFloor(28);
        assertTrue(rooms.size() > 0);
        System.out.println(rooms);
    }

    @Test
    public void getByIdTest() throws SQLException {
        Room room = roomDAO.getById(118);
        System.out.println(room);
    }

    @Test
    public void updateTest() throws SQLException {
        Room update = new Room(118, 20, 28, "0", "0", "100", "100");
        Integer returned = roomDAO.update(update);
        System.out.println(returned);
    }

    @Test
    public void removeByIdTest() throws SQLException {
        boolean isDeleted = roomDAO.removeById(118);
        assertTrue(isDeleted);
    }
}
