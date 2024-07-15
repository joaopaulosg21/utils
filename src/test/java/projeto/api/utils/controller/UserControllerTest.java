package projeto.api.utils.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import projeto.api.utils.domain.user.*;

    import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<UserRegisterData> userRegisterDataJson;

    @Autowired
    private JacksonTester<UserDataDetails> userDataDetailsJson;

    @MockBean
    private UserService userService;

    @Test
    void registerTest() throws Exception {
        UserRegisterData data = new UserRegisterData("test","test@email.com","123");
        UserDataDetails details = new UserDataDetails(1L,"test","test@email.com");
        when(userService.register(data)).thenReturn(details);

        var response = mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userRegisterDataJson.write(data).getJson()))
                .andReturn().getResponse();
        var json = userDataDetailsJson.write(details).getJson();

        assertThat(response.getContentAsString()).isEqualTo(json);
        assertThat(response.getStatus()).isEqualTo(201);
    }

    @Test
    @WithMockUser
    public void getUserByIdTest() throws Exception{
        Long id = 1L;
        UserDataDetails details = new UserDataDetails(1L,"test","test@email.com");
        when(userService.findById(id)).thenReturn(details);

        var response = mvc.perform(get("/users/"+id))
                .andReturn().getResponse();

        var json = userDataDetailsJson.write(details).getJson();
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentAsString()).isEqualTo(json);
    }
}