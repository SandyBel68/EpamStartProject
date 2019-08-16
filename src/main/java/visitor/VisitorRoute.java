package visitor;

import floor.Floor;
import room.Room;
import room.RoomDAO;
import room.RoomDAOImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static visitor.GetVisitorLocation.getVisitorLocation;

public class VisitorRoute {
    private static Long tempHumanLocationX1;
    private static Long tempHumanLocationY1;
    private static List<Long> routX = new ArrayList<>();
    private static List<Long> routY = new ArrayList<>();
    private static Floor tempFloor;
    private static int visitorStep = 153;
    private static RoomDAO roomDAO = RoomDAOImpl.getInstance();
    private static List<Room> rooms;

    static {
        try {
            rooms = roomDAO.getAllRooms();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void routeGenerator(int n){
        Long tempX = tempHumanLocationX1;
        Long tempY = tempHumanLocationY1;
        for (int i = 0; i < n; i++) {
            tempX = Long.parseLong(tempFloor.getMaxXSize())<(tempHumanLocationX1+visitorStep) ? tempHumanLocationX1-visitorStep : tempHumanLocationX1+visitorStep;
            tempHumanLocationX1 = tempX;
            routX.add(tempHumanLocationX1);

            tempY = Long.parseLong(tempFloor.getMaxYSize())<(tempHumanLocationY1+visitorStep) ? tempHumanLocationY1-visitorStep : tempHumanLocationY1+visitorStep;
            tempHumanLocationY1 = tempY;
            routY.add(tempHumanLocationY1);
        }
    }

    public static void main(String[] args) {
        tempHumanLocationX1 = 10L;
        tempHumanLocationY1 = 40L;
        tempFloor = new Floor(1, 1, "1300", "600");
        routeGenerator(20);
        for (int i = 0; i < routY.size(); i++) {
            System.out.println(routX.get(i) + " " + routY.get(i) + " roomId: " + getVisitorLocation(rooms, routX.get(i), routY.get(i)));

        }

    }

}
