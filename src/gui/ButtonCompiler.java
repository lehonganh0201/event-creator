package gui;
/*
 * @author HongAnh
 * @created 12 / 02 / 2024 - 9:54 AM
 * @project IntelliJ IDEA
 * @social Github: https://github.com/lehonganh0201
 */

import domain.Event;
import domain.User;
import service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonCompiler extends DefaultCellEditor {
    private JButton button;
    private String label;
    private boolean isPushed;
    private final UserService userService;
    private User currentUser = new User();
    private Event currentEvent = new Event();

    public ButtonCompiler(JCheckBox checkBox, Event event) {
        super(checkBox);
        userService = new UserService();
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
        currentEvent = event;
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
        currentUser = userService.getUserById((Integer) table.getValueAt(row, 0));
        return button;
    }

    public Object getCellEditorValue() {
        if (isPushed) {
            if ("-".equals(label)){
                userService.cancelEventRegistration(currentUser,currentEvent);
            }
        }
        isPushed = false;
        return label;
    }

    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    public void setCurrentEvent(User currentEvent) {
        this.currentUser = currentEvent;
    }
}
