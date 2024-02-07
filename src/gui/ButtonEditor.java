package gui;


import domain.Event;
import service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonEditor extends DefaultCellEditor{
    private JButton button;
    private String label;
    private boolean isPushed;
    private final UserService userService;
    private Event currentEvent = new Event();

    public ButtonEditor(JCheckBox checkBox) {
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
        currentEvent = userService.getEventById((Integer) table.getValueAt(row, 0));
        return button;
    }

    public Object getCellEditorValue() {
        if (isPushed) {
            if ("+".equals(label) && currentEvent.getEventId()!=0) {
                userService.registerForEvent(LoginFrame.user, currentEvent);
            }
            else if ("->".equals(label)){
                viewEventDetailsFrame viewEventDetailsFrame = new viewEventDetailsFrame(currentEvent);
                viewEventDetailsFrame.setVisible(true);
            }
        }
        isPushed = false;
        return label;
    }

    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    public void setCurrentEvent(Event currentEvent) {
        this.currentEvent = currentEvent;
    }
}