package projeto.api.utils.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import projeto.api.utils.domain.user.UserDataDetails;
import projeto.api.utils.domain.user.UserRegisterData;
import projeto.api.utils.domain.user.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDataDetails> register(@RequestBody @Valid UserRegisterData data) {
        UserDataDetails details = userService.register(data);
        return ResponseEntity.created(
                UriComponentsBuilder.fromPath("/users/{id}")
                        .buildAndExpand(details.id()).toUri())
                .body(details);
    }
}
