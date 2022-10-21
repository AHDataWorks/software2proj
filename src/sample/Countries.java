package sample;
import java.sql.Timestamp;

/**
 * <Code>Countries</Code> acts as the constructor class for Countries objects.
 */
public class Countries {
    private int countryID;
    private String country;
    private Timestamp createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;

    /**
     *
     * @param countryID - INT value of this country
     * @param country - String value of this country.
     * @param createDate - Timestamp of the created date.
     * @param createdBy - String of the user that created this object.
     * @param lastUpdate - Timestamp of last time this country was updated.
     * @param lastUpdatedBy - String value of the user that last updated this country.
     */
    public Countries(int countryID, String country, Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy) {
        this.countryID = countryID;
        this.country = country;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     *
     * @return returns the int countryID of this country.
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * setCountryID accepts an int CountryID and sets it as the new ID for this object.
     * @param countryID - int of this countryID
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    /**
     * getCountry returns the string of this country
     * @return String value of the selected country.
     */
    public String getCountry() {
        return country;
    }

    /**
     * setCountry accepts a string and sets it as the new value for this country.
     * @param country - string value of the new country name.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * getCreateDate returns a timestamp of the selected country.
     * @return a timestamp of the selected country.
     */
    public Timestamp getCreateDate() {
        return createDate;
    }

    /**
     * setCreateDate changes the create date to a new timestamp.
     * @param createDate - the new timestamp to be used as the createDate.
     */
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    /**
     * getCreatedBy returns a string value of the user that created this country.
     * @return - A string value of the user that created this country.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * setCreatedBy sets a new creator for this country.
     * @param createdBy - String value of the new creator.
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * getLastUpdate returns a timestamp of last time this country object was updated.
     * @return A timestamp of last time this country object was updated.
     */
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}
