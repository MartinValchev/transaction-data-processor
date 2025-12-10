package com.batch.transaction_data_processor.processor;

import com.batch.transaction_data_processor.dtos.Person;
import com.batch.transaction_data_processor.dtos.PersonDto;
import com.batch.transaction_data_processor.mapper.PersonMapper;
import com.batch.transaction_data_processor.repository.PersonDao;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
public class PersonProcessor implements ItemProcessor<PersonDto, Person> {

    private final PersonMapper mapper;
    private final PersonDao personDao;

    public PersonProcessor(PersonMapper mapper, PersonDao personDao) {
        this.mapper = mapper;
        this.personDao = personDao;
    }

    @Override
    public @Nullable Person process(PersonDto item) throws Exception {
        Person person = mapper.mapPersonDto(item);
        syncWithDatabase(person);
        return person;
    }

    private void syncWithDatabase(Person mappedPerson) {
        Person personById = personDao.findPersonById(mappedPerson.getPersonId()).orElse(null);
        if (Objects.nonNull(personById)) {
            mappedPerson.setId(personById.getId());
            mappedPerson.setCreatedDate(personById.getCreatedDate());
        } else {
            mappedPerson.setCreatedDate(LocalDateTime.now());
        }
        mappedPerson.setModifiedDate(LocalDateTime.now());
    }
}
