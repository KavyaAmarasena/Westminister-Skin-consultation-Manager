package com.skin_consultation_centre;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Consultation implements Serializable {
    private Doctor doctor;
    private Patient patient;
    private LocalDateTime dateTime;
    private int hours;
    private double cost;
    private String notes;

    public Consultation() {
    }

    public Consultation(Doctor doctor, Patient patient, LocalDateTime dateTime, int hours, double cost, String notes) {
        this.doctor = doctor;
        this.patient = patient;
        this.dateTime = dateTime;
        this.hours = hours;
        this.cost = cost;
        this.notes = notes;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
