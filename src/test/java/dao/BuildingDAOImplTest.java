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
    private static String address;
    private static String newAddress;
    private static Building newBuilding;
    private static Integer idBuilding;

    @BeforeAll
    public static void init() throws SQLException {
        buildingDAO = BuildingDAOImpl.getInstance();
        address = "St.Petersburg, Zastavskaya 22122345";
        newAddress = "HHH 123";
        newBuilding = new Building(address);
    }

    @Test
    public void addTest() throws SQLException {
        idBuilding = buildingDAO.add(newBuilding);
        newBuilding.setIdBuilding(idBuilding);
        System.out.println(idBuilding);
        List<Building> buildings = buildingDAO.getAll();
        assertTrue(buildings.contains(newBuilding));

    }

    @Test
    public void getAllTest() throws SQLException {
        List<Building> buildings = buildingDAO.getAll();
        assertEquals(1, buildings.size());
    }

    @Test
    public void updateByIdTest() throws SQLException{
        System.out.println(newBuilding);
//        Building update = new Building(18, newAddress);
//        Integer returned = buildingDAO.update(update);
//        assertEquals(returned, idBuilding);
    }

    @Test
    public void getByAddressTest() throws SQLException{
        Building returned = buildingDAO.getByAddress(newAddress);
        assertEquals(returned.getAddress(), newAddress);
    }

    @Test
    public void deleteByIdTest() throws SQLException{
        Integer idBuilding2 = buildingDAO.add(newBuilding);
        boolean isDeleted = buildingDAO.deleteById(idBuilding2);
        assertTrue(isDeleted);
    }

//    @Test
//    public void buildingDAOTest() throws SQLException {
//        Integer idBuilding = buildingDAO.add(newBuilding);
//
//        List<Building> buildings = buildingDAO.getAll();
//        assertTrue(buildings.contains(newBuilding));
//
//        Building returnedBuilding = buildingDAO.getByAddress(address);
//        assertEquals(returnedBuilding.getAddress(), newBuilding.getAddress());
//
//        String addressNew = "HHH 123";
//        Building update = new Building(idBuilding, addressNew);
//        Integer returnedId = buildingDAO.update(update);
//
//        Building returnedNewBuilding = buildingDAO.getByAddress(addressNew);
//        assertEquals(returnedNewBuilding.getAddress(), addressNew);
//
//        boolean isDeleted = buildingDAO.deleteById(idBuilding);
//        assertTrue(isDeleted);
//    }

//    private static BuildingDAO buildingDAO;
//    private static Building building;
//    private static Integer id;
//
//    @BeforeAll
//    public static void init() {
//        buildingDAO = BuildingDAOImpl.getInstance();
//        building = new Building("Moscow, Tverskaya str. 1234");
//    }
//
//    @Test
//    public void addTest() throws SQLException {
//        Integer id = buildingDAO.add(building);
//        assertTrue(id > 0);
//    }
//
//
//    @Test
//    public void getAllTest() throws SQLException {
//        List<Building> buildings = buildingDAO.getAll();
//        assertTrue(buildings.size() > 0);
//    }
//
//    @Test
//    public void getByAddressTest() throws SQLException{
//        Building returned = buildingDAO.getByAddress("Moscow, Tverskaya str. 1234");
//        assertTrue(returned.getIdBuilding().equals(building.getIdBuilding()));
//    }
//
//    @Test
//    public void updateByIdTest() throws SQLException{
//        Building update = new Building(3,"HHH 123");
//        Integer returned = buildingDAO.update(update);
//        assertTrue(returned > 0);
//    }
//
//    @Test
//    public void deleteByIdTest() throws SQLException{
//        boolean isDeleted = buildingDAO.deleteById(2);
//        assertTrue(isDeleted);
//    }
}
