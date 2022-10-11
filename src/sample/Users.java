package sample;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class Users {
    private int userID;
    private String userName;
    private String password;
    private Timestamp dateTime;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    public Users(int userID,String userName,String password,java.sql.Timestamp dateTime,String createdBy,java.sql.Timestamp lastUpdate,String lastUpdatedBy){
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.dateTime = dateTime;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
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
}
