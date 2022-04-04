package co.edu.udea.ayds2.mapper;

import co.edu.udea.ayds2.collection.store.*;
import co.edu.udea.ayds2.collection.store.product.Product;
import co.edu.udea.ayds2.collection.store.product.ProductCategory;
import co.edu.udea.ayds2.collection.store.product.ProductQuestion;
import co.edu.udea.ayds2.dto.store.*;
import co.edu.udea.ayds2.dto.store.product.ProductCategoryDto;
import co.edu.udea.ayds2.dto.store.product.ProductDto;
import co.edu.udea.ayds2.dto.store.product.ProductQuestionDto;
import co.edu.udea.ayds2.mapper.interfaces.StoreMapperFromEntityToDto;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StoreMapperFromEntityToDtoImpl implements StoreMapperFromEntityToDto {

    private static StoreMapperFromEntityToDto instance;

    private StoreMapperFromEntityToDtoImpl(){}

    public static synchronized StoreMapperFromEntityToDto getInstance(){
        if(instance == null){
            instance = new StoreMapperFromEntityToDtoImpl();
        }
        return instance;
    }

    @Override
    public Function<GarageSaleStore, GarageSaleStoreDto> mapFromEntityToDto() {
        return (garageSaleStore) -> GarageSaleStoreDto.builder()
                .id(garageSaleStore.getId())
                .storeName(garageSaleStore.getStoreName())
                .storeExistencePeriod(mapStoreExistencePeriod(garageSaleStore.getStoreExistencePeriod()))
                .storeDescription(mapStoreDescription(garageSaleStore.getStoreDescription()))
                .storeVisualDescription(mapStoreVisualDescription(garageSaleStore.getStoreVisualDescription()))
                .sellerId(garageSaleStore.getSellerId())
                .productCategoryList(mapProductCategoryList(garageSaleStore.getProductCategoryList()))
                .build();
    }

    private List<PurchasedItemDto> mapPurchasedItemsList(List<PurchasedItem> purchasedItemList) {
        return purchasedItemList.stream()
                .map(purchasedItem -> PurchasedItemDto.builder()
                        .price(purchasedItem.getPrice())
                        .quantity(purchasedItem.getQuantity())
                        .product(mapProductList(Collections.singletonList(purchasedItem.getProduct())).get(0))
                .build()).collect(Collectors.toList());
    }

    private List<ProductCategoryDto> mapProductCategoryList(List<ProductCategory> productCategoryList) {
        return productCategoryList.stream()
                .map(productCategory -> ProductCategoryDto.builder()
                        .name(productCategory.getName())
                        .imageUrl(productCategory.getImageUrl())
                        .productList(mapProductList(productCategory.getProductList()))
                        .build())
                .collect(Collectors.toList());
    }

    private List<ProductDto> mapProductList(List<Product> productList) {
        return productList.stream().map(product -> ProductDto.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .productStatus(product.getProductStatus())
                .additionalDescription(product.getAdditionalDescription())
                .productImageUrlList(product.getProductImageUrlList())
                 .productQuestionList(mapProductQuestionList(product.getProductQuestionList()))
                .productTagList(product.getProductTagList()).build())
                .collect(Collectors.toList());
    }

    private List<ProductQuestionDto> mapProductQuestionList(List<ProductQuestion> productQuestionList) {
        return productQuestionList.stream().map(productQuestion -> ProductQuestionDto.builder()
                .id(productQuestion.getId())
                .questionDate(productQuestion.getQuestionDate())
                .answerDate(productQuestion.getAnswerDate())
                .question(productQuestion.getQuestion())
                .response(productQuestion.getResponse())
                .customerId(productQuestion.getCustomerId())
                .build()).collect(Collectors.toList());
    }

    private StoreVisualDescriptionDto mapStoreVisualDescription(StoreVisualDescription storeVisualDescription) {
        return StoreVisualDescriptionDto.builder()
                .portraitUrl(storeVisualDescription.getPortraitUrl())
                .prevImagesList(storeVisualDescription.getPrevImagesList())
                .physicalStoreImageUrl(storeVisualDescription.getPhysicalStoreImageUrl())
                .build();
    }

    private StoreDescriptionDto mapStoreDescription(StoreDescription storeDescription) {
        return StoreDescriptionDto.builder()
                .description(storeDescription.getDescription())
                .slogan(storeDescription.getSlogan())
                .tagsList(storeDescription.getTagsList())
                .build();
    }

    private StoreExistencePeriodDto mapStoreExistencePeriod(StoreExistencePeriod storeExistencePeriod) {
        return StoreExistencePeriodDto.builder()
                .startingDate(storeExistencePeriod.getStartingDate())
                .endingDate(storeExistencePeriod.getEndingDate())
                .build();
    }

}
