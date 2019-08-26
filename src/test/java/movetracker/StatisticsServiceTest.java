package movetracker;

import common.ReportDocument;
import dao.*;
import dao.impl.*;
import org.junit.jupiter.api.Test;
import service.StatisticsService;
import service.WriterToMoveTrackerService;

import static org.junit.jupiter.api.Assertions.assertTrue;


import java.sql.SQLException;
import java.util.List;

public class StatisticsServiceTest {
    private static StatisticsService statisticsService = StatisticsService.getInstance();
    private static WriterToMoveTrackerService instance = null;
    private static VisitorDAO visitorDAO = VisitorDAOImpl.getInstance();
    private static MoveTrackerDAO moveDAO = MoveTrackerDAOImpl.getInstance();
    private static RoomDAO roomDAO = RoomDAOImpl.getInstance();
    private static FloorDAO floorDAO = FloorDAOImpl.getInstance();
    private static BuildingDAO buildingDAO = BuildingDAOImpl.getInstance();

    @Test
    public void getListByVisitorReportTest() {
        String name = "John Bell";
        List<ReportDocument> list =  statisticsService.getListByVisitorReport(name);
        assertTrue(list.size() > 0);
    }

    @Test
    public void getListByRoomReportTest(){
        Integer number = 101;
        List<ReportDocument> list =  statisticsService.getListByRoomReport(number);
        assertTrue(list.size() > 0);
    }

    @Test

    public void addNewMoveTracker(){
        String name = "John Bell";
        WriterToMoveTrackerService writerToMoveTrackerService = WriterToMoveTrackerService.getInstance();
        writerToMoveTrackerService.addVisitorInMoveTracker(name, 1, "Zastavskaya 22");
    }

}
