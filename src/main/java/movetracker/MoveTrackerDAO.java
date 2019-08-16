package movetracker;

import java.sql.SQLException;
import java.util.List;

public interface MoveTrackerDAO {
    Integer add(MoveTracker movement) throws SQLException;

    List<MoveTracker> getByRoomId(Integer idRoom) throws SQLException;

    List<MoveTracker> getByVisitorId(Integer idVisitor) throws SQLException;

    Integer update(MoveTracker tracker) throws SQLException;

    boolean deleteById(Integer idMove) throws SQLException;

}
