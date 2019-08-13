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
        semen = new Visitor(5, "Semen");
    }

    @Test
    public void addVisitorTest() throws SQLException {
        visitorDAO.addVisitor(semen);
        List<Visitor> visitors = visitorDAO.getAllVisitors();
        assertTrue(visitors.contains(semen));
    }

    @Test
    public void getAllVisitorsTest() throws SQLException {
        List<Visitor> visitors = visitorDAO.getAllVisitors();
        assertTrue(visitors.size() > 0);
    }

    @Test
    public void removeVisitorByIdTest() throws SQLException {
        visitorDAO.removeVisitorById(5);
        List<Visitor> visitors = visitorDAO.getAllVisitors();
        assertFalse(visitors.contains(semen));
    }
}
