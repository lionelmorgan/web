package com.spider.test;

import com.spider.controllers.UsersController;
import com.spider.models.Users;
import com.spider.services.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

public class UsersControllerTest {

    @InjectMocks
    private UsersController usersController;

    @Mock
    private UsersService usersService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSignup_Success() {
        Users user = new Users();
        user.setUsername("testUser");
        user.setPassword("password123");

        ResponseEntity<String> response = usersController.signup(user);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Signup successful", response.getBody());
        verify(usersService, times(1)).saveUser(user);
    }

    @Test
    public void testSignup_Error() {
        Users user = new Users();
        user.setUsername("testUser");
        user.setPassword("password123");

        doThrow(new RuntimeException()).when(usersService).saveUser(user);

        ResponseEntity<String> response = usersController.signup(user);

        assertEquals(500, response.getStatusCodeValue());
        assertEquals("Error signing up", response.getBody());
    }

    @Test
    public void testLogin_Success() {
        Users user = new Users();
        user.setUsername("testUser");
        user.setPassword("password123");

        when(usersService.getUserByUsername("testUser")).thenReturn(Optional.of(user));

        ResponseEntity<String> response = usersController.login(user);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Login successful", response.getBody());
    }

    @Test
    public void testLogin_InvalidCredentials() {
        Users user = new Users();
        user.setUsername("testUser");
        user.setPassword("wrongPassword");

        when(usersService.getUserByUsername("testUser")).thenReturn(Optional.of(user));

        ResponseEntity<String> response = usersController.login(user);

        assertEquals(401, response.getStatusCodeValue());
        assertEquals("Invalid username or password", response.getBody());
    }

    @Test
    public void testGetUsers() {
        Users user = new Users();
        user.setUsername("testUser");
        when(usersService.getAllUsers()).thenReturn(Collections.singletonList(user));

        ResponseEntity<List<Users>> response = usersController.getUsers();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals("testUser", response.getBody().get(0).getUsername());
    }

    @Test
    public void testGetUserById_Found() {
        Users user = new Users();
        user.setId(1L);
        user.setUsername("testUser");

        when(usersService.getUserById(1L)).thenReturn(Optional.of(user));

        ResponseEntity<Users> response = usersController.getUserById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("testUser", response.getBody().getUsername());
    }

    @Test
    public void testGetUserById_NotFound() {
        when(usersService.getUserById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Users> response = usersController.getUserById(1L);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testDeleteUser() {
        ResponseEntity<Void> response = usersController.deleteUser(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(usersService, times(1)).deleteUser(1L);
    }
}

