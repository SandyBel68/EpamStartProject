package building;

import java.sql.SQLException;
import java.util.List;

public interface BuildingDAO {

    Integer add(Building building) throws SQLException;

    List<Building> getAll() throws SQLException;

    Building getByAddress(String address) throws SQLException;

    Integer update(Building building) throws SQLException;

    boolean deleteById(Integer idBuilding) throws SQLException;
}
