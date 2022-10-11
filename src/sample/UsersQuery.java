package sample;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class UsersQuery {

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
