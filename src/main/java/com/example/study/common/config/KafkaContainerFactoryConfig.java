package com.example.study.common.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 沉香微风
 * @date 2020-09-25
 * 自定义的kafka ContainerFactoryConfig
 * @see org.springframework.kafka.annotation.KafkaListener#containerFactory
 *
 */
@Configuration
public class KafkaContainerFactoryConfig {

 private ConcurrentKafkaListenerContainerFactory concurrentKafkaListenerContainerFactory;
  	@Autowired
	public  void setConcurrentKafkaListenerContainerFactory(ConcurrentKafkaListenerContainerFactory concurrentKafkaListenerContainerFactory){
       this.concurrentKafkaListenerContainerFactory=concurrentKafkaListenerContainerFactory;
	}

	@Bean("ackContainerFactory")
	public ConcurrentKafkaListenerContainerFactory ackContainerFactory() {
		ConsumerFactory consumerFactory = concurrentKafkaListenerContainerFactory.getConsumerFactory();
		Map configurationProperties = consumerFactory.getConfigurationProperties();
		HashMap map = new HashMap(10);
		map.putAll(configurationProperties);
		map.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
		map.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
		ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
		factory.setConsumerFactory(new DefaultKafkaConsumerFactory(map));
		factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
	    factory.setConsumerFactory(new DefaultKafkaConsumerFactory(map));
		return factory;
	}

	@Bean("batchContainerFactory")
	public ConcurrentKafkaListenerContainerFactory batchContainerFactory() {
		ConsumerFactory consumerFactory = concurrentKafkaListenerContainerFactory.getConsumerFactory();
		Map configurationProperties = consumerFactory.getConfigurationProperties();
		HashMap consumerMap = new HashMap(10);
		consumerMap.putAll(configurationProperties);
		consumerMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
		consumerMap.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
		consumerMap.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
//		该项默认是500，默认一次可以最大拉取到的数量是500
//		propsMap.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 100);
		consumerMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		consumerMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
		factory.setBatchListener(true);
		factory.setConsumerFactory(new DefaultKafkaConsumerFactory(consumerMap));
		factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
		factory.getContainerProperties().setPollTimeout(3000);
		factory.setConsumerFactory(new DefaultKafkaConsumerFactory(consumerMap));
		return factory;
	}

}
