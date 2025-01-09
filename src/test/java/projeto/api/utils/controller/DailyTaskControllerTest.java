package projeto.api.utils.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import projeto.api.utils.domain.dailyTask.DailyTask;
import projeto.api.utils.domain.dailyTask.DailyTaskDataDetailsDTO;
import projeto.api.utils.domain.dailyTask.DailyTaskRegisterDataDTO;
import projeto.api.utils.domain.dailyTask.DailyTaskService;
import projeto.api.utils.domain.dailyTask.Priority;
import projeto.api.utils.domain.user.User;
import projeto.api.utils.domain.user.UserDataDetails;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class DailyTaskControllerTest {

    @Autowired
    private JacksonTester<DailyTaskRegisterDataDTO> dailyTaskRegisterDataJson;

    @Autowired
    private JacksonTester<DailyTaskDataDetailsDTO> dailyTaskDataDetailsJson;

    @Autowired
    private JacksonTester<Page<DailyTaskDataDetailsDTO>> pageJacksonTester;

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

    @Test
    @WithMockUser
    void findAllByUserTest() throws Exception {
        DailyTaskRegisterDataDTO data = new DailyTaskRegisterDataDTO("test name","description test",
                LocalDateTime.now().plusHours(1), Priority.LOW);
        UserDataDetails userDetails = new UserDataDetails(1L,"test","test@email.com");
        DailyTaskDataDetailsDTO details = new DailyTaskDataDetailsDTO(1L,new DailyTask(data,new User()),userDetails);
        List<DailyTaskDataDetailsDTO> dailyTasks = List.of(details);

        Page<DailyTaskDataDetailsDTO> page = new PageImpl<>(dailyTasks);

        when(service.findAllByUser(any(),any())).thenReturn(page);

        var response = mvc.perform(get("/tasks")).andReturn().getResponse();

        var json = pageJacksonTester.write(page).getJson();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentAsString()).isEqualTo(json);
    }
}