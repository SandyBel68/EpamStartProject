package visitor;

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

public class VisitorDAOImpl implements VisitorDAO {
    public static VisitorDAOImpl instance;
    public final DataSource DATASOURCE;

    private VisitorDAOImpl(DataSource dataSource) {
        DATASOURCE = dataSource;
    }

    synchronized public static VisitorDAOImpl getInstance() {
        if (instance == null) {
            try {
                instance = new VisitorDAOImpl(DataSourceInit.getMsInstance());
            } catch (IOException | PropertyVetoException e) {
                System.err.println(e);
//TODO Logging!
            }
        }
        return instance;
    }

    @Override
    public List<Visitor> getAllVisitors() throws SQLException {
        List<Visitor> allVisitors = new ArrayList<>();
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM visitor")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Visitor visitor = new Visitor(
                            resultSet.getInt("idVisitor"),
                            resultSet.getString("visitorName")
                    );
                    allVisitors.add(visitor);
                }
            }
        }
        return allVisitors;
    }

    @Override
    public void addVisitor(Visitor visitor) throws SQLException {
        {
            try (Connection connection = DATASOURCE.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO visitor (idVisitor, visitorName) VALUES (?,?)")
            ) {
                preparedStatement.setInt(1, visitor.getIdVisitor());
                preparedStatement.setString(2, visitor.getVisitorName());
                preparedStatement.execute();
            }
        }
    }

    @Override
    public void removeVisitorById(Integer idVisitor) throws SQLException {
        {
            try (Connection connection = DATASOURCE.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM visitor WHERE idvisitor = ?")
            ) {
                preparedStatement.setInt(1, idVisitor);
                preparedStatement.execute();
            }
        }
    }
}

