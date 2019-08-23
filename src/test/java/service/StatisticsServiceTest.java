package service;

import org.junit.jupiter.api.Test;
import common.ReportDocument;
import service.StatisticsService;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.util.List;

public class StatisticsServiceTest {
    private static StatisticsService statisticsService = StatisticsService.getInstance();

    @Test
    public void getListByVisitorReportTest() throws SQLException {
        String name = "John Bell";
        List<ReportDocument> list =  statisticsService.getListByVisitorReport(name);
        assertTrue(list.size() > 0);
    }

    @Test
    public void getListByRoomReportTest() throws SQLException {
        Integer number = 101;
        List<ReportDocument> list =  statisticsService.getListByRoomReport(number);
        assertTrue(list.size() > 0);
    }
}
