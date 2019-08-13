package building;

import java.sql.SQLException;
import java.util.List;

public interface BuildingDAO {
    List<Building> getAllBuidlings() throws SQLException;

    void addBuilding(Building building) throws SQLException;
}
