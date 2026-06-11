/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankingsystem_nerosa;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author FernanCarl
 */

public class Transaction {
    
    private int transaction_id;
    private int account_id;
    private String transaction_type;
    private BigDecimal amount;
    private Date transaction_date;

    public Transaction(int transaction_id, int account_id, String transaction_type, BigDecimal amount, Date transaction_date) {
        this.transaction_id = transaction_id;
        this.account_id = account_id;
        this.transaction_type = transaction_type;
        this.amount = amount;
        this.transaction_date = transaction_date;
    }

    public Transaction() {
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(Date transaction_date) {
        this.transaction_date = transaction_date;
    }

    @Override
    public String toString() {
        return transaction_id + " - " + transaction_type
                + " | Amount: " + amount
                + " | Date: " + transaction_date
                + " | Account ID: " + account_id;
    }
    
    
}
