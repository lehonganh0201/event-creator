package gui;
/*
 * @author HongAnh
 * @created 15 / 02 / 2024 - 10:07 PM
 * @project IntelliJ IDEA
 * @social Github: https://github.com/lehonganh0201
 */

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.plot.PlotOrientation;
import service.EventService;
import service.UserService;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class StatisticalFrame extends JPanel {

    private int year = Calendar.getInstance().get(Calendar.YEAR);
    private final EventService eventService;
    private final UserService userService;

    public StatisticalFrame() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        eventService = new EventService();
        userService = new UserService();
        JPanel eventPanel = new JPanel();
        eventPanel.setLayout(new BorderLayout());
        DefaultCategoryDataset eventDataset = createEventDataset();
        JFreeChart eventChart = ChartFactory.createBarChart(
                "Event Statistics", "Category", "Quantity", eventDataset, PlotOrientation.VERTICAL, true, true, false);
        ChartPanel eventChartPanel = new ChartPanel(eventChart);
        eventPanel.add(eventChartPanel, BorderLayout.CENTER);
        add(eventPanel);

        add(Box.createVerticalStrut(10));

        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BorderLayout());
        DefaultCategoryDataset userDataset = createUserDataset();
        JFreeChart userChart = ChartFactory.createBarChart(
                "User Statistics", "Category", "Quantity", userDataset, PlotOrientation.VERTICAL, true, true, false);
        ChartPanel userChartPanel = new ChartPanel(userChart);
        userPanel.add(userChartPanel, BorderLayout.CENTER);
        add(userPanel);
    }

    private DefaultCategoryDataset createEventDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(eventService.countEvent(1,year), "January", "Thống kê số lượng sự kiện được tạo năm " +year);
        dataset.addValue(eventService.countEvent(2,year), "February", "Thống kê số lượng sự kiện được tạo năm " +year);
        dataset.addValue(eventService.countEvent(3,year), "March", "Thống kê số lượng sự kiện được tạo năm " +year);
        dataset.addValue(eventService.countEvent(4,year), "April", "Thống kê số lượng sự kiện được tạo năm " +year);
        dataset.addValue(eventService.countEvent(5,year), "May", "Thống kê số lượng sự kiện được tạo năm " +year);
        dataset.addValue(eventService.countEvent(6,year), "June", "Thống kê số lượng sự kiện được tạo năm " +year);
        dataset.addValue(eventService.countEvent(7,year), "July", "Thống kê số lượng sự kiện được tạo năm " +year);
        dataset.addValue(eventService.countEvent(8,year), "August", "Thống kê số lượng sự kiện được tạo năm " +year);
        dataset.addValue(eventService.countEvent(9,year), "September", "Thống kê số lượng sự kiện được tạo năm " +year);
        dataset.addValue(eventService.countEvent(10,year), "October", "Thống kê số lượng sự kiện được tạo năm " +year);
        dataset.addValue(eventService.countEvent(11,year), "November", "Thống kê số lượng sự kiện được tạo năm " +year);
        dataset.addValue(eventService.countEvent(12,year), "December", "Thống kê số lượng sự kiện được tạo năm " +year);

        return dataset;
    }

    private DefaultCategoryDataset createUserDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(userService.countUser(1,year), "January", "Thống kê số lượng người dùng đăng ký năm " +year);
        dataset.addValue(userService.countUser(2,year), "February", "Thống kê số lượng người dùng đăng ký năm " +year);
        dataset.addValue(userService.countUser(3,year), "March", "Thống kê số lượng người dùng đăng ký năm " +year);
        dataset.addValue(userService.countUser(4,year), "April", "Thống kê số lượng người dùng đăng ký năm " +year);
        dataset.addValue(userService.countUser(5,year), "May", "Thống kê số lượng người dùng đăng ký năm " +year);
        dataset.addValue(userService.countUser(6,year), "June", "Thống kê số lượng người dùng đăng ký năm " +year);
        dataset.addValue(userService.countUser(7,year), "July", "Thống kê số lượng người dùng đăng ký năm " +year);
        dataset.addValue(userService.countUser(8,year), "August", "Thống kê số lượng người dùng đăng ký năm " +year);
        dataset.addValue(userService.countUser(9,year), "September", "Thống kê số lượng người dùng đăng ký năm " +year);
        dataset.addValue(userService.countUser(10,year), "October", "Thống kê số lượng người dùng đăng ký năm " +year);
        dataset.addValue(userService.countUser(11,year), "November", "Thống kê số lượng người dùng đăng ký năm " +year);
        dataset.addValue(userService.countUser(12,year), "December", "Thống kê số lượng người dùng đăng ký năm " +year);
        return dataset;
    }
}
