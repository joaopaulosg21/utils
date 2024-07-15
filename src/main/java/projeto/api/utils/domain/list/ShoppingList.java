package projeto.api.utils.domain.list;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import projeto.api.utils.domain.user.User;

@Entity
@Table(name = "shopping_list")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class ShoppingList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(columnDefinition = "user_id",referencedColumnName = "id")
    private User user;

    @ElementCollection
    private List<Item> items;

    public ShoppingList(ListRegisterData data, User user) {
        this.name = data.name();
        this.items = data.items();
        this.user = user;
    }
}
