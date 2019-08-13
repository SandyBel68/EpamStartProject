package visitor;

import visitor.Visitor;

import java.sql.SQLException;
import java.util.List;

public interface VisitorDAO {
    List<Visitor> getAllVisitors() throws SQLException;

    void addVisitor(Visitor visitor) throws SQLException;

    void removeVisitorById(Integer idVisitor) throws SQLException;
}
