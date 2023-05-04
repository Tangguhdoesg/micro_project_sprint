package com.bca.travel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.bca.travel.model.CCPaymentEntity;
import com.bca.travel.model.CryptoPaymentEntity;
import com.bca.travel.model.Transaction;
import com.bca.travel.repository.TransactionRepository;

@Service
public class KafkaService {
	
	@Autowired TransactionRepository transRepo;
	
	@Autowired
    private KafkaTemplate<String, CryptoPaymentEntity>kafkaTemplateCryp;
	
	@Autowired
    private KafkaTemplate<String, CCPaymentEntity>kafkaTemplateCc;
	
	private static final String TOPICRYP = "CryptoPaymentAuth";
	private static final String TOPICCC = "CCPaymentAuth";
	
	@KafkaListener(
			  topicPartitions = @TopicPartition(topic = TOPICRYP,
			  partitionOffsets = {
			    @PartitionOffset(partition = "0", initialOffset = "0")}),
			  containerFactory = "cryptoListener")
	public void consumeResponseCrypto(CryptoPaymentEntity crypto) {
		if(crypto.getMessageType().equalsIgnoreCase("Response")) {
			Transaction transaction = transRepo.findById(crypto.getPaymentId()).get();
			if(transaction==null) {
				return;
			}
			transaction.setStatus(crypto.getStatus());
			
			transRepo.save(transaction);
		}else {
			
		}
	}
	
	@KafkaListener(
			  topicPartitions = @TopicPartition(topic = TOPICCC,
			  partitionOffsets = {
			    @PartitionOffset(partition = "0", initialOffset = "0")}),
			  containerFactory = "ccListener")
	public void consumeResponseCC(CCPaymentEntity cc) {
		if(cc.getMessageType().equalsIgnoreCase("Response")) {
			Transaction transaction = transRepo.findById(cc.getTransactionId()).get();
			if(transaction==null) {
				return;
			}
			transaction.setStatus(cc.getStatus());
			
			transRepo.save(transaction);
		}else {

		}
	}
	
	public String paymentCrypto(CryptoPaymentEntity payment) {
		
		kafkaTemplateCryp.send(TOPICRYP,payment);
		return "ok";
		
	}
	
public String paymentCC(CCPaymentEntity payment) {
		
		kafkaTemplateCc.send(TOPICCC,payment);
		return "ok";
		
	}

}
