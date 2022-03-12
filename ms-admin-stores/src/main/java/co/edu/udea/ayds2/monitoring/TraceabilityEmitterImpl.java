package co.edu.udea.ayds2.monitoring;

import co.edu.udea.ayds2.config.RabbitMqConfig;
import co.edu.udea.ayds2.dto.helpers.response.AppServerResponse;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class TraceabilityEmitterImpl implements TraceabilityEmitter {

    private final RabbitTemplate rabbitTemplate = new RabbitTemplate();
    private final Gson gson = new Gson();

    @Override
    public void emitTraceability(AppServerResponse appServerResponse) {
        this.rabbitTemplate
                .convertAndSend(RabbitMqConfig.getExchange(), RabbitMqConfig.getRoutingKey(), gson.toJson(appServerResponse));
    }

}
