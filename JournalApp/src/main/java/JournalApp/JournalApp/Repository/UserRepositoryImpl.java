package JournalApp.JournalApp.Repository;

import JournalApp.JournalApp.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.security.PrivateKey;
import java.util.List;

public class UserRepositoryImpl {
    @Autowired
    private MongoTemplate mongoTemplate;
    public List<User> getUserForSA() {
        Query query = new Query();
//        query.addCriteria(Criteria.where("email").exists(true));
//        query.addCriteria(Criteria.where("email").ne(null).ne(""));
        query.addCriteria(Criteria.where("email")
                .regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", "i"));
        query.addCriteria(Criteria.where("sentimentAnalysis").exists(true));
//        query.addCriteria(Criteria.where("roles").in("USER","ADMIN"));

//        Criteria criteria = new Criteria();
        // or Condition for field
//        query.addCriteria(
//        criteria.orOperator(Criteria.where("email").exists(true),
//        Criteria.where("sentimentAnalysis").exists(true)));
        List<User> users = mongoTemplate.find(query, User.class);
        return users;
    }
}
