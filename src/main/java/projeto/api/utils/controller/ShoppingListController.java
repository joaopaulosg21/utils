package projeto.api.utils.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import projeto.api.utils.domain.list.ListDataDetails;
import projeto.api.utils.domain.list.ListRegisterData;
import projeto.api.utils.domain.list.ShoppingListService;
import projeto.api.utils.domain.user.User;

import java.net.URI;
import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<ListDataDetails> findById(@PathVariable Long id, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(service.findListByIdAndUser(id, user));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ListDataDetails>> findAllByUser(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(service.findAllByUser(user));
    }
}
