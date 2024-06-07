package projeto.api.utils.domain.list;

import projeto.api.utils.domain.user.UserDataDetails;

import java.util.List;

public record ListDataDetails(Long id, String name, List<Item> items, UserDataDetails user) {
    public ListDataDetails(ShoppingList shoppingList) {
        this(shoppingList.getId(),shoppingList.getName(), shoppingList.getItems(),
                new UserDataDetails(shoppingList.getUser()));
    }
}
