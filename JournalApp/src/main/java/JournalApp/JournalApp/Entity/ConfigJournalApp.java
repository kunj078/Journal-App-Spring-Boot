package JournalApp.JournalApp.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document("config_journal_app")
public class ConfigJournalApp {
    private String key;
    private String value;
    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
