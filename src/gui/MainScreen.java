/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

/**
 *
 * @author PC
 */
public class MainScreen extends javax.swing.JFrame {

    public MainScreen() {
        initComponents();
        setLocationRelativeTo(null);
    }


    @SuppressWarnings("unchecked")
    private void initComponents() {

        EditProfile = new javax.swing.JButton();
        showListEvent = new javax.swing.JButton();
        RegisterEvent = new javax.swing.JButton();
        createEventBtn = new javax.swing.JButton();
        backLogin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        EditProfile.setBackground(new java.awt.Color(255, 153, 255));
        EditProfile.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        EditProfile.setText("Edit Profile");
        EditProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditProfileActionPerformed(evt);
            }
        });

        showListEvent.setBackground(new java.awt.Color(255, 153, 255));
        showListEvent.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        showListEvent.setText("List Event");
        showListEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showListEventActionPerformed(evt);
            }
        });

        RegisterEvent.setBackground(new java.awt.Color(255, 153, 255));
        RegisterEvent.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        RegisterEvent.setText("Registed Event");
        RegisterEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegisterEventActionPerformed(evt);
            }
        });

        createEventBtn.setBackground(new java.awt.Color(255, 153, 255));
        createEventBtn.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        createEventBtn.setText("Create Event");
        createEventBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createEventBtnActionPerformed(evt);
            }
        });

        backLogin.setText("Log Out");
        backLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(EditProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(showListEvent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(RegisterEvent, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                                        .addComponent(createEventBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                                .addGap(147, 147, 147)
                                .addComponent(backLogin)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(EditProfile)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(showListEvent)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(RegisterEvent)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(createEventBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 201, Short.MAX_VALUE)
                                .addComponent(backLogin)
                                .addContainerGap())
        );

        pack();
    }

    private void EditProfileActionPerformed(java.awt.event.ActionEvent evt) {
        EditUserFrame editUserFrame = new EditUserFrame();
        editUserFrame.setVisible(true);
        dispose();
    }

    private void backLoginActionPerformed(java.awt.event.ActionEvent evt) {
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
        dispose();
    }

    private void RegisterEventActionPerformed(java.awt.event.ActionEvent evt) {
        RegisteredEventsFrame registeredEventsFrame = new RegisteredEventsFrame(LoginFrame.user);
        registeredEventsFrame.setVisible(true);
        dispose();
    }

    private void createEventBtnActionPerformed(java.awt.event.ActionEvent evt) {
        CreateEventFrame createEventFrame = new CreateEventFrame();
        createEventFrame.setVisible(true);
        dispose();
    }

    private void showListEventActionPerformed(java.awt.event.ActionEvent evt) {
        EventListFrame eventListFrame = new EventListFrame();
        eventListFrame.setVisible(true);
        dispose();
    }

    private javax.swing.JButton EditProfile;
    private javax.swing.JButton RegisterEvent;
    private javax.swing.JButton backLogin;
    private javax.swing.JButton createEventBtn;
    private javax.swing.JButton showListEvent;
}
