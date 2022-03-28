package co.edu.udea.ayds2.mapper.interfaces;

import co.edu.udea.ayds2.collection.store.GarageSaleStore;
import co.edu.udea.ayds2.collection.store.product.ProductQuestion;
import co.edu.udea.ayds2.dto.store.GarageSaleStoreDto;
import co.edu.udea.ayds2.dto.store.product.ProductQuestionDto;

import java.util.function.Function;

public interface StoreMapperFromDtoToEntity {

    Function<GarageSaleStoreDto, GarageSaleStore> mapFromDtoToEntity();
    Function<ProductQuestionDto, ProductQuestion> mapProductQuestionFromDtoToEntity();

}
