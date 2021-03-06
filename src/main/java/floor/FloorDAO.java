package floor;

import java.sql.SQLException;
import java.util.List;

public interface FloorDAO {
    Floor getByBuildingAndNumber(Integer idBuilding, Integer numberFloor) throws SQLException;

    Integer add(Floor floor) throws SQLException;

    Integer update(Floor floor) throws SQLException;

    boolean removeById(Integer idFloor) throws SQLException;

    Floor getById(Integer idFloor) throws SQLException;
}
