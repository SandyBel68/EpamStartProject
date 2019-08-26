package service;

import dao.BuildingDAO;
import dao.FloorDAO;
import dao.RoomDAO;
import dao.impl.BuildingDAOImpl;
import dao.impl.FloorDAOImpl;
import dao.impl.RoomDAOImpl;
import entities.Building;
import entities.Floor;
import entities.Room;

import java.sql.SQLException;
import java.util.List;

import static service.GetVisitorLocationService.isContain;

public class RoomService {
    private static RoomService instance = null;
    private RoomDAO roomDAO;
    private FloorDAO floorDAO;
    private BuildingDAO buildingDAO;

    private RoomService(RoomDAO roomDAO, FloorDAO floorDAO, BuildingDAO buildingDAO) {
        this.floorDAO = floorDAO;
        this.roomDAO = roomDAO;
    }

    synchronized public static RoomService getInstance() {
        if (instance == null) {
            instance = new RoomService(RoomDAOImpl.getInstance(), FloorDAOImpl.getInstance(), BuildingDAOImpl.getInstance());
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

    public Room addRoomOnTheFloor(Integer numberRoom, String x1, String y1, String x2, String y2, Integer numberFloor, String address) throws SQLException {
        Building building = buildingDAO.getByAddress(address);
        Integer idBuilding = building.getIdBuilding();

        Floor floor = floorDAO.getByBuildingAndNumber(idBuilding, numberFloor);
        Integer idFloor = floor.getIdFloor();

        Room newRoom = new Room(numberRoom, idFloor, x1, y1, x2, y2);

        Long maxFloorSizeX = Long.valueOf(floor.getMaxXSize());
        Long maxFloorSizeY = Long.valueOf(floor.getMaxYSize());

        Integer leftDownPointX = Integer.parseInt(x1);
        Integer leftDownPointY = Integer.parseInt(y1);
        Integer rightUpperPointX = Integer.parseInt(x2);
        Integer rightUpperPointY = Integer.parseInt(y2);
        Integer leftUpperPointX = leftDownPointX;
        Integer leftUpperPointY = rightUpperPointY;
        Integer rightDownPointX = rightUpperPointX;
        Integer rightDownPointY = leftUpperPointY;

        Integer floorId = floor.getIdFloor();
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
