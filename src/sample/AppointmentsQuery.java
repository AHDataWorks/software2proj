package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZonedDateTime;

/**
 * AppointmentsQuery handles interactions with the Appointments table in the database.
 * @author Andrew Hobbs
 */
public abstract class AppointmentsQuery {

    /**
     * addAppointment collects the data that has already passed a quality check and adds it to the database as a new appointment.
     * @param title - STRING title of the appointment
     * @param description - STRING description of the appointment
     * @param location - STRING address of the appointment.
     * @param type - STRING type of the appointment.
     * @param start - TIMESTAMP of the start datetime.
     * @param end - TIMESTAMP of the end datetime.
     * @param createDate - TIMESTAMP of the current time.
     * @param createdBy - STRING of the user that created this appointment.
     * @param lastUpdate - TIMESTAMP of the last update made to this appointment.
     * @param lastUpdatedBy - STRING of the last user that last made an update to this appointment.
     * @param customerID - INT ID of the customer.
     * @param userIDNumber - INT ID of the user.
     * @param contactID - INT ID of the contact selected.
     * @throws SQLException
     */
    public static void addAppointment(String title, String description, String location, String type,Timestamp start,Timestamp end,java.sql.Timestamp createDate, String createdBy,java.sql.Timestamp lastUpdate,String lastUpdatedBy, int customerID,int userIDNumber,int contactID) throws SQLException {
        String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4,type);
        ps.setTimestamp(5, start);
        ps.setTimestamp(6, end);
        ps.setTimestamp(7,createDate);
        ps.setString(8, createdBy);
        ps.setTimestamp(9,lastUpdate);
        ps.setString(10,lastUpdatedBy);
        ps.setInt(11,customerID);
        ps.setInt(12,userIDNumber);
        ps.setInt(13,contactID);
        int rowsAffected = ps.executeUpdate();
        System.out.println(rowsAffected + " rows affected.");

    }

    /**
     * updateAppointment accepts an appointmentID and updates the values of the cooresponding appointment.
     * @param title - STRING title of the appointment
     * @param description - STRING description of the appointment
     * @param location - STRING address of the appointment.
     * @param type - STRING type of the appointment.
     * @param start - TIMESTAMP of the start datetime.
     * @param end - TIMESTAMP of the end datetime.
     * @param createDate - TIMESTAMP of the current time.
     * @param createdBy - STRING of the user that created this appointment.
     * @param lastUpdate - TIMESTAMP of the last update made to this appointment.
     * @param lastUpdatedBy - STRING of the last user that last made an update to this appointment.
     * @param customerID - INT ID of the customer.
     * @param userIDNumber - INT ID of the user.
     * @param contactID - INT ID of the contact selected.
     * @param apptID -Int of the selected appointment ID.
     * @throws SQLException
     */
    public static void updateAppointment(String title, String description, String location, String type, Timestamp start, Timestamp end, java.sql.Timestamp createDate, String createdBy, java.sql.Timestamp lastUpdate, String lastUpdatedBy, int customerID, int userIDNumber, int contactID, int apptID) throws SQLException {
        String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?,Type = ?, Start = ?, End = ?,Create_Date = ?,Created_By = ?,Last_Update = ?,Last_Updated_By = ?,Customer_ID = ?,User_ID = ?,Contact_ID = ? WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4,type);
        ps.setTimestamp(5, start);
        ps.setTimestamp(6, end);
        ps.setTimestamp(7,createDate);
        ps.setString(8, createdBy);
        ps.setTimestamp(9,lastUpdate);
        ps.setString(10,lastUpdatedBy);
        ps.setInt(11,customerID);
        ps.setInt(12,userIDNumber);
        ps.setInt(13,contactID);
        ps.setInt(14,apptID);
        int rowsAffected = ps.executeUpdate();
        System.out.println(rowsAffected + " rows affected.");

    }

    /**
     * deleteAppointment accepts an appointment ID and deletes the corresponding record in the database.
     * @param apptID - INT ID of the user selected appointment.
     * @throws SQLException
     */
    public static void deleteAppointment(int apptID) throws SQLException {
        String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, apptID);
        int rowsAffected = ps.executeUpdate();
        System.out.println(rowsAffected + "rows affected.");
    }

    /**
     * allAppointmentsByUserID accepts an int user ID and
     * @param userID - INT of the current User.
     * @return
     * @throws SQLException
     */
    public static ObservableList<Appointments> allAppointmentsByUserID(int userID) throws SQLException {
        ObservableList<Appointments> allAppts = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments WHERE User_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, String.valueOf(userID));
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int apptID = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            java.sql.Timestamp start = rs.getTimestamp("Start");
            java.sql.Timestamp end = rs.getTimestamp("End");
            java.sql.Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            java.sql.Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int customerID = rs.getInt("Customer_ID");
            int userIDNumber = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");

            Appointments appointment = new Appointments(apptID,title,description,location,type,start,end,createDate,createdBy,lastUpdate,lastUpdatedBy,customerID,userIDNumber,contactID);
            allAppts.add(appointment);
        }
        return allAppts;
    }

    /**
     * allTypes performs a check in the database for all "Types" of appointment entered in the Type column and returns an observable list of strings with these results.
     * @return
     * @throws SQLException
     */
    public static ObservableList<String> allTypes() throws SQLException {
        ObservableList<String> allTypes = FXCollections.observableArrayList();
        String sql = "SELECT DISTINCT Type FROM appointments";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {

            String type = rs.getString("Type");

            allTypes.add(type);
        }
        return allTypes;
    }

    /**
     * AllAppointmentsAllUsers returns a SELECT * of the table appointments reguardless of the user.
     * @return
     * @throws SQLException
     */
    public static ObservableList<Appointments> allAppointmentsAllUsers() throws SQLException {
        ObservableList<Appointments> allAppts = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int apptID = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            java.sql.Timestamp start = rs.getTimestamp("Start");
            java.sql.Timestamp end = rs.getTimestamp("End");
            java.sql.Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            java.sql.Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int customerID = rs.getInt("Customer_ID");
            int userIDNumber = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");

            Appointments appointment = new Appointments(apptID,title,description,location,type,start,end,createDate,createdBy,lastUpdate,lastUpdatedBy,customerID,userIDNumber,contactID);
            allAppts.add(appointment);
        }
        return allAppts;
    }

    /**
     * allAppointmentsByUserIDByWeek returns all of the scheduled appointment by week for the selected user.
     * @param userID - INT of the current User.
     * @return
     * @throws SQLException
     */
    public static ObservableList<Appointments> allAppointmentsByUserIDByWeek(int userID) throws SQLException {
        ObservableList<Appointments> allAppts = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments WHERE User_ID = ? AND YEARWEEK(Start, 1) = YEARWEEK(CURDATE(), 1)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, String.valueOf(userID));
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int apptID = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            java.sql.Timestamp start = rs.getTimestamp("Start");
            java.sql.Timestamp end = rs.getTimestamp("End");
            java.sql.Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            java.sql.Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int customerID = rs.getInt("Customer_ID");
            int userIDNumber = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");

            Appointments appointment = new Appointments(apptID,title,description,location,type,start,end,createDate,createdBy,lastUpdate,lastUpdatedBy,customerID,userIDNumber,contactID);
            allAppts.add(appointment);
        }
        return allAppts;
    }

    /**
     * allAppointmentsByUserIDByMonth returns an observablelist of scheduled appointments by month for the selected User.
     * @param userID - Int ID of the current user.
     * @return
     * @throws SQLException
     */
    public static ObservableList<Appointments> allAppointmentsByUserIDByMonth(int userID) throws SQLException {
        ObservableList<Appointments> allAppts = FXCollections.observableArrayList();
        String sql = "SELECT * from appointments where MONTH(start)=MONTH(now()) And YEAR(start)=YEAR(now()) And User_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, String.valueOf(userID));
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int apptID = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            java.sql.Timestamp start = rs.getTimestamp("Start");
            java.sql.Timestamp end = rs.getTimestamp("End");
            java.sql.Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            java.sql.Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int customerID = rs.getInt("Customer_ID");
            int userIDNumber = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");

            Appointments appointment = new Appointments(apptID,title,description,location,type,start,end,createDate,createdBy,lastUpdate,lastUpdatedBy,customerID,userIDNumber,contactID);
            allAppts.add(appointment);
        }
        return allAppts;
    }

    /**
     * allAppointmentsAllUsersByMonth returns an observable list of all apointments for the selected month.
     * @param month - STRING of the month queried
     * @return
     * @throws SQLException
     */
    public static ObservableList<Appointments> allAppointmentsAllUsersByMonth(String month) throws SQLException {
        ObservableList<Appointments> allAppts = FXCollections.observableArrayList();
        String sql = "SELECT * from appointments where MONTH(start)= ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, month);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int apptID = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            java.sql.Timestamp start = rs.getTimestamp("Start");
            java.sql.Timestamp end = rs.getTimestamp("End");
            java.sql.Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            java.sql.Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int customerID = rs.getInt("Customer_ID");
            int userIDNumber = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");

            Appointments appointment = new Appointments(apptID,title,description,location,type,start,end,createDate,createdBy,lastUpdate,lastUpdatedBy,customerID,userIDNumber,contactID);
            allAppts.add(appointment);
        }
        return allAppts;
    }
}
