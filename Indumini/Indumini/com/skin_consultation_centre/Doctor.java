package com.skin_consultation_centre;

import java.time.LocalDate;

public class Doctor extends Person implements Comparable<Doctor> {
    private int medicalLicenseNumber;
    private String specialisation;

    public Doctor() {
    }

    public Doctor(String name, String surname, LocalDate dateOfBirth, String mobileNumber, int medicalLicenseNumber,
            String specialisation) {
        super(name, surname, dateOfBirth, mobileNumber);
        this.medicalLicenseNumber = medicalLicenseNumber;
        this.specialisation = specialisation;
    }

    public int getMedicalLicenseNumber() {
        return medicalLicenseNumber;
    }

    public void setMedicalLicenseNumber(int medicalLicenseNumber) {
        this.medicalLicenseNumber = medicalLicenseNumber;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    @Override
    public String toString() {
        return String.format("%sMedical license number: %d%nSpecialisation: %s%n",
                super.toString(),
                this.medicalLicenseNumber,
                this.specialisation);
    }

    public int compareTo(Doctor d) {
        return this.getSurname().compareTo(d.getSurname());
    }
}
