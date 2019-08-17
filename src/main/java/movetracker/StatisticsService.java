package movetracker;

import visitor.Visitor;
import visitor.VisitorDAO;
import visitor.VisitorDAOImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatisticsService {
    private static StatisticsService instance = null;
    private MoveTrackerDAO moveDao;
    private VisitorDAO visitorDAO;

    private StatisticsService(MoveTrackerDAO moveDao, VisitorDAO visitorDAO) {
        this.moveDao = moveDao;
        this.visitorDAO = visitorDAO;
    }

    synchronized public static StatisticsService getInstance() {
        if (instance == null) {
            instance = new StatisticsService(MoveTrackerDAOImpl.getInstance(), VisitorDAOImpl.getInstance());
        }
        return instance;
    }

    public List<MoveTracker> getListByRoom(int idRoom, int idBuilding) {
        List<MoveTracker> moveListRoom = null;
        try {
            moveListRoom = moveDao.getByRoomId(idRoom);
        } catch (SQLException e) {
            System.err.println(e + "21");
            //TODO logging
        }
        return moveListRoom;
    }

    public List<MoveTracker> getListByVisitor(String visitorName) {
        List<MoveTracker> listByVisitor = new ArrayList<>();
        try {
            Visitor selected = visitorDAO.getByName(visitorName);
            Integer idSelected = selected.getIdVisitor();
            listByVisitor = moveDao.getByVisitorId(idSelected);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listByVisitor;
    }
}
