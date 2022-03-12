package co.edu.udea.ayds2.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    private static String TRACEABILITY_QUEUE = "monitoring.traceability.queue";

    private static String EXCHANGE = "monitoring.traceability.exchange";

    private static String ROUTING_KEY = "monitoring.traceability.routingKey";

    @Bean
    public Queue queue() {
        return new Queue(TRACEABILITY_QUEUE, true);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(this.queue())
                .to(this.topicExchange())
                .with(ROUTING_KEY);
    }

    public static String getTraceabilityQueue() {
        return RabbitMqConfig.TRACEABILITY_QUEUE;
    }

    public static String getExchange() {
        return RabbitMqConfig.EXCHANGE;
    }

    public static String getRoutingKey() {
        return RabbitMqConfig.ROUTING_KEY;
    }
}
