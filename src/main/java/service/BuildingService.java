package service;

import dao.BuildingDAO;
import dao.impl.BuildingDAOImpl;
import entities.Building;

import java.sql.SQLException;
import java.util.List;

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

    public List<Building> getAllBuildings() {
        List<Building> buildingList = null;
        try {
            buildingList = buildingDAO.getAll();
        } catch (SQLException e) {
            System.err.println(e);
            //TODO logging
        }
        return buildingList;
    }
}
