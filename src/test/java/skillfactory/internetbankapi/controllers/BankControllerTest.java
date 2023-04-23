package skillfactory.internetbankapi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import skillfactory.internetbankapi.entities.User;
import skillfactory.internetbankapi.services.UserService;

import java.util.Optional;

import static org.hamcrest.Matchers.*;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BankControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;


    @MockBean
    UserService userService;

    User USER_1 = new User(1L, "Igor", "Cidorov", 14500.00);
    User USER_2 = new User(2L, "Olrg", "Putin", 20050.00);
    User USER_3 = new User(3L, "Andrey", "Sidorov", 24050.00);

    @Test
    void getBalance_success() throws Exception {
        Mockito.when(userService.findUserById(USER_1.getId())).thenReturn(Optional.of(USER_1));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/balance/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$", is(14500.00)));
    }

    @Test
    void takeMoney() throws Exception {
        USER_2.setBalance(USER_2.getBalance() - 200.00);
        Mockito.when(userService.findUserById(USER_2.getId())).thenReturn(Optional.of(USER_2));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/balance/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$", is(19850.00)));
    }

}