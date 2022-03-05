package co.edu.udea.ayds2.repository;

import co.edu.udea.ayds2.collection.store.GarageSaleStore;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GarageSaleStoreRepository extends MongoRepository<GarageSaleStore, String> {
}
