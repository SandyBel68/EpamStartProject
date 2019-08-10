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
    static final String addFloorQuery = "INSERT INTO floor (idFloor, maxXsize, maxYsize) VALUES (?,?,?)";
    static final String getAllFloorsQuery = "SELECT * FROM floor";
    static final String removeFloorByIdQuery = "DELETE FROM floor WHERE idfloor = ?";

    public static FloorDAOImpl instance = null;
    public final DataSource DATASOURCE;

    private FloorDAOImpl(DataSource dataSource) {
        DATASOURCE = dataSource;
    }

    synchronized public static FloorDAOImpl getInstance() {
        if (instance == null) {
            try {
                instance = new FloorDAOImpl(DataSourceInit.getMsInstance());
            } catch (IOException | PropertyVetoException e) {
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
                preparedStatement.setString(2, floor.getMaxXSize());
                preparedStatement.setString(3, floor.getMaxYSize());
                preparedStatement.execute();
            } catch (SQLException e) {
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
                            resultSet.getString("maxXSize"),
                            resultSet.getString("MaxYSize")
                    );
                    allFloors.add(floor);
                }
            }
        } catch (SQLException e) {
            //TODO Logging!
        }
        return allFloors;
    }

    @Override
    public void removeFloorById(Integer idFloor) {
        {
            try (Connection connection = DATASOURCE.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(removeFloorByIdQuery)
            ) {
                preparedStatement.setInt(1, idFloor);
                preparedStatement.execute();
            } catch (SQLException e) {
                //TODO Logging!
            }
        }
    }
}

