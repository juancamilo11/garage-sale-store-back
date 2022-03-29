package co.edu.udea.ayds2.repository;

import co.edu.udea.ayds2.collection.store.GarageSaleStore;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GarageSaleStoreRepository extends MongoRepository<GarageSaleStore, String> {

    List<GarageSaleStore> findAllBySellerId(String sellerId);

}
