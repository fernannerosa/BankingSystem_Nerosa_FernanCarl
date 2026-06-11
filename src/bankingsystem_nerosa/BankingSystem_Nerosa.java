/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bankingsystem_nerosa;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author FernanCarl
 */
public class BankingSystem_Nerosa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            System.out.println("Using default look and feel.");
        }

        SwingUtilities.invokeLater(() -> new MainDashboardFrame().setVisible(true));
    }
    
}
