package JournalApp.JournalApp.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class EmailServiceTests {
    @Autowired
    private EmailService emailService;

    @Test
    void sendMailTest() {
        try {
            emailService.sendMail("kunjkhimani13@gmail.com", "From Java mail Sender", "Hello");
        } catch(Exception e) {
            log.error("Error in email testing: "+e.getMessage());
        }
    }
}
