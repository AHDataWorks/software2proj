package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZonedDateTime;

public abstract class AppointmentsQuery {

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

    public static void deleteAppointment(int apptID) throws SQLException {
        String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, apptID);
        int rowsAffected = ps.executeUpdate();
        System.out.println(rowsAffected + "rows affected.");
    }


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

    public static ObservableList<String> allTypes() throws SQLException {
        ObservableList<String> allTypes = FXCollections.observableArrayList();
        String sql = "SELECT Type FROM appointments";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {

            String type = rs.getString("Type");

            allTypes.add(type);
        }
        return allTypes;
    }

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
}
