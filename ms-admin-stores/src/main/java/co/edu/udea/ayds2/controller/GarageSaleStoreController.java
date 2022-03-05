package co.edu.udea.ayds2.controller;

import co.edu.udea.ayds2.dto.helpers.response.AppServerResponse;
import co.edu.udea.ayds2.dto.helpers.response.EnumResponseStatus;
import co.edu.udea.ayds2.dto.store.GarageSaleStoreDto;
import co.edu.udea.ayds2.monitoring.TraceabilityEmitterImpl;
import co.edu.udea.ayds2.services.interfaces.GarageSaleStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class GarageSaleStoreController {

    @Autowired
    private final GarageSaleStoreService garageSaleStoreService;

    @Autowired
    private final TraceabilityEmitterImpl traceabilityEmitter;

    public GarageSaleStoreController(GarageSaleStoreService garageSaleStoreService, TraceabilityEmitterImpl traceabilityEmitter) {
        this.garageSaleStoreService = garageSaleStoreService;
        this.traceabilityEmitter = traceabilityEmitter;
    }

    @PostMapping("/post/store")
    public ResponseEntity<String> createStore(@RequestBody GarageSaleStoreDto garageSaleStoreDto) {

        //toDo --> Implement save store, then send the traceability

        AppServerResponse response = this.garageSaleStoreService.createStore(garageSaleStoreDto);
        this.traceabilityEmitter.emitTraceability(response);

        return response.getStatus().equals(EnumResponseStatus.OK) ?
                new ResponseEntity<>("Success", HttpStatus.OK) :
                new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);

    }

}
