package movetracker;

import java.sql.SQLException;
import java.util.List;

public class StatisticsService {
    private static StatisticsService instance = null;
    private MoveTrackerDAO moveDao;

    private StatisticsService(MoveTrackerDAO moveDao) {
        this.moveDao = moveDao;
    }

    synchronized public static StatisticsService getInstance() {
        if (instance == null) {
            instance = new StatisticsService(MoveTrackerDAOImpl.getInstance());
        }
        return instance;
    }

    public List<MoveTracker> getListByRoom(int idRoom, int idBuilding) {
        List<MoveTracker> moveListRoom = null;
        try {
            moveListRoom = moveDao.getByRoomId(idRoom);
        } catch (SQLException e) {
            System.err.println(e);
            //TODO logging
        }
        return moveListRoom;
    }

    public List<MoveTracker> getListByVisitor(int idVisitor) {
        List<MoveTracker> moveListVisitor = null;
        try {
            moveListVisitor = moveDao.getByVisitorId(idVisitor);
        } catch (SQLException e) {
            System.err.println(e);
            //TODO logging
        }
        return moveListVisitor;
    }
}
