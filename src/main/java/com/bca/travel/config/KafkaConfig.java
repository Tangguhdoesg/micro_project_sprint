package com.bca.travel.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.bca.travel.model.CCPaymentEntity;
import com.bca.travel.model.CryptoPayemntGatewayAuth;

@EnableKafka
@Configuration
public class KafkaConfig {
	
	@Bean
    public ProducerFactory<String, CryptoPayemntGatewayAuth>
    producerFactoryCrypto()
    {
        Map<String, Object> config
            = new HashMap<>();
  
        config.put(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
            "127.0.0.1:9092");
  
        config.put(
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
            StringSerializer.class);
  
        config.put(
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
            JsonSerializer.class);
  
        return new DefaultKafkaProducerFactory<>(config);
    }
	
	@Bean
    public ProducerFactory<String, CCPaymentEntity>
    producerFactoryCard()
    {
        Map<String, Object> config
            = new HashMap<>();
  
        config.put(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
            "127.0.0.1:9092");
  
        config.put(
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
            StringSerializer.class);
  
        config.put(
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
            JsonSerializer.class);
  
        return new DefaultKafkaProducerFactory<>(config);
    }
  
    @Bean
    public KafkaTemplate<String, CryptoPayemntGatewayAuth>
    kafkaTemplateCrypto(){
        return new KafkaTemplate<>(
        		producerFactoryCrypto());
    }
    
    @Bean
    public KafkaTemplate<String, CCPaymentEntity>
    kafkaTemplateCC(){
        return new KafkaTemplate<>(
        		producerFactoryCard());
    }
	
	@Bean
	public ConsumerFactory<String, CryptoPayemntGatewayAuth> cryptoFactory(){
		JsonDeserializer<CryptoPayemntGatewayAuth> deserialize = new JsonDeserializer<>(CryptoPayemntGatewayAuth.class);
		deserialize.setRemoveTypeHeaders(false);
		deserialize.addTrustedPackages("*");
		deserialize.setUseTypeMapperForKey(true);
	       Map<String, Object> map
           = new HashMap<>();

       // put the host IP in the map
       map.put(ConsumerConfig
                   .BOOTSTRAP_SERVERS_CONFIG,
               "127.0.0.1:9092");

       // put the group ID of consumer in the map
       map.put(ConsumerConfig
                   .GROUP_ID_CONFIG,
               "id");
       map.put(ConsumerConfig
                   .KEY_DESERIALIZER_CLASS_CONFIG,
               StringDeserializer.class);
       map.put(ConsumerConfig
                   .VALUE_DESERIALIZER_CLASS_CONFIG,
               deserialize);
       map.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"latest");
       // return message in JSON formate
       return new DefaultKafkaConsumerFactory<>(map, new StringDeserializer(), deserialize);
	}
	
	@Bean
	public ConsumerFactory<String, CCPaymentEntity> ccFactory(){
		JsonDeserializer<CCPaymentEntity> deserialize = new JsonDeserializer<>(CCPaymentEntity.class);
		deserialize.setRemoveTypeHeaders(false);
		deserialize.addTrustedPackages("*");
		deserialize.setUseTypeMapperForKey(true);
	       Map<String, Object> map
           = new HashMap<>();

       // put the host IP in the map
       map.put(ConsumerConfig
                   .BOOTSTRAP_SERVERS_CONFIG,
               "127.0.0.1:9092");

       // put the group ID of consumer in the map
       map.put(ConsumerConfig
                   .GROUP_ID_CONFIG,
               "id");
       map.put(ConsumerConfig
                   .KEY_DESERIALIZER_CLASS_CONFIG,
               StringDeserializer.class);
       map.put(ConsumerConfig
                   .VALUE_DESERIALIZER_CLASS_CONFIG,
               deserialize);
       map.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"latest");
       // return message in JSON formate
       return new DefaultKafkaConsumerFactory<>(map, new StringDeserializer(), deserialize);
	}
	
	@Bean
    public ConcurrentKafkaListenerContainerFactory<String,CryptoPayemntGatewayAuth> cryptoListener(){
        ConcurrentKafkaListenerContainerFactory<String,CryptoPayemntGatewayAuth> factory
        = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(cryptoFactory());
        return factory;
    }
	
	@Bean
    public ConcurrentKafkaListenerContainerFactory<String,CCPaymentEntity> ccListener(){
        ConcurrentKafkaListenerContainerFactory<String,CCPaymentEntity> factory
        = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(ccFactory());
        return factory;
    }

}
