package projeto.api.utils.domain.list;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projeto.api.utils.domain.list.validations.ShoppingListValidations;
import projeto.api.utils.domain.user.User;
import projeto.api.utils.infra.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShoppingListService {

    private final ShoppingListRepository repository;

    private final List<ShoppingListValidations> validations;

    public ListDataDetails create(ListRegisterData data, User user) {
        validations.forEach(v -> v.valid(data));
        ShoppingList shoppingList = repository.save(new ShoppingList(data,user));

        return new ListDataDetails(shoppingList);
    }

    public ListDataDetails findListByIdAndUser(Long id, User user) {
        Optional<ShoppingList> optionalShoppingList = repository.findByIdAndUser(id,user);

        if(optionalShoppingList.isEmpty()) {
            throw new NotFoundException("Shopping list with this id not found");
        }
        ShoppingList shoppingList = optionalShoppingList.get();
        return new ListDataDetails(shoppingList);
    }

    public List<ListDataDetails> findAllByUser(User user) {
        return repository.findAllByUser(user).stream().map(ListDataDetails::new).toList();
    }

}
