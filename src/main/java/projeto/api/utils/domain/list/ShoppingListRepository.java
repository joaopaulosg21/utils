package projeto.api.utils.domain.list;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingListRepository extends JpaRepository<ShoppingList,Long> {
}
