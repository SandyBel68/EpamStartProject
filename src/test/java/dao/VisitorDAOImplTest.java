package dao;

import dao.impl.VisitorDAOImpl;
import org.junit.jupiter.api.Test;
import entities.Visitor;

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

        Visitor visitorByName = visitorDAO.getByName("Semen");
        assertEquals(visitorByName.getVisitorName(), name);

        String newName = "Mike";
        Visitor update = new Visitor(idVisitor, newName);
        Integer returnedId = visitorDAO.update(update);
        assertEquals(returnedId, idVisitor);

        Visitor visitorByNewName = visitorDAO.getByName(newName);
        assertEquals(visitorByNewName.getVisitorName(), newName);

        boolean isDeleted = visitorDAO.deleteById(idVisitor);
        assertTrue(isDeleted);
    }
}


