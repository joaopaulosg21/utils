package projeto.api.utils.domain.list;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import projeto.api.utils.domain.list.validations.ShoppingListValidations;
import projeto.api.utils.domain.user.User;
import projeto.api.utils.domain.user.UserDataDetails;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShoppingListService {

    private final ShoppingListRepository repository;

    private final List<ShoppingListValidations> validations;

    public ListDataDetails create(ListRegisterData data, User user) {
        validations.forEach(v -> v.valid(data));
        ShoppingList shoppingList = repository.save(new ShoppingList(data,user));

        return new ListDataDetails(shoppingList.getId(),shoppingList.getName(), shoppingList.getItems(),
                new UserDataDetails(shoppingList.getUser()));
    }
}
