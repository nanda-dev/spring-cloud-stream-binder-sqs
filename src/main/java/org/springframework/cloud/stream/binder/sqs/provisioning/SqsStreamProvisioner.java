package org.springframework.cloud.stream.binder.sqs.provisioning;

import org.springframework.cloud.stream.binder.ExtendedConsumerProperties;
import org.springframework.cloud.stream.binder.ExtendedProducerProperties;
import org.springframework.cloud.stream.binder.sqs.properties.SqsConsumerProperties;
import org.springframework.cloud.stream.binder.sqs.properties.SqsProducerProperties;
import org.springframework.cloud.stream.provisioning.ConsumerDestination;
import org.springframework.cloud.stream.provisioning.ProducerDestination;
import org.springframework.cloud.stream.provisioning.ProvisioningException;
import org.springframework.cloud.stream.provisioning.ProvisioningProvider;

public class SqsStreamProvisioner implements
ProvisioningProvider<ExtendedConsumerProperties<SqsConsumerProperties>, ExtendedProducerProperties<SqsProducerProperties>> {

	@Override
	public ConsumerDestination provisionConsumerDestination(String arg0, String arg1,
			ExtendedConsumerProperties<SqsConsumerProperties> arg2) throws ProvisioningException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProducerDestination provisionProducerDestination(String arg0,
			ExtendedProducerProperties<SqsProducerProperties> arg1) throws ProvisioningException {
		// TODO Auto-generated method stub
		return null;
	}

}
