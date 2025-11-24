package com.batch.transaction_data_processor.repository;

import com.batch.transaction_data_processor.dtos.Person;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class PersonDao implements GenericDao<Person>{

    private static final String INSERT_SQL = "INSERT INTO from person (id, personId, first_name, last_name, street, city, state, postal_code, " +
            "telephone, email, created_date, modified_date) VALUES ?,?,?,?,?,?,?,?,?,?,?,?";

    private JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Person> findPersonById(Long id) {
        try {
            String sql = "SELECT * from person WHERE person_id = ?";
            Person person = jdbcTemplate.queryForObject(sql, getRowMapper(), id);
            return Optional.ofNullable(person);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Person> findAll() {
        String sql = "SELECT * from person";
        return jdbcTemplate.queryForList(sql, Person.class);
    }

    @Override
    public Person update(Person person) {
        String sql = "UPDATE person SET person_id = ?  first_name = ?, last_name = ?, street = ?, city = ?, state = ?, postal_code = ?, " +
                "telephone = ?, email = ?, created_date = ?, modified_date = ?";
        jdbcTemplate.update(sql, person.getPersonId(), person.getFirstName(), person.getLastName(), person.getStreet(),
                person.getCity(), person.getState(), person.getPostalCode(), person.getTelephone(), person.getEmail(),
                person.getCreatedDate(), person.getModifiedDate());
        return person;
    }

    @Override
    public UUID save(Person person) {
        jdbcTemplate.update(INSERT_SQL, person.getId(), person.getPersonId(), person.getFirstName(), person.getLastName(),
                person.getStreet(), person.getState(), person.getPostalCode(), person.getTelephone(), person.getEmail(),
                person.getCreatedDate(), person.getModifiedDate());
        return person.getId();
    }

    @Override
    public Long deleteByEntityId(Long personId) {
        jdbcTemplate.update("DELETE FROM person WHERE person_id = ?", personId);
        return personId;
    }

    @Override
    public void batchInsert(List<Person> entities) {
        jdbcTemplate.batchUpdate(INSERT_SQL, entities, entities.size(),
            (PreparedStatement ps, Person person) -> {
                ps.setString(1, person.getId().toString());
                ps.setLong(2, person.getPersonId());
                ps.setString(3, person.getFirstName());
                ps.setString(4, person.getLastName());
                ps.setString(5, person.getStreet());
                ps.setString(6, person.getCity());
                ps.setString(7, person.getState());
                ps.setString(8, person.getPostalCode());
                ps.setString(9, person.getTelephone());
                ps.setString(10, person.getEmail());
                ps.setTimestamp(11, Timestamp.from(person.getCreatedDate().toInstant(ZoneOffset.UTC)));
                ps.setTimestamp(12, Timestamp.from(person.getCreatedDate().toInstant(ZoneOffset.UTC)));
            });
        }

    @Override
    public RowMapper<Person> getRowMapper() {
        return new PersonRowMapper();
    }
}

