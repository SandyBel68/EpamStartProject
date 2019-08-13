package movetracker;

import java.sql.SQLException;
import java.util.List;

public interface MoveTrackerDAO {
    void addMovement(MoveTracker movement) throws SQLException;

    List<MoveTracker> getByRoomId(Integer idRoom, Integer idBuilding) throws SQLException;

    List<MoveTracker> getByVisitorId(Integer idVisitor) throws SQLException;
}
