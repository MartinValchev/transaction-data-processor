package com.batch.transaction_data_processor.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddressDto {
    private String street;
    private String city;
    private String state;
    private String postCode;
}
