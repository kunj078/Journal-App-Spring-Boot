package JournalApp.JournalApp.service;

import JournalApp.JournalApp.Entity.JournalEntry;
import JournalApp.JournalApp.Entity.User;
import JournalApp.JournalApp.Repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

//    private static final Logger logger = LoggerFactory.getLogger(JournalEntryService.class);

    // find the user by name and save journal entry id into the User
    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username) {
        try {
            User user = userService.findByUsername(username);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveUser(user);
        } catch (Exception e) {
            throw new RuntimeException("An Error occurred while saving entry "+e);
        }
    }
    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId journalId) {
        return journalEntryRepository.findById(journalId);
    }

    // remove id from the user's journal list
    @Transactional
    public boolean deleteById(ObjectId journalId, String username) {
        boolean removed = false;
        try {
            User user = userService.findByUsername(username);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(journalId));
            if(removed) {
                userService.saveUser(user);
                journalEntryRepository.deleteById(journalId);
            }
        } catch (Exception e) {
            log.error("Error: ", e);
            throw new RuntimeException("An Error occurred while deleting the entry" + e);
        }
        return removed;
    }

    public List<JournalEntry> findByUserName(String userName) {
        return null;
    }
}
