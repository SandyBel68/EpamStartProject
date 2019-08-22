package dao.impl;

import common.DataSourceInit;
import dao.BuildingDAO;
import entities.Building;
import lombok.extern.log4j.Log4j2;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class BuildingDAOImpl implements BuildingDAO {

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
                log.error(e.getMessage());
            }
        }
        return instance;
    }

    @Override
    public List<Building> getAll() throws SQLException {
        List<Building> allBuildings = new ArrayList<>();
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM building")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Building building = new Building(
                            resultSet.getInt("idBuilding"),
                            resultSet.getString("address")
                    );
                    allBuildings.add(building);
                }
            }
        }
        return allBuildings;
    }

    @Override
    public Building getByAddress(String address) throws SQLException {
        Building building = new Building();
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM building where address = ? ")) {
            preparedStatement.setString(1, address);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    building = new Building(
                            resultSet.getInt("idBuilding"),
                            resultSet.getString("address")
                    );

                }
                return building;
            }
        }
    }

    @Override
    public Integer update(Building building) throws SQLException {
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE building SET address = ? WHERE idbuilding = ?")) {
            preparedStatement.setString(1, building.getAddress());
            preparedStatement.setInt(2, building.getIdBuilding());
            preparedStatement.executeUpdate();
            return building.getIdBuilding();
        }
    }

    @Override
    public boolean deleteById(Integer idBuilding) throws SQLException {
        int numRows;
        {
            try (Connection connection = DATASOURCE.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM building WHERE idbuilding = ?")
            ) {
                preparedStatement.setInt(1, idBuilding);
                numRows = preparedStatement.executeUpdate();
            }
        }
        if (numRows > 0){
            return true;
        }
        else return false;
    }

    @Override
    public Integer add(Building building) throws SQLException {
        Integer id;
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO building (address) VALUES (?)", Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, building.getAddress());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creation failed");
                }
            }
        }
        return id;
    }
}

