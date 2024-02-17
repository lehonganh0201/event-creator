package gui;
/*
 * @author HongAnh
 * @created 14 / 02 / 2024 - 8:16 PM
 * @project IntelliJ IDEA
 * @social Github: https://github.com/lehonganh0201
 */

import domain.Event;
import domain.User;
import service.EventService;
import service.UserService;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminEditor extends DefaultCellEditor{
    private JButton button;
    private String label;
    private boolean isPushed;
    private final UserService userService;
    private final EventService eventService;
    private User currentUser = new User();
    private Event currentEvent = new Event();

    public AdminEditor(JCheckBox checkBox) {
        super(checkBox);
        userService = new UserService();
        eventService = new EventService();
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
        currentUser = userService.getUserById((Integer) table.getValueAt(row, 0));
        currentEvent = eventService.getEventById((Integer) table.getValueAt(row,0));
        return button;
    }

    public Object getCellEditorValue() {
        if (isPushed) {
            if("Remove".equals(label)){
                userService.deleteUser(currentUser);
            }
            else if("REMOVE".equals(label)){
                eventService.deleteEvent(currentEvent);
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