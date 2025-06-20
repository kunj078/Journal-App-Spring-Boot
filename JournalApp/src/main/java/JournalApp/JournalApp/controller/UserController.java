package JournalApp.JournalApp.controller;


import JournalApp.JournalApp.Entity.User;
import JournalApp.JournalApp.Repository.UserRepository;
import JournalApp.JournalApp.api.response.WeatherResponse;
import JournalApp.JournalApp.service.UserService;
import JournalApp.JournalApp.service.WeatherService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private WeatherService weatherService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("Id/{userId}")
    public Optional<User> findByUserId(@RequestParam ObjectId userId) {
        return userService.findById(userId);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User dbuser = userService.findByUsername(userName);
        dbuser.setUsername(user.getUsername());
        dbuser.setPassword(user.getPassword());
        userService.saveNewUser(dbuser);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteUserById(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUsername(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherService.getWhether("Surat");
        String greeting = "";
        if(weatherResponse != null) {
            greeting = ", Weather Fills Like " + weatherResponse.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("Hi "+ authentication.getName() + greeting , HttpStatus.OK);
    }
}
