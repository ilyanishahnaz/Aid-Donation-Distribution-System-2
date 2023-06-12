import java.util.Comparator;

/**
 * Aid represents the details an aid.
 * The aid, aidDonor, aidRecipient and status are String
 * The aidQuantity is an int
 * Superclass
 * 
 *
 */

public class Aid {
    /**
    * aid created as protected string
    */
    protected String aid; 
    
    /**
    * aidQuantity created as protected integer
    */
    protected int aidQuantity;
        
    /**
    * aidDonor created as protected string
    */
    protected String aidDonor;
        
    /**
    * aidRecipient created as protected string
    */
    protected String aidRecipient;
        
    /**
    * status created as protected string
    */
    protected String status;

    /** 
   * Constructs a NGO with all variables set to NULL or 0.
   */
    public Aid() {
    }
    
    /**
     * Constructs the aid with the specified aid, aidQuantity, aidDonor, aidRecipient and status
     *
     * @param aid    the name of the aid
     * @param aidQuantity    the quantity of the aid
     * @param aidDonor       the name of the aid donor
     * @param aidRecipient   the recipient of the aid
     * @param status        the status of the aid
     */
    public Aid(String aid, int aidQuantity, String aidDonor, String aidRecipient, String status) {
        this.aid = aid;
        this.aidQuantity = aidQuantity;
        this.aidDonor = aidDonor;
        this.aidRecipient = aidRecipient;
        this.status = status;
    }

    /**
     * @return aid, aidQuantity, aidDonor, aidRecipient and status as String to display in console
     */
    public String toString() {
        return aid + "     " + aidQuantity + "      " + aidDonor + "     " + aidRecipient+ "     " + status;
    }

    /**
     * @return aid, aidQuantity, aidDonor, aidRecipient and status to be formatted into the csv file
     */
    public String toCSVString() {
        return aid + "," + aidQuantity + "," + aidDonor + "," + aidRecipient+ "," + status;
    }

    /**
     * Sets the aid name to a String value of aid
     * @param aid the type of aid
     */
    public void setAid (String aid){
        this.aid = aid;
    }

    /**
     * Retrieves the name of aid
     * @return aid
     */
    public String getAid() {
        return aid;
    }

    /**
     * Sets the aid quantity to an Integer value of aidQuantity
     * @param aidQuantity quantity of the aid
     */
    public void setAidQuantity (int aidQuantity){
        this.aidQuantity = aidQuantity;
    }

    /**
     * Retrieves aid quantity 
     * @return aidQuantity
     */
    public int getAidQuantity() {
        return aidQuantity;
    }

    /**
     * Sets the aid donor to a String value of aidDonor
     * @param aidDonor Donor's Aid
     */
    public void setAidDonor (String aidDonor){
        this.aidDonor = aidDonor;
    }

    /**
     * Retrieves the name of the aid Donor
     * @return aidDonor
     */
    public String getAidDonor() {
        return aidDonor;
    }

    /**
     * Sets the String name of the aidRecipient (NGO)
     * @param aidRecipient Recipient's aid
     */
    public void setAidRecipient (String aidRecipient){
        this.aidRecipient = aidRecipient;
    }

    /**
     * Retrieves the name of the aidRecipient (NGO)
     * @return aidRecipient
     */
    public String getAidRecipient() {
        return aidRecipient;
    }

    /**
     * Sets the status of the aid whether it is Available, Reserved or Collected
     * @param status status for the aid
     */
    public void setStatus (String status){
        this.status = status;
    }

    /**
     * Retrieves the status of the aid
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Returns a value if an object is an instance of an Aid
     */
    public boolean equals(Object obj) {
        if (obj instanceof Aid) {
            Aid o = (Aid) obj;
            return o.aidDonor == this.aidDonor;
        }
        return false;
    }

    /**
     * Compares aid quantity by ascending order for output
     */
    public static Comparator<Aid> COMPARE_BY_QUANTITY_ASCENDING = new Comparator<Aid>() {
        public int compare(Aid one, Aid two) {
            return Integer.compare(one.aidQuantity,two.aidQuantity);
        }
    };

    /**
     * Compares aid quantity by descending order for output
     */
    public static Comparator<Aid> COMPARE_BY_QUANTITY_DESCENDING = new Comparator<Aid>() {
        public int compare(Aid one, Aid two) {
            if (one.aid.compareTo(two.aid) == 0){
                one.setAidQuantity(one.getAidQuantity() + two.getAidQuantity());
            }
            return Integer.compare(two.aidQuantity,one.aidQuantity);
        }
    };

    /**
     * Compares an aidRecipient whether they are matched
     */

    public static Comparator<Aid> COMPARE_BY_MATCHED = new Comparator<Aid>() {
        public int compare(Aid one, Aid two) {
            return two.aidRecipient.compareTo(one.aidRecipient);
        }
    };
}
