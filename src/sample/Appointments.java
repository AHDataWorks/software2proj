package sample;

import java.sql.Timestamp;

/**
 * <Code>Appointments</Code> Appointments is the constructor for appointments
 * @author Andrew Hobbs
 */

public class Appointments {
    private int apptID;
    private String title;
    private String description;
    private String location;
    private String type;
    private java.sql.Timestamp start;
    private java.sql.Timestamp end;
    private java.sql.Timestamp createDate;
    private String createdBy;
    private java.sql.Timestamp lastUpdate;
    private String lastUpdatedBy;
    private int customerID;
    private int userID;
    private int contactID;
    public Appointments(int apptID,String title,String description,String location,String type,java.sql.Timestamp start,java.sql.Timestamp end,java.sql.Timestamp createDate,String createdBy,java.sql.Timestamp lastUpdate,String lastUpdatedBy,int customerID,int userID,int contactID) {
        this.apptID = apptID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
    }

    /**
     * getApptId retrieves a cooresponding appointment ID.
     * @return
     */
    public int getApptID() {
        return apptID;
    }

    /**
     * setApptID takes in an integer and assigns it to the appointment.
     * @param apptID - an integer meant to assign to apptID.
     */
    public void setApptID(int apptID) {
        this.apptID = apptID;
    }

    /**
     * getTitle returns the String title of this appointment.
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * setTitle accepts a String and sets it as the title of this appointment.
     * @param title - String of the new title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * getDescription returns the description value of this appointment.
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * setDescription accepts a string and sets it as the new description of this appointment.
     * @param description - string of the new description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * getLocation returns a string of the current location of this appointment.
     * @return
     */
    public String getLocation() {
        return location;
    }

    /**
     * setLocation accepts a string and replaces it as the location of this appointment.
     * @param location - String of the new location.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * getType returns a string value of the type of this appointment.
     * @return - returns a string of this type.
     */
    public String getType() {
        return type;
    }

    /**
     * setType accepts a string and sets it as the type of this appointment.
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * getStart returns the Timestamp value of this appointment.
     * @return Timestamp of the current appointment start.
     */
    public Timestamp getStart() {
        return start;
    }

    /**
     * setStart accepts a Timestamp and sets it as the new start time for the appointment.
     * @param start
     */
    public void setStart(Timestamp start) {
        this.start = start;
    }

    /**
     * getEnd returns the timestamp value of the end time of this appointment.
     * @return a Timestamp value of this end.
     */
    public Timestamp getEnd() {
        return end;
    }

    /**
     * setEnd accepts a timestamp and sets it as the end time of this appointment.
     * @param end
     */
    public void setEnd(Timestamp end) {
        this.end = end;
    }

    /**
     * getCreateDate returns a timestamp value of the current createDate.
     * @return
     */
    public Timestamp getCreateDate() {
        return createDate;
    }

    /**
     * setCreateDate accepts a timestamp and sets it as the new createDate of this appointment.
     * @param createDate
     */
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    /**
     * getCreatedBy returns a string value of the creator of this appointment.
     * @return
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * setCreatedBy accepts a string and sets it to the createdBy of this appointment.
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * getLastUpdate returns a Timestamp of the last update of this appointment.
     * @return
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

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getContactID() {
        return contactID;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }
}
