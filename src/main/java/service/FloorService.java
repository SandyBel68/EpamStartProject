package service;

import dao.BuildingDAO;
import dao.FloorDAO;
import dao.impl.FloorDAOImpl;
import entities.Building;
import entities.Floor;
import lombok.extern.log4j.Log4j2;

import java.sql.SQLException;
import java.util.List;

@Log4j2
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

    public List<Floor> getAllFloors() throws SQLException {
        List<Floor> FloorList = null;
        FloorList = floorDAO.getAll();
        return FloorList;
    }

    public Integer addFloor (Integer numberFloor, String address, String maxXSize, String maxYSize) throws SQLException{
        Integer idFloor = null;
        Building building = buildingDAO.getByAddress(address);
        Integer idBuilding = building.getIdBuilding();
        Floor floor = new Floor(numberFloor, idBuilding, maxYSize, maxXSize);
        idFloor = floorDAO.add(floor);
        return idFloor;
    }
}
