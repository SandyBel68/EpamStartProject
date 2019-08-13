package movetracker;

import common.DataSourceInit;

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
    public void addMovement(MoveTracker movement) throws SQLException {
        {
            try (Connection connection = DATASOURCE.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO movetracker (idbuilding, idvisitor, idroom, timestart, timefinish) VALUES (?,?,?,?,?)")
            ) {
                preparedStatement.setInt(1, movement.getIdBuilding());
                preparedStatement.setInt(2, movement.getIdVisitor());
                preparedStatement.setInt(3, movement.getIdRoom());
                preparedStatement.setTimestamp(4, Timestamp.valueOf(movement.getTimeStart()));
                preparedStatement.setTimestamp(5, Timestamp.valueOf(movement.getTimeFinish()));
                preparedStatement.execute();
            }
        }
    }

    @Override
    public List<MoveTracker> getByRoomId(Integer idRoom, Integer idBuilding) throws SQLException {
        List<MoveTracker> allByRoom = new ArrayList<>();
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM movetracker where idroom = ? AND idbuilding = ?")) {
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
                            resultSet.getInt("idBuilding"),
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
}

