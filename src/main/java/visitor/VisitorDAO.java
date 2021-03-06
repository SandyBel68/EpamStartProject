package visitor;

import java.sql.SQLException;
import java.util.List;

public interface VisitorDAO {
    List<Visitor> getAll() throws SQLException;

    Integer add(Visitor visitor) throws SQLException;

    Integer update(Visitor visitor) throws SQLException;

    boolean deleteById(Integer idVisitor) throws SQLException;

    Visitor getByName(String name) throws SQLException;

    Visitor getById(Integer idVisitor) throws SQLException;
}
