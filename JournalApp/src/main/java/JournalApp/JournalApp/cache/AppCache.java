package JournalApp.JournalApp.cache;

import JournalApp.JournalApp.Entity.ConfigJournalApp;
import JournalApp.JournalApp.Repository.ConfigJournalRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {
    public enum keys{
        WEATHER_API;
    }
    @Autowired
    private ConfigJournalRepository configJournalRepository;

    public Map<String, String> appCache = new HashMap<>();

    @PostConstruct
    public void init() {
        appCache = new HashMap<>();
        List<ConfigJournalApp> all = configJournalRepository.findAll();
        for (ConfigJournalApp configJournalApp : all) {
            appCache.put(configJournalApp.getKey(), configJournalApp.getValue());
        }
    }
}
