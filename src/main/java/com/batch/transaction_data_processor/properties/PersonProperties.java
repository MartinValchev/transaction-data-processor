package com.batch.transaction_data_processor.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.person")
public class PersonProperties {
    private int chunkSize;
    private String basePath;
    private String filePattern;

    public int getChunkSize() {
        return chunkSize;
    }

    public String getBasePath() {
        return basePath;
    }

    public String getFilePattern() {
        return filePattern;
    }
}
