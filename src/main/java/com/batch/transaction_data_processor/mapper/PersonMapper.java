package com.batch.transaction_data_processor.mapper;

import com.batch.transaction_data_processor.dtos.AddressDto;
import com.batch.transaction_data_processor.dtos.Person;
import com.batch.transaction_data_processor.dtos.PersonDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    @Mapping(source="address", target = ".", qualifiedByName = "mapAddress")
    Person mapPersonDto(PersonDto personDto);


    @Named("mapAddress")
    @Mapping(source="postCode", target = "postalCode")
    Person mapAddress(AddressDto address);

}
