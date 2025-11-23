package com.batch.transaction_data_processor.configuration;

import com.batch.transaction_data_processor.dtos.Person;
import com.batch.transaction_data_processor.dtos.PersonDto;
import lombok.AllArgsConstructor;
import org.springframework.batch.infrastructure.item.ItemReader;
import org.springframework.batch.infrastructure.item.database.JdbcBatchItemWriter;
import org.springframework.batch.infrastructure.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.infrastructure.item.json.JacksonJsonObjectReader;
import org.springframework.batch.infrastructure.item.json.builder.JsonItemReaderBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@AllArgsConstructor
public class PersonConfiguration {
    private JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;

    private ItemReader<PersonDto> itemReader() {
        return new JsonItemReaderBuilder<PersonDto>()
                .jsonObjectReader(new JacksonJsonObjectReader<>(PersonDto.class))
                .resource(new ClassPathResource("large_person_data.json"))
                .name("personJsonReader")
                .build();
    }

    private JdbcBatchItemWriter<Person> wrtier() {
        return new JdbcBatchItemWriterBuilder<Person>()
                .sql("INSERT INTO person (id, person_id, first_name, last_name, street, city, state, postal_code, postal_code, telephone, email) VALUES ???????????")
                .build();
    }
}
