package dao;

import entities.MoveTracker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MoveTrackerDAOImplTest {

    private static MoveTrackerDAOImpl trackerDAO;
    private static MoveTracker move1;

    @BeforeAll
    public static void init() {
        trackerDAO = MoveTrackerDAOImpl.getInstance();
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime finish = LocalDateTime.now().plusMinutes(1);
        move1 = new MoveTracker(1, 1, 101, start, finish);
    }

    @Test
    public void addMovementTest(){
        trackerDAO.addMovement(move1);
    }

    @Test
    public void getByRoomIdTest() {
        List<MoveTracker> byRoom = trackerDAO.getByRoomId(101);
        assertTrue(byRoom.contains(move1));
    }

    @Test
    public void getByVisitorIdTest(){
        List<MoveTracker> byVisitor = trackerDAO.getByVisitorId(1);
        assertTrue(byVisitor.contains(move1));
    }
}
