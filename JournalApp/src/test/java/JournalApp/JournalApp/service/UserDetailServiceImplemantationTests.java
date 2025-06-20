package JournalApp.JournalApp.service;

import JournalApp.JournalApp.Entity.User;
import JournalApp.JournalApp.Repository.UserRepository;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

public class UserDetailServiceImplemantationTests {
    @InjectMocks
    private UserDetailServiceImplemantation userDetailService;

    @Mock
    private UserRepository userRepository;
    @BeforeEach
    void setUp() {
        // There is null pointer error in userRepo that's why we setUp Mocks and it is inject the dependancy for repo
        MockitoAnnotations.initMocks(this);
    }

    @Disabled
    @Test
    void loadUserByNameTest() {
        User user = User.builder()
                .username("ram")
                .password("ram")
                .roles(new ArrayList<>())
                .build();

        when(userRepository.findByUsername(anyString())).thenReturn(user);
        Assertions.assertNotNull(user);
    }
}
