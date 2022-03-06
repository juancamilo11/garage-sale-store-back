package co.edu.udea.ayds2.monitoring;

import co.edu.udea.ayds2.dto.helpers.response.AppServerResponse;
import com.google.gson.Gson;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;

/**
 * Singleton Patter Implementation
 * */

public class TraceabilityEmitterImpl implements TraceabilityEmitter {

    private static TraceabilityEmitterImpl emitter = null;

    @Value("${rabbitmq.traceability.queue}")
    public String TRACEABILITY_QUEUE;

    @Value("${rabbitmq.traceability.exchange}")
    public String EXCHANGE;

    @Value("${rabbitmq.traceability.routingKey}")
    public String ROUTING_KEY;

    private final RabbitTemplate rabbitTemplate = new RabbitTemplate();
    private final Gson gson = new Gson();

    private TraceabilityEmitterImpl() {
    }

    public static TraceabilityEmitterImpl getInstance() {
        if(TraceabilityEmitterImpl.emitter == null) {
            emitter = new TraceabilityEmitterImpl();
        }
        return TraceabilityEmitterImpl.emitter;
    }

    public Queue queue() {
        return new Queue(TRACEABILITY_QUEUE, true);
    }

    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE);
    }

    public Binding binding() {
        return BindingBuilder
                .bind(this.queue())
                .to(this.topicExchange())
                .with(ROUTING_KEY);
    }

    @Override
    public void emitTraceability(AppServerResponse appServerResponse) {
        this.rabbitTemplate
                .convertAndSend(EXCHANGE, ROUTING_KEY, gson.toJson(appServerResponse));
    }

}