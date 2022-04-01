package co.edu.udea.ayds2.monitoring;

import co.edu.udea.ayds2.config.RabbitMqConfig;
import co.edu.udea.ayds2.dto.helpers.response.AppServerResponse;
import com.google.gson.Gson;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

public class TraceabilityEmitterImpl implements TraceabilityEmitter {

    private static TraceabilityEmitterImpl instance;

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    @Builder(toBuilder = true)
    private TraceabilityEmitterImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public static synchronized TraceabilityEmitterImpl getInstance(){
        if(instance == null){
            instance = TraceabilityEmitterImpl
                    .builder()
                    .build();
        }
        return instance;
    }

    @Override
    public void emitTraceability(AppServerResponse appServerResponse) {
        final Gson gson = new Gson();
        this.rabbitTemplate
                .convertAndSend(RabbitMqConfig.EXCHANGE, RabbitMqConfig.ROUTING_KEY, gson.toJson(appServerResponse));
    }

}
