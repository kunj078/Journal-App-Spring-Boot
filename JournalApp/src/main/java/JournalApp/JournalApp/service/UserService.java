package JournalApp.JournalApp.service;

import JournalApp.JournalApp.Entity.User;
import JournalApp.JournalApp.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public boolean saveNewUser(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
            return true;
        } catch(Exception e) {
//            log.info("Information");
//            log.warn("Warn");
            log.debug("Debug");
            log.error("Error: ",user.getUsername());
//            log.trace("Trace");
            return false;
        }
    }
    public void saveUser(User user) {
        userRepository.save(user);
    }

    public boolean saveAdmin(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER", "ADMIN"));
            userRepository.save(user);
            return true;
        } catch(Exception e) {
            return false;
        }
    }
    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId userId) {
        return userRepository.findById(userId);
    }

    public boolean deleteById(ObjectId userId) {
        userRepository.deleteById(userId);
        return true;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
