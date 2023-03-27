package com.skin_consultation_centre;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Person implements Serializable {
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String mobileNumber;

    public Person() {

    }

    public Person(String name, String surname, LocalDate dateOfBirth, String mobileNumber) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.mobileNumber = mobileNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        return String.format("Name: %s%nSurname: %s%nDate of birth: %s%nMobile number: %s%n",
                this.name, this.surname, this.dateOfBirth.format(DateTimeFormatter.ofPattern("yyyy MM dd")),
                this.mobileNumber);
    }
}
