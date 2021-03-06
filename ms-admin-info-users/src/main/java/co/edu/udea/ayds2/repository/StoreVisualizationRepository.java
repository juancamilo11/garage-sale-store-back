package co.edu.udea.ayds2.repository;

import co.edu.udea.ayds2.collection.store.StoreVisualization;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreVisualizationRepository extends MongoRepository<StoreVisualization, String> {
}
