import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * NGO represents the details of an NGO account.
 * The username, password, accountType, manpower and matched are String
 * aidList is an ArrayList of the Aid class
 * Uses superclass Account
 */
public class NGO extends Account {
    private String manpower;
    private String matched;
    HashMap<String, Integer> aidNeeded = new HashMap<>();


    
     /** 
   * Constructs a NGO with all variables set to NULL or 0.
   */
    public NGO() {}
    
    /**
     * Constructs a NGO with the specified username, password, accountType, manpower and matched
     * @param username the username of the account
     * @param password the password of the account
     * @param accountType the account type of the account
     * @param manpower the manpower of the account
     * @param matched the matched status of the account
     */
    public NGO (String username, String password, String accountType, String manpower,String matched) {
        super(username, password, accountType);
        this.manpower = manpower;
        this.matched = matched;
    }
    
    /** 
   * Returns the manpower from the NGO.
   * @return manpower
   */ 
    public String getManpower() {
        return manpower;
    }
    /** 
   * The current NGO's matched will be the main Matched
   * @param matched the matched status of the account
   */ 
    public void setMatched (String matched){
        this.matched = matched;
    }
    
    /** 
   * Returns the matched from the Donor.
   * @return matched
   */ 
    public String getMatched() {
        return matched;
    }

    /** 
   * To add a new aid and its quantity to HashMap for aidNeeded
   * @param aid type of aid
   * @param aidQuantity quantitiy of aid
   */ 
    public void addAidNeeded (String aid,Integer aidQuantity){
        aidNeeded.put(aid,aidQuantity);
    }

    /** 
   * To save the AidNeeded as a .csv file
   * @throws IOException to ensure the input is valid
   */ 
    public void saveAidNeededToFile() throws IOException {
        StringBuilder sb = new StringBuilder();
        for (HashMap.Entry<String, Integer> aidNeeded : aidNeeded.entrySet()) {
            sb.append(aidNeeded.getKey());
            sb.append(",");
            sb.append(aidNeeded.getValue());
        Files.write(Paths.get("aidNeeded.csv"), sb.toString().getBytes());
        }
    }
    /** 
   * Returns the username, password, aids, accountType, manpower and matched from the Donor.
   * and
   * Convert all of them to CSV type string
   * 
   * @return username, password, aids, accountType, manpower and matched from Donor
   */ 
    public String toCSVString() {
        return username + "," + password + "," + accountType + "," + manpower+ "," + matched;
    }
    
    /** 
   * Returns the username, password, aids, accountType, manpower and matched from the Donor.
   * and
   * Convert all of them to string
   * 
   * @return username, password, aids, accountType, manpower and matched from Donor
   */ 
    public String toString() {
        return username + "     " + password + "     " + accountType + "     " + manpower;
    }
   
    /** 
   * Returns the username, password and accountType from the NGO.
   * and
   * Convert all of them to string
   * 
   * @return username, password and accountType from NGO
   */ 
    public String getNGOLogin() {
        return username + "," + password + ","+ accountType;
    }

    /** 
   * To compare the the Object either it is same or not
   * @param comparedObject object that compared
   */ 
    @Override
    public boolean equals(Object comparedObject) {
        if (this == comparedObject) {
            return true;
        }
        if (!(comparedObject instanceof NGO)) {
            return false;
        }

        NGO comparedNGO = (NGO) comparedObject;

        // if the instance variables of the objects are the same, so are the objects
        if (this.username.equals(comparedNGO.username) &&
                this.password.equals(comparedNGO.password) &&
                //this.name.equals(comparedNGO.name) &&
                this.accountType.equals(comparedNGO.accountType)) {//&&
            //this.manpower.equals(comparedNGO.manpower)) {
            return true;
        }

        // otherwise, the objects aren't the same
        return false;
    }
}