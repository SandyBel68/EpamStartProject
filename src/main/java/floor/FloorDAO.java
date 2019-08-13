package floor;

import java.sql.SQLException;
import java.util.List;

public interface FloorDAO {
    void addFloor(Floor floor) throws SQLException;

    List<Floor> getAllFloors() throws SQLException;

    void removeFloorById(Integer idFloor, Integer idBuilding) throws SQLException;

    Floor getFloorById(Integer idFloor, Integer idBuilding) throws SQLException;
}
