package movetracker;

import org.junit.jupiter.api.Test;
import report.ReportDocument;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

public class StatisticsServiceTest {
    private static StatisticsService statisticsService = StatisticsService.getInstance();

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
}
