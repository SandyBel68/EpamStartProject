package room;

import floor.Floor;
import floor.FloorDAO;
import floor.FloorDAOImpl;

import java.net.Inet4Address;
import java.sql.SQLException;
import java.util.List;

import static visitor.GetVisitorLocationService.isContain;


public class RoomService {
    private static RoomService instance = null;
    private static RoomDAO roomDAO;
    private static FloorDAO floorDAO;

    private RoomService(RoomDAO roomDAO, FloorDAO floorDAO) {
        this.floorDAO = floorDAO;
        this.roomDAO = roomDAO;
    }

    synchronized public static RoomService getInstance() {
        if (instance == null) {
            instance = new RoomService(RoomDAOImpl.getInstance(), FloorDAOImpl.getInstance());
        }
        return instance;
    }

    public List<Room> getAllRooms() {
        List<Room> roomList = null;
        try {
            roomList = roomDAO.getAll();
        } catch (SQLException e) {
            System.err.println(e);
            //TODO logging
        }
        return roomList;
    }

    public static Room addRoomOnTheFloor(Room newRoom, Floor floor) throws SQLException {
        Long maxFloorSizeX = Long.valueOf(floor.getMaxXSize());
        Long maxFloorSizeY = Long.valueOf(floor.getMaxYSize());

        System.out.println(floor);

        Integer leftDownPointX = Integer.parseInt(newRoom.getX1());
        Integer leftDownPointY = Integer.parseInt(newRoom.getY1());
        Integer rightUpperPointX = Integer.parseInt(newRoom.getX2());
        Integer rightUpperPointY = Integer.parseInt(newRoom.getY2());
        Integer leftUpperPointX = leftDownPointX;
        Integer leftUpperPointY = rightUpperPointY;
        Integer rightDownPointX = rightUpperPointX;
        Integer rightDownPointY = leftUpperPointY;

        Integer floorId = floor.getIdFloor();
        System.out.println(roomDAO);
        List<Room> roomList = roomDAO.getAllByFloor(floorId);


        if ((leftDownPointX < 0 || leftDownPointX > maxFloorSizeX) || (leftUpperPointX < 0 || leftUpperPointX > maxFloorSizeX) ||
                (rightDownPointX < 0 || rightDownPointX > maxFloorSizeX) ||  (rightUpperPointX < 0 || rightUpperPointX > maxFloorSizeX)){
            System.out.println("Invalid param");
        }
        else if ((leftDownPointY < 0 || leftDownPointY > maxFloorSizeY) || (leftUpperPointY < 0 || leftUpperPointY > maxFloorSizeY) ||
                (rightDownPointY < 0 || rightDownPointY > maxFloorSizeY) ||  (rightUpperPointY < 0 || rightUpperPointY > maxFloorSizeY)){
            System.out.println("Invalid param");
        }
        else if((isContain(roomList, leftDownPointX, leftDownPointY) != null) || (isContain(roomList, rightDownPointX, rightDownPointY) != null) ||
                (isContain(roomList, leftUpperPointX, leftUpperPointY) != null) || (isContain(roomList, rightUpperPointX, rightUpperPointY) != null))
        {
            roomDAO.add(newRoom);
            return newRoom;
        }
        return null;
    }
}
