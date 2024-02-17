package gui;
/*
 * @author HongAnh
 * @created 16 / 02 / 2024 - 11:29 PM
 * @project IntelliJ IDEA
 * @social Github: https://github.com/lehonganh0201
 */

import domain.Event;
import domain.Group;
import service.EventService;
import service.UserService;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GroupEditor extends DefaultCellEditor{
    private JButton button;
    private String label;
    private boolean isPushed;
    private final UserService userService;
    private Group currentGroup = new Group();

    public GroupEditor(JCheckBox checkBox) {
        super(checkBox);
        userService = new UserService();
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(UIManager.getColor("Button.background"));
        }

        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        currentGroup = userService.getGroupById((Integer) table.getValueAt(row, 0));
        return button;
    }

    public Object getCellEditorValue() {
        if (isPushed) {
            if("Join".equals(label)){
                userService.joinGroup(LoginFrame.user,currentGroup);
            }
        }
        isPushed = false;
        return label;
    }

    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
}
