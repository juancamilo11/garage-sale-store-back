package co.edu.udea.ayds2.services;

import co.edu.udea.ayds2.collection.store.GarageSaleStore;
import co.edu.udea.ayds2.dto.helpers.response.AppServerResponse;
import co.edu.udea.ayds2.dto.helpers.response.EnumResponseStatus;
import co.edu.udea.ayds2.dto.store.GarageSaleStoreDto;
import co.edu.udea.ayds2.mapper.interfaces.StoreMapperFromDtoToEntity;
import co.edu.udea.ayds2.mapper.interfaces.StoreMapperFromEntityToDto;
import co.edu.udea.ayds2.repository.GarageSaleStoreRepository;
import co.edu.udea.ayds2.services.interfaces.GarageSaleStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Service
@Validated
@RequiredArgsConstructor
public class GarageSaleStoreServiceImpl implements GarageSaleStoreService {

    private final StoreMapperFromEntityToDto storeMapperFromEntityToDto;
    private final StoreMapperFromDtoToEntity storeMapperFromDtoToEntity;

    private final GarageSaleStoreRepository garageSaleStoreRepository;

////    @Value("${microservice.name}")
//    private final String microserviceName = "ms-admin-stores";

    @Override
    public AppServerResponse createStore(GarageSaleStoreDto garageSaleStoreDto) {
        GarageSaleStore result = this.garageSaleStoreRepository.save(this.storeMapperFromDtoToEntity.mapFromDtoToEntity().apply(garageSaleStoreDto));
        return AppServerResponse.builder()
                .currentDate(LocalDate.now())
                .microserviceName("ms-admin-stores")
                .status(result != null ? EnumResponseStatus.OK : EnumResponseStatus.ERROR)
                .build();
    }
}
