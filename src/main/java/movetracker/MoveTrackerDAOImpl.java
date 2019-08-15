package movetracker;

import common.DataSourceInit;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MoveTrackerDAOImpl implements MoveTrackerDAO {
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
    public Integer add(MoveTracker movement) throws SQLException {
        Integer id;
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO movetracker (idvisitor, idroom, timestart, timefinish) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setInt(1, movement.getIdVisitor());
            preparedStatement.setInt(2, movement.getIdRoom());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(movement.getTimeStart()));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(movement.getTimeFinish()));
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
    public List<MoveTracker> getByRoomId(Integer idRoom) throws SQLException {
        List<MoveTracker> allByRoom = new ArrayList<>();
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM movetracker where idroom = ?")) {
            preparedStatement.setInt(1, idRoom);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    MoveTracker tracker = new MoveTracker(
                            resultSet.getInt("idMove"),
                            resultSet.getInt("idVisitor"),
                            resultSet.getInt("idRoom"),
                            resultSet.getTimestamp("timeStart").toLocalDateTime(),
                            resultSet.getTimestamp("timeFinish").toLocalDateTime()
                    );
                    allByRoom.add(tracker);
                }
            }
        }
        return allByRoom;
    }

    @Override
    public List<MoveTracker> getByVisitorId(Integer idVisitor) throws SQLException {
        List<MoveTracker> allByVisitor = new ArrayList<>();
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM movetracker where idvisitor = ?")) {
            preparedStatement.setInt(1, idVisitor);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    MoveTracker tracker = new MoveTracker(
                            resultSet.getInt("idmove"),
                            resultSet.getInt("idvisitor"),
                            resultSet.getInt("idroom"),
                            resultSet.getTimestamp("timestart").toLocalDateTime(),
                            resultSet.getTimestamp("timefinish").toLocalDateTime()
                    );
                    allByVisitor.add(tracker);
                }
            }
        }
        return allByVisitor;
    }

    @Override
    public Integer update(MoveTracker tracker) throws SQLException {
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE movetracker SET idvisitor = ?, idroom = ?, timestart = ?, timefinish = ? WHERE idmove = ?")) {
            preparedStatement.setInt(1, tracker.getIdVisitor());
            preparedStatement.setInt(2, tracker.getIdRoom());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(tracker.getTimeStart()));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(tracker.getTimeFinish()));
            preparedStatement.setInt(5, tracker.getIdMove());
            preparedStatement.executeUpdate();
            return tracker.getIdMove();
        }
    }

    @Override
    public boolean deleteById(Integer idMove) throws SQLException {
        int numRows;
        {
            try (Connection connection = DATASOURCE.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM movetracker WHERE idmove = ?")
            ) {
                preparedStatement.setInt(1, idMove);
                numRows = preparedStatement.executeUpdate();
            }
        }
        if (numRows > 0) {
            return true;
        } else return false;
    }
}

