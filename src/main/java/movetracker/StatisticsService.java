package movetracker;

import building.BuildingDAO;
import building.BuildingDAOImpl;
import floor.FloorDAO;
import floor.FloorDAOImpl;
import report.ReportDocument;
import room.Room;
import room.RoomDAO;
import room.RoomDAOImpl;
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
    private RoomDAO roomDAO;

    private StatisticsService(MoveTrackerDAO moveDao, VisitorDAO visitorDAO,  RoomDAO roomDAO) {
        this.moveDao = moveDao;
        this.visitorDAO = visitorDAO;
        this.roomDAO = roomDAO;
    }

    synchronized public static StatisticsService getInstance() {
        if (instance == null) {
            instance = new StatisticsService(MoveTrackerDAOImpl.getInstance(), VisitorDAOImpl.getInstance(), RoomDAOImpl.getInstance());
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

    public List<ReportDocument> getListByRoomReport(Integer numberRoom) {
        List<ReportDocument> reportDocumentList = new ArrayList<>();
        try {
            Room selected = roomDAO.getByNumber(numberRoom);
            Integer idSelected = selected.getIdRoom();
            List<MoveTracker> listMove = moveDao.getByRoomId(idSelected);
            for (int i = 0; i < listMove.size(); i++) {
                ReportDocument report = new ReportDocument();
                report.setNumberRoom(numberRoom);
                report.setStart(listMove.get(i).getTimeStart());
                report.setFinish(listMove.get(i).getTimeFinish());
                Integer idVisitor = listMove.get(i).getIdVisitor();
                Visitor visitor = visitorDAO.getById(idVisitor);
                String visitorName = visitor.getVisitorName();
                report.setVisitorName(visitorName);
                reportDocumentList.add(report);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return reportDocumentList;
    }

    public List<MoveTracker> getListByVisitor(String visitorName) {
        List<MoveTracker> listByVisitor = new ArrayList<>();
        try {
            Visitor selected = visitorDAO.getByName(visitorName);
            Integer idSelected = selected.getIdVisitor();
            listByVisitor = moveDao.getByVisitorId(idSelected);
        } catch (SQLException e) {
            System.err.println(e);
        }
        return listByVisitor;
    }

    public List<ReportDocument> getListByVisitorReport(String visitorName) {
        List<ReportDocument> reportDocumentList = new ArrayList<>();
        try {
            Visitor selected = visitorDAO.getByName(visitorName);
            Integer idSelected = selected.getIdVisitor();
            List<MoveTracker> listMove = moveDao.getByVisitorId(idSelected);
            for (int i = 0; i < listMove.size(); i++) {
                ReportDocument report = new ReportDocument();
                report.setVisitorName(visitorName);
                report.setStart(listMove.get(i).getTimeStart());
                report.setFinish(listMove.get(i).getTimeFinish());
                Integer idRoom = listMove.get(i).getIdRoom();
                Room room = roomDAO.getById(idRoom);
                Integer roomNumber = room.getNumberRoom();
                report.setNumberRoom(roomNumber);
                reportDocumentList.add(report);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return reportDocumentList;
    }
}
