package org.springframework.cloud.stream.binder.sqs.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.cloud.stream.sqs.binder")
public class SqsBinderConfigurationProperties {	

	public String[] getHeaders() {
		return null;
	}	
	

}
