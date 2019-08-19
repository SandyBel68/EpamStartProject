package room;

import common.DataSourceInit;

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

public class RoomDAOImpl implements RoomDAO {
    public static RoomDAOImpl instance;
    public final DataSource DATASOURCE;

    private RoomDAOImpl(DataSource dataSource) {
        DATASOURCE = dataSource;
    }

    synchronized public static RoomDAOImpl getInstance() {
        if (instance == null) {
            try {
                instance = new RoomDAOImpl(DataSourceInit.getMsInstance());
            } catch (IOException | PropertyVetoException e) {
//TODO Logging!
                System.err.println(e);
            }
        }
        return instance;
    }

    @Override
    public List<Room> getAllByFloor(Integer idFloor) throws SQLException {
        List<Room> allRooms = new ArrayList<>();
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM room WHERE idfloor = ?")) {
            preparedStatement.setInt(1, idFloor);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Room room = new Room(
                            resultSet.getInt("idRoom"),
                            resultSet.getInt("numberRoom"),
                            resultSet.getInt("idFloor"),
                            resultSet.getString("x1"),
                            resultSet.getString("y1"),
                            resultSet.getString("x2"),
                            resultSet.getString("y2")
                    );
                    allRooms.add(room);
                }
            }
        }
        return allRooms;
    }

    @Override
    public List<Room> getAll() throws SQLException {
        List<Room> allRooms = new ArrayList<>();
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM room")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Room room = new Room(
                            resultSet.getInt("idRoom"),
                            resultSet.getInt("numberRoom"),
                            resultSet.getInt("idFloor"),
                            resultSet.getString("x1"),
                            resultSet.getString("y1"),
                            resultSet.getString("x2"),
                            resultSet.getString("y2")
                    );
                    allRooms.add(room);
                }
            }
        }
        return allRooms;
    }

    @Override
    public Room getByNumber(Integer numberRoom) throws SQLException {
        Room room = new Room();
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM room WHERE numberRoom = ?")) {
            preparedStatement.setInt(1, numberRoom);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    room = new Room(
                            resultSet.getInt("idRoom"),
                            resultSet.getInt("numberRoom"),
                            resultSet.getInt("idFloor"),
                            resultSet.getString("x1"),
                            resultSet.getString("y1"),
                            resultSet.getString("x2"),
                            resultSet.getString("y2")
                    );
                }
            }
        }
        return room;
    }

    @Override
    public Integer add(Room room) throws SQLException {
        Integer id;
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO room (numberRoom, idfloor, x1, y1, x2, y2) VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setInt(1, room.getNumberRoom());
            preparedStatement.setInt(2, room.getIdFloor());
            preparedStatement.setString(3, room.getX1());
            preparedStatement.setString(4, room.getY1());
            preparedStatement.setString(5, room.getX2());
            preparedStatement.setString(6, room.getY2());
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
    public Room getById(Integer idRoom) throws SQLException {
        Room room = new Room();
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM room WHERE idroom = ?")) {
            preparedStatement.setInt(1, idRoom);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    room = new Room(
                            resultSet.getInt("idRoom"),
                            resultSet.getInt("numberRoom"),
                            resultSet.getInt("idFloor"),
                            resultSet.getString("x1"),
                            resultSet.getString("y1"),
                            resultSet.getString("x2"),
                            resultSet.getString("y2")
                    );
                }
            }
        }
        return room;
    }

    @Override
    public Integer update(Room room) throws SQLException {
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE room SET numberRoom = ?, idfloor = ?, x1 = ?, y1 = ?, x2 =?, y2 = ? WHERE idRoom = ?")) {
            preparedStatement.setInt(1, room.getNumberRoom());
            preparedStatement.setInt(2, room.getIdFloor());
            preparedStatement.setString(3, room.getX1());
            preparedStatement.setString(4, room.getY1());
            preparedStatement.setString(5, room.getX2());
            preparedStatement.setString(6, room.getY2());
            preparedStatement.setInt(7, room.getIdRoom());
            preparedStatement.executeUpdate();
            return room.getIdRoom();
        }
    }

    @Override
    public boolean removeById(Integer idRoom) throws SQLException {
        int numRows;
        {
            try (Connection connection = DATASOURCE.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM room WHERE idroom = ?")
            ) {
                preparedStatement.setInt(1, idRoom);
                numRows = preparedStatement.executeUpdate();
            }
        }
        if (numRows > 0) {
            return true;
        } else return false;
    }
}
