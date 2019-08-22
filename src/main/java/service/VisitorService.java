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

    public List<Visitor> getAllVisitors() {
        List<Visitor> visitorList = null;
        try {
            visitorList = visitorDAO.getAll();
        } catch (SQLException e) {
            System.err.println(e);
            //TODO logging
        }
        return visitorList;
    }

    public Integer addVisitor(String visitorName){
        Integer visitorId  = null;
        Visitor visitorToAdd = new Visitor(visitorName);
        try {
            visitorId = visitorDAO.add(visitorToAdd);
        } catch (SQLException e) {
            System.err.println(e);
            //TODO logging
        }
        return visitorId;
    }
}
