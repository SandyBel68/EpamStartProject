package dao;

import entities.Building;
import entities.Floor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

public class BuildingDAOImplTest {
    private static BuildingDAO buildingDAO;
    private static Building building2;

    @BeforeAll
    public static void init() {
        buildingDAO = BuildingDAOImpl.getInstance();
        building2 = new Building(2, "Moscow, Tverskaya str. 1");
    }

    @Test
    public void addBuildingTest(){
        buildingDAO.addBuilding(building2);
        List<Building> buildings = buildingDAO.getAllBuidlings();
        assertTrue(buildings.contains(building2));
    }

    @Test
    public void getAllBuildingsTest(){
        List<Building> buildings = buildingDAO.getAllBuidlings();
        assertTrue(buildings.size() > 0);
    }
}
