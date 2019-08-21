package room;

import java.sql.SQLException;
import java.util.List;

public class RoomService {
    private static RoomService instance = null;
    private RoomDAO roomDAO;

    private RoomService(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    synchronized public static RoomService getInstance() {
        if (instance == null) {
            instance = new RoomService(RoomDAOImpl.getInstance());
        }
        return instance;
    }

    public List<Room> getAllRooms() {
        List<Room> roomList = null;
        try {
            roomList = roomDAO.getAll();
        } catch (SQLException e) {
            System.err.println(e);
            //TODO logging
        }
        return roomList;
    }
}
