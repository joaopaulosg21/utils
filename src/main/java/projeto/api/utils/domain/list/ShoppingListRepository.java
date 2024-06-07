package projeto.api.utils.domain.list;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.api.utils.domain.user.User;

import java.util.Optional;

public interface ShoppingListRepository extends JpaRepository<ShoppingList,Long> {
    Optional<ShoppingList> findByIdAndUser(Long id, User user);
}
