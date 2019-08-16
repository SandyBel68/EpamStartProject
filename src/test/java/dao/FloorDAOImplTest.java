package dao;

import floor.Floor;
import floor.FloorDAO;
import floor.FloorDAOImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FloorDAOImplTest {
    private static FloorDAO floorDAO;
    private static Floor floor25;

    @BeforeAll
    public static void init() {
        floorDAO = FloorDAOImpl.getInstance();
        floor25 = new Floor(25, 1, "400", "400");
    }

    @Test
    public void addTest() throws SQLException {
        Integer id = floorDAO.add(floor25);
        assertTrue(id > 0);
    }

    @Test
    public void getAllByBuildingAndNumberTest() throws SQLException {
        Integer idBuilding = 1;
        Integer floorNumber = 25;
        Floor returned = floorDAO.getByBuildingAndNumber(idBuilding, floorNumber);
        assertTrue(returned.getIdFloor().equals(floor25.getIdFloor()));;
    }

    @Test
    public void getByIDTest() throws SQLException {
        Floor floorEx25 = floorDAO.getById(27);
        assertTrue(floorEx25.getIdFloor().equals(floor25.getIdFloor()));
    }

    @Test
    public void updateTest() throws SQLException{
        Floor update = new Floor(27,25,1, "1400", "600");
        Integer returned = floorDAO.update(update);
        assertTrue(returned > 0);
    }

    @Test
    public void removeByIdTest() throws SQLException {
        boolean isDeleted = floorDAO.removeById(27);
        assertTrue(isDeleted);
    }
}
