package projeto.api.utils.domain.list;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Item {

    private String name;

    private String quantity;
}
