package org.springframework.cloud.stream.binder.sqs.provisioning;

import org.springframework.cloud.stream.binder.ExtendedConsumerProperties;
import org.springframework.cloud.stream.binder.ExtendedProducerProperties;
import org.springframework.cloud.stream.binder.sqs.properties.SqsBinderConfigurationProperties;
import org.springframework.cloud.stream.binder.sqs.properties.SqsConsumerProperties;
import org.springframework.cloud.stream.binder.sqs.properties.SqsProducerProperties;
import org.springframework.cloud.stream.provisioning.ConsumerDestination;
import org.springframework.cloud.stream.provisioning.ProducerDestination;
import org.springframework.cloud.stream.provisioning.ProvisioningException;
import org.springframework.cloud.stream.provisioning.ProvisioningProvider;

import com.amazonaws.services.sqs.AmazonSQSAsync;

public class SqsProvisioner implements
	ProvisioningProvider<ExtendedConsumerProperties<SqsConsumerProperties>, 
	ExtendedProducerProperties<SqsProducerProperties>> {
	
	private final AmazonSQSAsync amazonSqs;

	private final SqsBinderConfigurationProperties configurationProperties;

	public SqsProvisioner(AmazonSQSAsync amazonSqs, SqsBinderConfigurationProperties configurationProperties) {
		this.amazonSqs = amazonSqs;
		this.configurationProperties = configurationProperties;
	}

	@Override
	public ConsumerDestination provisionConsumerDestination(String name, String group,
			ExtendedConsumerProperties<SqsConsumerProperties> properties) throws ProvisioningException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProducerDestination provisionProducerDestination(String name,
			ExtendedProducerProperties<SqsProducerProperties> properties) throws ProvisioningException {
		// TODO Auto-generated method stub
		return null;
	}

}
