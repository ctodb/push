package cn.ctodb.push.server.conf;

import cn.ctodb.push.server.kafka.KafkaConsumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * Created by cc on 2017/8/9.
 */
// @Configuration
@EnableKafka
public class KafkaConfiguration {

    @Bean
    public KafkaConsumer kafkaProduct() {
        return new KafkaConsumer();
    }

}
