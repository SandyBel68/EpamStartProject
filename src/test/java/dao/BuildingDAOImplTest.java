package dao;

import building.BuildingDAO;
import building.BuildingDAOImpl;
import building.Building;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BuildingDAOImplTest {
    private static BuildingDAO buildingDAO;
    private static Building building;
    private static Integer id;

    @BeforeAll
    public static void init() {
        buildingDAO = BuildingDAOImpl.getInstance();
        building = new Building("Moscow, Tverskaya str. 1234");
    }

    @Test
    public void addTest() throws SQLException {
        Integer id = buildingDAO.add(building);
        assertTrue(id > 0);
    }

    @Test
    public void getAll() throws SQLException {

    }


    @Test
    public void getAllTest() throws SQLException {
        List<Building> buildings = buildingDAO.getAll();
        assertTrue(buildings.size() > 0);
    }

    @Test
    public void getByAddressTest() throws SQLException{
        Building returned = buildingDAO.getByAddress("Moscow, Tverskaya str. 1234");
        System.out.println((buildingDAO.getAll().get(0)));
        assertTrue(returned.getIdBuilding().equals(buildingDAO.getAll().get(0).getIdBuilding()));
    }

    @Test
    public void updateByIdTest() throws SQLException{
        Building update = new Building(3,"HHH 123");
        Integer returned = buildingDAO.update(update);
        assertTrue(returned > 0);
    }

    @Test
    public void deleteByIdTest() throws SQLException{
        boolean isDeleted = buildingDAO.deleteById(2);
        assertTrue(isDeleted);
    }
}
