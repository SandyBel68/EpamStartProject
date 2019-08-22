package common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportDocument {
    private Integer numberFloor;
    private String address;
    private String visitorName;
    private Integer numberRoom;
    private LocalDateTime start;
    private LocalDateTime finish;
}
