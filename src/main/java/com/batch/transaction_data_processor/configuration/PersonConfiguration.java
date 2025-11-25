package com.batch.transaction_data_processor.configuration;

import com.batch.transaction_data_processor.dtos.PersonDto;
import com.batch.transaction_data_processor.processor.PersonProcessor;
import com.batch.transaction_data_processor.properties.PersonProperties;
import com.batch.transaction_data_processor.writer.PersonWriter;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.parameters.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.ItemReader;
import org.springframework.batch.infrastructure.item.json.JacksonJsonObjectReader;
import org.springframework.batch.infrastructure.item.json.builder.JsonItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@AllArgsConstructor
public class PersonConfiguration {

    private PersonProperties properties;

    @Bean
    public ItemReader<PersonDto> itemReader() {
        return new JsonItemReaderBuilder<PersonDto>()
                .jsonObjectReader(new JacksonJsonObjectReader<>(PersonDto.class))
                .resource(new ClassPathResource("large_person_data.json"))
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
}
