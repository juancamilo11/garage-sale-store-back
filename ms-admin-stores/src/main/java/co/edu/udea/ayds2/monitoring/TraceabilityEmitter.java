package co.edu.udea.ayds2.monitoring;

import co.edu.udea.ayds2.dto.helpers.AppServerResponse;

public interface TraceabilityEmitter {

    void emitTraceability(AppServerResponse appServerResponse);

}
