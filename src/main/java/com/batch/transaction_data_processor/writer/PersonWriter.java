package com.batch.transaction_data_processor.writer;

import com.batch.transaction_data_processor.dtos.Person;
import com.batch.transaction_data_processor.repository.PersonDao;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class PersonWriter implements ItemWriter<Person> {

    private PersonDao personDao;

    @Override
    public void write(Chunk<? extends Person> chunk) throws Exception {
        List<Person> personList = new ArrayList<>(chunk.getItems());
        List<Person> newEntries = personList.stream().filter(person -> person.getId() == null).toList();
        if (!CollectionUtils.isEmpty(newEntries)) {
            newEntries.forEach(ent -> ent.setId(UUID.randomUUID()));
            personDao.batchInsert(newEntries);
            personList.removeAll(newEntries);
        }
        if (!CollectionUtils.isEmpty(newEntries)) {
            personDao.batchUpdate(personList);
        }
    }
}
