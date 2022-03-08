package co.edu.udea.ayds2.mapper;

import co.edu.udea.ayds2.collection.store.*;
import co.edu.udea.ayds2.collection.store.product.Product;
import co.edu.udea.ayds2.collection.user.User;
import co.edu.udea.ayds2.collection.user.UserContact;
import co.edu.udea.ayds2.dto.store.*;
import co.edu.udea.ayds2.dto.store.product.ProductDto;
import co.edu.udea.ayds2.dto.user.UserContactDto;
import co.edu.udea.ayds2.dto.user.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class StoreMapperFromDtoToEntity {

//    public Function<GarageSaleStoreDto, GarageSaleStore> mapFromDtoToEntity() {
//        return (garageSaleStoreDto) -> GarageSaleStore.builder()
//                .id(garageSaleStoreDto.getId())
//                .storeName(garageSaleStoreDto.getStoreName())
//                .storeExistencePeriod(mapStoreExistencePeriod(garageSaleStoreDto.getStoreExistencePeriod()))
//                .storeDescription(mapStoreDescription(garageSaleStoreDto.getStoreDescription()))
//                .storeVisualDescription(mapStoreVisualDescription(garageSaleStoreDto.getStoreVisualDescription()))
//                .seller(mapSellerInformation(garageSaleStoreDto.getSeller()))
//                .productList(mapProductList(garageSaleStoreDto.getProductList()))
//                .purchaseTestimonialList(mapPurchaseTestimonialList(garageSaleStoreDto.getPurchaseTestimonialList()))
//                .purchaseOrderList(mapPurchaseOrderList(garageSaleStoreDto.getPurchaseOrderList()))
//                .build();
//    }
//
//    private List<PurchaseOrder> mapPurchaseOrderList(List<PurchaseOrderDto> purchaseOrderDtoList) {
//        return purchaseOrderDtoList.stream().map(purchaseOrder -> PurchaseOrder.builder().build()).collect(Collectors.toList());
//    }
//
//    private List<PurchaseTestimonial> mapPurchaseTestimonialList(List<PurchaseTestimonialDto> purchaseTestimonialDtoList) {
//        return purchaseTestimonialDtoList.stream().map(purchaseTestimonial -> PurchaseTestimonial.builder().build()).collect(Collectors.toList());
//    }
//
//    private List<Product> mapProductList(List<ProductDto> productDtoList) {
//        return productDtoList.stream().map(productDto -> Product.builder().build()).collect(Collectors.toList());
//    }
//
//    private User mapSellerInformation(UserDto userDto) {
//        return User.builder()
//                .id(userDto.getId())
//                .name(userDto.getId())
//                .userContact(mapUserContact(userDto.getUserContact()))
//                .occupation(userDto.getOccupation())
//                .dateOfBirth(userDto.getDateOfBirth())
//                .registerDate(userDto.getRegisterDate())
//                .creationTime(userDto.getCreationTime())
//                .lastSignInTime(userDto.getLastSignInTime())
//                .registerDate(userDto.getRegisterDate())
//                .build();
//    }
//
//    private UserContact mapUserContact(UserContactDto userContactDto) {
//        return UserContact.builder()
//                .email(userContactDto.getEmail())
//                .address(userContactDto.getAddress())
//                .colombianState(userContactDto.getColombianState())
//                .phoneNumber(userContactDto.getPhoneNumber())
//                .postalCode(userContactDto.getPostalCode())
//                .build();
//    }
//
//    private StoreVisualDescription mapStoreVisualDescription(StoreVisualDescriptionDto storeVisualDescriptionDto) {
//        return StoreVisualDescription.builder()
//                .portraitUrl(storeVisualDescriptionDto.getPortraitUrl())
//                .prevImagesList(storeVisualDescriptionDto.getPrevImagesList())
//                .physicalStoreImageUrl(storeVisualDescriptionDto.getPhysicalStoreImageUrl())
//                .build();
//    }
//
//    private StoreDescription mapStoreDescription(StoreDescriptionDto storeDescriptionDto) {
//        return StoreDescription.builder()
//                .description(storeDescriptionDto.getDescription())
//                .slogan(storeDescriptionDto.getSlogan())
//                .tagsList(storeDescriptionDto.getTagsList())
//                .build();
//    }
//
//    private StoreExistencePeriod mapStoreExistencePeriod(StoreExistencePeriodDto storeExistencePeriodDto) {
//        return StoreExistencePeriod.builder()
//                .startingDate(storeExistencePeriodDto.getStartingDate())
//                .endingDate(storeExistencePeriodDto.getEndingDate())
//                .build();
//    }

}
