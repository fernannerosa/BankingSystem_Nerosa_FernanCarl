/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankingsystem_nerosa;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author FernanCarl
 */

public class TransactionDAO {

    public boolean addTransaction(Transaction transaction) throws SQLException {
        String sql = "INSERT INTO `transaction` (transaction_type, amount, transaction_date, account_id) VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, transaction.getTransaction_type());
            statement.setBigDecimal(2, transaction.getAmount());
            statement.setDate(3, Date.valueOf(LocalDate.now()));
            statement.setInt(4, transaction.getAccount_id());
            return statement.executeUpdate() > 0;
        }
    }

    public List<Transaction> getTransactionsByAccountId(int accountId) throws SQLException {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT transaction_id, transaction_type, amount, transaction_date, account_id "
                + "FROM `transaction` WHERE account_id = ? ORDER BY transaction_id DESC";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, accountId);

            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    transactions.add(new Transaction(
                            result.getInt("transaction_id"),
                            result.getInt("account_id"),
                            result.getString("transaction_type"),
                            result.getBigDecimal("amount"),
                            result.getDate("transaction_date")));
                }
            }
        }

        return transactions;
    }
}
