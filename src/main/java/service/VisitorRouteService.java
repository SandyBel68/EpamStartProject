package service;

import entities.Floor;
import entities.Room;
import dao.RoomDAO;
import dao.impl.RoomDAOImpl;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static service.GetVisitorLocationService.getVisitorLocation;

public class VisitorRouteService {
    private static Long tempHumanLocationX1;
    private static Long tempHumanLocationY1;
    static List<Long> routX = new ArrayList<>();
    static List<Long> routY = new ArrayList<>();
    private static Floor tempFloor;
    private static int visitorStep = 100+(int)(Math.random()*50);
    private static RoomDAO roomDAO = RoomDAOImpl.getInstance();
    private static List<Room> rooms;

    public static class MyThread extends Thread{
        public void run(){
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

            System.out.println(tempHumanLocationX1 + " " + tempHumanLocationY1 + " Room number: " + getVisitorLocation(rooms, tempHumanLocationX1, tempHumanLocationY1) + " time: " + dateTimeFormatter.format(LocalDateTime.now()));

            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < 60; i++) {
                if (i % 10 == 0) {
                    System.out.println(routX.get(i) + " " + routY.get(i) + " Room number: " + getVisitorLocation(rooms, routX.get(i), routY.get(i)) + " time: " + dateTimeFormatter.format(LocalDateTime.now()));
                    try {
                        Thread.sleep(5 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    static {
        try {
            rooms = roomDAO.getAllByFloor(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void routeGenerator(int n){
        Long tempX = tempHumanLocationX1;
        Long tempY = tempHumanLocationY1;
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

    public static void main(String[] args) throws InterruptedException {
        tempHumanLocationX1 = (long)(Math.random()*600);
        tempHumanLocationY1 = (long)(Math.random()*300);
        tempFloor = new Floor(1, 1, "600", "1300");
        routeGenerator(60);


        MyThread myThread = new MyThread();
        myThread.start();
    }

}
