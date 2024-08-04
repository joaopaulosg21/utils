package projeto.api.utils.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import projeto.api.utils.domain.dailyTask.DailyTaskDataDetailsDTO;
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
    public ResponseEntity<DailyTaskDataDetailsDTO> create(@Valid @RequestBody DailyTaskRegisterDataDTO data, @AuthenticationPrincipal User user,
                                    UriComponentsBuilder builder) {
        DailyTaskDataDetailsDTO dailyData = service.create(user,data);
        URI uri = builder.path("/tasks/{id}").buildAndExpand(dailyData.id()).toUri();

        return ResponseEntity.created(uri).body(dailyData);
    }

    @GetMapping
    public ResponseEntity<Page<DailyTaskDataDetailsDTO>> findAllByUser(@AuthenticationPrincipal User user, Pageable pageable) {
        return ResponseEntity.ok(service.findAllByUser(pageable,user));
    }
}
