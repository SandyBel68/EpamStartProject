package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MoveTracker {
    private Integer idMove;
    private Integer idVisitor;
    private Integer idRoom;
    private LocalDateTime timeStart;
    private LocalDateTime timeFinish;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MoveTracker)) return false;
        MoveTracker that = (MoveTracker) o;
        return Objects.equals(getIdMove(), that.getIdMove()) &&
                Objects.equals(getIdVisitor(), that.getIdVisitor()) &&
                Objects.equals(getIdRoom(), that.getIdRoom());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdMove(), getIdVisitor(), getIdRoom());
    }
}
