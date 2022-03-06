package co.edu.udea.ayds2.services;

import co.edu.udea.ayds2.dto.helpers.response.AppServerResponse;
import co.edu.udea.ayds2.dto.store.GarageSaleStoreDto;
import co.edu.udea.ayds2.mapper.StoreMapperFromEntityToDto;
import co.edu.udea.ayds2.repository.GarageSaleStoreRepository;
import co.edu.udea.ayds2.services.interfaces.GarageSaleStoreService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class GarageSaleStoreServiceImpl implements GarageSaleStoreService {

    @Autowired
    private final GarageSaleStoreService garageSaleStoreService;

    @Autowired
    private final StoreMapperFromEntityToDto storeMapperFromEntityToDto;

    @Autowired
    private final GarageSaleStoreRepository garageSaleStoreRepository;

    @Builder(toBuilder = true)
    public GarageSaleStoreServiceImpl(GarageSaleStoreService garageSaleStoreService,
                                      StoreMapperFromEntityToDto storeMapperFromEntityToDto,
                                      GarageSaleStoreRepository garageSaleStoreRepository) {
        this.garageSaleStoreService = garageSaleStoreService;
        this.storeMapperFromEntityToDto = storeMapperFromEntityToDto;
        this.garageSaleStoreRepository = garageSaleStoreRepository;
    }

    @Override
    public AppServerResponse createStore(GarageSaleStoreDto garageSaleStoreDto) {

        return null;
    }
}
