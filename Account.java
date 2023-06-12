/**
 * Account represents the details needed to register and login to an account.
 * The username, password, name and accountType are String
 * Superclass
 */
public class Account {
    /** 
    * Protected data field of username.
    */
    protected String username;
    /** 
    * Protected data field of password.
    */
    protected String password;
    /** 
    * Protected data field of accountType.
    */
    protected String accountType;

    /** 
    * Constructs an Account class.
    */
    public Account() {}
    /**
     * Constructs the account with the specified username, password and account type ; Donor/NGO
     *
     * @param username    the username of an account
     * @param password    the password of an account
     * @param accountType the account type (Donor/NGO) of an account
     */

    public Account(String username, String password, String accountType) {
        this.username = username;
        this.password = password;
        this.accountType = accountType;
    }

    /**
     * @return username, password, name, accountType as String to display in console
     */
    public String toString() {
        return username + "     " + password + "     " + accountType;
    }

    /**
     * @return username, password, name, accountType to be formatted into the csv file
     */
    public String toCSVString() {
        return username + "," + password + "," + accountType;
    }

    /**
     * Returns the username from the Account.
     * 
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password from the Account.
     * 
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the accountType from the Account.
     * 
     * @return accountType
     */
    public String getAccountType() {
        return accountType;
    }
}