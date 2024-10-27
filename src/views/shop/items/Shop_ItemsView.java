/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.shop.items;

import com.formdev.flatlaf.FlatClientProperties;
import controllers.ProductBrandController;
import controllers.ProductController;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import models.ProductModel;
import includes.LoggerConfig;
import java.util.logging.Logger;

/**
 *
 * @author Dinuka
 */
public class Shop_ItemsView extends javax.swing.JPanel {

    private static final Logger logger = LoggerConfig.getLogger();

    /**
     * Creates new form shop_ItemsJPanel
     */
    private Shop_ItemsView shop_ItemsView;

    public Shop_ItemsView() {
        initComponents();
        loadItems();
        loadBrands();

        search_box.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter Item Name or Id ");

        this.shop_ItemsView = this;
        ItemsTableRender();

    }

    private void loadItems() {
        try {

            ResultSet ItemResultSet = new ProductController().show();
            ResultSet BrandResultSet = new ProductBrandController().show();

            HashMap<Integer, String> BrandMap = new HashMap<>();

            while (BrandResultSet.next()) {
                int BrandId = BrandResultSet.getInt("id");
                String BrandName = BrandResultSet.getString("name");
                BrandMap.put(BrandId, BrandName);
            }

            DefaultTableModel model = (DefaultTableModel) Items_View_Table.getModel();
            model.setRowCount(0);

            while (ItemResultSet.next()) {
                Vector<String> vector = new Vector<>();

                String employeeId = ItemResultSet.getString("id");
                vector.add(employeeId);
                vector.add(ItemResultSet.getString("name"));

                vector.add(ItemResultSet.getString("brand_id"));

                int BrandId = ItemResultSet.getInt("brand_id");

                String BrandName = BrandMap.getOrDefault(BrandId, "Unknown Employee Type");

                vector.add(BrandName);

                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loding items to table in Shop Items : " + e.getMessage());
        }

    }

    private void fetchItems(String searchText) throws Exception {
        DefaultTableModel model = (DefaultTableModel) Items_View_Table.getModel();
        model.setRowCount(0);

        try {
            ResultSet resultSet = new ProductController().searchProductId(searchText);
            ResultSet resultSet1 = new ProductBrandController().search("");

            HashMap<Integer, String> BrandMap = new HashMap<>();

            while (resultSet1.next()) {
                int BrandId = resultSet1.getInt("id");
                String BrandName = resultSet1.getString("name");
                BrandMap.put(BrandId, BrandName);
            }

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                int BrandId = resultSet.getInt("brand_id");

                String BrandName = BrandMap.getOrDefault(BrandId, "Unknown Brand");

                model.addRow(new Object[]{id, name, BrandId, BrandName});
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.severe("Error while searching Items in Shop Items : " + ex.getMessage());
        }
    }

    private void fetchBrands(String searchText) throws Exception {
        DefaultTableModel model = (DefaultTableModel) Items_View_Table.getModel();
        model.setRowCount(0);

        try {
            ResultSet resultSet = new ProductController().searchBrand(searchText);;
            ResultSet resultSet1 = new ProductBrandController().search("");

            HashMap<Integer, String> BrandMap = new HashMap<>();

            while (resultSet1.next()) {
                int BrandId = resultSet1.getInt("id");
                String BrandName = resultSet1.getString("name");
                BrandMap.put(BrandId, BrandName);
            }

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                int BrandId = resultSet.getInt("brand_id");

                String BrandName = BrandMap.getOrDefault(BrandId, "Unknown Brand");

                model.addRow(new Object[]{id, name, BrandId, BrandName});
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.severe("Error while searching Items in Shop Items : " + ex.getMessage());
        }
    }

    public void ItemsTableRender() {

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        JTableHeader tableHeader = Items_View_Table.getTableHeader();

        tableHeader.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                Font headerFont = new Font("Verdana", Font.BOLD, 14);
                label.setBorder(BorderFactory.createEmptyBorder()); // Remove header borders
                label.setFont(headerFont);
                label.setBackground(new Color(5, 15, 76)); // Optional: Set header background color
                label.setForeground(Color.WHITE); // Optional: Set header text color
                label.setHorizontalAlignment(SwingConstants.CENTER); // Center the text
                return label;
            }
        });

        tableHeader.setPreferredSize(new Dimension(tableHeader.getPreferredSize().width, 30));

        for (int i = 0; i < 4; i++) {
            Items_View_Table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public void reloadTable() {
        loadItems();
    }

    private static HashMap<String, String> BrandMap = new HashMap<>();

//load brands to sort button
    private void loadBrands() {

        try {
            ResultSet resultSet = new ProductBrandController().show();

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                BrandMap.put(resultSet.getString("name"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            Brand_chooser.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loding brands to sort button in Shop Items : " + e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        search_box = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        Brand_chooser = new javax.swing.JComboBox<>();
        Item_Register_Button = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Items_View_Table = new javax.swing.JTable();

        setMinimumSize(new java.awt.Dimension(1300, 539));
        setPreferredSize(new java.awt.Dimension(1300, 539));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel1.setText("Search Items");

        search_box.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        search_box.setFocusCycleRoot(true);
        search_box.setSelectionColor(new java.awt.Color(214, 132, 13));
        search_box.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                search_boxKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel2.setText("Sort By Brand");

        Brand_chooser.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        Brand_chooser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Brand_chooser.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Brand_chooserItemStateChanged(evt);
            }
        });
        Brand_chooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Brand_chooserActionPerformed(evt);
            }
        });

        Item_Register_Button.setBackground(new java.awt.Color(199, 232, 199));
        Item_Register_Button.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        Item_Register_Button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/DashboardIcons/add-3.png"))); // NOI18N
        Item_Register_Button.setText(" REGISTER ITEM");
        Item_Register_Button.setBorderPainted(false);
        Item_Register_Button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Item_Register_Button.setFocusPainted(false);
        Item_Register_Button.setFocusable(false);
        Item_Register_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Item_Register_ButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(search_box, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(Brand_chooser, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 576, Short.MAX_VALUE)
                .addComponent(Item_Register_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(18, Short.MAX_VALUE)
                        .addComponent(Item_Register_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(search_box)
                            .addComponent(Brand_chooser))))
                .addGap(9, 9, 9))
        );

        Items_View_Table.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        Items_View_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item Id", "Item Name", "Brand Id", "Brand Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Items_View_Table.setRowHeight(30);
        Items_View_Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Items_View_TableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Items_View_Table);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Item_Register_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Item_Register_ButtonActionPerformed

        // TODO: need to fix this
        new RegisterItems(null, true, shop_ItemsView).setVisible(true);
    }//GEN-LAST:event_Item_Register_ButtonActionPerformed

    private void search_boxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_boxKeyReleased
        // TODO add your handling code here:
        try {
            fetchItems(search_box.getText().toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.severe("Error while search Items in Shop Items : " + ex.getMessage());
        }
    }//GEN-LAST:event_search_boxKeyReleased

    private void Items_View_TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Items_View_TableMouseClicked
        // TODO add your handling code here:
        int row = Items_View_Table.getSelectedRow();

        if (evt.getClickCount() == 2 && row != -1) {

            String ItemId = String.valueOf(Items_View_Table.getValueAt(row, 0));
            String ItemName = String.valueOf(Items_View_Table.getValueAt(row, 1));
            String BrandName = String.valueOf(Items_View_Table.getValueAt(row, 3));

            ProductModel ItemModel = new ProductModel();
            ItemModel.setItemId(ItemId);
            ItemModel.setName(ItemName);
            ItemModel.setbrandName(BrandName);

            try {
                Frame shop_ItemsView = null;
                ItemsUpdate employeeUpdate = new ItemsUpdate(shop_ItemsView, true, ItemModel);
                employeeUpdate.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
            loadItems();
        }

    }//GEN-LAST:event_Items_View_TableMouseClicked

    private void Brand_chooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Brand_chooserActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_Brand_chooserActionPerformed

    private void Brand_chooserItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Brand_chooserItemStateChanged
        // TODO add your handling code here:
        try {
            fetchBrands(Brand_chooser.getSelectedItem().toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.severe("Error while sorting brands in Shop Items : " + ex.getMessage());
        }
        String BrandName = String.valueOf(Brand_chooser.getSelectedItem());
        if (BrandName.equals("Select")) {
            reloadTable();
        }

    }//GEN-LAST:event_Brand_chooserItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Brand_chooser;
    private javax.swing.JButton Item_Register_Button;
    private javax.swing.JTable Items_View_Table;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField search_box;
    // End of variables declaration//GEN-END:variables
}
