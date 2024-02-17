package gui;
/*
 * @author HongAnh
 * @created 16 / 02 / 2024 - 10:00 PM
 * @project IntelliJ IDEA
 * @social Github: https://github.com/lehonganh0201
 */

import domain.Event;
import domain.Group;
import service.UserService;

import javax.swing.*;

public class CreateGroupFrame extends javax.swing.JFrame {

    /**
     * Creates new form CreateGroupFrame
     */
    public CreateGroupFrame(Event event) {
        userService = new UserService();
        initComponents(event);
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents(Event event) {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        createBtn = new javax.swing.JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Group Name:");

        createBtn.setText("Create");
        createBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createBtnActionPerformed(evt,event);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(createBtn)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(9, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(createBtn))
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>

    private void createBtnActionPerformed(java.awt.event.ActionEvent evt,Event event) {
        // TODO add your handling code here:
        Group group = new Group();
        group.setName(jTextField1.getText());
        userService.createGroup(LoginFrame.user,group);
        userService.addEventToGroup(event, userService.getGroup());
        userService.addUserToGroup(LoginFrame.user, userService.getGroup());
    }

    // Variables declaration - do not modify
    private javax.swing.JButton createBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    private final UserService userService;
}