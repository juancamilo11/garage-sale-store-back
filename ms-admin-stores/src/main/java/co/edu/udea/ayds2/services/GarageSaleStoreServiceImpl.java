package co.edu.udea.ayds2.services;

import co.edu.udea.ayds2.collection.store.GarageSaleStore;
import co.edu.udea.ayds2.collection.store.product.Product;
import co.edu.udea.ayds2.collection.store.product.ProductCategory;
import co.edu.udea.ayds2.collection.store.product.ProductQuestion;
import co.edu.udea.ayds2.dto.helpers.response.AppServerResponse;
import co.edu.udea.ayds2.dto.helpers.response.EnumResponseStatus;
import co.edu.udea.ayds2.dto.store.GarageSaleStoreDto;
import co.edu.udea.ayds2.dto.store.product.ProductQuestionDto;
import co.edu.udea.ayds2.mapper.interfaces.StoreMapperFromDtoToEntity;
import co.edu.udea.ayds2.mapper.interfaces.StoreMapperFromEntityToDto;
import co.edu.udea.ayds2.repository.GarageSaleStoreRepository;
import co.edu.udea.ayds2.services.interfaces.GarageSaleStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Validated
@RequiredArgsConstructor
public class GarageSaleStoreServiceImpl implements GarageSaleStoreService {

    private final StoreMapperFromEntityToDto storeMapperFromEntityToDto;
    private final StoreMapperFromDtoToEntity storeMapperFromDtoToEntity;
    private final GarageSaleStoreRepository garageSaleStoreRepository;

    @Override
    public boolean createStore(GarageSaleStoreDto garageSaleStoreDto) {
        GarageSaleStore result = this.garageSaleStoreRepository.save(this.storeMapperFromDtoToEntity.mapFromDtoToEntity().apply(garageSaleStoreDto));
        return result.getId().equals(garageSaleStoreDto.getId());
    }

    @Override
    public List<GarageSaleStore> getAllActiveStores() {

        return this.garageSaleStoreRepository.findAll()
                .stream()
                .filter(garageSaleStore -> garageSaleStore.getStoreExistencePeriod()
                        .getStartingDate()
                        .isBefore(LocalDate.now()) &&
                        garageSaleStore.getStoreExistencePeriod()
                                .getEndingDate()
                                .isAfter(LocalDate.now())
                        )
                .collect(Collectors.toList());
    }

    @Override
    public boolean postNewQuestion(String storeId, String categoryName, String productId, ProductQuestionDto productQuestionDto) {
       Optional<GarageSaleStore> garageSaleStoreOptional = this.garageSaleStoreRepository.findById(storeId);
        return garageSaleStoreOptional.map(garageSaleStore -> {
            List<ProductCategory> productCategoryList = garageSaleStore.getProductCategoryList()
                    .stream()
                    .map(getProductCategoryToUpdate(categoryName, productId, productQuestionDto)).collect(Collectors.toList());

            garageSaleStore.setProductCategoryList(productCategoryList);
            this.garageSaleStoreRepository.save(garageSaleStore);
            return Boolean.TRUE;
        }).orElse(Boolean.FALSE);
    }

    @Override
    public boolean postAnswerToProductQuestion(String storeId, String categoryName, String productId, ProductQuestionDto productQuestionDto) {
        Optional<GarageSaleStore> garageSaleStoreOptional = this.garageSaleStoreRepository.findById(storeId);
        return garageSaleStoreOptional.map(garageSaleStore -> {
            List<ProductCategory> productCategoryList = garageSaleStore.getProductCategoryList()
                    .stream()
                    .map(getProductCategoryToUpdate(categoryName, productId, productQuestionDto)).collect(Collectors.toList());

            garageSaleStore.setProductCategoryList(productCategoryList);
            this.garageSaleStoreRepository.save(garageSaleStore);
            return Boolean.TRUE;
        }).orElse(Boolean.FALSE);
    }

    private Function<ProductCategory, ProductCategory> getProductCategoryToUpdate(String categoryName, String productId, ProductQuestionDto productQuestionDto) {
        return productCategory -> {
            if (productCategory.getName().equalsIgnoreCase(categoryName)) {
                return ProductCategory.builder()
                        .name(productCategory.getName())
                        .imageUrl(productCategory.getImageUrl())
                        .productList(productCategory.getProductList()
                                .stream()
                                .map(addQuestionToProductFunction(productId, productQuestionDto))
                                .collect(Collectors.toList()))
                        .build();
            }
            return productCategory;
        };
    }

    private Function<Product, Product> addQuestionToProductFunction(String productId, ProductQuestionDto productQuestionDto) {
        return product -> {
            if (product.getId().equalsIgnoreCase(productId)) {
                List<ProductQuestion> productQuestionList = product.getProductQuestionList();
                if(productQuestionList == null) {
                    productQuestionList = new ArrayList<>();
                }
                if(productQuestionList.stream().anyMatch(productQuestion -> productQuestion.getId().equalsIgnoreCase(productQuestionDto.getId()))) {
                    productQuestionList = addAnswerToExistingQuestion(productQuestionDto, productQuestionList);
                } else {
                    addQuestionToProduct(productQuestionDto, productQuestionList);
                }
                product.setProductQuestionList(productQuestionList);
                return product;
            }
            return product;
        };
    }

    private void addQuestionToProduct(ProductQuestionDto productQuestionDto, List<ProductQuestion> productQuestionList) {
        productQuestionList.add(this.storeMapperFromDtoToEntity
                .mapProductQuestionFromDtoToEntity()
                .apply(productQuestionDto));
    }

    private List<ProductQuestion> addAnswerToExistingQuestion(ProductQuestionDto productQuestionDto, List<ProductQuestion> productQuestionList) {
        productQuestionList = productQuestionList
                .stream()
                .map(productQuestion -> {
                    if(productQuestion.getId().equalsIgnoreCase(productQuestionDto.getId())) {
                        productQuestion.setResponse(productQuestionDto.getResponse());
                        productQuestion.setAnswerDate(productQuestionDto.getAnswerDate());

                        return productQuestion;
                    }
                    return productQuestion;
                }).collect(Collectors.toList());
        return productQuestionList;
    }
}
