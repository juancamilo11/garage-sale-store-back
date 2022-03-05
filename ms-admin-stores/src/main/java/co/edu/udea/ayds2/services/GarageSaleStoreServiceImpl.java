package co.edu.udea.ayds2.services;

import co.edu.udea.ayds2.dto.helpers.AppServerResponse;
import co.edu.udea.ayds2.dto.store.GarageSaleStoreDto;
import co.edu.udea.ayds2.mapper.GarageSaleStoreMapper;
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
    private final GarageSaleStoreMapper garageSaleStoreMapper;

    @Autowired
    private final GarageSaleStoreRepository garageSaleStoreRepository;

    @Builder(toBuilder = true)
    public GarageSaleStoreServiceImpl(GarageSaleStoreService garageSaleStoreService,
                                      GarageSaleStoreMapper garageSaleStoreMapper,
                                      GarageSaleStoreRepository garageSaleStoreRepository) {
        this.garageSaleStoreService = garageSaleStoreService;
        this.garageSaleStoreMapper = garageSaleStoreMapper;
        this.garageSaleStoreRepository = garageSaleStoreRepository;
    }

    @Override
    public AppServerResponse createStore(GarageSaleStoreDto garageSaleStoreDto) {

        return null;
    }
}
