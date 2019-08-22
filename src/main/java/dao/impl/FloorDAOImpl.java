package dao.impl;

import common.DataSourceInit;
import dao.FloorDAO;
import entities.Floor;

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

public class FloorDAOImpl implements FloorDAO {
    public static FloorDAOImpl instance;
    public final DataSource DATASOURCE;

    private FloorDAOImpl(DataSource dataSource) {
        DATASOURCE = dataSource;
    }

    synchronized public static FloorDAOImpl getInstance() {
        if (instance == null) {
            try {
                instance = new FloorDAOImpl(DataSourceInit.getMsInstance());
            } catch (IOException | PropertyVetoException e) {
                System.err.println(e);
//TODO Logging!
            }
        }
        return instance;
    }

    @Override
    public Integer add(Floor floor) throws SQLException {
        Integer id;
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO floor (numberfloor, idbuilding, maxYsize, maxXsize) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setInt(1, floor.getNumberFloor());
            preparedStatement.setInt(2, floor.getIdBuilding());
            preparedStatement.setString(3, floor.getMaxYSize());
            preparedStatement.setString(4, floor.getMaxXSize());
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

    @Override
    public Integer update(Floor floor) throws SQLException {
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE floor SET numberFloor = ?, idbuilding = ?, maxYsize = ?, maxXsize = ? WHERE idFloor = ?")) {
            preparedStatement.setInt(1, floor.getNumberFloor());
            preparedStatement.setInt(2, floor.getIdBuilding());
            preparedStatement.setString(3, floor.getMaxYSize());
            preparedStatement.setString(4, floor.getMaxXSize());
            preparedStatement.setInt(5, floor.getIdFloor());
            preparedStatement.executeUpdate();
            return floor.getIdFloor();
        }
    }

    @Override
    public Floor getByBuildingAndNumber(Integer idBuilding, Integer numberFloor) throws SQLException {
        Floor floor = new Floor();
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM floor WHERE idbuilding = ? AND numberFloor = ?")) {
            preparedStatement.setInt(1, idBuilding);
            preparedStatement.setInt(2, numberFloor);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    floor = new Floor(
                            resultSet.getInt("idFloor"),
                            resultSet.getInt("numberFloor"),
                            resultSet.getInt("idBuilding"),
                            resultSet.getString("maxYSize"),
                            resultSet.getString("MaxXSize")
                    );
                }
            }
        }
        return floor;
    }

    @Override
    public boolean removeById(Integer idFloor) throws SQLException {
        int numRows;
        {
            try (Connection connection = DATASOURCE.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM floor WHERE idFloor = ?")
            ) {
                preparedStatement.setInt(1, idFloor);
                numRows = preparedStatement.executeUpdate();
            }
        }
        if (numRows > 0) {
            return true;
        } else return false;
    }

    @Override
    public Floor getById(Integer idFloor) throws SQLException {
        Floor floor = new Floor();
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM floor where idfloor = ?")) {
            preparedStatement.setInt(1, idFloor);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    floor = new Floor(
                            resultSet.getInt("idFloor"),
                            resultSet.getInt("numberFloor"),
                            resultSet.getInt("idBuilding"),
                            resultSet.getString("maxYSize"),
                            resultSet.getString("MaxXSize")
                    );
                }
                return floor;
            }
        }
    }

    @Override
    public List<Floor> getAll() throws SQLException {
        List<Floor> allFloors = new ArrayList<>();
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM floor")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Floor floor = new Floor(
                            resultSet.getInt("idFloor"),
                            resultSet.getInt("numberFloor"),
                            resultSet.getInt("idBuilding"),
                            resultSet.getString("maxYSize"),
                            resultSet.getString("maxXSize")
                    );
                    allFloors.add(floor);
                }
            }
        }
        return allFloors;
    }
}


