package sample;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <Code>UsersQuery</Code> handles database queries directed to the users table.
 * @author Andrew Hobbs
 */
public abstract class UsersQuery {

    /**
     * getUserID accepts a username and password and returns a corresponding userID if found.
     * @param userName user name entered by the user on the login page.
     * @param password password entered by the user on the login page.
     * @return
     * @throws SQLException
     */
    public static int getUserID(String userName,String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE User_Name = ? AND Password = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, userName);
        ps.setString(2, password);
        ResultSet user = ps.executeQuery();
        int userID = 0;
        while(user.next()) {
            userID = user.getInt("User_ID");}
        return userID;
    }

    /**
     * getUsername takes in a int userID and returns a String of the corresponding user name.
     * @param userID int userID of the user.
     * @return String of the corresponding name.
     * @throws SQLException
     */
    public static String getUserName(int userID) throws SQLException {
        String sql = "SELECT User_Name FROM users WHERE User_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, userID);

        ResultSet user = ps.executeQuery();
        String userName = null;
        while(user.next()) {
            userName = user.getString("User_Name");}
        return userName;
    }
}
