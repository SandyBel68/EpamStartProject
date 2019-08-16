package room;

import java.sql.SQLException;
import java.util.List;

public interface RoomDAO {
    List<Room> getAllByFloor(Integer idFloor) throws SQLException;

    Integer add(Room room) throws SQLException;

    Room getById(Integer idRoom) throws SQLException;

    Integer update(Room room) throws SQLException;

    boolean removeById(Integer idRoom) throws SQLException;
}
