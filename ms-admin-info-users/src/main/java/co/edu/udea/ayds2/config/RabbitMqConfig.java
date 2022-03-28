package co.edu.udea.ayds2.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    public static String TRACEABILITY_QUEUE = "monitoring.traceability.queue";

    public static String EXCHANGE = "monitoring.traceability.exchange";

    public static String ROUTING_KEY = "monitoring.traceability.routingKey";

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

}
