package cn.ctodb.push.server.conf;

import cn.ctodb.push.server.kafka.KafkaConsumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * All rights Reserved, Designed By www.ctodb.cn
 *
 * @version V1.0
 * @author: lichaohn@163.com
 * @Copyright: 2018 www.ctodb.cn Inc. All rights reserved.
 */
// @Configuration
@EnableKafka
public class KafkaConfiguration {

    @Bean
    public KafkaConsumer kafkaProduct() {
        return new KafkaConsumer();
    }

}
