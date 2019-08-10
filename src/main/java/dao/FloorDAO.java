package dao;

import entities.Floor;

import java.util.List;

public interface FloorDAO {
    void addFloor(Floor floor);

    List<Floor> getAllFloors();

    void removeFloorById(Integer idFloor);
}
