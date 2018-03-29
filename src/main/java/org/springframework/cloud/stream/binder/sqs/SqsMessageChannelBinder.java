package org.springframework.cloud.stream.binder.sqs;

import java.util.Arrays;

import org.springframework.cloud.stream.binder.AbstractMessageChannelBinder;
import org.springframework.cloud.stream.binder.BinderHeaders;
import org.springframework.cloud.stream.binder.ExtendedConsumerProperties;
import org.springframework.cloud.stream.binder.ExtendedProducerProperties;
import org.springframework.cloud.stream.binder.ExtendedPropertiesBinder;
import org.springframework.cloud.stream.binder.sqs.properties.SqsBinderConfigurationProperties;
import org.springframework.cloud.stream.binder.sqs.properties.SqsConsumerProperties;
import org.springframework.cloud.stream.binder.sqs.properties.SqsExtendedBindingProperties;
import org.springframework.cloud.stream.binder.sqs.properties.SqsProducerProperties;
import org.springframework.cloud.stream.binder.sqs.provisioning.SqsProvisioner;
import org.springframework.cloud.stream.provisioning.ConsumerDestination;
import org.springframework.cloud.stream.provisioning.ProducerDestination;
import org.springframework.integration.aws.inbound.SqsMessageDrivenChannelAdapter;
import org.springframework.integration.aws.outbound.SqsMessageHandler;
import org.springframework.integration.core.MessageProducer;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import com.amazonaws.services.sqs.AmazonSQSAsync;

public class SqsMessageChannelBinder extends
	AbstractMessageChannelBinder<ExtendedConsumerProperties<SqsConsumerProperties>, 
								ExtendedProducerProperties<SqsProducerProperties>, 
								SqsProvisioner> 
	implements ExtendedPropertiesBinder<MessageChannel, SqsConsumerProperties, SqsProducerProperties> {
	
	private SqsExtendedBindingProperties extendedBindingProperties = new SqsExtendedBindingProperties();
	private final SqsBinderConfigurationProperties configurationProperties;
	private final AmazonSQSAsync amazonSqs;

	/*public SqsMessageChannelBinder(boolean supportsHeadersNatively, String[] headersToEmbed,
			SqsStreamProvisioner provisioningProvider) {
		super(supportsHeadersNatively, headersToEmbed, provisioningProvider);
		// TODO Auto-generated constructor stub
	}*/
	
	public SqsMessageChannelBinder(AmazonSQSAsync amazonSqs,
			SqsBinderConfigurationProperties configurationProperties,
			SqsProvisioner provisioningProvider) {
		super(true, headersToMap(configurationProperties), provisioningProvider);
		this.configurationProperties = configurationProperties;
		this.amazonSqs = amazonSqs;
	}

	//@Override
	public SqsConsumerProperties getExtendedConsumerProperties(String channelName) {
		// TODO Auto-generated method stub
		return null;
	}

	//@Override
	public SqsProducerProperties getExtendedProducerProperties(String channelName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected MessageProducer createConsumerEndpoint(ConsumerDestination destination, String group,
			ExtendedConsumerProperties<SqsConsumerProperties> properties) throws Exception {		
		SqsMessageDrivenChannelAdapter adapter = new SqsMessageDrivenChannelAdapter(this.amazonSqs, destination.getName());
		return adapter;
	}

	@Override
	protected MessageHandler createProducerMessageHandler(ProducerDestination destination,
			ExtendedProducerProperties<SqsProducerProperties> properties, MessageChannel errorChannel) throws Exception {
		SqsMessageHandler msgHandler = new SqsMessageHandler(this.amazonSqs);
		msgHandler.setQueue(destination.getName());
		return msgHandler;
	}
	
	private static String[] headersToMap(SqsBinderConfigurationProperties configurationProperties) {
		Assert.notNull(configurationProperties, "'configurationProperties' must not be null");
		if (ObjectUtils.isEmpty(configurationProperties.getHeaders())) {
			return BinderHeaders.STANDARD_HEADERS;
		}
		else {
			String[] combinedHeadersToMap = Arrays.copyOfRange(BinderHeaders.STANDARD_HEADERS, 0,
					BinderHeaders.STANDARD_HEADERS.length + configurationProperties.getHeaders().length);
			System.arraycopy(configurationProperties.getHeaders(), 0, combinedHeadersToMap,
					BinderHeaders.STANDARD_HEADERS.length, configurationProperties.getHeaders().length);
			return combinedHeadersToMap;
		}
	}

	public void setExtendedBindingProperties(SqsExtendedBindingProperties sqsExtendedBindingProperties) {
		this.extendedBindingProperties = sqsExtendedBindingProperties;
		
	}

}
