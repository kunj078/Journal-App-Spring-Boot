package JournalApp.JournalApp.scheduler;

import JournalApp.JournalApp.Entity.JournalEntry;
import JournalApp.JournalApp.Entity.User;
import JournalApp.JournalApp.Repository.UserRepositoryImpl;
import JournalApp.JournalApp.service.EmailService;
import JournalApp.JournalApp.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {
    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Scheduled(cron = "* 02 17 * * *")
    public void fetchUserAndSendMail() {
        List<User> userForSA = userRepository.getUserForSA();
        for (User user : userForSA) {
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<String> filteredEntries = journalEntries.stream().filter(
                    x -> x.getDate()
                            .isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS)))
                    .map(x -> x.getContent()).collect(Collectors.toList());
            String entry = String.join(" ", filteredEntries);
            String sentiment = sentimentAnalysisService.getSentiment(entry);
            emailService.sendMail(user.getEmail(), "last 7 days sentiment", "hello");
        }
    }
}
