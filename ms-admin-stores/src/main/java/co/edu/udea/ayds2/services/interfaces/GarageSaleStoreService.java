package co.edu.udea.ayds2.services.interfaces;

import co.edu.udea.ayds2.dto.helpers.response.AppServerResponse;
import co.edu.udea.ayds2.dto.store.GarageSaleStoreDto;

import javax.validation.Valid;

public interface GarageSaleStoreService {
    AppServerResponse createStore(@Valid GarageSaleStoreDto garageSaleStoreDto);
}
