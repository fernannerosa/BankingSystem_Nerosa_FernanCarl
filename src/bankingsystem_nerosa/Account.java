/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankingsystem_nerosa;
import java.math.BigDecimal;
/**
 *
 * @author FernanCarl
 */
public class Account {
    
    private int account_id;
    private String account_type;
    private BigDecimal bal;
    private int customer_id;

    public Account() {
    }

    public Account(int account_id, String account_type, BigDecimal bal) {
        this.account_id = account_id;
        this.account_type = account_type;
        this.bal = bal;
    }

    public Account(int account_id, String account_type, BigDecimal bal, int customer_id) {
        this.account_id = account_id;
        this.account_type = account_type;
        this.bal = bal;
        this.customer_id = customer_id;
    }

    public int getAccountid() {
        return account_id;
    }

    public void setAccountid(int accountid) {
        this.account_id = accountid;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public BigDecimal getBal() {
        return bal;
    }

    public void setBal(BigDecimal bal) {
        this.bal = bal;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    @Override
    public String toString() {
        return account_id + " - " + account_type
                + " | Balance: " + bal
                + " | Customer ID: " + customer_id;
    }
    
    
    
}
