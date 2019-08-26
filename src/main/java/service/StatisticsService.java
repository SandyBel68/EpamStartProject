package service;
import dao.MoveTrackerDAO;
import dao.impl.MoveTrackerDAOImpl;
import entities.MoveTracker;
import common.ReportDocument;
import entities.Room;
import dao.RoomDAO;
import dao.impl.RoomDAOImpl;
import entities.Visitor;
import dao.VisitorDAO;
import dao.impl.VisitorDAOImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatisticsService {
    private static StatisticsService instance = null;
    private MoveTrackerDAO moveDao;
    private VisitorDAO visitorDAO;
    private RoomDAO roomDAO;

    private StatisticsService(MoveTrackerDAO moveDao, VisitorDAO visitorDAO, RoomDAO roomDAO) {
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

    public List<MoveTracker> getListByRoom(int idRoom, int idBuilding) throws SQLException {
        List<MoveTracker> moveListRoom = null;
        moveListRoom = moveDao.getByRoomId(idRoom);
        return moveListRoom;
    }

    public List<ReportDocument> getListByRoomFloorBuildingReport(String address, Integer numberFloor, Integer numberRoom) throws SQLException {
        List<ReportDocument> reportDocumentList = new ArrayList<>();
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
            report.setNumberFloor(numberFloor);
            report.setAddress(address);
            reportDocumentList.add(report);
        }
        return reportDocumentList;
    }

    public List<ReportDocument> getListByRoomReport(Integer numberRoom) throws SQLException {
        List<ReportDocument> reportDocumentList = new ArrayList<>();
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
        return reportDocumentList;
    }

    public List<MoveTracker> getListByVisitor(String visitorName) throws SQLException {
        List<MoveTracker> listByVisitor = new ArrayList<>();
        Visitor selected = visitorDAO.getByName(visitorName);
        Integer idSelected = selected.getIdVisitor();
        listByVisitor = moveDao.getByVisitorId(idSelected);
        return listByVisitor;
    }

    public List<ReportDocument> getListByVisitorReport(String visitorName) throws SQLException {
        List<ReportDocument> reportDocumentList = new ArrayList<>();
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
        return reportDocumentList;
    }

}
