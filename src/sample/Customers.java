package sample;

import java.sql.Timestamp;

/**
 * <Code>Customers</Code> acts as the constructor class for Customers objects.
 * @author Andrew Hobbs
 */
public class Customers {
    private int customerID;
    private String customerName;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private java.sql.Timestamp createDate;
    private String createdBy;
    private java.sql.Timestamp lastUpdate;
    private String lastUpdatedBy;
    private int divisionID;

    /**
     *
     * @param customerID - int ID of this customer.
     * @param customerName - String value of this customer's name.
     * @param address - String value of this customer's address.
     * @param postalCode - String value of this customer's postal code.
     * @param phoneNumber - String value of this customer's phone number.
     * @param createDate - Timestamp value of when this customer record was created.
     * @param createdBy - String value of the user that created this record.
     * @param lastUpdate - Timestamp of the last time this record was updated.
     * @param lastUpdatedBy - String value of the user that last updated this record.
     * @param divisionID - int ID of a corresponding first level division.
     */
    public Customers(int customerID,String customerName,String address,String postalCode,String phoneNumber,java.sql.Timestamp createDate,String createdBy,java.sql.Timestamp lastUpdate,String lastUpdatedBy,int divisionID) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionID = divisionID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public int getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }
}
