package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoveTracker {
    @EqualsAndHashCode.Exclude
    private Integer idMove;
    private Integer idBuilding;
    private Integer idVisitor;
    private Integer idRoom;
    @EqualsAndHashCode.Exclude
    private LocalDateTime timeStart;
    @EqualsAndHashCode.Exclude
    private LocalDateTime timeFinish;

    public MoveTracker(Integer idBuilding, Integer idVisitor, Integer idRoom, LocalDateTime timeStart, LocalDateTime timeFinish) {
        this.idBuilding = idBuilding;
        this.idVisitor = idVisitor;
        this.idRoom = idRoom;
        this.timeStart = timeStart;
        this.timeFinish = timeFinish;
    }
}
