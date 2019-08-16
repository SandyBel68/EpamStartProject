package visitor;

import java.sql.SQLException;
import java.util.List;

public interface VisitorDAO {
    List<Visitor> getAll() throws SQLException;

    Integer add(Visitor visitor) throws SQLException;

    Integer update(Visitor visitor) throws SQLException;

    boolean deleteById(Integer idVisitor) throws SQLException;

    List <Visitor> getByName(String name) throws SQLException;
}
