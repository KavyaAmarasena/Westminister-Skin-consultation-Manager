package com.skin_consultation_centre;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;

import com.skin_consultation_centre.views.SkinConsultationManagerView;

public class WestminsterSkinConsultationManager implements SkinConsultationManager {
    private static final int MAX_DOCTORS = 10;
    Scanner input = new Scanner(System.in);

    private ArrayList<Doctor> doctors = new ArrayList<>();
    private ArrayList<Patient> patients = new ArrayList<>();
    private ArrayList<Consultation> consultations = new ArrayList<>();

    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(ArrayList<Doctor> doctors) {
        this.doctors = doctors;
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public void setPatients(ArrayList<Patient> patients) {
        this.patients = patients;
    }

    public ArrayList<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(ArrayList<Consultation> consultations) {
        this.consultations = consultations;
    }

    private static WestminsterSkinConsultationManager manager;

    private WestminsterSkinConsultationManager() {
    }

    public static WestminsterSkinConsultationManager getManager() {
        return manager;
    }
    public static void main(String[] args) {
        manager = new WestminsterSkinConsultationManager();
        manager.run();
    }
    public void run() {
        String option;
        System.out.println("\n--- Westminster Skin Consultation Centre ---\n");
        loadDataFromFile();
        do {
            System.out.println("\n- CONSOLE MENU -");
            System.out.println("1. Add a new doctor");
            System.out.println("2. Delete a doctor");
            System.out.println("3. Print list of doctors");
            System.out.println("4. Save data in file");
            System.out.println("5. Open GUI");

            System.out.print("Select option (Enter 0 to exit): ");
            option = input.next();

            switch (option) {
                case "0":
                    break;

                case "1":
                    addDoctor();
                    break;

                case "2":
                    deleteDoctor();
                    break;

                case "3":
                    System.out.println("\n--- List of doctors in consultation centre ---");
                    viewDoctors();
                    break;

                case "4":
                    saveDataToFile();
                    break;

                case "5":
                    startGUI();
                    break;

                default:
                    System.out.println("Please enter a valid input");
                    break;
            }

        } while (!"0".equals(option));
        System.out.println("--- Exiting program ---");
    }
    @Override
    public void addDoctor() {   
        try{
            System.out.println("\n--- Add a new doctor ---");
            System.out.print("Name: ");
            String name = input.next();
            System.out.print("Surname: ");
            String surname = input.next();
            System.out.print("Date of birth (YYYY-MM-DD): ");
            LocalDate dob = LocalDate.parse(input.next());
            System.out.print("Mobile number: ");
            String mobileNumber = input.next();
            System.out.print("Medical license number: ");
            int medicalLicenseNumber = input.nextInt();
            System.out.print("Specialisation: ");
            String specialisation = input.next();
            Doctor newDoctor = new Doctor(name, surname, dob, mobileNumber, medicalLicenseNumber, specialisation);
            if (doctors.size() <= MAX_DOCTORS) {
                doctors.add(newDoctor);
                System.out.println("Doctor added successfully");
            } else {
                System.out.println("The centre can only allocate a maximum of 10 doctors");
        }
        }
        catch(Exception e){
            input.next();
            System.out.println("Input invalid");
        }
    }

    @Override
    public void deleteDoctor() {
        boolean foundFlag = false;
        System.out.println("\n--- Delete doctor ---");
        System.out.print("Medical license number of doctor to be deleted: ");
        try{
            int medicalLicenseNumber = input.nextInt();
            for (Doctor d : doctors) {
                if (d.getMedicalLicenseNumber() == medicalLicenseNumber) {
                    foundFlag = true;
                    Doctor currentDoctor = d;
                    doctors.remove(currentDoctor);
                    System.out.println(
                        "Doctor with medical license number " + medicalLicenseNumber + " deleted successfully");
                    System.out.println(currentDoctor);
                    System.out.println("No. of doctors in centre: " + doctors.size());
                    break;
                }
            }
            if (!foundFlag) {
                System.out.println("Doctor with medical license number " + medicalLicenseNumber + " not found");
            }
        }
        catch(Exception e){
            input.next();
            System.out.println("Input invalid");
        }
    }

    @Override
    public void viewDoctors() {
        if (doctors.isEmpty()) {
            System.out.println("No doctors");
        } else {
            System.out.println();
            doctors.sort((d1, d2) -> d1.getSurname().compareTo(d2.getSurname()));
            for (Doctor doctor : doctors) {
                System.out.println(doctor);
            }
        }
    }

    @Override
    public void saveDataToFile() {
        File doctorsFile = new File("doctors");

        try (FileOutputStream fileOutput = new FileOutputStream(doctorsFile)) {
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);

            for (Doctor doctor : doctors) {
                objectOutput.writeObject(doctor);
            }

            objectOutput.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("All data saved to file: doctors");
        System.out.println();

    }

    @Override
    public void loadDataFromFile() {
        System.out.println("Trying to load data from file: doctors");
        File doctorData = new File("doctors");
        try {
            FileInputStream fileInput = new FileInputStream(doctorData);

            try (ObjectInputStream objectInput = new ObjectInputStream(fileInput)) {
                while (true) {
                    Doctor doctor = (Doctor) objectInput.readObject();
                    if (doctor != null) {
                        doctors.add(doctor);
                    } else {
                        break;
                    }
                }

            } catch (EOFException e) {
                System.out.println("Doctor data loaded from file");
            }

            catch (Exception e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            System.out.println("No data file found");
        }
    }

    public JFrame startGUI() {
        SkinConsultationManagerView gui = new SkinConsultationManagerView();
        return gui.start();
    }
}