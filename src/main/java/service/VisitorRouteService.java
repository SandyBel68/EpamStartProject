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
    private static Long tempHumanLocationX1;
    private static Long tempHumanLocationY1;
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
    private static int plusMin = 0;


    public VisitorRouteService(Floor floor) {
        this.tempFloor = floor;
    }

    public static void myThread() throws SQLException {
            Integer floorId = tempFloor.getIdFloor();
            rooms = roomDAO.getAllByFloor(floorId);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime temp = LocalDateTime.now();
        System.out.println("Temp human location: " + tempHumanLocationX1 + " " + tempHumanLocationY1 + " Room number: " + getVisitorLocation(rooms, tempHumanLocationX1, tempHumanLocationY1)
                + " time: " + dateTimeFormatter.format(temp));

        for (int i = 0; i < 10; i++) {
            Integer roomId = getVisitorLocation(rooms, routX.get(i), routY.get(i));

        }
        System.out.println(start.toString());
        System.out.println(finish.toString());
        System.out.println(routX);
        System.out.println(routY);
    }

    public static void routeGenerator(int n, String name, Integer numberFloor, String address) {
        tempHumanLocationX1 = (long) (Math.random() * Long.parseLong(tempFloor.getMaxXSize())/2);
        tempHumanLocationY1 = (long) (Math.random() * Long.parseLong(tempFloor.getMaxYSize())/2);
        LocalDateTime tempTimeStart = LocalDateTime.now();
        LocalDateTime tempTimeFinish = tempTimeStart.plusMinutes(plusMin);
        routY.clear();
        routX.clear();
        start.add(tempTimeStart);
        finish.add(tempTimeFinish);
        for (int i = 0; i < n; i++) {
            int vector = (int) (Math.random() * 4);
            int temp = vector % 2;
            plusMin += 3;
            switch (temp) {
                case 0:
                    tempHumanLocationX1 = (tempHumanLocationX1 + visitorStep) % Long.parseLong(tempFloor.getMaxXSize());
                    tempHumanLocationY1 = (tempHumanLocationY1 + visitorStep) % Long.parseLong(tempFloor.getMaxYSize());
                    routX.add(tempHumanLocationX1);
                    routY.add(tempHumanLocationY1);
                    tempTimeStart = tempTimeStart.plusMinutes(plusMin);
                    tempTimeFinish = tempTimeStart.plusMinutes(plusMin);
                    start.add(tempTimeStart);
                    finish.add(tempTimeFinish);
                    break;

                case 1:
                    tempHumanLocationX1 = Math.abs(tempHumanLocationX1 - visitorStep) % Long.parseLong(tempFloor.getMaxXSize());
                    tempHumanLocationY1 = Math.abs(tempHumanLocationY1 - visitorStep) % Long.parseLong(tempFloor.getMaxYSize());
                    if (tempHumanLocationY1 + visitorStep < Long.parseLong(tempFloor.getMaxYSize()) && vector == 1) {
                        tempHumanLocationY1 += visitorStep;
                    } else if (tempHumanLocationY1 - visitorStep >= 0 && vector == 3) {
                        tempHumanLocationY1 -= visitorStep;
                    }
                    routX.add(tempHumanLocationX1);
                    routY.add(tempHumanLocationY1);
                    tempTimeStart = tempTimeStart.plusMinutes(plusMin);
                    tempTimeFinish = tempTimeStart.plusMinutes(plusMin);
                    start.add(tempTimeStart);
                    finish.add(tempTimeFinish);
                    break;
            }
        }

    }
}





