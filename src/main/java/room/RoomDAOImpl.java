package room;

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
    public List<Room> getAllRooms() throws SQLException {
        List<Room> allRooms = new ArrayList<>();
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM room")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Room room = new Room(
                            resultSet.getInt("idRoom"),
                            resultSet.getInt("idBuilding"),
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
    public void addRoom(Room room) throws SQLException {
        {
            try (Connection connection = DATASOURCE.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO room (idroom, idbuilding, idfloor, x1, y1, x2, y2) VALUES (?,?,?,?,?,?,?)")
            ) {
                preparedStatement.setInt(1, room.getIdRoom());
                preparedStatement.setInt(2, room.getIdBuilding());
                preparedStatement.setInt(3, room.getIdFloor());
                preparedStatement.setString(4, room.getX1());
                preparedStatement.setString(5, room.getY1());
                preparedStatement.setString(6, room.getX2());
                preparedStatement.setString(7, room.getY2());
                preparedStatement.execute();
            }
        }
    }

    @Override
    public List<Room> getRoomsByFloor(Integer idFloor, Integer idBuilding) throws SQLException {
        List<Room> roomsByFloor = new ArrayList<>();
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM room WHERE idfloor = ? AND idbuilding = ?")) {
            preparedStatement.setInt(1, idFloor);
            preparedStatement.setInt(2, idBuilding);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Room room = new Room(
                            resultSet.getInt("idRoom"),
                            resultSet.getInt("idBuilding"),
                            resultSet.getInt("idFloor"),
                            resultSet.getString("x1"),
                            resultSet.getString("y1"),
                            resultSet.getString("x2"),
                            resultSet.getString("y2")
                    );
                    roomsByFloor.add(room);
                }
            }
        }
        return roomsByFloor;
    }

    @Override
    public void removeRoomById(Integer idRoom, Integer idBuilding) throws SQLException {
        {
            try (Connection connection = DATASOURCE.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM room WHERE idroom = ? AND idbuilding = ?")
            ) {
                preparedStatement.setInt(1, idRoom);
                preparedStatement.setInt(2, idBuilding);
                preparedStatement.execute();
            }
        }
    }
}
