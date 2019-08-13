package dao;

import database.DataSourceInit;
import entities.MoveTracker;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MoveTrackerDAOImpl implements MoveTrackerDAO {
    static final String addTrackQuery = "INSERT INTO movetracker (idbuilding, idvisitor, idroom, timestart, timefinish) VALUES (?,?,?,?,?)";
    static final String getAllTracksByRoom = "SELECT * FROM movetracker where idroom = ? AND idbuilding = ?";
    static final String getAllTracksByVisitor = "SELECT * FROM movetracker where idvisitor = ?";

    public static MoveTrackerDAOImpl instance;
    public final DataSource DATASOURCE;

    private MoveTrackerDAOImpl(DataSource dataSource) {
        DATASOURCE = dataSource;
    }

    synchronized public static MoveTrackerDAOImpl getInstance() {
        if (instance == null) {
            try {
                instance = new MoveTrackerDAOImpl(DataSourceInit.getMsInstance());
            } catch (IOException | PropertyVetoException e) {
//TODO Logging!
                System.err.println(e);
            }
        }
        return instance;
    }

    @Override
    public void addMovement(MoveTracker movement) {
        {
            try (Connection connection = DATASOURCE.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(addTrackQuery)
            ) {
                preparedStatement.setInt(1, movement.getIdBuilding());
                preparedStatement.setInt(2, movement.getIdVisitor());
                preparedStatement.setInt(3, movement.getIdRoom());
                preparedStatement.setTimestamp(4, Timestamp.valueOf(movement.getTimeStart()));
                preparedStatement.setTimestamp(5, Timestamp.valueOf(movement.getTimeFinish()));
                preparedStatement.execute();
            } catch (SQLException e) {
                //TODO Logging!
                System.err.println(e);
            }
        }
    }

    @Override
    public List<MoveTracker> getByRoomId(Integer idRoom, Integer idBuilding) {
        List<MoveTracker> allByRoom = new ArrayList<>();
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getAllTracksByRoom)) {
            preparedStatement.setInt(1, idRoom);
            preparedStatement.setInt(2, idBuilding);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    MoveTracker tracker = new MoveTracker(
                            resultSet.getInt("idMove"),
                            resultSet.getInt("idBuilding"),
                            resultSet.getInt("idVisitor"),
                            resultSet.getInt("idRoom"),
                            resultSet.getTimestamp("timeStart").toLocalDateTime(),
                            resultSet.getTimestamp("timeFinish").toLocalDateTime()
                    );
                    allByRoom.add(tracker);
                }
            }
        } catch (SQLException e) {
            //TODO logging
            System.err.println(e);
        }
        return allByRoom;
    }

    @Override
    public List<MoveTracker> getByVisitorId(Integer idVisitor) {
        List<MoveTracker> allByVisitor = new ArrayList<>();
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getAllTracksByVisitor)) {
            preparedStatement.setInt(1, idVisitor);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    MoveTracker tracker = new MoveTracker(
                            resultSet.getInt("idmove"),
                            resultSet.getInt("idBuilding"),
                            resultSet.getInt("idvisitor"),
                            resultSet.getInt("idroom"),
                            resultSet.getTimestamp("timestart").toLocalDateTime(),
                            resultSet.getTimestamp("timefinish").toLocalDateTime()
                    );
                    allByVisitor.add(tracker);
                }
            }
        } catch (SQLException e) {
            //TODO logging!
            System.err.println(e);
        }
        return allByVisitor;
    }
}

