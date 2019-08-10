package dao;

import entities.Floor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

public class FloorDAOImplTest {
    private static FloorDAOImpl floorDAO;
    private static Floor floor25;

    @BeforeAll
    public static void init() {
        floorDAO = FloorDAOImpl.getInstance();
        floor25 = new Floor(25, "40", "40");
    }

    @Test
    public void addFloorTest(){
        floorDAO.addFloor(floor25);
        List<Floor> floors = floorDAO.getAllFloors();
        assertTrue(floors.contains(floor25));
    }

    @Test
    public void getAllFloorsTest(){
        List<Floor> floors = floorDAO.getAllFloors();
        assertTrue(floors.size() > 0);
    }

    @Test
    public void removeFloorByIdTest(){
        floorDAO.removeFloorById(25);
        List<Floor> floors = floorDAO.getAllFloors();
        assertFalse(floors.contains(floor25));
    }
}
