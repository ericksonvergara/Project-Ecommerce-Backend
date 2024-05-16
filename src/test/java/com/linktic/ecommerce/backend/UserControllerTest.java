package com.linktic.ecommerce.backend;

import com.linktic.ecommerce.backend.application.UserService;
import com.linktic.ecommerce.backend.domain.model.User;
import com.linktic.ecommerce.backend.domain.model.UserType;
import com.linktic.ecommerce.backend.infrastructure.controller.UserController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void whenSaveUser_thenReturnsSavedUser() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("testUser");
        user.setFirstName("Test");
        user.setLastName("User");
        user.setEmail("testuser@example.com");
        user.setAddress("123 Test Street");
        user.setCellphone("1234567890");
        user.setPassword("securePassword");
        user.setUserType(UserType.ADMIN); // Asumiendo que UserType es un enum.
        user.setDateCreated(LocalDateTime.now());
        user.setDateUpdated(LocalDateTime.now());

        given(userService.save(any(User.class))).willReturn(user);

        mockMvc.perform(post("/api/v1/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"testUser\", \"firstName\": \"Test\", \"lastName\": \"User\", \"email\": \"testuser@example.com\", \"address\": \"123 Test Street\", \"cellphone\": \"1234567890\", \"password\": \"securePassword\", \"userType\": \"ADMIN\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testUser"))
                .andExpect(jsonPath("$.firstName").value("Test"))
                .andExpect(jsonPath("$.lastName").value("User"))
                .andExpect(jsonPath("$.email").value("testuser@example.com"))
                .andExpect(jsonPath("$.address").value("123 Test Street"))
                .andExpect(jsonPath("$.cellphone").value("1234567890"))
                .andExpect(jsonPath("$.userType").value("ADMIN"));

        verify(userService).save(any(User.class));
    }

    @Test
    void whenFindById_thenReturnsUser() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("testUser");
        user.setFirstName("Test");
        user.setLastName("User");
        user.setEmail("testuser@example.com");
        user.setAddress("123 Test Street");
        user.setCellphone("1234567890");
        user.setPassword("securePassword");
        user.setUserType(UserType.ADMIN); // Asumiendo que UserType es un enum.
        user.setDateCreated(LocalDateTime.now());
        user.setDateUpdated(LocalDateTime.now());

        given(userService.findById(1)).willReturn(user);

        mockMvc.perform(get("/api/v1/users/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testUser"))
                .andExpect(jsonPath("$.firstName").value("Test"))
                .andExpect(jsonPath("$.lastName").value("User"))
                .andExpect(jsonPath("$.email").value("testuser@example.com"))
                .andExpect(jsonPath("$.address").value("123 Test Street"))
                .andExpect(jsonPath("$.cellphone").value("1234567890"))
                .andExpect(jsonPath("$.userType").value("ADMIN"));

        verify(userService).findById(1);
    }
}
