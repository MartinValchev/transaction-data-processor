package com.batch.transaction_data_processor.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public final class PersonDto {
    private long personId;

    private String firstName;
    private String lastName;

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    private AddressDto address;
    private String email;
    private String telephone;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public AddressDto getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public long getPersonId() {
        return personId;
    }



}
