package dao;

import database.DataSourceInit;
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

public class RoomDAOImpl implements RoomDAO {
    static final String addRoomQuery = "INSERT INTO room (idroom, idfloor, x1, y1, x2, y2) VALUES (?,?,?,?,?,?)";
    static final String getAllRoomsQuery = "SELECT * FROM room";
    static final String getRoomsByFloor = "SELECT * FROM room WHERE idfloor = ? ";
    static final String removeRoomByIdQuery = "DELETE FROM room WHERE idroom = ?";

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
            }
        }
        return instance;
    }

    @Override
    public List<Room> getAllRooms() {
        List<Room> allRooms = new ArrayList<>();
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getAllRoomsQuery)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Room room = new Room(
                            resultSet.getInt("idRoom"),
                            resultSet.getInt("idFloor"),
                            resultSet.getString("x1"),
                            resultSet.getString("y1"),
                            resultSet.getString("x2"),
                            resultSet.getString("y2")
                    );
                    allRooms.add(room);
                }
            }
        } catch (SQLException e) {
            //TODO Logging!
        }
        return allRooms;
    }

    @Override
    public void addRoom(Room room) {
        {
            try (Connection connection = DATASOURCE.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(addRoomQuery)
            ) {
                preparedStatement.setInt(1, room.getIdRoom());
                preparedStatement.setInt(2, room.getIdFloor());
                preparedStatement.setString(3, room.getX1());
                preparedStatement.setString(4, room.getY1());
                preparedStatement.setString(5, room.getX2());
                preparedStatement.setString(6, room.getY2());
                preparedStatement.execute();
            } catch (SQLException e) {
                //TODO Logging!
            }
        }
    }

    @Override
    public List<Room> getRoomsByFloor(Integer idFloor) {
        List<Room> roomsByFloor = new ArrayList<>();
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getRoomsByFloor)) {
            preparedStatement.setInt(1, idFloor);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Room room = new Room(
                            resultSet.getInt("idRoom"),
                            resultSet.getInt("idFloor"),
                            resultSet.getString("x1"),
                            resultSet.getString("y1"),
                            resultSet.getString("x2"),
                            resultSet.getString("y2")
                    );
                    roomsByFloor.add(room);
                }
            }
        } catch (SQLException e) {
            //TODO Logging!
        }
        return roomsByFloor;
    }

    @Override
    public void removeRoomById(Integer idRoom) {
        {
            try (Connection connection = DATASOURCE.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(removeRoomByIdQuery)
            ) {
                preparedStatement.setInt(1, idRoom);
                preparedStatement.execute();
            } catch (SQLException e) {
                //TODO Logging!
            }
        }
    }
}
