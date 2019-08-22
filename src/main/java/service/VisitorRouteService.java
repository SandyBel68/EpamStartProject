package service;

import dao.FloorDAO;
import dao.RoomDAO;
import dao.impl.FloorDAOImpl;
import dao.impl.MoveTrackerDAOImpl;
import dao.impl.RoomDAOImpl;
import entities.Floor;
import entities.Room;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static service.GetVisitorLocationService.getVisitorLocation;

public class VisitorRouteService {
    private static Long tempHumanLocationX1 = (long) (Math.random() * 600);
    private static Long tempHumanLocationY1 = (long) (Math.random() * 600);
    public static MoveTrackerDAOImpl instance = MoveTrackerDAOImpl.getInstance();
    static List<Long> routX = new ArrayList<>();
    static List<Long> routY = new ArrayList<>();
    static List<LocalDateTime> start = new ArrayList<>();
    static List<LocalDateTime> finish = new ArrayList<>();
    private static FloorDAO floorDAO = FloorDAOImpl.getInstance();
    private static int visitorStep = 100 + (int) (Math.random() * 50);
    private static RoomDAO roomDAO = RoomDAOImpl.getInstance();
    private static List<Room> rooms;
    private static Floor tempFloor;


    public VisitorRouteService(Floor floor) {
        this.tempFloor = floor;
    }

    public static void myThread() {
        try {
            Integer floorId = tempFloor.getIdFloor();
            rooms = roomDAO.getAllByFloor(floorId);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime temp = LocalDateTime.now();
        System.out.println("Temp human location: " + tempHumanLocationX1 + " " + tempHumanLocationY1 + " Room number: " + getVisitorLocation(rooms, tempHumanLocationX1, tempHumanLocationY1)
                + " time: " + dateTimeFormatter.format(temp));

        for (int i = 0; i < 10; i++) {
            Integer roomId = getVisitorLocation(rooms, routX.get(i), routY.get(i));
            System.out.println(routX.get(i) + " " + routY.get(i) + " Room number: " + roomId + " time: " + start + " " + finish);

        }
        System.out.println(start.toString());
        System.out.println(finish.toString());
    }

    public static void routeGenerator(int n, String name, Integer numberFloor, String address) {
        for (int i = 0; i < n; i++) {
            int vector = (int) (Math.random() * 4);
            int temp = vector % 2;
            LocalDateTime tempTime = LocalDateTime.now();
            start.add(tempTime);
            finish.add(tempTime);
            switch (temp) {
                case 0:
                    if (tempHumanLocationX1 + visitorStep < Long.parseLong(tempFloor.getMaxXSize()) && (vector == 2)) {
                        tempHumanLocationX1 += visitorStep;
                        routX.add(tempHumanLocationX1);
                        routY.add(tempHumanLocationY1);

                    } else if ((tempHumanLocationX1 - visitorStep >= 0) && vector == 4) {
                        tempHumanLocationX1 -= visitorStep;
                        routX.add(tempHumanLocationX1);
                        routY.add(tempHumanLocationY1);
                    } else {
                        routX.add(tempHumanLocationX1);
                        routY.add(tempHumanLocationY1);
                    }
                    tempTime = tempTime.plusMinutes(3);
                    start.add(tempTime);
                    finish.add(tempTime.plusMinutes(3));
                    break;

                case 1:

                    if (tempHumanLocationY1 + visitorStep < Long.parseLong(tempFloor.getMaxYSize()) && vector == 1) {
                        tempHumanLocationY1 += visitorStep;
                        routY.add(tempHumanLocationY1);
                        routX.add(tempHumanLocationX1);
                    } else if (tempHumanLocationY1 - visitorStep >= 0 && vector == 3) {
                        tempHumanLocationY1 -= visitorStep;
                        routY.add(tempHumanLocationY1);
                        routX.add(tempHumanLocationX1);
                    } else {
                        routX.add(tempHumanLocationX1);
                        routY.add(tempHumanLocationY1);
                    }
                    tempTime = tempTime.plusMinutes(3);
                    start.add(tempTime);
                    finish.add(tempTime.plusMinutes(3));
                    break;
            }

        }
    }
}




