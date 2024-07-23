package projeto.api.utils.domain.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import projeto.api.utils.domain.list.validations.ShoppingListValidations;
import projeto.api.utils.domain.user.User;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShoppingListServiceTest {

    @Mock
    private ShoppingListRepository repository;

    @Mock
    private List<ShoppingListValidations> validations;

    private ShoppingListService service;

    @BeforeEach
    void setup() {
        this.service = new ShoppingListService(repository,validations);
    }

    @Test
    void createTest() {
        List<Item> listItem = List.of(new Item("Item test","10 und"));
        ListRegisterData data = new ListRegisterData("list test",listItem);
        User user = new User(1L,"test","test@emai.com","123");

        when(repository.save(any())).thenReturn(new ShoppingList(data,user));

        ListDataDetails details = service.create(data,user);

        assertThat(details.name()).isEqualTo(data.name());
        assertThat(details.user().id()).isEqualTo(user.getId());
    }

    @Test
    void findListByIdAndUserTest() {
        Long id = 1L;
        User user = new User(1L,"test","test@emai.com","123");
        List<Item> listItem = List.of(new Item("Item test","10 und"));
        ShoppingList shoppingList = new ShoppingList(1L,"test name",user,listItem,false);

        when(repository.findByIdAndUser(anyLong(),any())).thenReturn(Optional.of(shoppingList));

        ListDataDetails details = service.findListByIdAndUser(id,user);

        assertThat(details.name()).isEqualTo(shoppingList.getName());
        assertThat(details.user().id()).isEqualTo(user.getId());
    }
}