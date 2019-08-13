package dao;

import database.DataSourceInit;
import entities.Floor;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FloorDAOImpl implements FloorDAO {
    static final String addFloorQuery = "INSERT INTO floor (idfloor, idbuilding, maxXsize, maxYsize) VALUES (?,?,?,?)";
    static final String getAllFloorsQuery = "SELECT * FROM floor";
    static final String removeFloorByIdQuery = "DELETE FROM floor WHERE idfloor = ? AND idbuilding = ?";
    static final String getFloorByID = "SELECT * FROM floor where idfloor = ? AND idbuilding = ?";

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
    public void addFloor(Floor floor) {
        {
            try (Connection connection = DATASOURCE.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(addFloorQuery)
            ) {
                preparedStatement.setInt(1, floor.getIdFloor());
                preparedStatement.setInt(2, floor.getIdBuilding());
                preparedStatement.setString(3, floor.getMaxXSize());
                preparedStatement.setString(4, floor.getMaxYSize());
                preparedStatement.execute();
            } catch (SQLException e) {
                System.err.println(e);
                //TODO Logging!
            }
        }
    }

    @Override
    public List<Floor> getAllFloors() {
        List<Floor> allFloors = new ArrayList<>();
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getAllFloorsQuery)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Floor floor = new Floor(
                            resultSet.getInt("idFloor"),
                            resultSet.getInt("idBuilding"),
                            resultSet.getString("maxXSize"),
                            resultSet.getString("MaxYSize")
                    );
                    allFloors.add(floor);
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
            //TODO Logging!
        }
        return allFloors;
    }

    @Override
    public void removeFloorById(Integer idFloor, Integer idBuilding) {
        {
            try (Connection connection = DATASOURCE.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(removeFloorByIdQuery)
            ) {
                preparedStatement.setInt(1, idFloor);
                preparedStatement.setInt(2, idBuilding);
                preparedStatement.execute();
            } catch (SQLException e) {
                System.err.println(e);
                //TODO Logging!
            }
        }
    }

    @Override
    public Floor getFloorById(Integer idFloor, Integer idBuilding) {
        Floor floor = new Floor();
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getFloorByID)) {
            preparedStatement.setInt(1, idFloor);
            preparedStatement.setInt(2, idBuilding);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    floor = new Floor(
                            resultSet.getInt("idFloor"),
                            resultSet.getInt("idBuilding"),
                            resultSet.getString("maxXSize"),
                            resultSet.getString("MaxYSize")
                    );
                }
            }
        } catch (SQLException e) {
            //TODO logging
            System.err.println(e);
        }
        return floor;
    }
}

