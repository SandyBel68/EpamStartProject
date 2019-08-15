package dao;

import movetracker.MoveTracker;
import movetracker.MoveTrackerDAO;
import movetracker.MoveTrackerDAOImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MoveTrackerDAOImplTest {

    private static MoveTrackerDAO trackerDAO;
    private static MoveTracker move1;

    @BeforeAll
    public static void init() {
        trackerDAO = MoveTrackerDAOImpl.getInstance();
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime finish = LocalDateTime.now().plusMinutes(1);
        move1 = new MoveTracker( 7, 118, start, finish);
    }

    @Test
    public void addTest() throws SQLException {
        Integer id = trackerDAO.add(move1);
        assertTrue(id > 0);
    }

    @Test
    public void getByRoomIdTest() throws SQLException {
        List<MoveTracker> byRoom = trackerDAO.getByRoomId(118);
        System.out.println(byRoom);
    }

    @Test
    public void getByVisitorIdTest() throws SQLException {
        List<MoveTracker> byVisitor = trackerDAO.getByVisitorId(7);
        System.out.println(byVisitor);
    }

    @Test
    public void updateByIdTest() throws SQLException{
        LocalDateTime st = LocalDateTime.now();
        LocalDateTime fn = st.plusSeconds(2);
        MoveTracker update = new MoveTracker(3,7,118, st, fn);
        Integer returned = trackerDAO.update(update);
        assertTrue(returned > 0);
    }

    @Test
    public void deleteByIdTest() throws SQLException{
        boolean isDeleted = trackerDAO.deleteById(3);
        assertTrue(isDeleted);
    }
}
