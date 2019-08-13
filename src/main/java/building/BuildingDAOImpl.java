package building;

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

public class BuildingDAOImpl implements BuildingDAO {

    public static BuildingDAOImpl instance;
    public final DataSource DATASOURCE;

    private BuildingDAOImpl(DataSource dataSource) {
        DATASOURCE = dataSource;
    }

    synchronized public static BuildingDAOImpl getInstance() {
        if (instance == null) {
            try {
                instance = new BuildingDAOImpl(DataSourceInit.getMsInstance());
            } catch (IOException | PropertyVetoException e) {
                System.err.println(e);
//TODO Logging!
            }
        }
        return instance;
    }


    @Override
    public List<Building> getAllBuidlings() throws SQLException {
        List<Building> allBuildings = new ArrayList<>();
        try (Connection connection = DATASOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM building")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Building building = new Building(
                            resultSet.getInt("idBuilding"),
                            resultSet.getString("address")
                    );
                    allBuildings.add(building);
                }
            }
        }
        return allBuildings;
    }

    @Override
    public void addBuilding(Building building) throws SQLException {
        {
            try (Connection connection = DATASOURCE.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO building (idbuilding, address) VALUES (?,?)")
            ) {
                preparedStatement.setInt(1, building.getIdBuilding());
                preparedStatement.setString(2, building.getAddress());
                preparedStatement.execute();
            }
        }
    }
}

