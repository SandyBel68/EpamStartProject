package visitor;

import java.util.List;

public interface VisitorDAO {
    List<Visitor> getAllVisitors();

    void addVisitor(Visitor visitor);

    void removeVisitorById(Integer idVisitor);
}
