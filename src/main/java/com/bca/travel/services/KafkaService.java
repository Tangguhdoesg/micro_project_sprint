package com.bca.travel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.bca.travel.model.Transaction;

@Service
public class KafkaService {
	
	@Autowired
    private KafkaTemplate<String, Transaction>kafkaTemplate;
	
	@KafkaListener(
			  topicPartitions = @TopicPartition(topic = "travelTransaction",
			  partitionOffsets = {
			    @PartitionOffset(partition = "0", initialOffset = "0")}),
			  containerFactory = "transactionListener")
	public void consumeData() {
		
	}
	
	public void produceData() {
		
	}

}
