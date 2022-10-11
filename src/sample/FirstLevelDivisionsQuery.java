package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public abstract class FirstLevelDivisionsQuery {

    public static ObservableList<FirstLevelDivisions> getAllFirstLevelDivisions() throws SQLException {
        ObservableList<FirstLevelDivisions> allFirstLevelDivisions = FXCollections.observableArrayList();
        String sql = "SELECT * FROM first_level_divisions";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int divisionID = rs.getInt("Division_ID");
            String division = rs.getString("Division");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int countryID = rs.getInt("Country_ID");

        }
        return allFirstLevelDivisions;
    }

    public static ObservableList<String> getFirstLevelDivisionsByCountry(int countryID) throws SQLException {
        ObservableList<String> firstLevelDivisionsByCountry = FXCollections.observableArrayList();
        String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, String.valueOf(countryID));
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
//            int divisionID = rs.getInt("Division_ID");
            String division = rs.getString("Division");
//            Timestamp createDate = rs.getTimestamp("Create_Date");
//            String createdBy = rs.getString("Created_By");
//            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
//            String lastUpdatedBy = rs.getString("Last_Updated_By");
//            int newCountryID = rs.getInt("Country_ID");

            firstLevelDivisionsByCountry.add(division);

        }
        return firstLevelDivisionsByCountry;
    }

    public static int getFirstLevelDivisionID(String division) throws SQLException {
        int divisionID = 0;
        String sql = "SELECT Division_ID FROM first_level_divisions WHERE Division = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, division);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int divisionIDNumber = rs.getInt("Division_ID");
            divisionID = divisionIDNumber;
        }
        return divisionID;
    }

    public static String getFirstLevelDivisionByID(int divisionID) throws SQLException {
        String division = null;
        String sql = "SELECT Division FROM first_level_divisions WHERE Division_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, divisionID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String divisionName = rs.getString("Division");
            division = divisionName;
        }
        return division;
    }

    public static int getCountryByID(int divisionID) throws SQLException {
        int countryID = 0;
        String sql = "SELECT Country_ID FROM first_level_divisions WHERE Division_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, divisionID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int countryIDNumber = rs.getInt("Country_ID");
            countryID = countryIDNumber;
        }
        return countryID;
    }
}
