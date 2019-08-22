package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @EqualsAndHashCode.Exclude
    private Integer idRoom;
    private Integer numberRoom;
    private Integer idFloor;
    private String x1;
    private String y1;
    private String x2;
    private String y2;

    public Room(Integer numberRoom, Integer idFloor, String x1, String y1, String x2, String y2) {
        this.numberRoom = numberRoom;
        this.idFloor = idFloor;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
}

