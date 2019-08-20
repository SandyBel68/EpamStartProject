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
import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

import static visitor.GetVisitorLocationService.getVisitorLocation;

public class WriterToMoveTracker {
    private final DataSource DATASOURCE;
    private Visitor visitor;
    private static VisitorDAO visitorDAO = VisitorDAOImpl.getInstance();
    private static MoveTrackerDAO moveDAO = MoveTrackerDAOImpl.getInstance();
    private static RoomDAO roomDAO = RoomDAOImpl.getInstance();
    private static FloorDAO floorDAO = FloorDAOImpl.getInstance();
    private static List<Room> rooms;


    public WriterToMoveTracker(DataSource datasource) {
        DATASOURCE = datasource;
    }

    public void addVisitorInMoveTracker(Visitor visitor) throws SQLException {
        VisitorRouteService visitorRouteService = new VisitorRouteService();
        List<Visitor> visitors = visitorDAO.getAll();
        for (Visitor v: visitors
        ) {
            Integer steps = 100;
            visitorRouteService.routeGenerator(steps, v);
            VisitorRouteService.MyThread thread = new VisitorRouteService.MyThread();
            thread.start();

            for (int i = 0; i < steps; i++) {
                long tempX = VisitorRouteService.routX.get(i);
                long tempY = VisitorRouteService.routY.get(i);
                LocalDateTime tempStart = VisitorRouteService.start.get(i);
                LocalDateTime tempFinish = VisitorRouteService.finish.get(i);
                Integer roomId = getVisitorLocation(rooms, tempX, tempY);
                moveDAO.add(new MoveTracker(v.getIdVisitor(), roomId, tempStart, tempFinish));
            }
        }
    }


}
