/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package bankingsystem_nerosa;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author FernanCarl
 */
public class AccountListFrame extends javax.swing.JFrame {
    
    private final AccountDAO accountDAO = new AccountDAO();
    private final CustomerDAO customerDAO = new CustomerDAO();
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AccountListFrame.class.getName());

    /**
     * Creates new form AccountListFrame
     */
    public AccountListFrame() {
        initComponents();
        
        JTableHeader header = tblAccounts.getTableHeader();
        header.setBackground(new Color(236, 254, 255));
        header.setForeground(new Color(22, 78, 99));
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setReorderingAllowed(false);
        
        loadAccounts();
    }
    
    private void deleteSelectedAccount() {
        DefaultTableModel tableModel = (DefaultTableModel) tblAccounts.getModel();
        int row = tblAccounts.getSelectedRow();

        if (row < 0) {
            showError("Please select an account first.");
            return;
        }

        int modelRow = tblAccounts.convertRowIndexToModel(row);
        int accountId = (int) tableModel.getValueAt(modelRow, 0);

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Delete account " + accountId + "?",
                "Delete Account",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (choice != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            accountDAO.deleteAccount(accountId);
            loadAccounts();
        } catch (SQLException ex) {
            showError("Account cannot be deleted while transactions are linked to it.");
        }
    }
    
    private void loadAccounts() {
        DefaultTableModel tableModel = (DefaultTableModel) tblAccounts.getModel();
        try {
            List<Account> accounts = accountDAO.getAllAccounts();
            tableModel.setRowCount(0);

            for (Account account : accounts) {
                tableModel.addRow(new Object[]{
                    account.getAccountid(),
                    account.getAccount_type(),
                    account.getBal(),
                    account.getCustomer_id()
                });
            }

            countLabel.setText(accounts.size() + " accounts");
        } catch (SQLException ex) {
            showError(ex.getMessage());
        }
    }
    
    private String required(JTextField field, String label) {
        String value = field.getText().trim();

        if (value.isEmpty()) {
            throw new IllegalArgumentException(label + " is required.");
        }

        return value;
    }
    
    private int readInt(JTextField field, String label) {
        try {
            return Integer.parseInt(required(field, label));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(label + " must be a valid number.");
        }
    }
    
    private BigDecimal readAmount(JTextField field, String label) {
        try {
            BigDecimal amount = new BigDecimal(required(field, label));

            if (amount.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException(label + " cannot be negative.");
            }

            return amount;
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(label + " must be a valid amount.");
        }
    }
    
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    private void updateSelectedAccount() {
        DefaultTableModel tableModel = (DefaultTableModel) tblAccounts.getModel();
        int row = tblAccounts.getSelectedRow();

        if (row < 0) {
            showError("Please select an account first.");
            return;
        }

        int modelRow = tblAccounts.convertRowIndexToModel(row);
        int accountId = (int) tableModel.getValueAt(modelRow, 0);

        JTextField accountType = new JTextField(String.valueOf(tableModel.getValueAt(modelRow, 1)), 18);
        JTextField balance = new JTextField(String.valueOf(tableModel.getValueAt(modelRow, 2)), 18);
        JTextField customerId = new JTextField(String.valueOf(tableModel.getValueAt(modelRow, 3)), 18);
        JPanel panel = createEditorPanel(
                new String[]{"Account type:", "Balance:", "Customer ID:"},
                new JTextField[]{accountType, balance, customerId});

        int choice = JOptionPane.showConfirmDialog(this, panel, "Update Account", JOptionPane.OK_CANCEL_OPTION);

        if (choice != JOptionPane.OK_OPTION) {
            return;
        }

        try {
            int ownerId = readInt(customerId, "Customer ID");

            if (customerDAO.getCustomerById(ownerId) == null) {
                showError("Customer not found.");
                return;
            }

            Account account = new Account(
                    accountId,
                    required(accountType, "Account type"),
                    readAmount(balance, "Balance"),
                    ownerId);

            accountDAO.updateAccount(account);
            loadAccounts();
        } catch (IllegalArgumentException | SQLException ex) {
            showError(ex.getMessage());
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

        popupAccount = new javax.swing.JPopupMenu();
        menuUpdateAccount = new javax.swing.JMenuItem();
        menuDeleteAccount = new javax.swing.JMenuItem();
        headerPanel = new javax.swing.JPanel();
        textPanel = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        subtitle = new javax.swing.JLabel();
        refreshbutton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAccounts = new javax.swing.JTable();
        footer = new javax.swing.JPanel();
        countLabel = new javax.swing.JLabel();

        menuUpdateAccount.setText("Update Account");
        menuUpdateAccount.addActionListener(this::menuUpdateAccountActionPerformed);
        popupAccount.add(menuUpdateAccount);

        menuDeleteAccount.setText("Delete Account");
        menuDeleteAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuDeleteAccountMousePressed(evt);
            }
        });
        menuDeleteAccount.addActionListener(this::menuDeleteAccountActionPerformed);
        popupAccount.add(menuDeleteAccount);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Banking System - Accounts");

        headerPanel.setBackground(new java.awt.Color(22, 78, 99));
        headerPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 22, 18, 22));
        headerPanel.setLayout(new java.awt.BorderLayout());

        textPanel.setOpaque(false);
        textPanel.setLayout(new java.awt.BorderLayout());

        title.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setText("Accounts");
        textPanel.add(title, java.awt.BorderLayout.NORTH);

        subtitle.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        subtitle.setForeground(new java.awt.Color(207, 250, 254));
        subtitle.setText("Right-click a row to update or delete an account.");
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

        tblAccounts.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblAccounts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Account ID", "Account Type", "Balance", "Customer ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAccounts.setComponentPopupMenu(popupAccount);
        tblAccounts.setRowHeight(34);
        tblAccounts.setSelectionBackground(new java.awt.Color(204, 251, 241));
        tblAccounts.setSelectionForeground(new java.awt.Color(15, 23, 42));
        tblAccounts.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblAccounts.setShowGrid(false);
        tblAccounts.setDefaultRenderer(Object.class, new StripeRenderer());
        tblAccounts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblAccountsMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblAccounts);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        footer.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 22, 18, 22));
        footer.setLayout(new java.awt.BorderLayout());

        countLabel.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        countLabel.setForeground(new java.awt.Color(71, 85, 105));
        countLabel.setText("0 accounts");
        footer.add(countLabel, java.awt.BorderLayout.WEST);

        getContentPane().add(footer, java.awt.BorderLayout.SOUTH);

        setSize(new java.awt.Dimension(780, 500));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void refreshbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshbuttonActionPerformed
        loadAccounts();
    }//GEN-LAST:event_refreshbuttonActionPerformed

    private void menuUpdateAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuUpdateAccountActionPerformed
         updateSelectedAccount();   
    }//GEN-LAST:event_menuUpdateAccountActionPerformed

    private void menuDeleteAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDeleteAccountActionPerformed
        deleteSelectedAccount();
    }//GEN-LAST:event_menuDeleteAccountActionPerformed

    private void menuDeleteAccountMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuDeleteAccountMousePressed
       
    }//GEN-LAST:event_menuDeleteAccountMousePressed

    private void tblAccountsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAccountsMousePressed
    int row = tblAccounts.rowAtPoint(evt.getPoint());
        if (row >= 0) {
            tblAccounts.setRowSelectionInterval(row, row);
        }
    }//GEN-LAST:event_tblAccountsMousePressed

    private static class StripeRenderer extends javax.swing.table.DefaultTableCellRenderer {
    @Override
    public java.awt.Component getTableCellRendererComponent(
            javax.swing.JTable table, Object value, boolean selected,
            boolean focused, int row, int column) {

        java.awt.Component component = super.getTableCellRendererComponent(
                table, value, selected, focused, row, column);

        setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 12, 0, 12));

        if (!selected) {
            component.setBackground(row % 2 == 0
                    ? java.awt.Color.WHITE
                    : new java.awt.Color(240, 253, 250));
            component.setForeground(new java.awt.Color(15, 23, 42));
        }

        return component;
    }
}
    
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
        java.awt.EventQueue.invokeLater(() -> new AccountListFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel countLabel;
    private javax.swing.JPanel footer;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem menuDeleteAccount;
    private javax.swing.JMenuItem menuUpdateAccount;
    private javax.swing.JPopupMenu popupAccount;
    private javax.swing.JButton refreshbutton;
    private javax.swing.JLabel subtitle;
    private javax.swing.JTable tblAccounts;
    private javax.swing.JPanel textPanel;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
