package service;

import dao.BuildingDAO;
import dao.FloorDAO;
import dao.impl.FloorDAOImpl;
import entities.Building;
import entities.Floor;

import java.sql.SQLException;
import java.util.List;

public class FloorService {
    private static FloorService instance = null;
    private FloorDAO floorDAO;
    private BuildingDAO buildingDAO;

    private FloorService(FloorDAO FloorDAO) {
        this.floorDAO = FloorDAO;
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
            FloorList = floorDAO.getAll();
        } catch (SQLException e) {
            System.err.println(e);
            //TODO logging
        }
        return FloorList;
    }

    public Integer addFloor(Integer numberFloor, String address, String maxXSize, String maxYSize){
        Integer idFloor = null;
        try {
            Building building = buildingDAO.getByAddress(address);
            Integer idBuilding = building.getIdBuilding();
            Floor floor = new Floor(numberFloor, idBuilding, maxYSize, maxXSize);
            idFloor = floorDAO.add(floor);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idFloor;
    }
}
