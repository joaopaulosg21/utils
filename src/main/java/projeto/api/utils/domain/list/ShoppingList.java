package projeto.api.utils.domain.list;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import projeto.api.utils.domain.user.User;

import java.time.LocalDate;
import java.util.List;

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
