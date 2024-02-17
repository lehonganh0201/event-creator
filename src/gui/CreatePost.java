package gui;
/*
 * @author HongAnh
 * @created 07 / 02 / 2024 - 5:03 PM
 * @project IntelliJ IDEA
 * @social Github: https://github.com/lehonganh0201
 */

import domain.Event;
import service.UserService;

import javax.swing.*;

public class CreatePost extends javax.swing.JFrame {

    /**
     * Creates new form CreatePost
     *
     */
    public CreatePost(Event event) {
        this.userService = new UserService();
        initComponents(event);
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents(Event event) {

        jScrollPane1 = new javax.swing.JScrollPane();
        contentInput = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        postBtn = new javax.swing.JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        contentInput.setColumns(20);
        contentInput.setRows(5);
        jScrollPane1.setViewportView(contentInput);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Content:");

        postBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        postBtn.setText("Post");
        postBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                postBtnActionPerformed(evt,event);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(120, 120, 120)
                                                .addComponent(postBtn)))
                                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(postBtn)
                                .addContainerGap(19, Short.MAX_VALUE))
        );
        pack();
    }// </editor-fold>

    private void postBtnActionPerformed(java.awt.event.ActionEvent evt,Event event) {
        userService.createEventPost(LoginFrame.user,event,contentInput.getText());
    }

    // Variables declaration - do not modify
    private javax.swing.JTextArea contentInput;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton postBtn;
    private final UserService userService;
    // End of variables declaration
}
