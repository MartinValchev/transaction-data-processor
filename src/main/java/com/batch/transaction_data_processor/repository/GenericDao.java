package com.batch.transaction_data_processor.repository;

import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GenericDao<T> {

    Optional<T> findPersonById(Long entityId);
    List<T> findAll();
    T update(T entity);
    UUID save(T entity);
    Long deleteByEntityId(Long entityId);
    void batchInsert(List<T> entities);

    void batchUpdate(List<T> entities);

    RowMapper<T> getRowMapper();
}
