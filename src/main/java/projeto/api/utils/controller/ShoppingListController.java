package projeto.api.utils.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import projeto.api.utils.domain.list.ListRegisterData;
import projeto.api.utils.domain.list.ShoppingListService;
import projeto.api.utils.domain.user.User;

import java.net.URI;

@RestController
@RequestMapping("/list")
@RequiredArgsConstructor
public class ShoppingListController {

    private final ShoppingListService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid ListRegisterData data, @AuthenticationPrincipal User user,
                                    UriComponentsBuilder builder) {
        var details = service.create(data,user);
        URI uri = builder.path("/list/{id}").buildAndExpand(details.id()).toUri();
        return ResponseEntity.created(uri).body(details);
    }
}
