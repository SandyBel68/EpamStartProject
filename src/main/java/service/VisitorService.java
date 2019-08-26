package service;

import dao.VisitorDAO;
import dao.impl.VisitorDAOImpl;
import entities.Visitor;

import java.sql.SQLException;
import java.util.List;

public class VisitorService {
    private static VisitorService instance = null;
    private VisitorDAO visitorDAO;

    private VisitorService(VisitorDAO visitorDAO) {
        this.visitorDAO = visitorDAO;
    }

    synchronized public static VisitorService getInstance() {
        if (instance == null) {
            instance = new VisitorService(VisitorDAOImpl.getInstance());
        }
        return instance;
    }

    public List<Visitor> getAllVisitors() throws SQLException {
        List<Visitor> visitorList = null;
        visitorList = visitorDAO.getAll();
        return visitorList;
    }

    public Integer addVisitor(String visitorName) throws SQLException {
        Integer visitorId = null;
        Visitor visitorToAdd = new Visitor(visitorName);
        visitorId = visitorDAO.add(visitorToAdd);
        return visitorId;
    }
}
