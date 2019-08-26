package service;

import dao.*;
import dao.impl.*;
import entities.*;

import java.sql.SQLException;
import java.time.LocalDateTime;

import static service.GetVisitorLocationService.getVisitorLocation;
import static service.VisitorRouteService.*;


public class WriterToMoveTrackerService {
    private static VisitorDAO visitorDAO;
    private static MoveTrackerDAO moveDAO;
    private static RoomDAO roomDAO;
    private static FloorDAO floorDAO;
    private static BuildingDAO buildingDAO;
    private static WriterToMoveTrackerService instance = null;

    public WriterToMoveTrackerService(VisitorDAO visitorDAO, MoveTrackerDAO moveTrackerDAO, RoomDAO roomDAO, FloorDAO floorDAO, BuildingDAO buildingDAO) {
        this.visitorDAO = visitorDAO;
        this.moveDAO = moveTrackerDAO;
        this.roomDAO = roomDAO;
        this.floorDAO = floorDAO;
        this.buildingDAO = buildingDAO;
    }

    synchronized public static WriterToMoveTrackerService getInstance() {
        if (instance == null) {
            instance = new WriterToMoveTrackerService(VisitorDAOImpl.getInstance(), MoveTrackerDAOImpl.getInstance(), RoomDAOImpl.getInstance(), FloorDAOImpl.getInstance(), BuildingDAOImpl.getInstance());
        }
        return instance;
    }

    public static void addVisitorInMoveTracker(String visitorName, Integer numberFloor, String address) {
        try {

            Building building = buildingDAO.getByAddress(address);
            System.out.println(building);
            Integer buildingId = building.getIdBuilding();

            Visitor visitor = visitorDAO.getByName(visitorName);
            Integer visitorId = visitor.getIdVisitor();

            Floor floor = floorDAO.getByBuildingAndNumber(buildingId, numberFloor);
            Integer floorId = floor.getIdFloor();

            VisitorRouteService visitorRouteService = new VisitorRouteService(floor);

            Integer steps = 10;
            visitorRouteService.routeGenerator(steps, visitorName, numberFloor, address);
            myThread();
//        System.out.println(routX);
//        System.out.println(routY);
//        System.out.println("start time  " + start);
//        System.out.println("finish time   " + finish);
            for (int i = 0; i < steps; i++) {

                long tempX = VisitorRouteService.routX.get(i);
                long tempY = VisitorRouteService.routY.get(i);
                LocalDateTime tempStart = null;
                LocalDateTime tempFinish = null;

                if(start.get(i) != null) {
                    tempStart = VisitorRouteService.start.get(i);
                }
                if(finish.get(i) != null) {
                    tempFinish = VisitorRouteService.finish.get(i);
                }

                Integer roomNumber = getVisitorLocation(roomDAO.getAllByFloor(floorId), tempX, tempY);
                Room room = roomDAO.getByNumber(roomNumber);
                Integer roomId = room.getIdRoom();


                System.out.println(visitor.getIdVisitor() + " " + roomId + " " + tempStart + " " + tempFinish);
                moveDAO.add(new MoveTracker(visitorId, roomId, tempStart, tempFinish));
            }
        }
        catch (SQLException e){
            System.err.println(e);
        }
    }
}
