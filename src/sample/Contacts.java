package sample;

/**
 * <Code>Contacts</Code> acts as the constructor class for Contacts objects.
 */
public class Contacts {
    private int contactID;
    private String contactName;
    private String email;

    /**
     *
     * @param contactID - INT of the Contact
     * @param contactName - String value of the contact
     * @param email - String value of the email.
     */
    public Contacts(int contactID,String contactName,String email) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.email = email;
    }

    /**
     * getContactID returns an int of the selected contact.
     * @return
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * setContactID sets the contactID of the selected contact to an INT.
     * @param contactID
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**
     * getContactName returns a String value of the selected contactName.
     * @return
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * setContactName sets the current contact's contactName to this string.
     * @param contactName
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
