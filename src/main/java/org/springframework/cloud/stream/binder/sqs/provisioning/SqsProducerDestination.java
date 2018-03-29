package org.springframework.cloud.stream.binder.sqs.provisioning;

import org.springframework.cloud.stream.provisioning.ProducerDestination;

public class SqsProducerDestination implements ProducerDestination{
	private final String queueName;
	
	SqsProducerDestination(String queueName){
		this.queueName = "activation-queue";
	}

	//@Override
	public String getName() {
		return this.queueName;
	}

	//@Override
	public String getNameForPartition(int partition) {
		return this.queueName;
	}

}
