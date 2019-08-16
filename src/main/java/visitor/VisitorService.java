package visitor;

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

    public List<Visitor> getVisitors() {
        List<Visitor> visitorList = null;
        try {
            visitorList = visitorDAO.getAll();
        } catch (SQLException e) {
            System.err.println(e);
            //TODO logging
        }
        return visitorList;
    }
}
