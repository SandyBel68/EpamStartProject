package dao;

import database.DataSourceInit;
import entities.Visitor;

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
    static final String addVisitorQuery = "INSERT INTO visitor (idVisitor, visitorName) VALUES (?,?)";
    static final String getAllVisitorsQuery = "SELECT * FROM visitor";
    static final String removeVisitorByIdQuery = "DELETE FROM visitor WHERE idvisitor = ?";

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
    public List<Visitor> getAllVisitors() {
        List<Visitor> allVisitors = new ArrayList<>();
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getAllVisitorsQuery)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Visitor visitor = new Visitor(
                            resultSet.getInt("idVisitor"),
                            resultSet.getString("visitorName")
                    );
                    allVisitors.add(visitor);
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
            //TODO Logging!
        }
        return allVisitors;
    }

    @Override
    public void addVisitor(Visitor visitor) {
        {
            try (Connection connection = DATASOURCE.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(addVisitorQuery)
            ) {
                preparedStatement.setInt(1, visitor.getIdVisitor());
                preparedStatement.setString(2, visitor.getVisitorName());
                preparedStatement.execute();
            } catch (SQLException e) {
                System.err.println(e);
                //TODO Logging!
            }
        }
    }

    @Override
    public void removeVisitorById(Integer idVisitor) {
        {
            try (Connection connection = DATASOURCE.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(removeVisitorByIdQuery)
            ) {
                preparedStatement.setInt(1, idVisitor);
                preparedStatement.execute();
            } catch (SQLException e) {
                System.err.println(e);
                //TODO Logging!
            }
        }
    }
}

