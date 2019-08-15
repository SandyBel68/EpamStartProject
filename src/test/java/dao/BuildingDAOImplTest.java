package dao;

import building.BuildingDAO;
import building.BuildingDAOImpl;
import building.Building;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BuildingDAOImplTest {
    private static BuildingDAO buildingDAO;

    @BeforeAll
    public static void init() {
        buildingDAO = BuildingDAOImpl.getInstance();
    }

    @Test
    public void buildingDAOTest() throws SQLException {
        String address = "St.Petersburg, Zastavskaya 22";
        Building newBuilding = new Building(address);

        Integer idBuilding = buildingDAO.add(newBuilding);

        List<Building> buildings = buildingDAO.getAll();
        assertTrue(buildings.contains(newBuilding));

        Building returnedBuilding = buildingDAO.getByAddress(address);
        assertEquals(returnedBuilding.getAddress(), newBuilding.getAddress());

        String addressNew = "HHH 123";
        Building update = new Building(idBuilding, addressNew);
        Integer returnedId = buildingDAO.update(update);

        Building returnedNewBuilding = buildingDAO.getByAddress(addressNew);
        assertEquals(returnedNewBuilding.getAddress(), addressNew);

        boolean isDeleted = buildingDAO.deleteById(idBuilding);
        assertTrue(isDeleted);
    }
}
