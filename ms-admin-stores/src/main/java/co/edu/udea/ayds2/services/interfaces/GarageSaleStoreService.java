package co.edu.udea.ayds2.services.interfaces;

import co.edu.udea.ayds2.collection.store.GarageSaleStore;
import co.edu.udea.ayds2.dto.helpers.response.AppServerResponse;
import co.edu.udea.ayds2.dto.store.GarageSaleStoreDto;

import javax.validation.Valid;
import java.util.List;

public interface GarageSaleStoreService {
    boolean createStore(@Valid GarageSaleStoreDto garageSaleStoreDto);
    List<GarageSaleStore> getAllActiveStores();
}
