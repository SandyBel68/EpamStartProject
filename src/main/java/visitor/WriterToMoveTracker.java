package visitor;

import floor.FloorDAO;
import floor.FloorDAOImpl;
import movetracker.MoveTracker;
import movetracker.MoveTrackerDAO;
import movetracker.MoveTrackerDAOImpl;
import room.Room;
import room.RoomDAO;
import room.RoomDAOImpl;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static visitor.VisitorRouteService.getVisitorLocation;

public class WriterToMoveTracker {
    private Visitor visitor;
    private static VisitorDAO visitorDAO = VisitorDAOImpl.getInstance();
    private static MoveTrackerDAO moveDAO = MoveTrackerDAOImpl.getInstance();
    private static RoomDAO roomDAO = RoomDAOImpl.getInstance();
    private static FloorDAO floorDAO = FloorDAOImpl.getInstance();



    public void addVisitorInMoveTracker(Visitor visitor) throws SQLException {
        VisitorRouteService visitorRouteService = new VisitorRouteService();


        Integer steps = 10;
        visitorRouteService.routeGenerator(steps, visitor);
        VisitorRouteService.MyThread thread = new VisitorRouteService.MyThread();
        thread.start();

        for (int i = 0; i < steps; i++) {
            long tempX = VisitorRouteService.routX.get(i);
            long tempY = VisitorRouteService.routY.get(i);
            LocalDateTime tempStart = VisitorRouteService.start.get(i);
            LocalDateTime tempFinish = VisitorRouteService.finish.get(i);
            Integer roomId = getVisitorLocation(roomDAO.getAllByFloor(29), tempX, tempY);
            System.out.println(visitor.getIdVisitor() + " " + roomId + " " + tempStart + " " + tempFinish);
            moveDAO.add(new MoveTracker(visitor.getIdVisitor(), roomId, tempStart, tempFinish));
        }

    }


}
