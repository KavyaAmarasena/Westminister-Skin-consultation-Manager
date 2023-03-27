package com.skin_consultation_centre.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.skin_consultation_centre.Consultation;
import com.skin_consultation_centre.Doctor;
import com.skin_consultation_centre.Patient;
import com.skin_consultation_centre.WestminsterSkinConsultationManager;

public class SkinConsultationManagerView {
    JFrame frame;
    JTable tableDoctors;
    JPanel bodyPanel;
    JPanel headerPanel;
    JPanel doctorsSidePanel;
    JPanel doctorsContentWrapper;
    JPanel consultationsSidePanel;
    JPanel consultationsContentWrapper;
    JPanel consultationsListPanel;
    Doctor selectedDoctor;
    JButton btnSort;
    JButton btnAddConsultation;
    JPanel consultationItemPanel;
    JLabel heading1;
    JLabel heading2;
    JLabel heading3;
    JLabel lbl1;
    JLabel lbl2;
    JLabel lbl3;
    JScrollPane scrollPane1;
    JScrollPane scrollPane2;

    private static final String[] doctorsTableColumns = { "Name", "Surname", "Date of birth", "Mobile number",
            "License no:", "Specialisation" };

    private static WestminsterSkinConsultationManager wscm = WestminsterSkinConsultationManager
            .getManager();

    public JFrame start() {
        // GUI elements

        frame = new JFrame("W1870545_Skin Consultation Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(940, 600);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        headerPanel = new JPanel();
        headerPanel.setPreferredSize(new Dimension(frame.getWidth(), 40));
        headerPanel.setBorder(new EmptyBorder(0, 50, 0, 50));

        heading1 = new JLabel("Westminster Skin Consultation Manager", JLabel.CENTER);
        heading1.setForeground(Color.BLACK);
        heading1.setFont(new Font("Sans-serif", Font.BOLD, 20));
        headerPanel.setBackground(new Color(231, 246, 242));
        headerPanel.add(heading1);
        frame.getContentPane().add(headerPanel);

        // Body
        bodyPanel = new JPanel();
        bodyPanel.setPreferredSize(new Dimension(frame.getWidth(), 860));
        bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.X_AXIS));
        bodyPanel.setBackground(new Color(53, 59, 72));

        // Doctors panel
        doctorsSidePanel = new JPanel();
        doctorsSidePanel.setLayout(new BoxLayout(doctorsSidePanel, BoxLayout.Y_AXIS));
        doctorsSidePanel.setBorder(new EmptyBorder(15, 25, 25, 15));
        doctorsSidePanel.setBackground(new Color(165, 201, 202));

        // Doctors content
        doctorsContentWrapper = new JPanel();
        doctorsContentWrapper.setBorder(new EmptyBorder(20, 0, 30, 0));
        doctorsContentWrapper.setBackground(new Color(165, 201, 202));

        heading2 = new JLabel("Doctors list", JLabel.CENTER);
        heading2.setFont(new Font("Sans-serif", Font.BOLD, 18));
        heading2.setBorder(new EmptyBorder(0, 0, 0, 30));
        heading2.setForeground(Color.BLACK);
        doctorsContentWrapper.add(heading2);

        btnSort = new JButton("Sort list");
        btnSort.setFont(new Font("Sans-serif", Font.BOLD, 14));
        btnSort.setPreferredSize(new Dimension(150, 36));
        btnSort.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleBtnSortClick();
            }
        });
        doctorsContentWrapper.add(btnSort);

        doctorsSidePanel.add(doctorsContentWrapper);

        tableDoctors = new JTable(new DefaultTableModel(doctorsTableColumns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        tableDoctors.setShowHorizontalLines(true);
        tableDoctors.setFont(new Font("Sans-serif", Font.PLAIN, 13));
        tableDoctors.getTableHeader().setFont(new Font("Sans-serif", Font.BOLD, 13));
        tableDoctors.getTableHeader().setPreferredSize(new Dimension(400, 28));

        tableDoctors.setRowHeight(28);
        tableDoctors.setDragEnabled(false);
        tableDoctors.setSelectionMode(0);

        tableDoctors.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                setSelectedDoctor(
                        Integer.parseInt(tableDoctors.getValueAt(tableDoctors.getSelectedRow(), 4).toString()));

            }
        });
        generateDoctorTableData(false);
        scrollPane1 = new JScrollPane(tableDoctors);
        scrollPane1.setPreferredSize(new Dimension(700, 800));
        scrollPane1.setBackground(new Color(196, 220, 224));
        doctorsSidePanel.add(scrollPane1);

        bodyPanel.add(doctorsSidePanel);

        // Consultations
        consultationsSidePanel = new JPanel();
        consultationsSidePanel.setLayout(new BoxLayout(consultationsSidePanel, BoxLayout.Y_AXIS));
        consultationsSidePanel.setBorder(new EmptyBorder(15, 15, 25, 25));
        consultationsSidePanel.setBackground(new Color(165, 201, 202));


        consultationsContentWrapper = new JPanel();

        consultationsContentWrapper.setBorder(new EmptyBorder(20, 0, 30, 0));
        consultationsContentWrapper.setBackground(new Color(165, 201, 202));
        heading3 = new JLabel("Consultations list", JLabel.CENTER);
        heading3.setFont(new Font("Sans-serif", Font.BOLD, 18));
        heading3.setBorder(new EmptyBorder(0, 0, 0, 30));
        heading3.setForeground(Color.BLACK);
        consultationsContentWrapper.add(heading3);

        //consultation button
        btnAddConsultation = new JButton("Add consultation");
        btnAddConsultation.setFont(new Font("Sans-serif", Font.BOLD, 14));
        btnAddConsultation.setPreferredSize(new Dimension(180, 36));
        btnAddConsultation.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleBtnAddConsultationClick();

            }
        });
        consultationsContentWrapper.add(btnAddConsultation);

        consultationsSidePanel.add(consultationsContentWrapper);

        consultationsListPanel = new JPanel();
        consultationsListPanel.setLayout(new BoxLayout(consultationsListPanel, BoxLayout.Y_AXIS));
        consultationsListPanel.setBackground(Color.WHITE);
        generateConsultationListData();
        scrollPane2 = new JScrollPane(consultationsListPanel);
        scrollPane2.setPreferredSize(new Dimension(300, 800));
        scrollPane2.setBackground(Color.WHITE);
        consultationsSidePanel.add(scrollPane2);

        bodyPanel.add(consultationsSidePanel);
        frame.getContentPane().add(bodyPanel);
        frame.setVisible(true);
        return frame;
    }

    //doctor table
    private void generateDoctorTableData(boolean isSorted) {
        ArrayList<Doctor> doctors = wscm.getDoctors();

        // Sort doctors by surname
        if (isSorted) {
            Collections.sort(doctors);
        }

        DefaultTableModel dtm = (DefaultTableModel) tableDoctors.getModel();
        dtm.setRowCount(0);

        for (Doctor d : doctors) {

            String[] rowData = new String[] { d.getName(), d.getSurname(),
                    d.getDateOfBirth().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    d.getMobileNumber(),
                    String.valueOf(d.getMedicalLicenseNumber()),
                    d.getSpecialisation()
            };

            dtm.addRow(rowData);
        }

        frame.revalidate();
        frame.repaint();
    }

    //consultations list
    public void generateConsultationListData() {
        if (wscm.getConsultations().isEmpty()) {
            consultationsContentWrapper = new JPanel();
            consultationsContentWrapper.setBorder(null);
            consultationsContentWrapper.setBackground(Color.WHITE);
            lbl1 = new JLabel("No consultations added yet", JLabel.CENTER);
            lbl1.setFont(new Font("Sans-serif", Font.PLAIN, 16));
            lbl1.setPreferredSize(new Dimension(600, 100));
            lbl1.setForeground(new Color(231, 76, 60));
            consultationsContentWrapper.add(lbl1);
            consultationsListPanel.add(consultationsContentWrapper);

        } else {
            consultationsListPanel.removeAll();
            // Add consultations
            for (Consultation c : wscm.getConsultations()) {
                consultationItemPanel = new JPanel();
                consultationItemPanel.setPreferredSize(new Dimension(400, 10));
                consultationItemPanel.setBackground(Color.WHITE);

                lbl2 = new JLabel(c.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm")));
                lbl2.setFont(new Font("Sans-serif", Font.BOLD, 13));
                lbl2.setBorder(new EmptyBorder(0, 0, 0, 20));
                consultationItemPanel.add(lbl2);

                lbl3 = new JLabel(String.format("Patient %d with Dr. %s %s", c.getPatient().getPatientId(),
                        c.getDoctor().getName(), c.getDoctor().getSurname()));
                lbl3.setFont(new Font("Sans-serif", Font.PLAIN, 13));
                lbl3.setBorder(new EmptyBorder(0, 15, 0, 40));
                consultationItemPanel.add(lbl3);

                JButton btn1 = new JButton("View");
                btn1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        showConsultationDetails(c);
                    }
                });

                consultationItemPanel.add(btn1);
                consultationsListPanel.add(consultationItemPanel);
            }
        }

        SwingUtilities.updateComponentTreeUI(frame);
        frame.revalidate();
        frame.repaint();

    }

    private void handleBtnSortClick() {
        generateDoctorTableData(true);
    }

    private void handleBtnAddConsultationClick() {
        if (selectedDoctor != null) {
            openAddConsultationFrame();
        } else {
            JOptionPane.showMessageDialog(null, "Please select a doctor first");
        }
    }

    private void setSelectedDoctor(int medicalLicenseNumber) {
        for (Doctor d : wscm.getDoctors()) {
            if (d.getMedicalLicenseNumber() == medicalLicenseNumber) {
                selectedDoctor = d;
                break;
            }
        }
    }

    private JFrame openAddConsultationFrame() {
        AddConsultationView gui = new AddConsultationView(this, selectedDoctor);
        return gui.start();
    }

    private JFrame showConsultationDetails(Consultation consultation) {
        View gui = new View(consultation);
        return gui.start();
    }
}
