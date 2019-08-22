package service;

import dao.FloorDAO;
import dao.impl.FloorDAOImpl;
import entities.Floor;

import java.sql.SQLException;
import java.util.List;

public class FloorService {
    private static FloorService instance = null;
    private FloorDAO FloorDAO;

    private FloorService(FloorDAO FloorDAO) {
        this.FloorDAO = FloorDAO;
    }

    synchronized public static FloorService getInstance() {
        if (instance == null) {
            instance = new FloorService(FloorDAOImpl.getInstance());
        }
        return instance;
    }

    public List<Floor> getAllFloors() {
        List<Floor> FloorList = null;
        try {
            FloorList = FloorDAO.getAll();
        } catch (SQLException e) {
            System.err.println(e);
            //TODO logging
        }
        return FloorList;
    }

//    public Integer addFloor(Integer )
}
