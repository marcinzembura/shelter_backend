package pl.shelter.shelter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import pl.shelter.shelter.accountandrole.account.Account;
import pl.shelter.shelter.accountandrole.account.AccountRepository;
import pl.shelter.shelter.accountandrole.role.ERole;
import pl.shelter.shelter.accountandrole.role.Role;
import pl.shelter.shelter.accountandrole.role.RoleRepository;
import pl.shelter.shelter.payload.request.LoginRequest;
import pl.shelter.shelter.payload.request.SignupRequest;
import pl.shelter.shelter.security.jwt.JwtUtils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @BeforeEach
    public void setUp() {
        roleRepository.save(new Role(ERole.ROLE_ADMIN));
        roleRepository.save(new Role(ERole.ROLE_EMPLOYEE));
    }
    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
    }

    @Test
    public void testAuthenticateUser() throws Exception {
        // Create a test user
        Account user = new Account("testuser", encoder.encode("testpass"), "Test", "User", 123456789L, "testuser@test.com");
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_EMPLOYEE)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);

        // Perform authentication
        LoginRequest loginRequest = new LoginRequest("testuser", "testpass");
        String loginJson = new ObjectMapper().writeValueAsString(loginRequest);
        MvcResult result = mockMvc.perform(post("/api/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginJson))
                .andExpect(status().isOk())
                .andReturn();

        // Verify response contains JWT token
        String responseJson = result.getResponse().getContentAsString();
        assertTrue(responseJson.contains("token"));
    }

    @Test
    public void testRegisterUser() throws Exception {
        // Create a test user
        SignupRequest signUpRequest = new SignupRequest("testuser", "Test1234", "Test", "User", 123456789L, "testuser@test.com");
        Set<String> roles = new HashSet<>();
        roles.add("employee");
        signUpRequest.setRole(roles);
        String signUpJson = new ObjectMapper().writeValueAsString(signUpRequest);

        // Perform registration
        mockMvc.perform(post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(signUpJson))
                .andExpect(status().isOk());

        // Verify user is created in database
        assertTrue(userRepository.existsByUsername("testuser"));

        // Verify password is encoded correctly
        Optional<Account> optionalUser = userRepository.findByUsername("testuser");
        assertTrue(optionalUser.isPresent());
        Account user = optionalUser.get();
        assertTrue(encoder.matches("Test1234", user.getPassword()));
    }
}