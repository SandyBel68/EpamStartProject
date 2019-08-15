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
        Floor returned = floorDAO.getByBuildingAndNumber(1,25);
        assertTrue(returned.equals(floor25));
        System.out.println(returned);
    }

    @Test
    public void getByIDTest() throws SQLException {
        Floor floorEx25 = floorDAO.getById(27);
        assertTrue(floorEx25.equals(floor25));
    }

    @Test
    public void updateTest() throws SQLException{
        Floor update = new Floor(27,26,1, "1400", "400");
        Integer returned = floorDAO.update(update);
        System.out.println(returned);
    }

    @Test
    public void removeByIdTest() throws SQLException {
        floorDAO.removeById(27);
    }
}
