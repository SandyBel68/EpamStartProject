package room;

import java.sql.SQLException;
import java.util.List;

public interface RoomDAO {
    List<Room> getAllRooms() throws SQLException;

    void addRoom(Room room) throws SQLException;

    List<Room> getRoomsByFloor(Integer idFloor, Integer idBuilding) throws SQLException;

    void removeRoomById(Integer idRoom, Integer idBuilding) throws SQLException;
}
