package projeto.api.utils.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.RequiredArgsConstructor;
import projeto.api.utils.domain.monthlyexpense.MonthlyExpenseDataDTO;
import projeto.api.utils.domain.monthlyexpense.MonthlyExpenseDetailsDTO;
import projeto.api.utils.domain.monthlyexpense.MonthlyExpenseService;
import projeto.api.utils.domain.user.User;

@RestController
@RequestMapping("/expenses")
@RequiredArgsConstructor
public class MonthlyExpenseController {
    private final MonthlyExpenseService service;

    @PostMapping
    public ResponseEntity<MonthlyExpenseDetailsDTO> create(@AuthenticationPrincipal User user,
            @RequestBody MonthlyExpenseDataDTO data,
            UriComponentsBuilder builder) {
        MonthlyExpenseDetailsDTO details = service.create(user, data);
        URI uri = builder.path("/expenses/{id}").buildAndExpand(details.id()).toUri();
        return ResponseEntity.created(uri).body(details);
    }
}
