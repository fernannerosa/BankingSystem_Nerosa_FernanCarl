# Banking System (Nerosa)

A secure, desktop-based Banking Management System built using **Java Swing** for the graphical user interface (GUI) and the **DAO (Data Access Object) pattern** for robust database interaction. 

This application provides a comprehensive dashboard for managing bank customers, bank accounts, and handling secure transactions (deposits, withdrawals, etc.) along with historical transaction logging.

---

## 🚀 Features

*   **Main Dashboard:** A centralized control hub (`MainDashboardFrame`) to navigate between accounts, customers, and transactions seamlessly.
*   **Customer Management:** Create, read, update, and delete customer profiles (`Customer`, `CustomerDAO`, `CustomerListFrame`).
*   **Account Management:** Handle various bank accounts linked to specific customers (`Account`, `AccountDAO`, `AccountListFrame`).
*   **Transaction Processing:** Securely execute deposits and withdrawals via an intuitive interface (`Transaction`, `TransactionDAO`, `TransactionFrame`).
*   **Transaction Logs:** View comprehensive history of financial activities (`TransactionLogsFrame`).
*   **Database Connectivity:** Persistent data storage backed by reliable, structured SQL connectivity (`DBConnection`).

---

## 🏗️ Architecture & Project Structure

The project relies on a clean separation of concerns, separating the Data Models, Database Layer (DAO), and User Interface Components (Swing Forms):


## 1. Database Setup

Open your SQL database management tool.

Create a new database (e.g., banking_system).

Open DBConnection.java and update the connection URL, database user, and password strings to match your local setup:

Java
   private static final String URL = "jdbc:mysql://localhost:3306/banking_system";
   
   private static final String USER = "your_username";
   
   private static final String PASSWORD = "your_password";
