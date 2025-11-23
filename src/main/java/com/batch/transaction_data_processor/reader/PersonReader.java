package com.batch.transaction_data_processor.reader;

import com.batch.transaction_data_processor.dtos.PersonDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.infrastructure.item.ItemReader;
import org.springframework.batch.infrastructure.item.json.JsonItemReader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@Component
@AllArgsConstructor
public class PersonReader implements ItemReader<PersonDto> {

    private static final Logger log = LoggerFactory.getLogger(JsonItemReader.class);
    private ObjectMapper objectMapper;
    private boolean initialized = false;
    private Iterator<PersonDto> productIterator;

    @Override
    public @Nullable PersonDto read() throws Exception {
        return null;
    }

    public void setResource(Resource resource) throws IOException {
        if (!initialized) {
            log.info("Reading JSON file: {}", resource.getFilename());
            List<PersonDto> dtos = objectMapper.readValue(resource.getInputStream(), new TypeReference<>() {});
            log.info("Loaded items from file: " + dtos.size());
            productIterator = dtos.iterator();
            this.initialized = true;
        }
    }


}
