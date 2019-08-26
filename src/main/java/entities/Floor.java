package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Floor {
    @EqualsAndHashCode.Exclude
    private Integer idFloor;
    private Integer numberFloor;
    private Integer idBuilding;
    private String maxYSize;
    private String maxXSize;

    public Floor(Integer numberFloor, Integer idBuilding, String maxYSize, String maxXSize) {
        this.numberFloor = numberFloor;
        this.idBuilding = idBuilding;
        this.maxYSize = maxYSize;
        this.maxXSize = maxXSize;
    }
}


