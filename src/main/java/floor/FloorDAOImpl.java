package floor;

import common.DataSourceInit;

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
    public void addFloor(Floor floor) throws SQLException {
        {
            try (Connection connection = DATASOURCE.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO floor (idfloor, idbuilding, maxXsize, maxYsize) VALUES (?,?,?,?)")
            ) {
                preparedStatement.setInt(1, floor.getIdFloor());
                preparedStatement.setInt(2, floor.getIdBuilding());
                preparedStatement.setString(3, floor.getMaxXSize());
                preparedStatement.setString(4, floor.getMaxYSize());
                preparedStatement.execute();
            }
        }
    }

    @Override
    public List<Floor> getAllFloors() throws SQLException {
        List<Floor> allFloors = new ArrayList<>();
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM floor")) {
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
            return allFloors;
        }
    }

    @Override
    public void removeFloorById(Integer idFloor, Integer idBuilding) throws SQLException {
        {
            try (Connection connection = DATASOURCE.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM floor WHERE idfloor = ? AND idbuilding = ?")
            ) {
                preparedStatement.setInt(1, idFloor);
                preparedStatement.setInt(2, idBuilding);
                preparedStatement.execute();
            }
        }
    }

    @Override
    public Floor getFloorById(Integer idFloor, Integer idBuilding) throws SQLException {
        Floor floor = new Floor();
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM floor where idfloor = ? AND idbuilding = ?")) {
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
                return floor;
            }
        }
    }
}

