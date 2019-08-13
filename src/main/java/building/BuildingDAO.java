package building;

import java.util.List;

public interface BuildingDAO {
    List<Building> getAllBuidlings();

    void addBuilding(Building building);
}
