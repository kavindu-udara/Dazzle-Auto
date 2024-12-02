/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.dashboard;

import com.formdev.flatlaf.FlatClientProperties;
import controllers.VehicleController;
import controllers.VehicleTypeController;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import views.components.charts.ModelPieChart;
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import includes.LoggerConfig;
import includes.MySqlConnection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.SwingConstants;
import views.components.charts.com.raven.chart.ModelChart;

/**
 *
 * @author E
 */
public class DashboardPanel extends javax.swing.JPanel {

    private static final Logger logger = LoggerConfig.getLogger();
    ArrayList<String> categories = new ArrayList<>();

    public DashboardPanel() {
        initComponents();
        roundPanels();

        reload();
    }

    public void reload() {
        showDataInPieChart();
        setDailyIncome();
        setMonthlyIncome();
        setTodayWorks();
        setTodayStaff();
        setRegisteredCustomers();
        setRegisteredEmployees();
        setRegisteredVehicles();
        setTodayAppointments();
        setMostServicedVehicles();
        setTopCustomers();
        setIssuedInvoices();
    }

    public void setIssuedInvoices() {
        chart.addLegend("Invoice Count", new Color(245, 189, 135));
        try {

            ResultSet resultSet = MySqlConnection.executeSearch("SELECT DATE_FORMAT(`date`, '%Y-%m-%d') AS `inv_date`, COUNT(id) AS `count` "
                    + "FROM service_invoice GROUP BY `inv_date` ORDER BY `inv_date` ASC LIMIT 6 ");

            chart.model.clear();
            while (resultSet.next()) {
                String inv_date = resultSet.getString("inv_date");
                int count = resultSet.getInt("count");
                chart.addData(new ModelChart(inv_date, new double[]{count}));
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while setIssuedInvoices() : " + e.getMessage());
        }
    }

    public void setTopCustomers() {
        ArrayList<String> mobileArry = new ArrayList<>();
        ArrayList<String> customerArry = new ArrayList<>();

        try {
            // Format the current date to "YYYY-MM"
            String month = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));

            ResultSet resultSet = MySqlConnection.executeSearch("SELECT COUNT(service_invoice.id) AS `invoice_count`,"
                    + "CONCAT(customer.first_name,' ',customer.last_name) AS `fullname`, "
                    + "customer.mobile FROM service_invoice "
                    + "INNER JOIN vehicle ON service_invoice.vehicle_number=vehicle.vehicle_number "
                    + "INNER JOIN customer ON vehicle.customer_id=customer.id "
                    + "GROUP BY customer.id ORDER BY `invoice_count` DESC LIMIT 5 ");

            while (resultSet.next()) {
                customerArry.add(resultSet.getString("fullname"));
                mobileArry.add(resultSet.getString("mobile"));
            }

            DefaultListModel<String> customermodel = new DefaultListModel<>();
            DefaultListModel<String> mobilemodel = new DefaultListModel<>();

            for (String customers : customerArry) {
                customermodel.addElement(customers);
            }

            for (String mobile : mobileArry) {
                mobilemodel.addElement(mobile);
            }

            topCustomersList.setModel(customermodel);
            topMobileList.setModel(mobilemodel);

            // Set custom renderer for right alignment
            topMobileList.setCellRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(
                        JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

                    // Get default rendering component
                    JLabel label = (JLabel) super.getListCellRendererComponent(
                            list, value, index, isSelected, cellHasFocus);

                    // Set text alignment to the right
                    label.setHorizontalAlignment(SwingConstants.RIGHT);

                    return label;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while setTodayStaff() : " + e.getMessage());
        }
    }

    public void setMostServicedVehicles() {
        ArrayList<String> typeArry = new ArrayList<>();
        ArrayList<Integer> countArry = new ArrayList<>();

        try {
            // Format the current date to "YYYY-MM"
            String day = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            ResultSet resultSet = MySqlConnection.executeSearch("SELECT COUNT(`service_invoice`.`id`) AS `vcount`, `vehicle_type`.`name` AS `type` "
                    + "FROM service_invoice "
                    + "INNER JOIN vehicle ON service_invoice.vehicle_number=vehicle.vehicle_number "
                    + "INNER JOIN vehicle_type ON vehicle.vehicle_type_id=vehicle_type.id "
                    + "GROUP BY `vehicle_type`.`name` ORDER BY `vcount` DESC LIMIT 3");

            while (resultSet.next()) {
                typeArry.add(resultSet.getString("type"));
                countArry.add(resultSet.getInt("vcount"));
            }

            int arrySize = typeArry.size();

            if (arrySize >= 1) {
                jProgressBar1.setValue(countArry.get(0));
                typeLabel1.setText(typeArry.get(0));
                countLabel1.setText(String.valueOf(countArry.get(0)));
            }

            if (arrySize >= 2) {
                jProgressBar2.setValue(countArry.get(1));
                typeLabel2.setText(typeArry.get(1));
                countLabel2.setText(String.valueOf(countArry.get(1)));
            }

            if (arrySize >= 3) {
                jProgressBar3.setValue(countArry.get(2));
                typeLabel3.setText(typeArry.get(2));
                countLabel3.setText(String.valueOf(countArry.get(2)));
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while setTodayStaff() : " + e.getMessage());
        }
    }

    public void setRegisteredVehicles() {
        try {
            ResultSet resultSet = MySqlConnection.executeSearch("SELECT COUNT(`vehicle_number`) AS `veh_count` FROM `vehicle` ");

            int veh_count = 0;
            if (resultSet.next()) {
                veh_count = resultSet.getInt("veh_count");
            }
            regVehiclesLabel.setText(String.valueOf(veh_count));
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while setRegisteredVehicles() : " + e.getMessage());
        }
    }

    public void setRegisteredEmployees() {
        try {
            ResultSet resultSet = MySqlConnection.executeSearch("SELECT COUNT(`id`) AS `emp_count` FROM `employee` ");

            int emp_count = 0;
            if (resultSet.next()) {
                emp_count = resultSet.getInt("emp_count");
            }
            regEmployeesLabel.setText(String.valueOf(emp_count));
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while setRegisteredEmployees() : " + e.getMessage());
        }
    }

    public void setRegisteredCustomers() {
        try {
            ResultSet resultSet = MySqlConnection.executeSearch("SELECT COUNT(`id`) AS `cus_count` FROM `customer` ");

            int cus_count = 0;
            if (resultSet.next()) {
                cus_count = resultSet.getInt("cus_count");
            }
            regCustomersLabel.setText(String.valueOf(cus_count));
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while setRegisteredCustomers() : " + e.getMessage());
        }
    }

    public void setTodayAppointments() {
        ArrayList<String> servicesArry = new ArrayList<>();
        ArrayList<String> servicesCountArry = new ArrayList<>();

        try {
            // Format the current date to "YYYY-MM"
            String day = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            ResultSet resultSet = MySqlConnection.executeSearch("SELECT COUNT(`appointment`.`id`) AS `app_count`, `services`.`name` FROM `appointment` "
                    + "INNER JOIN services ON appointment.services_id=services.id WHERE `date` LIKE '%" + day + "%' GROUP BY `services`.`name`  ");

            int app_count = 0;
            while (resultSet.next()) {
                app_count += resultSet.getInt("app_count");
                servicesArry.add(resultSet.getString("name"));
                servicesCountArry.add(resultSet.getString("app_count"));
            }

            DefaultListModel<String> model = new DefaultListModel<>();
            DefaultListModel<String> countmodel = new DefaultListModel<>();

            for (String services : servicesArry) {
                model.addElement(services);
            }

            for (String count : servicesCountArry) {
                countmodel.addElement(count);
            }

            jList1.setModel(model);
            jList2.setModel(countmodel);

            appointmentLabel.setText(String.valueOf(app_count));
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while setTodayAppointments() : " + e.getMessage());
        }
    }

    public void setTodayStaff() {
        try {
            // Format the current date to "YYYY-MM"
            String day = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            ResultSet resultSet = MySqlConnection.executeSearch("SELECT COUNT(`employee_id`) AS `attends` FROM emp_attendance "
                    + "INNER JOIN attendance_date ON emp_attendance.attendance_date_id=attendance_date.id "
                    + "WHERE attendance_status_id='1' AND `attendance_date`.`date` LIKE '%" + day + "%'");

            int attends = 0;
            if (resultSet.next()) {
                attends = resultSet.getInt("attends");
            }
            staffLabel.setText(String.valueOf(attends));
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while setTodayStaff() : " + e.getMessage());
        }
    }

    public void setTodayWorks() {
        try {
            // Format the current date to "YYYY-MM"
            String day = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            ResultSet resultSet = MySqlConnection.executeSearch("SELECT COUNT(`id`) AS `works` FROM service_invoice WHERE `date` LIKE '%" + day + "%'");

            int works = 0;
            if (resultSet.next()) {
                works = resultSet.getInt("works");
            }
            worksLabel.setText(String.valueOf(works));
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while setTodayWorks() : " + e.getMessage());
        }
    }

    public void setMonthlyIncome() {
        try {
            // Format the current date to "YYYY-MM"
            String month = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));

            ResultSet resultSet = MySqlConnection.executeSearch("SELECT SUM(`total`) AS `total` FROM service_invoice WHERE `date` LIKE '%" + month + "%'");

            String total = "0";
            if (resultSet.next()) {
                total = resultSet.getString("total");
                if (total == null) {
                    total = " 0.00";
                }
            }
            MonthlyIncomeLabel.setText("Rs." + total);
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while setMonthlyIncome() : " + e.getMessage());
        }
    }

    public void setDailyIncome() {
        try {
            // Format the current date to "YYYY-MM"
            String day = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            ResultSet resultSet = MySqlConnection.executeSearch("SELECT SUM(`total`) AS `total` FROM service_invoice WHERE `date` LIKE '%" + day + "%'");

            String total = "0";
            if (resultSet.next()) {
                total = resultSet.getString("total");
                if (total == null) {
                    total = " 0.00";
                }
            }
            dailyIncomeLabel.setText("Rs." + total);
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while setDailyIncome() : " + e.getMessage());
        }
    }

    public void showDataInPieChart() {
        try {
            pieChart1.clearData();
            categoryList.removeAll();
            categories.clear();
            ResultSet rs = new VehicleController().showDataForChart();

            int index = 0;
            while (rs.next()) {
                String type = rs.getString("name");
                int count = rs.getInt("numofvehicles");
                categories.add("• " + type);
                pieChart1.addData(new ModelPieChart(type, count, getColor(index++)));
            }

            populateCategories();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Color getColor(int index) {
        Color[] color = new Color[]{
            new Color(32, 93, 247), //blue
            new Color(255, 105, 94), // red
            new Color(199, 183, 42), //yellow
            new Color(68, 219, 68), //green
            new Color(69, 43, 237), //purple
            new Color(29, 222, 164), //
            new Color(234, 68, 242), //
            new Color(224, 119, 81), //orange
            new Color(21, 177, 230), //
            new Color(113, 186, 48)};//
        return color[index];
    }

    // Populate the JList with data from the database
    private void populateCategories() {
        categoryList.setCellRenderer(new CategoryRenderer());
        categoryList.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane1.setBorder(BorderFactory.createEmptyBorder());

        DefaultListModel<String> model = new DefaultListModel<>();

        for (String category : categories) {
            model.addElement(category);
        }

        categoryList.setModel(model);
    }

    // Custom renderer for JList
    private class CategoryRenderer extends JLabel implements ListCellRenderer<String> {

        @Override
        public Component getListCellRendererComponent(JList<? extends String> list, String value, int index,
                boolean isSelected, boolean cellHasFocus) {
            setText(value); // Set the category name
            setOpaque(true);
            setFont(new Font("Arial", Font.BOLD, 14));
            setForeground(getColor(index++)); // Cycle through colors
            setBackground(isSelected ? Color.LIGHT_GRAY : Color.WHITE); // Highlight selection
            setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 5)); // Add padding
            return this;
        }
    }

    public void roundPanels() {
        cardPanel1.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        cardPanel2.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        cardPanel3.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        cardPanel4.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        jPanel3.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        jPanel4.putClientProperty(FlatClientProperties.STYLE, "arc:18");
        jPanel5.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        jPanel6.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        jPanel7.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        jPanel8.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        jPanel9.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        jPanel10.putClientProperty(FlatClientProperties.STYLE, "arc:15");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        cardPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        staffLabel = new javax.swing.JLabel();
        cardPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dailyIncomeLabel = new javax.swing.JLabel();
        cardPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        worksLabel = new javax.swing.JLabel();
        cardPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        MonthlyIncomeLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        appointmentLabel = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jPanel5 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        regVehiclesLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        categoryList = new javax.swing.JList<>();
        pieChart1 = new views.components.charts.PieChart();
        jPanel6 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        regCustomersLabel = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        typeLabel1 = new javax.swing.JLabel();
        typeLabel3 = new javax.swing.JLabel();
        typeLabel2 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jProgressBar2 = new javax.swing.JProgressBar();
        jProgressBar3 = new javax.swing.JProgressBar();
        countLabel1 = new javax.swing.JLabel();
        countLabel2 = new javax.swing.JLabel();
        countLabel3 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        topCustomersList = new javax.swing.JList<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        topMobileList = new javax.swing.JList<>();
        jPanel10 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        chart = new views.components.charts.com.raven.chart.Chart();
        jPanel8 = new javax.swing.JPanel();
        regEmployeesLabel = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1092, 610));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(1092, 610));
        jPanel1.setPreferredSize(new java.awt.Dimension(1092, 610));

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel2.setBackground(new java.awt.Color(243, 241, 241));

        cardPanel1.setBackground(new java.awt.Color(102, 153, 255));
        cardPanel1.setLayout(null);

        jLabel11.setBackground(new java.awt.Color(119, 163, 252));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/DashboardIcons/employees-64.png"))); // NOI18N
        jLabel11.setOpaque(true);
        cardPanel1.add(jLabel11);
        jLabel11.setBounds(160, -6, 78, 90);

        jLabel1.setBackground(new java.awt.Color(119, 163, 252));
        jLabel1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("   Today Staff");
        jLabel1.setOpaque(true);
        cardPanel1.add(jLabel1);
        jLabel1.setBounds(-4, 8, 270, 30);

        staffLabel.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        staffLabel.setForeground(new java.awt.Color(255, 255, 255));
        staffLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        staffLabel.setText("10");
        cardPanel1.add(staffLabel);
        staffLabel.setBounds(6, 44, 159, 30);

        cardPanel3.setBackground(new java.awt.Color(234, 178, 11));
        cardPanel3.setLayout(null);

        jLabel7.setBackground(new java.awt.Color(239, 187, 78));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/DashboardIcons/day-64.png"))); // NOI18N
        jLabel7.setOpaque(true);
        cardPanel3.add(jLabel7);
        jLabel7.setBounds(160, 0, 78, 90);

        jLabel3.setBackground(new java.awt.Color(239, 187, 78));
        jLabel3.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("   Today Income");
        jLabel3.setOpaque(true);
        cardPanel3.add(jLabel3);
        jLabel3.setBounds(-4, 8, 270, 30);

        dailyIncomeLabel.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        dailyIncomeLabel.setForeground(new java.awt.Color(255, 255, 255));
        dailyIncomeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dailyIncomeLabel.setText("Rs. 154000");
        cardPanel3.add(dailyIncomeLabel);
        dailyIncomeLabel.setBounds(6, 44, 159, 30);

        cardPanel2.setBackground(new java.awt.Color(177, 97, 255));
        cardPanel2.setLayout(null);

        jLabel9.setBackground(new java.awt.Color(187, 117, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/DashboardIcons/work-64.png"))); // NOI18N
        jLabel9.setOpaque(true);
        cardPanel2.add(jLabel9);
        jLabel9.setBounds(160, 0, 78, 90);

        jLabel2.setBackground(new java.awt.Color(187, 117, 255));
        jLabel2.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("   Today Works");
        jLabel2.setOpaque(true);
        cardPanel2.add(jLabel2);
        jLabel2.setBounds(-4, 8, 270, 30);

        worksLabel.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        worksLabel.setForeground(new java.awt.Color(255, 255, 255));
        worksLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        worksLabel.setText("30");
        cardPanel2.add(worksLabel);
        worksLabel.setBounds(6, 44, 159, 30);

        cardPanel4.setBackground(new java.awt.Color(96, 186, 7));
        cardPanel4.setPreferredSize(new java.awt.Dimension(255, 114));
        cardPanel4.setLayout(null);

        jLabel5.setBackground(new java.awt.Color(127, 192, 63));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/DashboardIcons/income-64.png"))); // NOI18N
        jLabel5.setOpaque(true);
        cardPanel4.add(jLabel5);
        jLabel5.setBounds(160, 0, 78, 90);

        jLabel4.setBackground(new java.awt.Color(127, 192, 63));
        jLabel4.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("   Monthly Income");
        jLabel4.setOpaque(true);
        cardPanel4.add(jLabel4);
        jLabel4.setBounds(0, 10, 260, 30);

        MonthlyIncomeLabel.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        MonthlyIncomeLabel.setForeground(new java.awt.Color(255, 255, 255));
        MonthlyIncomeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MonthlyIncomeLabel.setText("Rs. 1540000");
        cardPanel4.add(MonthlyIncomeLabel);
        MonthlyIncomeLabel.setBounds(6, 44, 159, 30);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 67, 133));
        jLabel13.setText("Appointments");

        jPanel4.setBackground(new java.awt.Color(229, 242, 255));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));

        appointmentLabel.setFont(new java.awt.Font("Roboto", 1, 48)); // NOI18N
        appointmentLabel.setForeground(new java.awt.Color(0, 60, 151));
        appointmentLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        appointmentLabel.setText("00");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(appointmentLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(appointmentLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel16.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(182, 182, 182));
        jLabel16.setText("Today");

        jScrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jList1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jList1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList1.setFixedCellHeight(20);
        jList1.setFocusable(false);
        jScrollPane3.setViewportView(jList1);

        jScrollPane4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jList2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jList2.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList2.setFixedCellHeight(20);
        jList2.setFocusable(false);
        jScrollPane4.setViewportView(jList2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 8, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(null);

        jLabel26.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 67, 133));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel26.setText("Registered Vehicles");
        jPanel5.add(jLabel26);
        jLabel26.setBounds(12, 6, 187, 22);

        regVehiclesLabel.setFont(new java.awt.Font("Roboto", 1, 48)); // NOI18N
        regVehiclesLabel.setForeground(new java.awt.Color(0, 60, 151));
        regVehiclesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        regVehiclesLabel.setText("00");
        jPanel5.add(regVehiclesLabel);
        regVehiclesLabel.setBounds(12, 34, 108, 57);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        categoryList.setBorder(null);
        categoryList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "○ Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        categoryList.setFocusable(false);
        categoryList.setOpaque(false);
        categoryList.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(categoryList);

        jPanel5.add(jScrollPane1);
        jScrollPane1.setBounds(120, 40, 130, 170);
        jPanel5.add(pieChart1);
        pieChart1.setBounds(270, 0, 240, 220);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel22.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 67, 133));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Registered Customers");

        regCustomersLabel.setFont(new java.awt.Font("Roboto", 1, 48)); // NOI18N
        regCustomersLabel.setForeground(new java.awt.Color(0, 60, 151));
        regCustomersLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        regCustomersLabel.setText("00");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(regCustomersLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(regCustomersLabel)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel28.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 67, 133));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText("Most Serviced Vehicles");

        typeLabel1.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        typeLabel1.setText("Type 1");

        typeLabel3.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        typeLabel3.setText("Type 3");

        typeLabel2.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        typeLabel2.setText("Type 2");

        jProgressBar1.setBackground(new java.awt.Color(255, 255, 255));
        jProgressBar1.setForeground(new java.awt.Color(255, 153, 0));
        jProgressBar1.setMaximum(10);

        jProgressBar2.setBackground(new java.awt.Color(255, 255, 255));
        jProgressBar2.setForeground(new java.awt.Color(0, 153, 255));
        jProgressBar2.setMaximum(10);

        jProgressBar3.setBackground(new java.awt.Color(255, 255, 255));
        jProgressBar3.setForeground(new java.awt.Color(0, 153, 255));
        jProgressBar3.setMaximum(10);

        countLabel1.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        countLabel1.setForeground(new java.awt.Color(255, 153, 0));
        countLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        countLabel1.setText("00");

        countLabel2.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        countLabel2.setForeground(new java.awt.Color(0, 153, 255));
        countLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        countLabel2.setText("00");

        countLabel3.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        countLabel3.setForeground(new java.awt.Color(0, 153, 255));
        countLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        countLabel3.setText("00");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jProgressBar2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(typeLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(countLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(typeLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(countLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                            .addComponent(typeLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(countLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jProgressBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(typeLabel1)
                    .addComponent(countLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(typeLabel2)
                    .addComponent(countLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(typeLabel3)
                    .addComponent(countLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel36.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 67, 133));
        jLabel36.setText("Top Customers");

        jLabel37.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(182, 182, 182));
        jLabel37.setText("This Month");

        jScrollPane5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane5.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        topCustomersList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        topCustomersList.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        topCustomersList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        topCustomersList.setFixedCellHeight(30);
        topCustomersList.setFocusable(false);
        jScrollPane5.setViewportView(topCustomersList);

        jScrollPane6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jScrollPane6.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane6.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        topMobileList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        topMobileList.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        topMobileList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        topMobileList.setFixedCellHeight(30);
        topMobileList.setFocusable(false);
        jScrollPane6.setViewportView(topMobileList);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jLabel35.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 67, 133));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel35.setText("Issued Invoices");

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        regEmployeesLabel.setFont(new java.awt.Font("Roboto", 1, 48)); // NOI18N
        regEmployeesLabel.setForeground(new java.awt.Color(0, 60, 151));
        regEmployeesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        regEmployeesLabel.setText("00");

        jLabel25.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 67, 133));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Registered Employees");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(regEmployeesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(regEmployeesLabel)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cardPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cardPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cardPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(cardPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(526, 526, 526))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cardPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                    .addComponent(cardPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cardPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cardPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(136, 136, 136))
        );

        jScrollPane2.setViewportView(jPanel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1092, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel MonthlyIncomeLabel;
    private javax.swing.JLabel appointmentLabel;
    private javax.swing.JPanel cardPanel1;
    private javax.swing.JPanel cardPanel2;
    private javax.swing.JPanel cardPanel3;
    private javax.swing.JPanel cardPanel4;
    private javax.swing.JList<String> categoryList;
    private views.components.charts.com.raven.chart.Chart chart;
    private javax.swing.JLabel countLabel1;
    private javax.swing.JLabel countLabel2;
    private javax.swing.JLabel countLabel3;
    private javax.swing.JLabel dailyIncomeLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JProgressBar jProgressBar3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private views.components.charts.PieChart pieChart1;
    private javax.swing.JLabel regCustomersLabel;
    private javax.swing.JLabel regEmployeesLabel;
    private javax.swing.JLabel regVehiclesLabel;
    private javax.swing.JLabel staffLabel;
    private javax.swing.JList<String> topCustomersList;
    private javax.swing.JList<String> topMobileList;
    private javax.swing.JLabel typeLabel1;
    private javax.swing.JLabel typeLabel2;
    private javax.swing.JLabel typeLabel3;
    private javax.swing.JLabel worksLabel;
    // End of variables declaration//GEN-END:variables
}
