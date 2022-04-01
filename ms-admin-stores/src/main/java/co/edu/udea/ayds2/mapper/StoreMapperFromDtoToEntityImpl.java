package co.edu.udea.ayds2.mapper;

import co.edu.udea.ayds2.collection.store.*;
import co.edu.udea.ayds2.collection.store.product.Product;
import co.edu.udea.ayds2.collection.store.product.ProductCategory;
import co.edu.udea.ayds2.collection.store.product.ProductQuestion;
import co.edu.udea.ayds2.collection.user.User;
import co.edu.udea.ayds2.collection.user.UserContact;
import co.edu.udea.ayds2.dto.store.*;
import co.edu.udea.ayds2.dto.store.product.ProductCategoryDto;
import co.edu.udea.ayds2.dto.store.product.ProductDto;
import co.edu.udea.ayds2.dto.store.product.ProductQuestionDto;
import co.edu.udea.ayds2.dto.user.UserContactDto;
import co.edu.udea.ayds2.dto.user.UserDto;
import co.edu.udea.ayds2.mapper.interfaces.StoreMapperFromDtoToEntity;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StoreMapperFromDtoToEntityImpl implements StoreMapperFromDtoToEntity {

    private static StoreMapperFromDtoToEntity instance;

    private StoreMapperFromDtoToEntityImpl(){}

    public static synchronized StoreMapperFromDtoToEntity getInstance(){
        if(instance == null){
            instance = new StoreMapperFromDtoToEntityImpl();
        }
        return instance;
    }

    @Override
    public Function<GarageSaleStoreDto, GarageSaleStore> mapFromDtoToEntity() {
        return (garageSaleStore) -> GarageSaleStore.builder()
                .id(garageSaleStore.getId())
                .storeName(garageSaleStore.getStoreName())
                .storeExistencePeriod(mapStoreExistencePeriod(garageSaleStore.getStoreExistencePeriod()))
                .storeDescription(mapStoreDescription(garageSaleStore.getStoreDescription()))
                .storeVisualDescription(mapStoreVisualDescription(garageSaleStore.getStoreVisualDescription()))
                .sellerId(garageSaleStore.getSellerId())
                .productCategoryList(mapProductCategoryList(garageSaleStore.getProductCategoryList()))
                .purchaseTestimonialList(mapPurchaseTestimonialList(garageSaleStore.getPurchaseTestimonialList()))
                .build();
    }

    private PurchaseTestimonial mapPurchaseTestimonial(PurchaseTestimonialDto purchaseTestimonialDto) {
        return PurchaseTestimonial.builder()
                .title(purchaseTestimonialDto.getTitle())
                .purchaseTestimonial(purchaseTestimonialDto.getPurchaseTestimonial())
                .date(purchaseTestimonialDto.getDate())
                .grade(purchaseTestimonialDto.getGrade())
                .user(mapSellerInformation(purchaseTestimonialDto.getUser()))
                .build();
    }

    private List<PurchasedItem> mapPurchasedItemsList(List<PurchasedItemDto> purchasedItemDtoList) {
        return purchasedItemDtoList.stream()
                .map(purchasedItemDto -> PurchasedItem.builder()
                        .price(purchasedItemDto.getPrice())
                        .quantity(purchasedItemDto.getQuantity())
                        .product(mapProductList(Collections.singletonList(purchasedItemDto.getProduct())).get(0))
                        .build()).collect(Collectors.toList());
    }
    private List<PurchaseTestimonial> mapPurchaseTestimonialList(List<PurchaseTestimonialDto> purchaseTestimonialDtoList) {
        return purchaseTestimonialDtoList.stream()
                .map(purchaseTestimonialDto -> PurchaseTestimonial.builder()
                        .title(purchaseTestimonialDto.getTitle())
                        .date(purchaseTestimonialDto.getDate())
                        .grade(purchaseTestimonialDto.getGrade())
                        .user(mapSellerInformation(purchaseTestimonialDto.getUser()))
                        .build())
                .collect(Collectors.toList());
    }

    private List<ProductCategory> mapProductCategoryList(List<ProductCategoryDto> productCategoryDtoList) {
        return productCategoryDtoList.stream()
                .map(productCategory -> ProductCategory.builder()
                        .name(productCategory.getName())
                        .imageUrl(productCategory.getImageUrl())
                        .productList(mapProductList(productCategory.getProductList()))
                        .build())
                .collect(Collectors.toList());
    }

    private List<Product> mapProductList(List<ProductDto> productDtoList) {
        return productDtoList.stream().map(product -> Product.builder()
                        .id(product.getId())
                        .productName(product.getProductName())
                        .additionalDescription(product.getAdditionalDescription())
                        .price(product.getPrice())
                        .quantity(product.getQuantity())
                        .productStatus(product.getProductStatus())
                        .productImageUrlList(product.getProductImageUrlList())
                        .productTagList(product.getProductTagList())
                        .productQuestionList(mapProductQuestionList(product.getProductQuestionList()))
                        .build())
                .collect(Collectors.toList());

    }

    private List<ProductQuestion> mapProductQuestionList(List<ProductQuestionDto> productQuestionList) {
        return productQuestionList.stream().map(productQuestionDto -> ProductQuestion.builder()
                .id(productQuestionDto.getId())
                .questionDate(productQuestionDto.getQuestionDate())
                .answerDate(productQuestionDto.getAnswerDate())
                .question(productQuestionDto.getQuestion())
                .response(productQuestionDto.getResponse())
                .customerId(productQuestionDto.getCustomerId())
                .build()).collect(Collectors.toList());
    }

    public Function<ProductQuestionDto,ProductQuestion> mapProductQuestionFromDtoToEntity() {
        return productQuestionDto -> ProductQuestion.builder()
                .id(productQuestionDto.getId())
                .questionDate(productQuestionDto.getQuestionDate())
                .answerDate(productQuestionDto.getAnswerDate())
                .question(productQuestionDto.getQuestion())
                .response(productQuestionDto.getResponse())
                .customerId(productQuestionDto.getCustomerId())
                .build();
    }

    private User mapSellerInformation(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getId())
                .occupation(userDto.getOccupation())
                .dateOfBirth(userDto.getDateOfBirth())
                .registerDate(userDto.getRegisterDate())
                .creationTime(userDto.getCreationTime())
                .lastSignInTime(userDto.getLastSignInTime())
                .registerDate(userDto.getRegisterDate())
                .userContact(mapUserContact(userDto.getUserContact()))
                .build();
    }

    private UserContact mapUserContact(UserContactDto userContactDto) {
        return UserContact.builder()
                .email(userContactDto.getEmail())
                .address(userContactDto.getAddress())
                .colombianState(userContactDto.getColombianState())
                .phoneNumber(userContactDto.getPhoneNumber())
                .postalCode(userContactDto.getPostalCode())
                .build();
    }

    private StoreVisualDescription mapStoreVisualDescription(StoreVisualDescriptionDto storeVisualDescriptionDto) {
        return StoreVisualDescription.builder()
                .portraitUrl(storeVisualDescriptionDto.getPortraitUrl())
                .prevImagesList(storeVisualDescriptionDto.getPrevImagesList())
                .physicalStoreImageUrl(storeVisualDescriptionDto.getPhysicalStoreImageUrl())
                .build();
    }

    private StoreDescription mapStoreDescription(StoreDescriptionDto storeDescriptionDto) {
        return StoreDescription.builder()
                .description(storeDescriptionDto.getDescription())
                .slogan(storeDescriptionDto.getSlogan())
                .tagsList(storeDescriptionDto.getTagsList())
                .build();
    }

    private StoreExistencePeriod mapStoreExistencePeriod(StoreExistencePeriodDto storeExistencePeriodDto) {
        return StoreExistencePeriod.builder()
                .startingDate(storeExistencePeriodDto.getStartingDate())
                .endingDate(storeExistencePeriodDto.getEndingDate())
                .build();
    }

}
