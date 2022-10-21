package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * <Code>CountriesQuery</Code> handles requests made to the database for the countries table.
 * @author Andrew Hobbs
 */
public abstract class CountriesQuery {

    /**
     * getAllCountries returns an observable list of all entries into the countries table.
     * @return An observable list of all entries into the countries table.
     * @throws SQLException
     */
    public static ObservableList<Countries> getAllCountries() throws SQLException {
        ObservableList<Countries> allCountries = FXCollections.observableArrayList();
        String sql = "SELECT * FROM countries";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int countryID = rs.getInt("Country_ID");
            String country = rs.getString("Country");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
        }
        return allCountries;
    }

    /**
     * getAllCountryNames returns an observableList of all entries in the countries table.
     * @return An observableList of all entries in the countries table.
     * @throws SQLException
     */
    public static ObservableList<String> getAllCountryNames() throws SQLException {
        ObservableList<String> allCountriesNames = FXCollections.observableArrayList();
        String sql = "SELECT * FROM countries";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {

            String country = rs.getString("Country");
            allCountriesNames.add(country);
        }
        return allCountriesNames;
    }

    /**
     * getCountryByID returns a string of the corresponding country by country ID.
     * @param countryID - int value of the selected country ID.
     * @return A string of the corresponding country by country ID
     * @throws SQLException
     */
    public static String getCountryByID(int countryID) throws SQLException {
        String country = null;
        String sql = "SELECT Country FROM countries WHERE Country_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, countryID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String countryName = rs.getString("Country");
            country = countryName;
        }
        return country;
    }
}
