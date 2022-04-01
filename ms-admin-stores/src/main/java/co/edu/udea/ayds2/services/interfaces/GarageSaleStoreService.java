package co.edu.udea.ayds2.services.interfaces;

import co.edu.udea.ayds2.collection.store.GarageSaleStore;
import co.edu.udea.ayds2.dto.store.GarageSaleStoreDto;
import co.edu.udea.ayds2.dto.store.product.ProductQuestionDto;

import javax.validation.Valid;
import java.util.List;

public interface GarageSaleStoreService {
    boolean createStore(@Valid GarageSaleStoreDto garageSaleStoreDto);
    List<GarageSaleStore> getAllActiveStores();

    boolean postNewQuestion(String storeId, String categoryName, String productId, ProductQuestionDto productQuestionDto);
    boolean postAnswerToProductQuestion(String storeId, String categoryName, String productId, ProductQuestionDto productQuestionDto);

    List<GarageSaleStore> getAllActiveStoresBySellerId(String sellerId);

    boolean postStoreView(String storeId, String userId);

    GarageSaleStoreDto getStoreById(String id);


}
