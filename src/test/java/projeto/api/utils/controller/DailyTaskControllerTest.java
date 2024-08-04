package projeto.api.utils.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import projeto.api.utils.domain.dailyTask.*;
import projeto.api.utils.domain.user.User;
import projeto.api.utils.domain.user.UserDataDetails;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class DailyTaskControllerTest {

    @Autowired
    private JacksonTester<DailyTaskRegisterDataDTO> dailyTaskRegisterDataJson;

    @Autowired
    private JacksonTester<DailyTaskDataDetailsDTO> dailyTaskDataDetailsJson;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DailyTaskService service;

    @Test
    @WithMockUser
    void createDailyTaskTest() throws Exception {
        DailyTaskRegisterDataDTO data = new DailyTaskRegisterDataDTO("test name","description test",
                LocalDateTime.now().plusHours(1), Priority.LOW);
        UserDataDetails userDetails = new UserDataDetails(1L,"test","test@email.com");
        DailyTaskDataDetailsDTO details = new DailyTaskDataDetailsDTO(1L,new DailyTask(data,new User()),userDetails);

        when(service.create(any(),any())).thenReturn(details);

        var response = mvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dailyTaskRegisterDataJson.write(data).getJson()))
                .andReturn().getResponse();

        var json = dailyTaskDataDetailsJson.write(details).getJson();

        assertThat(response.getContentAsString()).isEqualTo(json);
    }
}