package projeto.api.utils.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import projeto.api.utils.domain.user.UserDataDetails;
import projeto.api.utils.domain.user.UserRegisterData;
import projeto.api.utils.domain.user.UserService;

import java.net.URI;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDataDetails> register(@RequestBody @Valid UserRegisterData data,
                                                    UriComponentsBuilder builder) {
        UserDataDetails details = userService.register(data);
        URI uri = builder.path("/users/{id}").buildAndExpand(details.id()).toUri();
        return ResponseEntity.created(uri).body(details);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDataDetails> getUserById(@PathVariable Long id) {
        UserDataDetails details = userService.findById(id);
        return ResponseEntity.ok(details);
    }
}
