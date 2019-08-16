package dao;

import building.Building;
import building.BuildingDAO;
import building.BuildingDAOImpl;
import floor.Floor;
import floor.FloorDAO;
import floor.FloorDAOImpl;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FloorDAOImplTest {
    private static FloorDAO floorDAO = FloorDAOImpl.getInstance();
    private static BuildingDAO buildingDAO = BuildingDAOImpl.getInstance();

    @Test
    public void floorDaoTest() throws SQLException {
        String address = "St.Petersburg, Zastavskaya 22";
        Building newBuilding = new Building(address);
        Integer idBuilding = buildingDAO.add(newBuilding);

        Integer floorNumber = 3;
        Floor floor3 = new Floor(floorNumber, idBuilding, "500", "500");

        //add
        Integer idFloor = floorDAO.add(floor3);

        //getByBuilding
        Floor returned = floorDAO.getByBuildingAndNumber(idBuilding, floorNumber);
        assertEquals(floor3.getNumberFloor(), returned.getNumberFloor());
        assertEquals(floor3.getIdBuilding(), returned.getIdBuilding());

        //getById
        Floor returned2 = floorDAO.getById(idFloor);
        assertEquals(idFloor, returned2.getIdFloor());

        //update
        String newMaxY = "600";
        String newMaxX = "600";
        Floor update = new Floor(idFloor, floorNumber, idBuilding, newMaxY, newMaxX);
        Integer updated = floorDAO.update(update);
        assertEquals(updated, idFloor);

        //getById
        Floor returned3 = floorDAO.getById(idFloor);
        assertEquals(returned3.getIdFloor(), idFloor);

        //delete
        boolean isFloorDeleted = floorDAO.removeById(idFloor);
        assertTrue(isFloorDeleted);
        boolean isBuildingDeleted = buildingDAO.deleteById(idBuilding);
        assertTrue(isBuildingDeleted);
    }
}

