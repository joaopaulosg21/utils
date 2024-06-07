package projeto.api.utils.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projeto.api.utils.domain.list.ListRegisterData;
import projeto.api.utils.domain.list.ShoppingList;
import projeto.api.utils.domain.list.ShppingListRepository;
import projeto.api.utils.domain.user.User;

@RestController
@RequestMapping("/list")
@RequiredArgsConstructor
public class ShoppingListController {

    private final ShppingListRepository shppingListRepository;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid ListRegisterData data, @AuthenticationPrincipal User user) {
        ShoppingList shoppingList = new ShoppingList(data,user);
        return ResponseEntity.ok(shppingListRepository.save(shoppingList));
    }
}
