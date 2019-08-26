package dao.impl;

import common.DataSourceInit;
import dao.VisitorDAO;
import entities.Visitor;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
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
    public List<Visitor> getAll() throws SQLException {
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
    public Integer add(Visitor visitor) throws SQLException {
        Integer id;
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO visitor (visitorname) VALUES (?)", Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, visitor.getVisitorName());
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
    public boolean deleteById(Integer idVisitor) throws SQLException {
            int numRows;
            boolean isDeleted;
            {
                try (Connection connection = DATASOURCE.getConnection();
                     PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM visitor WHERE idvisitor = ?")
                ) {
                    preparedStatement.setInt(1, idVisitor);
                    numRows = preparedStatement.executeUpdate();
                }
            }
            if (numRows > 0){
                return true;
            }
            else return false;
    }

    @Override
    public Visitor getByName(String name) throws SQLException {
        Visitor visitorByName = new Visitor();
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM visitor WHERE visitorname = ?")) {
            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    visitorByName = new Visitor(
                            resultSet.getInt("idVisitor"),
                            resultSet.getString("visitorName")
                    );
                }
            }
        }
        return visitorByName;
    }

    @Override
    public Visitor getById(Integer idVisitor) throws SQLException {
        Visitor visitorByName = new Visitor();
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM visitor WHERE idvisitor = ?")) {
            preparedStatement.setInt(1, idVisitor);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    visitorByName = new Visitor(
                            resultSet.getInt("idVisitor"),
                            resultSet.getString("visitorName")
                    );
                }
            }
        }
        return visitorByName;
    }

    @Override
    public Integer update(Visitor visitor) throws SQLException {
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE visitor SET visitorname = ? WHERE idvisitor = ?")) {
            preparedStatement.setString(1, visitor.getVisitorName());
            preparedStatement.setInt(2, visitor.getIdVisitor());
            preparedStatement.executeUpdate();
            return visitor.getIdVisitor();
        }
    }
}

