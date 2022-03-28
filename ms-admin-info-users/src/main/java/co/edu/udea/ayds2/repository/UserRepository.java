package co.edu.udea.ayds2.repository;

import co.edu.udea.ayds2.collection.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
