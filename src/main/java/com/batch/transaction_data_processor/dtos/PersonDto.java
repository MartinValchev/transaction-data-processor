package com.batch.transaction_data_processor.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PersonDto {
    private long personId;
    private String firstName;
    private String lastName;
    private AddressDto address;
    private String email;
    private String telephone;
}
