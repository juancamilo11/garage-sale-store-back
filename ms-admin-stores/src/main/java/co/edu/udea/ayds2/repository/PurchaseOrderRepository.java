package co.edu.udea.ayds2.repository;

import co.edu.udea.ayds2.collection.store.PurchaseOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseOrderRepository extends MongoRepository<PurchaseOrder, String> {
    List<PurchaseOrder> findAllBySellerId(String SellerId);
    List<PurchaseOrder> findAllByCustomerId(String CustomerId);
}
