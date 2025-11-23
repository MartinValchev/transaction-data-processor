package com.batch.transaction_data_processor.processor;

import com.batch.transaction_data_processor.dtos.Person;
import com.batch.transaction_data_processor.dtos.PersonDto;
import com.batch.transaction_data_processor.mapper.PersonMapper;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class PersonProcessor implements ItemProcessor<PersonDto, Person> {

    private final PersonMapper mapper;

    @Override
    public @Nullable Person process(PersonDto item) throws Exception {
        Person person = mapper.mapPersonDto(item);
        person.setId(UUID.randomUUID());
        return person;
    }
}
