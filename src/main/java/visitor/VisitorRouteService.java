package visitor;

import floor.Floor;
import floor.FloorDAO;
import floor.FloorDAOImpl;
import movetracker.MoveTracker;
import movetracker.MoveTrackerDAOImpl;
import org.w3c.dom.ls.LSOutput;
import room.Room;
import room.RoomDAO;
import room.RoomDAOImpl;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class VisitorRouteService {
    private static Long tempHumanLocationX1 = (long)(Math.random()*600);
    private static Long tempHumanLocationY1 = (long)(Math.random()*600);
    public static MoveTrackerDAOImpl instance = MoveTrackerDAOImpl.getInstance();
    static List<Long> routX = new ArrayList<>();
    static List<Long> routY = new ArrayList<>();
    static List<LocalDateTime> start = new ArrayList<>();
    static List<LocalDateTime> finish = new ArrayList<>();
    private static FloorDAO floorDAO = FloorDAOImpl.getInstance();
    private static int visitorStep = 100+(int)(Math.random()*50);
    private static RoomDAO roomDAO = RoomDAOImpl.getInstance();
    private static List<Room> rooms;
    private static Floor tempFloor;
    static {
        try {
            tempFloor = floorDAO.getById(29);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static class MyThread extends Thread{
        public void run(){
            try {
                rooms = roomDAO.getAllByFloor(29);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime temp = LocalDateTime.now();
            System.out.println(tempHumanLocationX1 + " " + tempHumanLocationY1 + " Room number: " + getVisitorLocation(rooms, tempHumanLocationX1, tempHumanLocationY1)
                    + " time: " + dateTimeFormatter.format(temp));
            start.add(temp);
            finish.add(temp);
            try {
                Thread.sleep(1 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < 10; i++) {
                    Integer roomId = getVisitorLocation(rooms, routX.get(i), routY.get(i));
                    System.out.println(routX.get(i) + " " + routY.get(i) + " Room number: " + roomId + " time: " + dateTimeFormatter.format(LocalDateTime.now()));
                    start.add(LocalDateTime.now());
                    try {
                        Thread.sleep(1 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish.add(LocalDateTime.now());
            }
            System.out.println(start.toString());
            System.out.println(finish.toString());
        }
    }


    static {
        try {
            rooms = roomDAO.getAllByFloor(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void routeGenerator(int n, Visitor visitor){
        for (int i = 0; i < n; i++) {
            int vector = (int)(Math.random()*4);
            int temp = vector % 2;
            switch (temp){
                case 0:
                    if(tempHumanLocationX1+visitorStep < Long.parseLong(tempFloor.getMaxXSize()) && (vector == 2)){
                        tempHumanLocationX1 += visitorStep;
                        routX.add(tempHumanLocationX1);
                        routY.add(tempHumanLocationY1);
                    }
                    else if ((tempHumanLocationX1-visitorStep >= 0) && vector == 4){
                        tempHumanLocationX1 -= visitorStep;
                        routX.add(tempHumanLocationX1);
                        routY.add(tempHumanLocationY1);
                    }
                    else{
                        routX.add(tempHumanLocationX1);
                        routY.add(tempHumanLocationY1);
                    }
                    break;

                case  1:

                    if(tempHumanLocationY1+visitorStep < Long.parseLong(tempFloor.getMaxYSize()) && vector == 1){
                        tempHumanLocationY1 += visitorStep;
                        routY.add(tempHumanLocationY1);
                        routX.add(tempHumanLocationX1);
                    }
                    else if (tempHumanLocationY1-visitorStep >= 0 && vector == 3){
                        tempHumanLocationY1 -= visitorStep;
                        routY.add(tempHumanLocationY1);
                        routX.add(tempHumanLocationX1);
                    }
                    else{
                        routX.add(tempHumanLocationX1);
                        routY.add(tempHumanLocationY1);
                    }
                    break;
            }

        }
    }


    public static Integer getVisitorLocation(List<Room> roomList, long tempX1, long tempY1) {
        return isContain(roomList, tempX1, tempY1);
    }


    public static Integer isContain(List<Room> roomList, long humanLocationX1, long humanLocationY1) {
        for (Room r : roomList) {
            try {
                long tempX1 = Long.parseLong(r.getX1());
                long tempY1 = Long.parseLong(r.getY1());
                long tempX2 = Long.parseLong(r.getX2());
                long tempY2 = Long.parseLong(r.getY2());

                if ((humanLocationX1 >= tempX1 && humanLocationY1 >= tempY1) && (humanLocationX1 <= tempX2 && humanLocationY1 <= tempY2)) {
                    return r.getNumberRoom();
                }
            } catch (NumberFormatException e) {
                System.out.println(e + "11");
                e.printStackTrace();

            }
        }
        return null;
    }
}
