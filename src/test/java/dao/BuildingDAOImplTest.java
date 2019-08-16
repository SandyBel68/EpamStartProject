package dao;

import building.Building;
import building.BuildingDAO;
import building.BuildingDAOImpl;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BuildingDAOImplTest {
    private static BuildingDAO buildingDAO = BuildingDAOImpl.getInstance();

    @Test
    public void buildingDAOTest() throws SQLException {
        String address = "Moskovskiy pr. 123";
        Building newBuilding = new Building(address);

        //add
        Integer idBuilding = buildingDAO.add(newBuilding);

        //getAll
        List<Building> buildings = buildingDAO.getAll();
        assertTrue(buildings.size() > 0);
        assertTrue(buildings.contains(newBuilding));

        //getByAddress
        Building returnedBuilding = buildingDAO.getByAddress(address);
        assertEquals(returnedBuilding.getAddress(), newBuilding.getAddress());

        //update
        String addressNew = "HHH 123";
        Building update = new Building(idBuilding, addressNew);
        Integer returnedId = buildingDAO.update(update);
        assertEquals(returnedId, idBuilding);

        //getByAddress
        Building returnedNewBuilding = buildingDAO.getByAddress(addressNew);
        assertEquals(returnedNewBuilding.getAddress(), addressNew);

        //delete
        boolean isDeleted = buildingDAO.deleteById(idBuilding);
        assertTrue(isDeleted);
    }
}
