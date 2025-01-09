package projeto.api.utils.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.math.BigDecimal;
import java.time.Month;

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

import projeto.api.utils.domain.monthlyexpense.MonthlyExpense;
import projeto.api.utils.domain.monthlyexpense.MonthlyExpenseDataDTO;
import projeto.api.utils.domain.monthlyexpense.MonthlyExpenseDetailsDTO;
import projeto.api.utils.domain.monthlyexpense.MonthlyExpenseService;
import projeto.api.utils.domain.user.User;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class MonthlyExpenseControllerTest {

    @Autowired
    private JacksonTester<MonthlyExpenseDataDTO> monthExpenseDataJson;

    @Autowired
    private JacksonTester<MonthlyExpenseDetailsDTO> monthExpenseDetailsJson;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MonthlyExpenseService service;

    @Test
    @WithMockUser
    void createExpenseTest() throws Exception {
        MonthlyExpenseDataDTO data = new MonthlyExpenseDataDTO(Month.APRIL, new BigDecimal(150.30), "test desc");
        User user = new User(1L, "test name", "test@email.com", "test");
        MonthlyExpenseDetailsDTO details = new MonthlyExpenseDetailsDTO(
                new MonthlyExpense(data.month(), data.amount(), data.description(), user), user);

        when(service.create(any(), any())).thenReturn(details);

        var response = mvc.perform(post("/expenses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(monthExpenseDataJson.write(data).getJson()))
                .andReturn().getResponse();
                
        var jsonExpected = monthExpenseDetailsJson.write(details).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonExpected);
    }
}
