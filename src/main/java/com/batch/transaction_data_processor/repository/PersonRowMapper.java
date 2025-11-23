package com.batch.transaction_data_processor.repository;

import com.batch.transaction_data_processor.dtos.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PersonRowMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();
        person.setId(UUID.fromString(rs.getString("id")));
        person.setPersonId(rs.getLong("person_id"));
        person.setFirstName(rs.getString("first_name"));
        person.setLastName(rs.getString("last_name"));
        person.setStreet(rs.getString("street"));
        person.setCity(rs.getString("city"));
        person.setState(rs.getString("state"));
        person.setPostalCode(rs.getString("postal_code"));
        person.setTelephone(rs.getString("telephone"));
        person.setEmail(rs.getString("email"));
        person.setCreatedDate(rs.getTimestamp("created_date").toLocalDateTime());
        person.setModifiedDate(rs.getTimestamp("modified_date").toLocalDateTime());
        return person;
    }
}
