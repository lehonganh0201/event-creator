package gui;

import domain.Event;
import domain.User;
import service.EventService;
import service.UserService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RegisteredEventsFrame extends JFrame {
    private JTable eventTable;
    private DefaultTableModel tableModel;
    private final EventService eventService;
    private final UserService userService;

    public static User user;

    public RegisteredEventsFrame(User user) {
        eventService = new EventService();
        userService = new UserService();
        setTitle("Registered Events");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        String[] columnNames = {"STT", "Event Name", "Creation Date", "Start Date", "End Date", "Place", "Capacity", "Price", "Show More"};
        tableModel = new DefaultTableModel(columnNames, 0);
        eventTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(eventTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel backPanel = new JPanel();
        backPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openMainScreen();
            }
        });
        backPanel.add(backButton);
        add(backPanel, BorderLayout.SOUTH);

        updateEventData(userService.viewRegisteredEvents(LoginFrame.user));
        setLocationRelativeTo(null);


        TableColumn showMoreColumn = eventTable.getColumn("Show More");
        showMoreColumn.setCellRenderer(new ButtonRenderer());
        showMoreColumn.setCellEditor(new ButtonEditor(new JCheckBox()));
    }

    private void updateEventData(List<Event> userEvents) {
        tableModel.setRowCount(0);

        for (Event event : userEvents) {
            Object[] rowData = {
                    event.getEventId(),
                    event.getName(),
                    event.getCreationDate(),
                    event.getStartDateTime(),
                    event.getEndDateTime(),
                    event.getPlace(),
                    event.getCapacity(),
                    event.getPrice(),
                    "->"
            };
            tableModel.addRow(rowData);
        }
    }

    private void openMainScreen() {
        MainScreen mainScreen = new MainScreen();
        mainScreen.setVisible(true);
        this.dispose();
    }
}
