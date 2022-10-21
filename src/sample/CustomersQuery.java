package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * <Code>CustomersQuery</Code> handles requests for the customers table on the database.
 * @author Andrew Hobbs
 */
public abstract class CustomersQuery {

    /**
     * addCustomer accepts new parameters to create a new entry on the customers table.
     * @param customerName - String value of this customer's name.
     * @param address - String value of this customer's address.
     * @param postalCode - String value of this customer's postal code.
     * @param phoneNumber - String value of this customer's phone number.
     * @param createDate - Timestamp value of when this customer record was created.
     * @param createdBy - String value of the user that created this record.
     * @param lastUpdate - Timestamp of the last time this record was updated.
     * @param lastUpdatedBy - String value of the user that last updated this record.
     * @param divisionID - int ID of a corresponding first level division.
     * @throws SQLException
     */
    public static void addCustomer(String customerName, String address, String postalCode, String phoneNumber, Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy, int divisionID) throws SQLException {
        String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, customerName);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phoneNumber);
        ps.setTimestamp(5, createDate);
        ps.setString(6, createdBy);
        ps.setTimestamp(7, lastUpdate);
        ps.setString(8, lastUpdatedBy);
        ps.setInt(9, divisionID);
        int rowsAffected = ps.executeUpdate();
        System.out.println(rowsAffected + " rows affected.");

    }

    /**
     * updateCustomer updates the selected customer record.,
     * @param customerName - String value of this customer's name.
     * @param address - String value of this customer's address.
     * @param postalCode - String value of this customer's postal code.
     * @param phoneNumber - String value of this customer's phone number.
     * @param createDate - Timestamp value of when this customer record was created.
     * @param createdBy - String value of the user that created this record.
     * @param lastUpdate - Timestamp of the last time this record was updated.
     * @param lastUpdatedBy - String value of the user that last updated this record.
     * @param divisionID - int ID of a corresponding first level division.
     * @param customerID - int value of this customer record to be updated.
     * @throws SQLException
     */
    public static void updateCustomer(String customerName, String address, String postalCode, String phoneNumber, Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy, int divisionID, int customerID) throws SQLException {
        String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Create_Date = ?, Created_By = ?, Last_Update = ?, Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ? ";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, customerName);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phoneNumber);
        ps.setTimestamp(5, createDate);
        ps.setString(6, createdBy);
        ps.setTimestamp(7, lastUpdate);
        ps.setString(8, lastUpdatedBy);
        ps.setInt(9, divisionID);
        ps.setInt(10, customerID);

        int rowsAffected = ps.executeUpdate();
        System.out.println(rowsAffected + " rows affected.");

    }

    /**
     * getAllCustomers returns an observable list of all customer records.
     * @return an observable list of all customer records.
     * @throws SQLException
     */
    public static ObservableList<Customers> getAllCustomers() throws SQLException {
        ObservableList<Customers> allCustomers = FXCollections.observableArrayList();
        String sql = "SELECT * FROM customers ";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int customerID = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int divisionID = rs.getInt("Division_ID");


            Customers customer = new Customers(customerID, customerName, address, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdatedBy, divisionID);
            allCustomers.add(customer);
        }
        return allCustomers;
    }

    /**
     * deleteCustomer removes the selected customer record from the customers table.
     * @param customerID - the corresponding int ID of this customer.
     * @throws SQLException
     */
    public static void deleteCustomer(int customerID) throws SQLException {
        String sql = "DELETE FROM customers WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerID);
        int rowsAffected = ps.executeUpdate();
        System.out.println(rowsAffected + "rows affected.");

    }
}