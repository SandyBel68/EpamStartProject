package building;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Building {
    @EqualsAndHashCode.Exclude
    private Integer idBuilding;
    private String address;

    public Building(String address) {
        this.address = address;
    }
}
