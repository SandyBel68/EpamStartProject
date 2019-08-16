package visitor;

import org.w3c.dom.ls.LSOutput;
import room.Room;

import java.util.List;

public class GetVisitorLocationService {


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
