/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package bankingsystem_nerosa;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author FernanCarl
 */

public class CustomerListFrame extends javax.swing.JFrame {
    
    private final CustomerDAO customerDAO = new CustomerDAO();
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CustomerListFrame.class.getName());

    /**
     * Creates new form CustomerListFrame
     */
    public CustomerListFrame() {
        initComponents();
        
        loadCustomers();
        JTableHeader header = tblCustomers.getTableHeader();
        header.setBackground(new Color(241, 245, 249));
        header.setForeground(new Color(30, 41, 59));
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setReorderingAllowed(false);

        tblCustomers.setDefaultRenderer(Object.class, new StripeRenderer());
    }
    
    private void loadCustomers() {
        try {
            DefaultTableModel tableModel = (DefaultTableModel) tblCustomers.getModel();
            List<Customer> customers = customerDAO.getAllCustomers();
            tableModel.setRowCount(0);

            for (Customer customer : customers) {
                tableModel.addRow(new Object[]{
                    customer.getCustomerid(),
                    customer.getFirst_name(),
                    customer.getLast_name(),
                    customer.getEmail(),
                    customer.getPhone_number()
                });
            }

            countLabel.setText(customers.size() + " customers");
        } catch (SQLException ex) {
            showError(ex.getMessage());
        }
    }
    
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    private String required(JTextField field, String label) {
        String value = field.getText().trim();

        if (value.isEmpty()) {
            throw new IllegalArgumentException(label + " is required.");
        }

        return value;
    }
    
    private static class StripeRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean selected,
                boolean focused, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 12));

            if (!selected) {
                component.setBackground(row % 2 == 0 ? Color.WHITE : new Color(248, 250, 252));
                component.setForeground(new Color(30, 41, 59));
            }

            return component;
        }
    }
    
    private void updateSelectedCustomer() {
        DefaultTableModel tableModel = (DefaultTableModel) tblCustomers.getModel();
        int row = tblCustomers.getSelectedRow();

        if (row < 0) {
            showError("Please select a customer first.");
            return;
        }

        int modelRow = tblCustomers.convertRowIndexToModel(row);
        int customerId = (int) tableModel.getValueAt(modelRow, 0);

        JTextField firstName = new JTextField(String.valueOf(tableModel.getValueAt(modelRow, 1)), 18);
        JTextField lastName = new JTextField(String.valueOf(tableModel.getValueAt(modelRow, 2)), 18);
        JTextField email = new JTextField(String.valueOf(tableModel.getValueAt(modelRow, 3)), 18);
        JTextField phone = new JTextField(String.valueOf(tableModel.getValueAt(modelRow, 4)), 18);
        JPanel panel = createEditorPanel(
                new String[]{"First name:", "Last name:", "Email:", "Phone number:"},
                new JTextField[]{firstName, lastName, email, phone});

        int choice = JOptionPane.showConfirmDialog(this, panel, "Update Customer", JOptionPane.OK_CANCEL_OPTION);

        if (choice != JOptionPane.OK_OPTION) {
            return;
        }

        try {
            Customer customer = new Customer(
                    customerId,
                    required(firstName, "First name"),
                    required(lastName, "Last name"),
                    required(email, "Email"),
                    required(phone, "Phone number"));

            customerDAO.updateCustomer(customer);
            loadCustomers();
        } catch (IllegalArgumentException | SQLException ex) {
            showError(ex.getMessage());
        }
    }

    private void deleteSelectedCustomer() {
        DefaultTableModel tableModel = (DefaultTableModel) tblCustomers.getModel();
        int row = tblCustomers.getSelectedRow();

        if (row < 0) {
            showError("Please select a customer first.");
            return;
        }

        int modelRow = tblCustomers.convertRowIndexToModel(row);
        int customerId = (int) tableModel.getValueAt(modelRow, 0);
        String name = tableModel.getValueAt(modelRow, 1) + " " + tableModel.getValueAt(modelRow, 2);

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Delete customer " + name + "?",
                "Delete Customer",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (choice != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            customerDAO.deleteCustomer(customerId);
            loadCustomers();
        } catch (SQLException ex) {
            showError("Customer cannot be deleted while accounts are still linked to them.");
        }
    }
    
    private JPanel createEditorPanel(String[] labels, JTextField[] fields) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            panel.add(new JLabel(labels[i]), gbc);

            gbc.gridx = 1;
            panel.add(fields[i], gbc);
        }

        return panel;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupCustomer = new javax.swing.JPopupMenu();
        menuUpdateCustomer = new javax.swing.JMenuItem();
        menuDeleteCustomer = new javax.swing.JMenuItem();
        headerPanel = new javax.swing.JPanel();
        textPanel = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        subtitle = new javax.swing.JLabel();
        refreshbutton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCustomers = new javax.swing.JTable();
        footer = new javax.swing.JPanel();
        countLabel = new javax.swing.JLabel();

        menuUpdateCustomer.setText("Update Customer");
        menuUpdateCustomer.addActionListener(this::menuUpdateCustomerActionPerformed);
        popupCustomer.add(menuUpdateCustomer);

        menuDeleteCustomer.setText("Delete Customer");
        menuDeleteCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuDeleteCustomerMousePressed(evt);
            }
        });
        menuDeleteCustomer.addActionListener(this::menuDeleteCustomerActionPerformed);
        popupCustomer.add(menuDeleteCustomer);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Banking System - Customers");

        headerPanel.setBackground(new java.awt.Color(28, 45, 74));
        headerPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 22, 18, 22));
        headerPanel.setLayout(new java.awt.BorderLayout());

        textPanel.setOpaque(false);
        textPanel.setLayout(new java.awt.BorderLayout());

        title.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setText("Customers");
        textPanel.add(title, java.awt.BorderLayout.NORTH);

        subtitle.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        subtitle.setForeground(new java.awt.Color(208, 218, 232));
        subtitle.setText("Right-click a row to update or delete a customer.");
        subtitle.setToolTipText("");
        textPanel.add(subtitle, java.awt.BorderLayout.SOUTH);

        headerPanel.add(textPanel, java.awt.BorderLayout.WEST);

        refreshbutton.setBackground(new java.awt.Color(14, 116, 144));
        refreshbutton.setForeground(new java.awt.Color(255, 255, 255));
        refreshbutton.setText("Refresh");
        refreshbutton.setBorder(javax.swing.BorderFactory.createEmptyBorder(9, 16, 9, 16));
        refreshbutton.setBorderPainted(false);
        refreshbutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        refreshbutton.setFocusPainted(false);
        refreshbutton.addActionListener(this::refreshbuttonActionPerformed);
        headerPanel.add(refreshbutton, java.awt.BorderLayout.EAST);

        getContentPane().add(headerPanel, java.awt.BorderLayout.NORTH);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 16, 8, 16));

        tblCustomers.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblCustomers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Customer ID", "First Name", "Last Name", "Email", "Phone Number"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCustomers.setComponentPopupMenu(popupCustomer);
        tblCustomers.setRowHeight(34);
        tblCustomers.setSelectionBackground(new java.awt.Color(214, 229, 255));
        tblCustomers.setSelectionForeground(new java.awt.Color(20, 28, 43));
        tblCustomers.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblCustomers.setShowGrid(false);
        tblCustomers.setDefaultRenderer(Object.class, new StripeRenderer());
        tblCustomers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblCustomersMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblCustomers);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        footer.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 22, 18, 22));
        footer.setLayout(new java.awt.BorderLayout());

        countLabel.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        countLabel.setForeground(new java.awt.Color(71, 85, 105));
        countLabel.setText("0 accounts");
        footer.add(countLabel, java.awt.BorderLayout.WEST);

        getContentPane().add(footer, java.awt.BorderLayout.SOUTH);

        setSize(new java.awt.Dimension(880, 520));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void refreshbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshbuttonActionPerformed
        loadCustomers();
    }//GEN-LAST:event_refreshbuttonActionPerformed

    private void tblCustomersMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCustomersMousePressed
        int row = tblCustomers.rowAtPoint(evt.getPoint());
        if (row >= 0) {
            tblCustomers.setRowSelectionInterval(row, row);
        }
    }//GEN-LAST:event_tblCustomersMousePressed

    private void menuUpdateCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuUpdateCustomerActionPerformed
    updateSelectedCustomer();
    }//GEN-LAST:event_menuUpdateCustomerActionPerformed

    private void menuDeleteCustomerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuDeleteCustomerMousePressed
        
    }//GEN-LAST:event_menuDeleteCustomerMousePressed

    private void menuDeleteCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDeleteCustomerActionPerformed
    deleteSelectedCustomer();
    }//GEN-LAST:event_menuDeleteCustomerActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new CustomerListFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel countLabel;
    private javax.swing.JPanel footer;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem menuDeleteCustomer;
    private javax.swing.JMenuItem menuUpdateCustomer;
    private javax.swing.JPopupMenu popupCustomer;
    private javax.swing.JButton refreshbutton;
    private javax.swing.JLabel subtitle;
    private javax.swing.JTable tblCustomers;
    private javax.swing.JPanel textPanel;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
