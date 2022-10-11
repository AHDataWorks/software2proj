package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ContactsQuery {
    public static ObservableList<Contacts> allContacts() throws SQLException {
        ObservableList<Contacts> allContacts = FXCollections.observableArrayList();
        String sql = "SELECT * FROM contacts ";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int contactID = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            String email = rs.getString("Email");


            Contacts contact = new Contacts(contactID,contactName,email);
            allContacts.add(contact);
        }
        return allContacts;
    }

    public static ObservableList<String> allContactNames() throws SQLException {
        ObservableList<String> allContactnames = FXCollections.observableArrayList();
        String sql = "SELECT * FROM contacts ";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String contactName = rs.getString("Contact_Name");


            allContactnames.add(contactName);

        }
        return allContactnames;
    }

    public static int getContactID(String contactName) throws SQLException {
        int contactID = 0;
        String sql = "SELECT Contact_ID FROM contacts WHERE Contact_Name = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, contactName);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            contactID = rs.getInt("Contact_ID");
        }
        return contactID;
    }

    public static String getContactName(int contactID) throws SQLException {
        String contactName = null;
        String sql = "SELECT Contact_Name FROM contacts WHERE Contact_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, contactID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            contactName = rs.getString("Contact_Name");
        }
        return contactName;
    }
}
