package projeto.api.utils.domain.user.validations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import projeto.api.utils.domain.user.User;
import projeto.api.utils.domain.user.UserRegisterData;
import projeto.api.utils.domain.user.UserRepository;
import projeto.api.utils.infra.exception.ValidationException;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmailAlreadyUsedValidation implements UserValidations {
    private final UserRepository repository;

    @Override
    public void valid(UserRegisterData data) {
        Optional<User> optionalUser = repository.findByEmail(data.email());

        if(optionalUser.isPresent()) {
            throw new ValidationException("Email already used");
        }
    }
}
