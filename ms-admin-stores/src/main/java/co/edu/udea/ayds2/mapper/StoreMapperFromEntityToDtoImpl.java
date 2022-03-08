package co.edu.udea.ayds2.mapper;

import co.edu.udea.ayds2.collection.store.GarageSaleStore;
import co.edu.udea.ayds2.collection.store.StoreDescription;
import co.edu.udea.ayds2.collection.store.StoreExistencePeriod;
import co.edu.udea.ayds2.collection.store.StoreVisualDescription;
import co.edu.udea.ayds2.collection.user.User;
import co.edu.udea.ayds2.collection.user.UserContact;
import co.edu.udea.ayds2.dto.store.*;
import co.edu.udea.ayds2.dto.store.product.ProductDto;
import co.edu.udea.ayds2.dto.user.UserContactDto;
import co.edu.udea.ayds2.dto.user.UserDto;
import co.edu.udea.ayds2.mapper.interfaces.StoreMapperFromEntityToDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class StoreMapperFromEntityToDtoImpl implements StoreMapperFromEntityToDto {

    @Override
    public Function<GarageSaleStore, GarageSaleStoreDto> mapFromEntityToDto() {
//        return (garageSaleStore) -> GarageSaleStoreDto.builder()
//                .id(garageSaleStore.getId())
//                .storeName(garageSaleStore.getStoreName())
//                .storeExistencePeriod(mapStoreExistencePeriod(garageSaleStore.getStoreExistencePeriod()))
//                .storeDescription(mapStoreDescription(garageSaleStore.getStoreDescription()))
//                .storeVisualDescription(mapStoreVisualDescription(garageSaleStore.getStoreVisualDescription()))
//                .seller(mapSellerInformation(garageSaleStore.getSeller()))
//                .productList(mapProductList(garageSaleStore))
//                .purchaseTestimonialList(mapPurchaseTestimonialList(garageSaleStore))
//                .purchaseOrderList(mapPurchaseOrderList(garageSaleStore))
//                .build();
        return null;
    }

    private List<PurchaseOrderDto> mapPurchaseOrderList(GarageSaleStore garageSaleStore) {
        return garageSaleStore.getPurchaseOrderList()
                .stream()
                .map(purchaseOrder -> PurchaseOrderDto.builder()
                        .build())
                .collect(Collectors.toList());
    }

    private List<PurchaseTestimonialDto> mapPurchaseTestimonialList(GarageSaleStore garageSaleStore) {
        return garageSaleStore.getPurchaseTestimonialList()
                .stream()
                .map(purchaseTestimonial -> PurchaseTestimonialDto.builder()
                        .build())
                .collect(Collectors.toList());
    }

    private List<ProductDto> mapProductList(GarageSaleStore garageSaleStore) {
        return garageSaleStore.getProductList()
                .stream()
                .map(product -> ProductDto.builder().build())
                .collect(Collectors.toList());
    }

    private UserDto mapSellerInformation(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getId())
                .userContact(mapUserContact(user.getUserContact()))
                .occupation(user.getOccupation())
                .dateOfBirth(user.getDateOfBirth())
                .registerDate(user.getRegisterDate())
                .creationTime(user.getCreationTime())
                .lastSignInTime(user.getLastSignInTime())
                .registerDate(user.getRegisterDate())
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


    public GarageSaleStore mapFromDtoToEntity(GarageSaleStoreDto garageSaleStoreDto) {
        return null;
    }
}
