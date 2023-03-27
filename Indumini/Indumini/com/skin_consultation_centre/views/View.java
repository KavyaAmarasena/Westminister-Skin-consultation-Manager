package com.skin_consultation_centre.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.skin_consultation_centre.Consultation;
import com.skin_consultation_centre.Patient;

public class View {

    JFrame frame;
    JPanel mainPanel;
    JPanel patientDetailsPanel;
    JPanel consultationDetailsPanel;
    JPanel formsPanel;
    JPanel panel1;
    JPanel panel3;
    JPanel panel4;
    JPanel panel5;
    JPanel panel6;
    JPanel panel7;
    JPanel panel8;
    JPanel panel9;
    JPanel panel10;
    JLabel heading1;
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
    JLabel patientNameValue;
    JLabel patientSurnameValue;
    JLabel patientDOBValue;
    JLabel patientMobileNumberValue;
    JLabel patientIdValue;
    JLabel consultationHoursValue;
    JLabel consultationCostValue;
    JLabel consultationNotesValue;
    Consultation consultation;

    public View(Consultation consultation) {
        this.consultation = consultation;
    }

    public JFrame start() {
        // GUI elements
        frame = new JFrame("View Consultation Details");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(900, 360);
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(false);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(),
                BoxLayout.Y_AXIS));

        // Main panel
        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(frame.getWidth(), 20));
        mainPanel.setBorder(new EmptyBorder(30, 20, 0, 20));
        mainPanel.setBackground(new Color(165, 201, 202));
        heading1 = new JLabel(String.format("Doctor: %s %s", consultation.getDoctor().getName(), consultation.getDoctor().getSurname()), JLabel.CENTER);
        heading1.setPreferredSize(new Dimension(frame.getWidth(), 15));
        heading1.setFont(new Font("Sans-serif", Font.BOLD, 16));
        mainPanel.add(heading1);

        // Patient details
        Patient p = consultation.getPatient();

        patientDetailsPanel = new JPanel();
        patientDetailsPanel.setLayout(new BoxLayout(patientDetailsPanel,
                BoxLayout.Y_AXIS));
        panel1 = new JPanel();
        panel1.setBorder(new EmptyBorder(0, 0, 10, 0));

        heading3 = new JLabel("Patient details",
                JLabel.CENTER);
        heading3.setPreferredSize(new Dimension(300, 40));
        heading3.setFont(new Font("Sans-serif", Font.ITALIC, 13));
        heading3.setForeground(new Color(41, 128, 185));
        patientDetailsPanel.add(heading3);

        panel3 = new JPanel();
        patientNameLabel = new JLabel("Name: ");
        patientNameLabel.setPreferredSize(new Dimension(120, 24));
        patientNameValue = new JLabel(p.getName());
        patientNameValue.setPreferredSize(new Dimension(180, 24));
        panel3.add(patientNameLabel);
        panel3.add(patientNameValue);
        patientDetailsPanel.add(panel3);

        panel4 = new JPanel();
        patientSurnameLabel = new JLabel("Surname: ");
        patientSurnameLabel.setPreferredSize(new Dimension(120, 24));
        patientSurnameValue = new JLabel(p.getSurname());
        patientSurnameValue.setPreferredSize(new Dimension(180, 24));
        panel4.add(patientSurnameLabel);
        panel4.add(patientSurnameValue);
        patientDetailsPanel.add(panel4);

        panel5 = new JPanel();
        patientDOBLabel = new JLabel("Date of birth: ");
        patientDOBLabel.setPreferredSize(new Dimension(120, 24));
        patientDOBValue = new JLabel(p.getDateOfBirth().toString());
        patientDOBValue.setPreferredSize(new Dimension(180, 24));
        panel5.add(patientDOBLabel);
        panel5.add(patientDOBValue);
        patientDetailsPanel.add(panel5);

        panel6 = new JPanel();
        patientMobileNumberLabel = new JLabel("Mobile number: ");
        patientMobileNumberLabel.setPreferredSize(new Dimension(120, 24));
        patientMobileNumberValue = new JLabel(p.getMobileNumber());
        patientMobileNumberValue.setPreferredSize(new Dimension(180, 24));
        panel6.add(patientMobileNumberLabel);
        panel6.add(patientMobileNumberValue);
        patientDetailsPanel.add(panel6);

        panel7 = new JPanel();
        patientIdLabel = new JLabel("Patient ID: ");
        patientIdLabel.setPreferredSize(new Dimension(120, 24));
        patientIdValue = new JLabel(String.valueOf(p.getPatientId()));
        patientIdValue.setPreferredSize(new Dimension(180, 24));
        panel7.add(patientIdLabel);
        panel7.add(patientIdValue);
        patientDetailsPanel.add(panel7);
        // Consultation details
        consultationDetailsPanel = new JPanel();
        consultationDetailsPanel.setLayout(new BoxLayout(consultationDetailsPanel,
                BoxLayout.Y_AXIS));

        heading4 = new JLabel("Consultation details",
                JLabel.CENTER);
        heading4.setPreferredSize(new Dimension(300, 40));
        heading4.setFont(new Font("Sans-serif", Font.ITALIC, 13));
        heading4.setForeground(new Color(41, 128, 185));
        consultationDetailsPanel.add(heading4);

        panel8 = new JPanel();
        consultationHoursLabel = new JLabel("Duration (hrs): ");
        consultationHoursLabel.setPreferredSize(new Dimension(100, 24));
        consultationHoursValue = new JLabel(String.valueOf(consultation.getHours()));
        consultationHoursValue.setPreferredSize(new Dimension(200, 24));
        panel8.add(consultationHoursLabel);
        panel8.add(consultationHoursValue);
        consultationDetailsPanel.add(panel8);

        panel9 = new JPanel();
        consultationCostLabel = new JLabel("Cost (£): ");
        consultationCostLabel.setPreferredSize(new Dimension(100, 24));
        consultationCostValue = new JLabel(String.valueOf(consultation.getCost()));
        consultationCostValue.setPreferredSize(new Dimension(200, 24));
        panel9.add(consultationCostLabel);
        panel9.add(consultationCostValue);
        consultationDetailsPanel.add(panel9);

        panel10 = new JPanel();
        panel10.setBorder(new EmptyBorder(5, 0, 0, 0));
        consultationNotesLabel = new JLabel("Notes: ");
        consultationNotesLabel.setPreferredSize(new Dimension(100, 56));
        consultationNotesValue = new JLabel(consultation.getNotes());
        consultationNotesValue.setPreferredSize(new Dimension(200, 72));
        panel10.add(consultationNotesLabel);
        panel10.add(consultationNotesValue);
        consultationDetailsPanel.add(panel10);

        formsPanel = new JPanel();
        formsPanel.add(patientDetailsPanel);
        formsPanel.add(consultationDetailsPanel);

        mainPanel.add(panel1);
        mainPanel.add(formsPanel);

        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);

        return frame;
    }

}


