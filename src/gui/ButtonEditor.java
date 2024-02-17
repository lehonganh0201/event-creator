package gui;
/*
 * @author HongAnh
 * @created 07 / 02 / 2024 - 5:03 PM
 * @project IntelliJ IDEA
 * @social Github: https://github.com/lehonganh0201
 */

import domain.Event;
import service.EventService;
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
    private final EventService eventService;

    public ButtonEditor(JCheckBox checkBox) {
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
        currentEvent = userService.getEventById((Integer) table.getValueAt(row, 0));
        return button;
    }

    public Object getCellEditorValue() {
        if (isPushed) {
            if ("+".equals(label) && currentEvent.getEventId()!=0) {
                userService.registerForEvent(LoginFrame.user, currentEvent,true);
                eventService.insertUserRole(LoginFrame.user,3);
                SendEmailFrame sendEmailFrame = new SendEmailFrame(LoginFrame.user,currentEvent);
                sendEmailFrame.sendBtnActionPerformed(LoginFrame.user,currentEvent);
            }
            else if ("->".equals(label)){
                RegisteredEventsFrame.getInstance().openDetailFrame(currentEvent);
            }
            else if("Show more".equals(label)){
                RegistedUserFrame frame = new RegistedUserFrame(currentEvent);
                frame.setVisible(true);
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
