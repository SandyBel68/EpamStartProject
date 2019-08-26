package dao.impl;

import dao.BuildingDAO;
import dao.FloorDAO;
import dao.MoveTrackerDAO;
import dao.RoomDAO;
import dao.VisitorDAO;
import dao.impl.BuildingDAOImpl;
import dao.impl.FloorDAOImpl;
import dao.impl.MoveTrackerDAOImpl;
import dao.impl.RoomDAOImpl;
import dao.impl.VisitorDAOImpl;
import entities.Building;
import entities.Floor;
import entities.MoveTracker;
import org.junit.jupiter.api.Test;
import entities.Room;
import entities.Visitor;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MoveTrackerDAOImplTest {
    private static FloorDAO floorDAO = FloorDAOImpl.getInstance();
    private static BuildingDAO buildingDAO = BuildingDAOImpl.getInstance();
    private static RoomDAO roomDAO = RoomDAOImpl.getInstance();
    private static MoveTrackerDAO moveDAO = MoveTrackerDAOImpl.getInstance();
    private static VisitorDAO visitorDAO = VisitorDAOImpl.getInstance();

    @Test
    public void moveTrackerDaoTest() throws SQLException {
        String address = "St.Petersburg, Zastavskaya 23";
        Building newBuilding = new Building(address);
        Integer idBuilding = buildingDAO.add(newBuilding);

        Integer floorNumber = 3;
        Floor floor3 = new Floor(floorNumber, idBuilding, "500", "500");
        Integer idFloor = floorDAO.add(floor3);

        Integer numberRoom = 101;
        Room roomEx = new Room(numberRoom, idFloor, "100", "0", "200", "300");
        Integer idRoom = roomDAO.add(roomEx);

        String name = "John";
        Visitor john = new Visitor(name);
        Integer idVisitor = visitorDAO.add(john);

        LocalDateTime start = LocalDateTime.of(2019, 2, 10, 14, 05 ,00);
        LocalDateTime finish = LocalDateTime.of(2019, 2, 10, 14, 05 ,10);

        //add
        MoveTracker moveNew = new MoveTracker(idVisitor, idRoom, start, finish);
        Integer idMove = moveDAO.add(moveNew);

        List<MoveTracker> returnedByVisitor = moveDAO.getByVisitorId(idVisitor);
        assertTrue(returnedByVisitor.size() > 0);
        assertTrue(returnedByVisitor.contains(moveNew));

        LocalDateTime startNew = LocalDateTime.of(2020, 2, 10, 14, 05 ,00);
        LocalDateTime finishNew = LocalDateTime.of(2020, 2, 10, 14, 05 ,10);;
        MoveTracker update = new MoveTracker(idMove, idVisitor, idRoom, startNew, finishNew);

        Integer returnedId = moveDAO.update(update);
        assertEquals(returnedId, idMove);

        List<MoveTracker> returnedByIdRoom = moveDAO.getByRoomId(idRoom);
        assertTrue(returnedByIdRoom.size() > 0);
        assertTrue(returnedByIdRoom.contains(moveNew));

        assertEquals(returnedByIdRoom.get(0).getTimeStart(), startNew);
        assertEquals(returnedByIdRoom.get(0).getTimeFinish(), finishNew);

        boolean isMoveDeleted = moveDAO.deleteById(idMove);
        assertTrue(isMoveDeleted);
        boolean isVisitorDeleted = visitorDAO.deleteById(idVisitor);
        assertTrue(isVisitorDeleted);
        boolean isRoomDeleted = roomDAO.removeById(idRoom);
        assertTrue(isRoomDeleted);
        boolean isFloorDeleted = floorDAO.removeById(idFloor);
        assertTrue(isFloorDeleted);
        boolean isBuildingDeleted = buildingDAO.deleteById(idBuilding);
        assertTrue(isBuildingDeleted);
    }
}
