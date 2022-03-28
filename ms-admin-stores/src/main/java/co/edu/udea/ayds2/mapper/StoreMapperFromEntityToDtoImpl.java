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
import co.edu.udea.ayds2.mapper.interfaces.StoreMapperFromEntityToDto;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class StoreMapperFromEntityToDtoImpl implements StoreMapperFromEntityToDto {

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
                .purchaseTestimonialList(mapPurchaseTestimonialList(garageSaleStore.getPurchaseTestimonialList()))
                .purchaseOrderList(mapPurchaseOrderList(garageSaleStore.getPurchaseOrderList()))
                .build();
    }

    private List<PurchaseOrderDto> mapPurchaseOrderList(List<PurchaseOrder> purchaseOrderList) {
        return purchaseOrderList
                .stream()
                .map(purchaseOrder -> PurchaseOrderDto.builder()
                        .id(purchaseOrder.getId())
                        .date(purchaseOrder.getDate())
                        .purchaseTestimonial(mapPurchaseTestimonial(purchaseOrder.getPurchaseTestimonial()))
                        .purchasedItemList(mapPurchasedItemsList(purchaseOrder.getPurchasedItemList()))
                        .build())
                .collect(Collectors.toList());
    }

    private PurchaseTestimonialDto mapPurchaseTestimonial(PurchaseTestimonial purchaseTestimonial) {
        return PurchaseTestimonialDto.builder()
                .title(purchaseTestimonial.getTitle())
                .purchaseTestimonial(purchaseTestimonial.getPurchaseTestimonial())
                .date(purchaseTestimonial.getDate())
                .grade(purchaseTestimonial.getGrade())
                .user(mapSellerInformation(purchaseTestimonial.getUser()))
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

    private List<PurchaseTestimonialDto> mapPurchaseTestimonialList(List<PurchaseTestimonial> purchaseTestimonialList) {
        return purchaseTestimonialList.stream()
                .map(purchaseTestimonial -> PurchaseTestimonialDto.builder()
                        .title(purchaseTestimonial.getTitle())
                        .date(purchaseTestimonial.getDate())
                        .grade(purchaseTestimonial.getGrade())
                        .user(mapSellerInformation(purchaseTestimonial.getUser()))
                        .build())
                .collect(Collectors.toList());
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

    private UserDto mapSellerInformation(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getId())
                .occupation(user.getOccupation())
                .dateOfBirth(user.getDateOfBirth())
                .registerDate(user.getRegisterDate())
                .creationTime(user.getCreationTime())
                .lastSignInTime(user.getLastSignInTime())
                .registerDate(user.getRegisterDate())
                .userContact(mapUserContact(user.getUserContact()))
                .build();
    }

    private UserContactDto mapUserContact(UserContact userContact) {
        return UserContactDto.builder()
                .email(userContact.getEmail())
                .address(userContact.getAddress())
                .colombianState(userContact.getColombianState())
                .phoneNumber(userContact.getPhoneNumber())
                .postalCode(userContact.getPostalCode())
                .build();
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
