package sk.stuba.fei.uim.asos.assignment2.service;

import lombok.extern.java.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import sk.stuba.fei.uim.asos.assignment2.user.domain.Address;
import sk.stuba.fei.uim.asos.assignment2.user.domain.User;
import sk.stuba.fei.uim.asos.assignment2.user.service.IUserService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Log
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:config.xml"})
public class UserServiceTest {

    public static final String TEST_EMAIL = "test@example.com";

    @Autowired
    private IUserService<User, Long> userService;

    @BeforeEach
    public void setupUserDb() {
        assertNotNull(userService);

        User testUser = new User();
        testUser.setSurname("Test");
        testUser.setLastname("Testovič");
        testUser.setEmail(TEST_EMAIL);
        testUser.setIdentificationNumber("651010/6455");
        testUser.setPermanentAddress(new Address("84105", "Bratislava", "Matejkova", "5"));

        userService.add(testUser);
    }

    @Test
    public void shouldAddUser() {
        User testUser = new User();
        testUser.setSurname("Test 2");
        testUser.setLastname("Testovič");
        testUser.setEmail(TEST_EMAIL);
        testUser.setIdentificationNumber("651010/6455");
        testUser.setPermanentAddress(new Address("84105", "Bratislava", "Matejkova", "5"));

        assertNull(testUser.getId());
        testUser = userService.add(testUser);
        log.info("Added user id:" + testUser.getId());

        assertNotNull(testUser.getId());
        assert testUser.getId() > 1L;
    }

    @Test
    public void shouldUpdateUser() {
        User testUser = userService.getOne(1L);
        assertNotNull(testUser);
        testUser.setSurname("Updated");
        userService.update(testUser);
        User newUser = userService.getOne(testUser.getId());
        log.info("Updated user " + testUser + " with surname 'Updated'");

        assertEquals("Updated", newUser.getSurname());
    }

    @Test
    public void shouldGetAll() {
        List<User> users = userService.getAll();
        log.info("Number of saved users " + users.size());
        assertNotNull(users);
        assertFalse(users.isEmpty());
    }

    @Test
    public void shouldGetOne() {
        User testUser = userService.getOne(1L);
        log.info("Found user for id 1L " + testUser);
        assertNotNull(testUser);
    }


}
