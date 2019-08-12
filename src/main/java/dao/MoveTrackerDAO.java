package dao;

import entities.MoveTracker;

import java.util.List;

public interface MoveTrackerDAO {
    void addMovement(MoveTracker movement);

    List<MoveTracker> getByRoomId(Integer idRoom, Integer idBuilding);

    List<MoveTracker> getByVisitorId(Integer idVisitor);
}
