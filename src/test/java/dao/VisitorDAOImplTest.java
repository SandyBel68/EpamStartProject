package dao;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import visitor.Visitor;
import visitor.VisitorDAO;
import visitor.VisitorDAOImpl;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class VisitorDAOImplTest {

    private static VisitorDAO visitorDAO;

    @BeforeAll
    public static void init() {
        visitorDAO = VisitorDAOImpl.getInstance();
    }

    @Test
    public void visitorDAOTest() throws SQLException {
        String name = "Semen";
        Visitor semen = new Visitor(name);

        Integer idVisitor = visitorDAO.add(semen);

        List<Visitor> visitors = visitorDAO.getAll();
        assertTrue(visitors.contains(semen));

        List<Visitor> visitorsByName = visitorDAO.getByName("Semen");
        assertTrue(visitorsByName.contains(semen));

        String newName = "Mike";
        Visitor update = new Visitor(idVisitor,newName);
        Integer returnedId = visitorDAO.update(update);

        List<Visitor> visitorsByNewName = visitorDAO.getByName(newName);
        assertTrue(visitorsByNewName.contains(update));

        boolean isDeleted = visitorDAO.deleteById(idVisitor);
        assertTrue(isDeleted);
    }

//    private static VisitorDAO visitorDAO;
//    private static Visitor semen;
//
//    @BeforeAll
//    public static void init() {
//        visitorDAO = VisitorDAOImpl.getInstance();
//        semen = new Visitor("Semen");
//    }
//
//    @Test
//    public void addTest() throws SQLException {
//        Integer id = visitorDAO.add(semen);
//        assertTrue(id > 0);
//    }
//
//    @Test
//    public void getAllTest() throws SQLException {
//        List<Visitor> visitors = visitorDAO.getAll();
//        assertTrue(visitors.size() > 0);
//    }
//
//    @Test
//    public void getByNameTest() throws SQLException{
//        List<Visitor> visitorslist = visitorDAO.getByName("Semen");
//        assertTrue(visitorslist.size() > 0);
//        System.out.println(visitorslist);
//    }
//
//    @Test
//    public void updateByIdTest() throws SQLException{
//        Visitor update = new Visitor(6,"Sonya");
//        Integer returned = visitorDAO.update(update);
//        System.out.println(returned);
//    }
//
//    @Test
//    public void removeByIdTest() throws SQLException {
//        boolean isDeleted = visitorDAO.deleteById(6);
//        assertTrue(isDeleted);
//    }
}


