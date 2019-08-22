package service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import entities.Room;
import dao.RoomDAO;
import dao.impl.RoomDAOImpl;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class VisitorLocationTest {
    private static RoomDAO roomDAO;

    @BeforeAll
    public static void init() {
        roomDAO = RoomDAOImpl.getInstance();
    }


    @Test
    public void getVisitorLocation1() throws SQLException {
        long x1 = 200;
        long y1 = 200;
        List<Room> rooms = roomDAO.getAllByFloor(1);
        assertTrue(getVisitorLocation(rooms, x1, y1) == 101);
    }
    @Test
    public void getVisitorLocation2() throws SQLException {
        long x1 = 1200;
        long y1 = 200;
        List<Room> rooms = roomDAO.getAllByFloor(1);
        assertTrue(getVisitorLocation(rooms, x1, y1) == 103);
    }

    @Test
    public void getVisitorLocation3() throws SQLException {
        long x1 = 1300;
        long y1 = 700;
        List<Room> rooms = roomDAO.getAllByFloor(1);
        assertTrue(getVisitorLocation(rooms, x1, y1) == null);
    }





    ////////////////////////////////////////
    public static Integer getVisitorLocation(List<Room> roomList, long tempX1, long tempY1){
        return isContain(roomList, tempX1, tempY1);
    }

    public static Integer isContain(List<Room> roomList, long humanLocationX1, long humanLocationY1){
        for (Room r: roomList) {
            try{
                long tempX1 = Long.parseLong(r.getX1());
                long tempY1 = Long.parseLong(r.getY1());
                long tempX2 = Long.parseLong(r.getX2());
                long tempY2 = Long.parseLong(r.getY2());

                if ((humanLocationX1 >= tempX1 && humanLocationY1 >= tempY1) && (humanLocationX1 <= tempX2 && humanLocationY1 <= tempY2)){
                    return r.getIdRoom();
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
