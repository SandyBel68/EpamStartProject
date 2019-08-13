package dao;

import floor.Floor;
import floor.FloorDAO;
import floor.FloorDAOImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.util.List;

public class FloorDAOImplTest {
    private static FloorDAO floorDAO;
    private static Floor floor25;

    @BeforeAll
    public static void init() {
        floorDAO = FloorDAOImpl.getInstance();
        floor25 = new Floor(25, 1, "40", "40");
    }

    @Test
    public void addFloorTest() throws SQLException {
        floorDAO.addFloor(floor25);
        List<Floor> floors = floorDAO.getAllFloors();
        assertTrue(floors.contains(floor25));
    }

    @Test
    public void getAllFloorsTest() throws SQLException {
        List<Floor> floors = floorDAO.getAllFloors();
        assertTrue(floors.size() > 0);
    }

    @Test
    public void getFloorByIDTest() throws SQLException {
        Floor floorEx25 = floorDAO.getFloorById(25,1);
        assertTrue(floorEx25.equals(floor25));
    }

    @Test
    public void removeFloorByIdTest() throws SQLException {
        floorDAO.removeFloorById(25, 1);
        List<Floor> floors = floorDAO.getAllFloors();
        assertFalse(floors.contains(floor25));
    }


}
