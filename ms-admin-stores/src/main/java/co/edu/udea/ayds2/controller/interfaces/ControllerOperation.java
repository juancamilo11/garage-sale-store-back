package co.edu.udea.ayds2.controller.interfaces;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface ControllerOperation {

    default <T> ResponseEntity<T> getResponseEntity(Boolean response, T value, T errorValue ) {
        return response.equals(Boolean.TRUE) ?
                new ResponseEntity<>(value, HttpStatus.OK) :
                new ResponseEntity<>(errorValue, HttpStatus.BAD_REQUEST);
    }

}
