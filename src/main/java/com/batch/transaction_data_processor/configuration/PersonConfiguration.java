package com.batch.transaction_data_processor.configuration;

import com.batch.transaction_data_processor.dtos.PersonDto;
import com.batch.transaction_data_processor.processor.PersonProcessor;
import com.batch.transaction_data_processor.properties.PersonProperties;
import com.batch.transaction_data_processor.writer.PersonWriter;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.parameters.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.ItemReader;
import org.springframework.batch.infrastructure.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;

@Configuration
public class PersonConfiguration {

    @Autowired
    private PersonProperties properties;

    @Bean
    public ItemReader<PersonDto> itemReader(PathMatchingResourcePatternResolver resolver) throws IOException {
        Resource[] resources = resolver.getResources(properties.getFilePattern() + "\\" + properties.getFilePattern());
        return new MultiResourceItemReaderBuilder<PersonDto>()
                .resources(resources)
                .name("personJsonReader")
                .build();
    }

    @Bean
    public Step personStep(JobRepository jobRepository, ItemReader itemReader,
                           PersonProcessor processor, PersonWriter personWriter) {
        return new StepBuilder("personStep", jobRepository)
                .chunk(properties.getChunkSize())
                .reader(itemReader)
                .processor(processor)
                .writer(personWriter)
                .build();
    }

    @Bean
    public Job personJob(JobRepository jobRepository, Step personStep) {
        return new JobBuilder( "personJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(personStep)
                .build();
    }

    @Bean
    public PathMatchingResourcePatternResolver resolver() {
        return new PathMatchingResourcePatternResolver();
    }

}
