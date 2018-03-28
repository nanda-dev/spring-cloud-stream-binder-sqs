package org.springframework.cloud.stream.binder.sqs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.aws.core.region.RegionProvider;
import org.springframework.cloud.stream.binder.Binder;
import org.springframework.cloud.stream.binder.sqs.SqsMessageChannelBinder;
import org.springframework.cloud.stream.binder.sqs.properties.SqsBinderConfigurationProperties;
import org.springframework.cloud.stream.binder.sqs.properties.SqsExtendedBindingProperties;
import org.springframework.cloud.stream.binder.sqs.provisioning.SqsProvisioner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;

@Configuration
@ConditionalOnMissingBean(Binder.class)
@EnableConfigurationProperties({ SqsBinderConfigurationProperties.class, SqsExtendedBindingProperties.class })
public class SqsBinderConfiguration {
	
	@Autowired
	private SqsBinderConfigurationProperties configurationProperties;
	
	@Bean
	@ConditionalOnMissingBean
	public AmazonSQSAsync amazonSqs(AWSCredentialsProvider awsCredentialsProvider,
			RegionProvider regionProvider) {

		return AmazonSQSAsyncClientBuilder.standard()
				.withCredentials(awsCredentialsProvider)
				.withRegion(regionProvider.getRegion().getName())
				.build();
	}
	
	@Bean
	public SqsProvisioner provisioningProvider(AmazonSQSAsync amazonSqs) {
		return new SqsProvisioner(amazonSqs, this.configurationProperties);
	}
	
	@Bean
	public SqsMessageChannelBinder sqsMessageChannelBinder(AmazonSQSAsync amazonSQSAsync,
			SqsProvisioner provisioningProvider, SqsExtendedBindingProperties sqsExtendedBindingProperties) {

		SqsMessageChannelBinder sqsMessageChannelBinder = new SqsMessageChannelBinder(amazonSQSAsync,
				this.configurationProperties, provisioningProvider);
		sqsMessageChannelBinder.setExtendedBindingProperties(sqsExtendedBindingProperties);

		return sqsMessageChannelBinder;
	}
}
