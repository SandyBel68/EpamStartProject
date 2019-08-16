package dao;

import visitor.Visitor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import visitor.VisitorDAO;
import visitor.VisitorDAOImpl;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.sql.SQLException;
import java.util.List;

public class VisitorDAOImplTest {

    private static VisitorDAO visitorDAO;
    private static Visitor semen;

    @BeforeAll
    public static void init() {
        visitorDAO = VisitorDAOImpl.getInstance();
        semen = new Visitor("Semen");
    }

    @Test
    public void addTest() throws SQLException {
        Integer id = visitorDAO.add(semen);
        assertTrue(id > 0);
    }

    @Test
    public void getAllTest() throws SQLException {
        List<Visitor> visitors = visitorDAO.getAll();
        assertTrue(visitors.size() > 0);
    }

    @Test
    public void getByNameTest() throws SQLException{
        List<Visitor> visitorslist = visitorDAO.getByName("Semen");
        assertTrue(visitorslist.size() > 0);
        System.out.println(visitorslist);
    }

    @Test
    public void updateByIdTest() throws SQLException{
        Visitor update = new Visitor(6,"Sonya");
        Integer returned = visitorDAO.update(update);
        System.out.println(returned);
    }

    @Test
    public void removeByIdTest() throws SQLException {
        boolean isDeleted = visitorDAO.deleteById(6);
        assertTrue(isDeleted);
    }
}


