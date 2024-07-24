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
import projeto.api.utils.domain.dailyTask.DailyTaskRegisterDataDTO;
import projeto.api.utils.domain.dailyTask.DailyTaskService;
import projeto.api.utils.domain.user.User;

import java.net.URI;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class DailyTaskController {
    private final DailyTaskService service;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody DailyTaskRegisterDataDTO data, @AuthenticationPrincipal User user,
                                    UriComponentsBuilder builder) {
        var dailyData = service.create(user,data);
        URI uri = builder.path("/tasks/{id}").buildAndExpand(dailyData.getId()).toUri();

        return ResponseEntity.created(uri).body(dailyData);
    }
}
