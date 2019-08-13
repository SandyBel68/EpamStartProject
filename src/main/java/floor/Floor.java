package floor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Floor {
    private Integer idFloor;
    private Integer idBuilding;
    private String maxXSize;
    private String maxYSize;
}

