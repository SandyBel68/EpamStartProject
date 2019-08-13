package dao;

import movetracker.MoveTracker;
import movetracker.MoveTrackerDAO;
import movetracker.MoveTrackerDAOImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MoveTrackerDAOImplTest {

    private static MoveTrackerDAO trackerDAO;
    private static MoveTracker move1;

    @BeforeAll
    public static void init() {
        trackerDAO = MoveTrackerDAOImpl.getInstance();
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime finish = LocalDateTime.now().plusMinutes(1);
        move1 = new MoveTracker(1, 1, 101, start, finish);
    }

    @Test
    public void addMovementTest() throws SQLException {
        trackerDAO.addMovement(move1);
    }

    @Test
    public void getByRoomIdTest() throws SQLException {
        List<MoveTracker> byRoom = trackerDAO.getByRoomId(101, 1);
        assertTrue(byRoom.contains(move1));
    }

    @Test
    public void getByVisitorIdTest() throws SQLException {
        List<MoveTracker> byVisitor = trackerDAO.getByVisitorId(1);
        assertTrue(byVisitor.contains(move1));
    }
}
