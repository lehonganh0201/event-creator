package gui;
/*
 * @author HongAnh
 * @created 16 / 02 / 2024 - 11:21 PM
 * @project IntelliJ IDEA
 * @social Github: https://github.com/lehonganh0201
 */

import domain.Event;
import domain.Group;
import service.UserService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.List;

public class ListGroupFrame extends javax.swing.JFrame {

    /**
     * Creates new form ListGroupFrame
     */
    public ListGroupFrame(Event event) {
        initComponents(event);
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents(Event event) {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                        {null, null, null}
                },
                new String [] {
                        "STT", "Name", "Join"
                }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();

        jTable1.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer());
        jTable1.getColumnModel().getColumn(2).setCellEditor(new GroupEditor(new JCheckBox()));
        jTable1.setRowHeight(40);
        tableModel = (DefaultTableModel) jTable1.getModel();


        updateGroupData(userService.getListGroup(event));
    }// </editor-fold>

    private void updateGroupData(List<Group> listGroup) {
        tableModel.setRowCount(0);

        for(Group group:listGroup){
            Object[] rowData = {
              group.getGroupId(),
              group.getName(),
              "Join"
            };
            tableModel.addRow(rowData);
        }
        jTable1.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer());
        jTable1.getColumnModel().getColumn(2).setCellEditor(new GroupEditor(new JCheckBox()));
    }

    // Variables declaration - do not modify
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private DefaultTableModel tableModel;
    private UserService userService = new UserService();
    // End of variables declaration
}
