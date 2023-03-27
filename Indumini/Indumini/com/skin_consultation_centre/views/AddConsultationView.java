package com.skin_consultation_centre.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.skin_consultation_centre.Consultation;
import com.skin_consultation_centre.Doctor;
import com.skin_consultation_centre.Patient;
import com.skin_consultation_centre.WestminsterSkinConsultationManager;

public class AddConsultationView {

        JFrame frame;
        JPanel mainPanel;
        JPanel availabilitySelectorPanel;
        JPanel patientDetailsPanel;
        JPanel consultationDetailsPanel;
        JPanel formsPanel;
        JPanel buttonsPanel;
        JPanel panel1;
        JPanel panel2;
        JPanel panel3;
        JPanel panel4;
        JPanel panel5;
        JPanel panel6;
        JPanel panel7;
        JPanel panel8;
        JPanel panel9;
        JPanel panel10;
        JPanel panel11;
        JLabel dateLabel;
        JLabel timeLabel;
        JLabel heading1;
        JLabel heading2;
        JLabel heading3;
        JLabel heading4;
        JLabel patientNameLabel;
        JLabel patientSurnameLabel;
        JLabel patientMobileNumberLabel;
        JLabel patientDOBLabel;
        JLabel patientIdLabel;
        JLabel consultationHoursLabel;
        JLabel consultationCostLabel;
        JLabel consultationNotesLabel;
        JTextField patientNameField;
        JTextField patientSurnameField;
        JTextField patientDOBField;
        JTextField patientMobileNumberField;
        JTextField patientIdField;
        JTextField consultationCostField;

        JLabel doctorAvailableLabel;
        JComboBox<String> appointmentYearComboBx;
        JComboBox<String> appointmentMonthComboBx;
        JComboBox<String> appointmentDayComboBx;
        JComboBox<String> timeComboBx;
        JComboBox<String> consultationHoursComboBx;
        JButton btnCheckAvailability;
        JButton btnSaveConsultation;

        JTextArea consultationNotesTextArea;

        private String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep",
                        "Oct", "Nov", "Dec" };
        private String[] appointmentTimeSlots = { "09:00", "10:00", "11:00", "12:00", "13:00", "14:00",
                        "15:00", "16:00", "17:00", "18:00", "19:00", "20:00" };

        private String[] appointmentHours = { "1", "2", "3", "4", "5" };

        Doctor selectedDoctor;
        SkinConsultationManagerView parent;

        static WestminsterSkinConsultationManager wscm = WestminsterSkinConsultationManager
                        .getManager();

        public AddConsultationView(SkinConsultationManagerView parent, Doctor selectedDoctor) {
                this.parent = parent;
                this.selectedDoctor = selectedDoctor;

        }

        private void generateAppointmentYearsDropDown(int currentYear) {
                for (int i = currentYear; i <= currentYear + 3; i++) {
                        appointmentYearComboBx.addItem(String.valueOf(i));
                }
        }

        private void generateAppointmentDaysDropDown(LocalDate date) {
                for (int i = 1; i <= date.lengthOfMonth(); i++) {
                        appointmentDayComboBx.addItem(i < 10 ? "0" + String.valueOf(i) : String.valueOf(i));
                }
        }

        private void updateAppointmentDaysDropDown() {
                String selectedYear = String.valueOf(appointmentYearComboBx.getSelectedItem());
                String selectedMonth = String.valueOf(appointmentMonthComboBx.getSelectedItem());

                String selectedDateString = String.format("%s-%s-%s", selectedYear,
                                selectedMonth, "01");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
                LocalDate selectedDate = LocalDate.parse(selectedDateString, formatter);

                appointmentDayComboBx.removeAllItems();
                generateAppointmentDaysDropDown(selectedDate);
        }
        public JFrame start() {
                // Main frame
                frame = new JFrame("Add Consultation");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(350, 200);
                frame.setLocationRelativeTo(null);
                frame.setAlwaysOnTop(false);
                frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(),
                                BoxLayout.Y_AXIS));
                mainPanel = new JPanel();
                mainPanel.setPreferredSize(new Dimension(frame.getWidth(), 100));
                mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
                mainPanel.setBackground(new Color(231, 246, 242));
                availabilitySelectorPanel = new JPanel();
                availabilitySelectorPanel.setLayout(new BoxLayout(availabilitySelectorPanel,
                                BoxLayout.Y_AXIS));
                availabilitySelectorPanel.setPreferredSize(new Dimension(frame.getWidth(),
                                100));

                panel1 = new JPanel();
                panel1.setBorder(new EmptyBorder(20, 20, 20, 20));
                panel1.setBackground(new Color(165, 201, 202));

                dateLabel = new JLabel("Date: ");
                dateLabel.setForeground(Color.WHITE);
                panel1.add(dateLabel);
                appointmentYearComboBx = new JComboBox();
                generateAppointmentYearsDropDown(LocalDate.now().getYear());
                appointmentYearComboBx.setSelectedIndex(0);
                appointmentYearComboBx.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent event) {
                                updateAppointmentDaysDropDown();
                        }
                });
                panel1.add(appointmentYearComboBx);
                appointmentMonthComboBx = new JComboBox(months);
                appointmentMonthComboBx.setSelectedItem(String.valueOf(LocalDate.now().getMonthValue()));
                appointmentMonthComboBx.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent event) {
                                updateAppointmentDaysDropDown();
                        }
                });
                panel1.add(appointmentMonthComboBx);

                appointmentDayComboBx = new JComboBox();
                generateAppointmentDaysDropDown(LocalDate.now());
                appointmentDayComboBx.setSelectedIndex(LocalDate.now().getDayOfMonth() - 1);
                panel1.add(appointmentDayComboBx);
                timeLabel = new JLabel("Time: ");
                timeLabel.setForeground(Color.WHITE);

                panel1.add(timeLabel);

                timeComboBx = new JComboBox(appointmentTimeSlots);
                panel1.add(timeComboBx);

                availabilitySelectorPanel.add(panel1);
                mainPanel.add(availabilitySelectorPanel);

                panel11 = new JPanel();
                panel11.setBorder(new EmptyBorder(10, 20, 10, 20));
                panel11.setBackground(new Color(231, 246, 242));
                btnCheckAvailability = new JButton("Check Availability");
                btnCheckAvailability.setFont(new Font("Sans-serif", Font.BOLD, 14));
                btnCheckAvailability.setPreferredSize(new Dimension(180, 36));
                btnCheckAvailability.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                handleBtnCheckAvailabilityClick();
                        }
                });
                panel11.add(btnCheckAvailability);
                mainPanel.add(panel11);

                panel2 = new JPanel();
                panel2.setBorder(new EmptyBorder(10, 20, 8, 20));
                panel2.setBackground(new Color(46, 204, 113));
                doctorAvailableLabel = new JLabel(String.format("Doctor %s %s is available", selectedDoctor.getName(),
                                selectedDoctor.getSurname()), JLabel.CENTER);

                doctorAvailableLabel.setPreferredSize(new Dimension(frame.getWidth(), 21));
                doctorAvailableLabel.setFont(new Font("Sans-serif", Font.BOLD, 14));
                doctorAvailableLabel.setForeground(Color.WHITE);
                panel2.add(doctorAvailableLabel);
                panel2.setVisible(false);
                mainPanel.add(panel2);

                // Patient details
                patientDetailsPanel = new JPanel();
                patientDetailsPanel.setLayout(new BoxLayout(patientDetailsPanel,
                                BoxLayout.Y_AXIS));
                patientDetailsPanel.setPreferredSize(new Dimension(400, 250));
                patientDetailsPanel.setBackground(new Color(57, 91, 100));

                heading3 = new JLabel("PATIENT",
                                JLabel.CENTER);
                heading3.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                heading3.setPreferredSize(new Dimension(frame.getWidth(), 40));
                heading3.setFont(new Font("Sans-serif", Font.BOLD, 13));
                heading3.setForeground(new Color(251, 197, 49));
                patientDetailsPanel.add(heading3);

                panel3 = new JPanel();
                panel3.setBackground(new Color(57, 91, 100));
                patientNameLabel = new JLabel("Name: ");
                patientNameLabel.setForeground(Color.WHITE);
                patientNameLabel.setPreferredSize(new Dimension(150, 25));
                patientNameField = new JTextField();
                patientNameField.setPreferredSize(new Dimension(275, 25));
                panel3.add(patientNameLabel);
                panel3.add(patientNameField);
                patientDetailsPanel.add(panel3);

                panel4 = new JPanel();
                panel4.setBackground(new Color(57, 91, 100));
                patientSurnameLabel = new JLabel("Surname: ");
                patientSurnameLabel.setForeground(Color.WHITE);

                patientSurnameLabel.setPreferredSize(new Dimension(150, 25));
                patientSurnameField = new JTextField();
                patientSurnameField.setPreferredSize(new Dimension(275, 25));
                panel4.add(patientSurnameLabel);
                panel4.add(patientSurnameField);
                patientDetailsPanel.add(panel4);

                panel5 = new JPanel();
                panel5.setBackground(new Color(57, 91, 100));
                patientDOBLabel = new JLabel("DOB (YYYY-MM-DD): ");
                patientDOBLabel.setForeground(Color.WHITE);
                patientDOBLabel.setPreferredSize(new Dimension(150, 25));
                patientDOBField = new JTextField();
                patientDOBField.setPreferredSize(new Dimension(150, 25));
                panel5.add(patientDOBLabel);
                panel5.add(patientDOBField);
                patientDetailsPanel.add(panel5);

                panel6 = new JPanel();
                panel6.setBackground(new Color(57, 91, 100));
                patientMobileNumberLabel = new JLabel("Mobile number: ");
                patientMobileNumberLabel.setForeground(Color.WHITE);
                patientMobileNumberLabel.setPreferredSize(new Dimension(150, 25));
                patientMobileNumberField = new JTextField();
                patientMobileNumberField.setPreferredSize(new Dimension(150, 25));
                panel6.add(patientMobileNumberLabel);
                panel6.add(patientMobileNumberField);
                patientDetailsPanel.add(panel6);

                panel7 = new JPanel();
                panel7.setBackground(new Color(57, 91, 100));
                patientIdLabel = new JLabel("Patient ID: ");
                patientIdLabel.setForeground(Color.WHITE);
                patientIdLabel.setPreferredSize(new Dimension(150, 25));
                patientIdField = new JTextField();
                patientIdField.setPreferredSize(new Dimension(150, 25));
                panel7.add(patientIdLabel);
                panel7.add(patientIdField);
                patientDetailsPanel.add(panel7);

                // Consultation details
                consultationDetailsPanel = new JPanel();
                consultationDetailsPanel.setLayout(new BoxLayout(consultationDetailsPanel,
                                BoxLayout.Y_AXIS));
                consultationDetailsPanel.setPreferredSize(new Dimension(400, 250));
                consultationDetailsPanel.setBackground(new Color(57, 91, 100));

                heading4 = new JLabel("CONSULTATION",
                                JLabel.CENTER);
                heading4.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                heading4.setPreferredSize(new Dimension(frame.getWidth(), 40));
                heading4.setFont(new Font("Sans-serif", Font.BOLD, 13));
                heading4.setForeground(new Color(251, 197, 49));
                consultationDetailsPanel.add(heading4);


                panel8 = new JPanel();
                panel8.setBackground(new Color(57, 91, 100));

                consultationHoursLabel = new JLabel("Duration (hrs): ");
                consultationHoursLabel.setForeground(Color.WHITE);

                consultationHoursLabel.setPreferredSize(new Dimension(125, 25));
                consultationHoursComboBx = new JComboBox(appointmentHours);
                consultationHoursComboBx.setPreferredSize(new Dimension(75, 25));
                consultationHoursComboBx.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent event) {
                                handleConsultationHoursChange(event);
                        }
                });
                panel8.add(consultationHoursLabel);
                panel8.add(consultationHoursComboBx);
                consultationDetailsPanel.add(panel8);

                panel9 = new JPanel();
                panel9.setBackground(new Color(57, 91, 100));

                consultationCostLabel = new JLabel("Cost (£): ");
                consultationCostLabel.setForeground(Color.WHITE);

                consultationCostLabel.setPreferredSize(new Dimension(125, 25));
                consultationCostField = new JTextField("15");
                consultationCostField.setPreferredSize(new Dimension(75, 25));
                panel9.add(consultationCostLabel);
                panel9.add(consultationCostField);
                consultationDetailsPanel.add(panel9);

                panel10 = new JPanel();
                panel10.setBackground(new Color(57, 91, 100));

                panel10.setBorder(new EmptyBorder(5, 0, 0, 0));
                consultationNotesLabel = new JLabel("Notes: ");
                consultationNotesLabel.setForeground(Color.WHITE);

                consultationNotesLabel.setPreferredSize(new Dimension(75, 50));
                consultationNotesTextArea = new JTextArea();
                consultationNotesTextArea.setPreferredSize(new Dimension(300, 100));
                panel10.add(consultationNotesLabel);
                panel10.add(consultationNotesTextArea);
                consultationDetailsPanel.add(panel10);

                formsPanel = new JPanel();
                formsPanel.setLayout(new BoxLayout(formsPanel, BoxLayout.Y_AXIS));
                formsPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
                formsPanel.setBackground(new Color(57, 91, 100));
                formsPanel.add(patientDetailsPanel);
                formsPanel.add(consultationDetailsPanel);

                // Buttons panel
                buttonsPanel = new JPanel();
                buttonsPanel.setPreferredSize(new Dimension(800, 60));
                buttonsPanel.setBorder(new EmptyBorder(8, 0, 10, 0));
                buttonsPanel.setBackground(new Color(231, 246, 242));

                btnSaveConsultation = new JButton("Save Consultation");
                btnSaveConsultation.setFont(new Font("Sans-serif", Font.BOLD, 14));
                btnSaveConsultation.setPreferredSize(new Dimension(200, 36));
                btnSaveConsultation.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                handleBtnSaveConsultationClick();
                        }
                });

                buttonsPanel.add(btnSaveConsultation);

                frame.getContentPane().add(mainPanel);
                frame.setVisible(true);

                return frame;
        }

        private void handleConsultationHoursChange(ActionEvent event) {
                // Update consultation cost field
                JComboBox comboBx = (JComboBox) event.getSource();
                Object selected = comboBx.getSelectedItem();
                int value = Integer.parseInt(selected.toString());
                consultationCostField.setText(String.valueOf(15 + (value - 1) * 25));
        }

        private void handleBtnResetFormClick() {
                patientNameField.setText("");
                patientSurnameField.setText("");
                patientMobileNumberField.setText("");
                patientIdField.setText("");
                consultationHoursComboBx.setSelectedIndex(0);
                consultationNotesTextArea.setText("");
        }

        private void handleBtnSaveConsultationClick() {
                String selectedYear = appointmentYearComboBx.getSelectedItem().toString();
                String selectedMonth = appointmentMonthComboBx.getSelectedItem().toString();
                String selectedDay = appointmentDayComboBx.getSelectedItem().toString();
                String selectedTime = timeComboBx.getSelectedItem().toString();

                String selectedDateTimeString = String.format("%s-%s-%s %s", selectedYear,
                                selectedMonth,
                                selectedDay, selectedTime);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm");
                LocalDateTime selectedDateTime = LocalDateTime.parse(selectedDateTimeString, formatter);

                String patientName = patientNameField.getText().trim();
                String patientSurname = patientSurnameField.getText().trim();
                String patientMobileNumber = patientMobileNumberField.getText().trim();
                String patientDobString = patientDOBField.getText().trim();
                String patientId = patientIdField.getText().trim();
                String consultationHours = consultationHoursComboBx.getSelectedItem().toString();
                String consultationCost = consultationCostField.getText().trim();
                String consultationNotes = consultationNotesTextArea.getText().trim();

                // Validate form fields
                if (patientName.isEmpty() || patientSurname.isEmpty() || patientMobileNumber.isEmpty()
                                || patientDobString.isEmpty() || patientId.isEmpty() || consultationHours.isEmpty()
                                || consultationCost.isEmpty() || consultationNotes.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Please fill in all fields");
                        return;
                }

                try {
                        int patientIdInt = Integer.parseInt(patientId);
                        int consultationHoursInt = Integer.parseInt(consultationHours);
                        double consultationCostDouble = Double.parseDouble(consultationCost);

                        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate patientDob = LocalDate.parse(patientDobString, formatter);

                        Patient patient = new Patient(patientName, patientSurname, patientDob,
                                        patientMobileNumber, patientIdInt);
                        Consultation consultation = new Consultation(selectedDoctor, patient,
                                        selectedDateTime,
                                        consultationHoursInt, consultationCostDouble,
                                        consultationNotes);

                        wscm.getPatients().add(patient);
                        wscm.getConsultations().add(consultation);

                        parent.generateConsultationListData();
                        frame.dispose();
                        JOptionPane.showMessageDialog(frame, "Consultation saved successfully");

                } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame,
                                        "Patient ID and consultation cost is invalid");
                } catch (DateTimeParseException ex) {
                        JOptionPane.showMessageDialog(frame,
                                        "Patient date of birth is invalid");
                }
        }

        private void handleBtnCheckAvailabilityClick() {
                String selectedYear = appointmentYearComboBx.getSelectedItem().toString();
                String selectedMonth = appointmentMonthComboBx.getSelectedItem().toString();
                String selectedDay = appointmentDayComboBx.getSelectedItem().toString();
                String selectedTime = timeComboBx.getSelectedItem().toString();

                String selectedDateTimeString = String.format("%s-%s-%s %s", selectedYear,
                                selectedMonth,
                                selectedDay, selectedTime);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm");
                LocalDateTime selectedDateTime = LocalDateTime.parse(selectedDateTimeString, formatter);
                LocalDateTime now = LocalDateTime.now();

                if (selectedDateTime.compareTo(now) < 0) {
                        JOptionPane.showMessageDialog(null,
                                        String.format("Selected date and time: %s is in the past",
                                                        selectedDateTimeString),
                                        "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                }

                if (!isDoctorAvailable(selectedDoctor, selectedDateTime)) {
                        JOptionPane.showMessageDialog(null,
                                        String.format("Doctor %s %s is not available at %s",
                                                        selectedDoctor.getName(),
                                                        selectedDoctor.getSurname(),
                                                        selectedDateTimeString),
                                        "Error", JOptionPane.ERROR_MESSAGE);
                        if (!assignFreeDoctorRandomly(selectedDateTime)) {
                                return;
                        }
                }

                availabilitySelectorPanel.setPreferredSize(new Dimension(frame.getWidth(),
                                75));

                appointmentYearComboBx.setEnabled(false);
                appointmentMonthComboBx.setEnabled(false);
                appointmentDayComboBx.setEnabled(false);
                timeComboBx.setEnabled(false);
                btnCheckAvailability.setEnabled(false);

                frame.setSize(600, 840);
                frame.setLocationRelativeTo(null);

                // Show doctor available
                panel2.setVisible(true);

                // Add patient details, consultation details and buttons
                mainPanel.add(formsPanel);
                mainPanel.add(buttonsPanel);

                frame.repaint();
                frame.revalidate();
                SwingUtilities.updateComponentTreeUI(frame);
        }

        private boolean isDoctorAvailable(Doctor selectedDoctor, LocalDateTime selectedDateTime) {
                for (Consultation c : wscm.getConsultations()) {
                        if (c.getDoctor().equals(selectedDoctor)
                                        && c.getDateTime().equals(selectedDateTime)) {
                                return false;
                        }
                }
                return true;
        }

        private boolean assignFreeDoctorRandomly(LocalDateTime selectedDateTime) {
                Random random = new Random();
                Set<Integer> generatedRandomIndexes = new HashSet<Integer>();

                boolean freeDoctorFound = false;

                int tries = 0;
                while (tries < wscm.getDoctors().size() && !freeDoctorFound) {
                        tries++;
                        int randomIndex;
                        do {
                                randomIndex = random.nextInt(wscm.getDoctors().size());
                        } while (generatedRandomIndexes.contains(randomIndex));

                        generatedRandomIndexes.add(randomIndex);

                        Doctor currentDoctor = wscm.getDoctors().get(randomIndex);


                        if (isDoctorAvailable(currentDoctor, selectedDateTime)) {
                                selectedDoctor = currentDoctor;
                                freeDoctorFound = true;
                        }
                }

                // Update doctor label
                doctorAvailableLabel.setText(String.format("Doctor %s %s is available (selected randomly)",
                                selectedDoctor.getName(), selectedDoctor.getSurname()));
                doctorAvailableLabel.repaint();
                SwingUtilities.updateComponentTreeUI(frame);

                // Show message to user
                if (freeDoctorFound) {
                        JOptionPane.showMessageDialog(null,
                                        String.format("Doctor %s %s has been selected randomly for this consultation",
                                                        selectedDoctor.getName(),
                                                        selectedDoctor.getSurname()),
                                        "Info", JOptionPane.INFORMATION_MESSAGE);
                        return true;
                } else {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm");
                        JOptionPane.showMessageDialog(null,
                                        String.format("No doctor is available at %s, please select another date and time",
                                                        selectedDateTime.format(formatter)),
                                        "Error", JOptionPane.ERROR_MESSAGE);
                        return false;
                }
        }
}
