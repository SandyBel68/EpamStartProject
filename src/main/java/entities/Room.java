package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    private Integer idRoom;
    private Integer idFloor;
    private String x1;
    private String y1;
    private String x2;
    private String y2;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return Objects.equals(getIdRoom(), room.getIdRoom()) &&
                Objects.equals(getIdFloor(), room.getIdFloor()) &&
                Objects.equals(getX1(), room.getX1()) &&
                Objects.equals(getY1(), room.getY1()) &&
                Objects.equals(getX2(), room.getX2()) &&
                Objects.equals(getY2(), room.getY2());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdRoom(), getIdFloor(), getX1(), getY1(), getX2(), getY2());
    }
}

