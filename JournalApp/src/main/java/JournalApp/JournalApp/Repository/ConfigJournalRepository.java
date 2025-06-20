package JournalApp.JournalApp.Repository;


import JournalApp.JournalApp.Entity.ConfigJournalApp;
import JournalApp.JournalApp.Entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigJournalRepository extends MongoRepository<ConfigJournalApp, ObjectId> {
}
