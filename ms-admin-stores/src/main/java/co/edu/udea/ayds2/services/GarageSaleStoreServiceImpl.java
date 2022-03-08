package co.edu.udea.ayds2.services;

import co.edu.udea.ayds2.dto.helpers.response.AppServerResponse;
import co.edu.udea.ayds2.dto.store.GarageSaleStoreDto;
import co.edu.udea.ayds2.mapper.StoreMapperFromEntityToDtoImpl;
import co.edu.udea.ayds2.mapper.interfaces.StoreMapperFromDtoToEntity;
import co.edu.udea.ayds2.mapper.interfaces.StoreMapperFromEntityToDto;
import co.edu.udea.ayds2.repository.GarageSaleStoreRepository;
import co.edu.udea.ayds2.services.interfaces.GarageSaleStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class GarageSaleStoreServiceImpl implements GarageSaleStoreService {

    private final StoreMapperFromEntityToDto storeMapperFromEntityToDto;
    private final StoreMapperFromDtoToEntity storeMapperFromDtoToEntity;

    private final GarageSaleStoreRepository garageSaleStoreRepository;

    @Override
    public AppServerResponse createStore(GarageSaleStoreDto garageSaleStoreDto) {

        return null;
    }
}
