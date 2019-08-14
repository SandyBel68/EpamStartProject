package visitor;

import floor.Floor;

import java.util.ArrayList;
import java.util.List;

public class VisitorRoute {
    private static Long tempHumanLocationX1;
    private static Long tempHumanLocationY1;
    private static List<Long> routX = new ArrayList<>();
    private static List<Long> routY = new ArrayList<>();
    private static Floor tempFloor;
    private static int visitorStep = 53;

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
        routeGenerator(3);
        for (int i = 0; i < routY.size(); i++) {
            System.out.println(routX.get(i) + " " + routY.get(i));
        }

    }

}
