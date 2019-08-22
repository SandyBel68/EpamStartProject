package dao.impl;

import dao.BuildingDAO;
import dao.FloorDAO;
import dao.RoomDAO;
import dao.impl.BuildingDAOImpl;
import dao.impl.FloorDAOImpl;
import dao.impl.RoomDAOImpl;
import entities.Building;
import entities.Floor;
import org.junit.jupiter.api.Test;
import entities.Room;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoomDAOImplTest {
    private static FloorDAO floorDAO = FloorDAOImpl.getInstance();
    private static BuildingDAO buildingDAO = BuildingDAOImpl.getInstance();
    private static RoomDAO roomDAO = RoomDAOImpl.getInstance();

    @Test
    public void roomDaoTest() throws SQLException {
        String address = "St.Petersburg, Zastavskaya 22";
        Building newBuilding = new Building(address);
        Integer idBuilding = buildingDAO.add(newBuilding);

        Integer floorNumber = 3;
        Floor floor3 = new Floor(floorNumber, idBuilding, "500", "500");
        Integer idFloor = floorDAO.add(floor3);

        Integer numberRoom = 101;
        Room roomEx = new Room(numberRoom, idFloor, "100", "0", "200", "300");
        Integer idRoom = roomDAO.add(roomEx);

        List<Room> returned = roomDAO.getAllByFloor(idFloor);
        assertTrue(returned.contains(roomEx));

        String x1New = "200";
        String y1New = "300";
        Room update = new Room(idRoom, numberRoom, idFloor, x1New, y1New, "200", "300");
        Integer returnedId = roomDAO.update(update);

        Room returnedNewRoom = roomDAO.getById(idRoom);
        assertEquals(returnedNewRoom.getX1(), x1New);
        assertEquals(returnedNewRoom.getY1(), y1New);

        boolean isRoomDeleted = roomDAO.removeById(idRoom);
        assertTrue(isRoomDeleted);
        boolean isFloorDeleted = floorDAO.removeById(idFloor);
        assertTrue(isFloorDeleted);
        boolean isBuildingDeleted = buildingDAO.deleteById(idBuilding);
        assertTrue(isBuildingDeleted);
    }
}