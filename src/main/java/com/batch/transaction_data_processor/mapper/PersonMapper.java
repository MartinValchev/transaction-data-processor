package com.batch.transaction_data_processor.mapper;

import com.batch.transaction_data_processor.dtos.Person;
import com.batch.transaction_data_processor.dtos.PersonDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    @Mapping(source="address.postCode", target = "postalCode")
    @Mapping(source="address.street", target = "street")
    @Mapping(source="address.city", target = "city")
    @Mapping(source="address.state", target = "state")
    Person mapPersonDto(PersonDto personDto);
}
