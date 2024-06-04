package projeto.api.utils.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import projeto.api.utils.domain.user.validations.UserValidations;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final List<UserValidations> validations;

    private final PasswordEncoder passwordEncoder;

    public UserDataDetails register(UserRegisterData data) {
        validations.forEach(v -> v.valid(data));

        User user = userRepository.save(new User(data,passwordEncoder.encode(data.password())));

        return new UserDataDetails(user);
    }
}
