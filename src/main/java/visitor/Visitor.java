package visitor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Visitor {
    @EqualsAndHashCode.Exclude
    private Integer idVisitor;
    private String visitorName;

    public Visitor(String visitorName) {
        this.visitorName = visitorName;
    }
}
