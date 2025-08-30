package com.miron.profileservice.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miron.profileservice.domain.api.AccountService;
import com.miron.profileservice.domain.entity.Account;
import com.miron.profileservice.domain.spi.BCryptEncoderForAccountPassword;
import com.miron.profileservice.infrastructure.config.DomainBeansCreationConfig;
import com.miron.profileservice.infrastructure.controller.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(DomainBeansCreationConfig.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AccountControllerTests {
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final String BASE_URL = "/api/v1/profile";
    @MockitoBean
    private AccountService<Account> accountService;
    @Autowired
    private TestRestTemplate testRestTemplate;

    private Account account = new Account("username", "password1234", "danya", new TestEncoder());

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    void retrieve_profile_by_id() {
        when(accountService.retrieveUser(account.getId())).thenReturn(account);

        AccountResponse template = testRestTemplate.getForObject(BASE_URL + "/" + account.getId(), AccountResponse.class);
        assertThat(template.getAccountUsername()).isEqualTo(new AccountResponse(account).getAccountUsername());
    }


    @Test
    public void retrieve_profiles_by_ids() throws Exception {
        List<UUID> uuids = List.of(account.getId());
        HttpEntity<String> httpEntity = new HttpEntity<>(objectMapper.writeValueAsString(uuids));
        when(accountService.retrieveUsers(new AccountsRequest(httpEntity.getBody()).getUsersId())).thenReturn(List.of(account));

        String firstValue = "{\"userId\":\"" + account.getId() + "\"}";
        mockMvc.perform(get("/api/v1/profile/retrieve-profiles")
                        .content(firstValue))
                .andExpect(status()
                        .isOk())
                .andDo(print());
    }

    @Test
    public void registrate_user() throws Exception {
        CreateAccountRequest createAccountRequest = new CreateAccountRequest(account.getUsername(), account.getPassword(), account.getAccountName());
        var jsonObject = objectMapper.writeValueAsString(createAccountRequest);
        when(accountService.createAccount(account.getUsername(), account.getPassword(), account.getAccountName())).thenReturn(account);
        mockMvc.perform(post("/api/v1/profile/register")
                        .content(jsonObject)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isOk())
                .andDo(print());
    }

    @Test
    public void change_account_name() throws Exception {
        ChangeAccountNameRequest changeAccountNameRequest = new ChangeAccountNameRequest(account.getUsername(), "new accountName");
        var jsonObject = objectMapper.writeValueAsString(changeAccountNameRequest);

        when(accountService.changeNameByUsername(account.getUsername(), "new accountName")).thenReturn(account.changeAccountName("new accountName"));
        mockMvc.perform(put("/api/v1/profile/change-account-name")
                        .content(jsonObject)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isOk())
                .andDo(print());
    }

    @Test
    public void change_account_password() throws Exception {
        ChangeAccountPasswordRequest changeAccountPasswordRequest = new ChangeAccountPasswordRequest(account.getUsername(), "password1234", "newPassword1234");
        var jsonObject = objectMapper.writeValueAsString(changeAccountPasswordRequest);

        when(accountService.changePasswordByUsername(account.getUsername(), "password1234", "newPassword1234"))
                .thenReturn(account.changeAccountPassword("password1234", "newPassword1234", new TestEncoder()));
        mockMvc.perform(put("/api/v1/profile/change-account-password")
                        .content(jsonObject)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isOk())
                .andDo(print());
    }

    private static class TestEncoder implements BCryptEncoderForAccountPassword {
        @Override
        public String encode(String password) {
            return password + " test encoder";
        }
    }
}
