/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankingsystem_nerosa;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author FernanCarl
 */

public class AccountDAO {

    public int addAccount(Account account) throws SQLException {
        String sql = "INSERT INTO account (account_type, balance, customer_id) VALUES (?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, account.getAccount_type());
            statement.setBigDecimal(2, account.getBal());
            statement.setInt(3, account.getCustomer_id());
            statement.executeUpdate();

            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getInt(1);
                }
            }
        }

        return 0;
    }

    public Account getAccountById(int accountId) throws SQLException {
        String sql = "SELECT account_id, account_type, balance, customer_id FROM account WHERE account_id = ?";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, accountId);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return mapAccount(result);
                }
            }
        }

        return null;
    }

    public List<Account> getAccountsByCustomerId(int customerId) throws SQLException {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT account_id, account_type, balance, customer_id FROM account WHERE customer_id = ? ORDER BY account_id";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, customerId);

            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    accounts.add(mapAccount(result));
                }
            }
        }

        return accounts;
    }

    public List<Account> getAllAccounts() throws SQLException {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT account_id, account_type, balance, customer_id FROM account ORDER BY account_id";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                accounts.add(mapAccount(result));
            }
        }

        return accounts;
    }

    public boolean updateAccount(Account account) throws SQLException {
        String sql = "UPDATE account SET account_type = ?, balance = ?, customer_id = ? WHERE account_id = ?";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, account.getAccount_type());
            statement.setBigDecimal(2, account.getBal());
            statement.setInt(3, account.getCustomer_id());
            statement.setInt(4, account.getAccountid());
            return statement.executeUpdate() > 0;
        }
    }

    public boolean deleteAccount(int accountId) throws SQLException {
        String sql = "DELETE FROM account WHERE account_id = ?";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, accountId);
            return statement.executeUpdate() > 0;
        }
    }

    public boolean deposit(int accountId, BigDecimal amount) throws SQLException {
        return updateBalance(accountId, amount);
    }

    public boolean withdraw(int accountId, BigDecimal amount) throws SQLException {
        Account account = getAccountById(accountId);

        if (account == null || account.getBal().compareTo(amount) < 0) {
            return false;
        }

        return updateBalance(accountId, amount.negate());
    }

    private boolean updateBalance(int accountId, BigDecimal amount) throws SQLException {
        String sql = "UPDATE account SET balance = balance + ? WHERE account_id = ?";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setBigDecimal(1, amount);
            statement.setInt(2, accountId);
            return statement.executeUpdate() > 0;
        }
    }

    private Account mapAccount(ResultSet result) throws SQLException {
        return new Account(
                result.getInt("account_id"),
                result.getString("account_type"),
                result.getBigDecimal("balance"),
                result.getInt("customer_id"));
    }
}
