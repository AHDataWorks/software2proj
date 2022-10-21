package sample;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * <Code>FirstLevelDivisions</Code> acts as the constructor class for all FirstLevelDivision objects.
 */
public class FirstLevelDivisions {
    private int divisionID;
    private String division;
    private Timestamp createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    private int countryID;

    /**
     *
     * @param divisionID - int value of this division. Unique and auto generated on the database.
     * @param division - String value of the name of this division.
     * @param createDate - Timestamp value of when this record was created.
     * @param createdBy - String value of the user that created this record.
     * @param lastUpdate - Timestamp value of the last time this record was updated.
     * @param lastUpdatedBy - String value of the last user to update this record.
     * @param countryID - int value of which country this division belongs to.
     */
    public FirstLevelDivisions(int divisionID,String division,Timestamp createDate,String createdBy,Timestamp lastUpdate,String lastUpdatedBy,int countryID) {
        this.divisionID = divisionID;
        this.division = division;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.countryID = countryID;
    }

    public int getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

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

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }
}
