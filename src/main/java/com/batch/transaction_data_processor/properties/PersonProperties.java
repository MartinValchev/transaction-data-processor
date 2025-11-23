package com.batch.transaction_data_processor.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.person")
@Getter
public class PersonProperties {
    private int chunkSize;
    private String filePattern;
}
