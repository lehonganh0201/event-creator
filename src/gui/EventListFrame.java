/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import domain.Event;
import service.EventService;
import service.UserService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author PC
 */
public class EventListFrame extends javax.swing.JFrame {

    public EventListFrame() {
        eventService = new EventService();
        userService = new UserService();
        initComponents();
        setLocationRelativeTo(null);
    }


    @SuppressWarnings("unchecked")
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        eventTable = new javax.swing.JTable();
        prevButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        eventTable.setModel(new DefaultTableModel(
                new Object [][] {
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null}
                },
                new String [] {
                        "STT", "Event Name", "Creation Date", "Reg. End Date", "Start Date", "End Date", "Place", "Capacity", "Status", "Register"
                }
        ));


        jScrollPane1.setViewportView(eventTable);

        prevButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        prevButton.setText("<");
        prevButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevButtonActionPerformed(evt);
            }
        });

        nextButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        nextButton.setText(">");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Page: " + currentPage);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(backButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(prevButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addGap(21, 21, 21)
                                .addComponent(nextButton)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(prevButton)
                                        .addComponent(nextButton)
                                        .addComponent(backButton)
                                        .addComponent(jLabel1))
                                .addContainerGap())
        );

        pack();

        eventTable.getColumnModel().getColumn(9).setCellRenderer(new ButtonRenderer());
        eventTable.getColumnModel().getColumn(9).setCellEditor(new ButtonEditor(new JCheckBox()));
        eventTable.setRowHeight(58);
        tableModel = (DefaultTableModel) eventTable.getModel();

        updateEventData(eventService.showListEvent());
    }

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        MainScreen mainScreen = new MainScreen();
        mainScreen.setVisible(true);
        dispose();
    }

    private void prevButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if(currentPage > 1){
            currentPage--;
            updateEventData(eventService.showListEvent());
            jLabel1.setText("Page: " + currentPage);
        }
    }

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int maxPage = (int) Math.ceil((double) eventService.showListEvent().size() / eventsPerPage);
        if (currentPage < maxPage) {
            currentPage++;
            updateEventData(eventService.showListEvent());
            jLabel1.setText("Page: " + currentPage);
        }
    }

    private void updateEventData(List<Event> events) {
        tableModel.setRowCount(0);

        int startIndex = (currentPage - 1) * eventsPerPage;
        int endIndex = Math.min(startIndex + eventsPerPage, events.size());

        for (int i = startIndex; i < endIndex; i++) {
            domain.Event event = events.get(i);
            String status = calculateEventStatus(event);

            JButton registerButton = new JButton("Đăng kí");
            if ("Open To Register".equals(status)) {
                registerButton.setText("+");
            } else {
                registerButton.setEnabled(false);
            }

            Object[] rowData = {
                    event.getEventId(),
                    event.getName(),
                    event.getCreationDate(),
                    event.getRegistrationEndDate(),
                    event.getStartDateTime(),
                    event.getEndDateTime(),
                    event.getPlace(),
                    event.getCapacity(),
                    status,
                    (registerButton.getText().equals("+") ? "+" : "")
            };
            tableModel.addRow(rowData);
        }

        eventTable.getColumnModel().getColumn(9).setCellRenderer(new ButtonRenderer());
        eventTable.getColumnModel().getColumn(9).setCellEditor(new ButtonEditor(new JCheckBox()));
    }

    private String calculateEventStatus(domain.Event event) {
        LocalDate currentDate = LocalDate.now();
        LocalDate endRes = event.getRegistrationEndDate().toLocalDate();
        LocalDate startDate = event.getStartDateTime().toLocalDate();
        LocalDate endDate = event.getEndDateTime().toLocalDate();

        if (currentDate.isEqual(endRes) || currentDate.isBefore(endRes)) {
            return "Open To Register";
        } else if (currentDate.isAfter(endRes) && currentDate.isBefore(startDate)) {
            return "Closed To Register";
        } else if ((currentDate.isEqual(startDate) || (currentDate.isAfter(startDate)) && currentDate.isBefore(endDate))) {
            return "On Going";
        } else if (currentDate.isAfter(endDate)) {
            return "Past";
        }

        return "Unknown";
    }

    private javax.swing.JButton prevButton;
    private javax.swing.JButton nextButton;
    private javax.swing.JButton backButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable eventTable;
    private DefaultTableModel tableModel;
    private int currentPage = 1;
    private int eventsPerPage = 5;
    private final EventService eventService;
    private final UserService userService;
}
