package dao;

import database.DataSourceInit;
import entities.Building;
import entities.Room;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuildingDAOImpl implements BuildingDAO {
    static final String addBuildingQuery = "INSERT INTO building (idbuilding, address) VALUES (?,?)";
    static final String getAllBuildingsQuery = "SELECT * FROM building";

    public static BuildingDAOImpl instance;
    public final DataSource DATASOURCE;

    private BuildingDAOImpl(DataSource dataSource) {
        DATASOURCE = dataSource;
    }

    synchronized public static BuildingDAOImpl getInstance() {
        if (instance == null) {
            try {
                instance = new BuildingDAOImpl(DataSourceInit.getMsInstance());
            } catch (IOException | PropertyVetoException e) {
                System.err.println(e);
//TODO Logging!
            }
        }
        return instance;
    }


    @Override
    public List<Building> getAllBuidlings() {
        List<Building> allBuildings = new ArrayList<>();
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getAllBuildingsQuery)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Building building = new Building(
                            resultSet.getInt("idBuilding"),
                            resultSet.getString("address")
                    );
                    allBuildings.add(building);
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
            //TODO Logging!
        }
        return allBuildings;
    }

    @Override
    public void addBuilding(Building building) {
        {
            try (Connection connection = DATASOURCE.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(addBuildingQuery)
            ) {
                preparedStatement.setInt(1, building.getIdBuilding());
                preparedStatement.setString(2, building.getAddress());
                preparedStatement.execute();
            } catch (SQLException e) {
                System.err.println(e);
                //TODO Logging!
            }
        }
    }
}
