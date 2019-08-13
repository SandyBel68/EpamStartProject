package movetracker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoveTracker {
    private Integer idMove;
    private Integer idBuilding;
    private Integer idVisitor;
    private Integer idRoom;
    @EqualsAndHashCode.Exclude
    private LocalDateTime timeStart;
    @EqualsAndHashCode.Exclude
    private LocalDateTime timeFinish;

}
