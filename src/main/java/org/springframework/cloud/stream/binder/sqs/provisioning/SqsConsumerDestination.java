package org.springframework.cloud.stream.binder.sqs.provisioning;

import org.springframework.cloud.stream.provisioning.ConsumerDestination;

public class SqsConsumerDestination implements ConsumerDestination {
	
	private final String queueName;
	
	SqsConsumerDestination(String queueName){
		this.queueName = "activation-queue";
	}

	//@Override
	public String getName() {
		return this.queueName;
	}

}
