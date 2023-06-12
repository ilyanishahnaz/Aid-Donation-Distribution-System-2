/**
 * Donor represents the details of a Donor account.
 * The username, password, accountType, phoneNo and matched are String
 * aidList is an ArrayList of the Aid class
 * Uses superclass Account
 */
public class Donor extends Account {
    private String phoneNo;
    private String matched;

    /**
     * Constructs a Donor with all parameter set to NULL or 0.
     */
    public Donor() {
    }

    /**
     * Constructs a Donor with the specified username, password, accountType,
     * phoneNo and matched
     * 
     * @param username    the username of the account
     * @param password    the password of the account
     * @param accountType the account type of the account
     * @param phoneNo     the phone number of the account
     * @param matched     the matched status of the account
     */
    public Donor(String username, String password, String accountType, String phoneNo, String matched) {
        super(username, password, accountType);
        this.phoneNo = phoneNo;
        this.matched = matched;
    }

    /**
     * Returns the phoneNumber from the Donor.
     * 
     * @return phoneNo
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * The current Donor's matched will be the main Matched
     * 
     * @param matched the matched status of the account
     */
    public void setMatched(String matched) {
        this.matched = matched;
    }

    /**
     * Returns the matched from the Donor.
     * 
     * @return matched
     */

    public String getMatched() {
        return matched;
    }

    /**
     * Returns the username, password, aids, accountType, phoneNo and matched from
     * the Donor.
     * and
     * Convert all of them to CSV type string
     * 
     * @return username, password, aids, accountType, phoneNo and matched from Donor
     */
    public String toCSVString() {
        return username + "," + password + "," + accountType + "," + phoneNo + "," + matched;
    }

    /**
     * Returns the username, password, aids, accountType, phoneNo and matched from
     * the Donor.
     * and
     * Convert all of them to string
     * 
     * @return username, password, aids, accountType, phoneNo and matched from Donor
     */
    public String toString() {
        return username + "     " + password + "     " + accountType + "     " + phoneNo;
    }

    /**
     * Returns the username, password and accountType from the Donor.
     * and
     * Convert all of them to string
     * 
     * @return username, password and accountType from Donor
     */
    public String getDonorLogin() {
        return username + "," + password + "," + accountType;
    }

    /**
     * To compare the the Object either it is same or not
     * 
     * @param comparedObject object to be compare
     */
    @Override
    public boolean equals(Object comparedObject) {
        if (this == comparedObject) {
            return true;
        }
        if (!(comparedObject instanceof Donor)) {
            return false;
        }

        Donor comparedDonor = (Donor) comparedObject;

        // if the instance variables of the objects are the same, so are the objects
        if (this.username.equals(comparedDonor.username) &&
                this.password.equals(comparedDonor.password) &&
                // this.name.equals(comparedDonor.name) &&
                this.accountType.equals(comparedDonor.accountType)) {// &&
            // this.manpower.equals(comparedDonor.manpower)) {
            return true;
        }

        // otherwise, the objects aren't the same
        return false;
    }
}