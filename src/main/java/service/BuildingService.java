package service;

import dao.BuildingDAO;
import dao.impl.BuildingDAOImpl;
import entities.Building;
import lombok.extern.log4j.Log4j2;

import java.sql.SQLException;
import java.util.List;

@Log4j2
public class BuildingService {
    private static BuildingService instance = null;
    private BuildingDAO buildingDAO;

    private BuildingService(BuildingDAO buildingDAO) {
        this.buildingDAO = buildingDAO;
    }

    synchronized public static BuildingService getInstance() {
        if (instance == null) {
            instance = new BuildingService(BuildingDAOImpl.getInstance());
        }
        return instance;
    }

    public List<Building> getAllBuildings() throws SQLException {
        List<Building> buildingList = null;
        buildingList = buildingDAO.getAll();
        return buildingList;
    }
}
