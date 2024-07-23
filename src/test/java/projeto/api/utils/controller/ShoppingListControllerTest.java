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
import projeto.api.utils.domain.list.Item;
import projeto.api.utils.domain.list.ListDataDetails;
import projeto.api.utils.domain.list.ListRegisterData;
import projeto.api.utils.domain.list.ShoppingListService;
import projeto.api.utils.domain.user.UserDataDetails;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ShoppingListControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ShoppingListService service;

    @Autowired
    private JacksonTester<ListRegisterData> listRegisterDataJson;

    @Autowired
    private JacksonTester<ListDataDetails> listDataDetailsJson;

    @Autowired
    private JacksonTester<List<ListDataDetails>> listDataDetailsListJson;

    @Test
    @WithMockUser
    void createTest() throws Exception {
        List<Item> items = List.of(new Item("item","10 und"));
        ListRegisterData listData = new ListRegisterData("List test",items);
        UserDataDetails userDetails = new UserDataDetails(1L,"test","test@email.com");
        ListDataDetails listDetails = new ListDataDetails(1L,listData.name(),listData.items(),userDetails);

        when(service.create(any(),any())).thenReturn(listDetails);

        var response = mvc.perform(post("/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(listRegisterDataJson.write(listData).getJson()))
                .andReturn().getResponse();

        var json = listDataDetailsJson.write(listDetails).getJson();

        assertThat(response.getContentAsString()).isEqualTo(json);
        assertThat(response.getStatus()).isEqualTo(201);
    }

    @Test
    @WithMockUser
    void findByIdTest() throws Exception {
        List<Item> items = List.of(new Item("item","10 und"));
        UserDataDetails userDetails = new UserDataDetails(1L,"test","test@email.com");
        ListDataDetails listDetails = new ListDataDetails(1L,"List test",items,userDetails);

        when(service.findListByIdAndUser(anyLong(),any())).thenReturn(listDetails);

        var response = mvc.perform(get("/list/1")).andReturn().getResponse();

        var json = listDataDetailsJson.write(listDetails).getJson();

        assertThat(response.getContentAsString()).isEqualTo(json);
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    @WithMockUser
    void findAllNoArchivedTest() throws Exception {
        List<Item> items = List.of(new Item("item","10 und"));
        UserDataDetails userDetails = new UserDataDetails(1L,"test","test@email.com");
        ListDataDetails listDetails = new ListDataDetails(1L,"List test",items,userDetails);
        List<ListDataDetails> lists = List.of(listDetails);

        when(service.findAllByUserAndNotArchived(any())).thenReturn(lists);

        var response = mvc.perform(get("/list/all")).andReturn().getResponse();

        var json = listDataDetailsListJson.write(lists).getJson();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentAsString()).isEqualTo(json);
    }
}