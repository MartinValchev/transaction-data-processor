package com.batch.transaction_data_processor.repository;

import com.batch.transaction_data_processor.dtos.Person;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class PersonDao implements GenericDao<Person>{

    private JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Person> findPersonById(Long id) {
        String sql = "SELECT * from person WHERE person_id = ?";
        jdbcTemplate.queryForObject(sql, getRowMapper(), id);
        return Optional.empty();
    }

    @Override
    public List<Person> findAll() {
        return List.of();
    }

    @Override
    public Person update(Person entity) {
        return null;
    }

    @Override
    public UUID save(Person entity) {
        return null;
    }

    @Override
    public UUID deleteByEntityId(Long entityId) {
        return null;
    }

    @Override
    public void batchInsert(List<Person> entities) {

    }

    @Override
    public RowMapper<Person> getRowMapper() {
        return new PersonRowMapper();
    }

}
