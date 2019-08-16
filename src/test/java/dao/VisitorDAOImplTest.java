package dao;

import org.junit.jupiter.api.Test;
import visitor.Visitor;
import visitor.VisitorDAO;
import visitor.VisitorDAOImpl;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VisitorDAOImplTest {

    private static VisitorDAO visitorDAO = VisitorDAOImpl.getInstance();

    @Test
    public void visitorDAOTest() throws SQLException {
        String name = "Semen";
        Visitor semen = new Visitor(name);

        Integer idVisitor = visitorDAO.add(semen);

        List<Visitor> visitors = visitorDAO.getAll();
        assertTrue(visitors.size() > 0);
        assertTrue(visitors.contains(semen));

        List<Visitor> visitorsByName = visitorDAO.getByName("Semen");
        assertTrue(visitorsByName.contains(semen));

        String newName = "Mike";
        Visitor update = new Visitor(idVisitor, newName);
        Integer returnedId = visitorDAO.update(update);
        assertEquals(returnedId, idVisitor);

        List<Visitor> visitorsByNewName = visitorDAO.getByName(newName);
        assertTrue(visitorsByNewName.contains(update));

        boolean isDeleted = visitorDAO.deleteById(idVisitor);
        assertTrue(isDeleted);
    }
}


