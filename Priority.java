/**
* A few private parameter created representing a Priority.
*/
public class Priority implements Comparable<Priority>{
    String NGO;  
    String manpower;  
    String status;  
    
    /** 
   * Constructs a NGO with all variables set to NULL or 0.
   */
    public Priority() {};
    
    /** 
    * Construct Priority with the specified NGO, manpower and status
    * 
    * @param NGO
    * @param manpower
    * @param status
    */
    Priority(String NGO,String manpower,String status){  
    this.NGO=NGO;  
    this.manpower=manpower;  
    this.status=status;  
    }
    
    /** 
   * Returns the NGO from the Priority.
   * @return NGO
   */ 
    public String getNGO(){
        return NGO;
    }
    
    /** 
   * Returns the NGO, manpower, aids,and matched from the Donor.
   * and
   * Convert all of them tostring
   * 
   * @return username, password, aids, accountType, phoneNo and matched from Donor
   */ 
    public String toString(){
        return NGO + "     " + manpower + "      " + status;
    }

    /**
     * To compare the manpower of NGOs 
     * 
     */
    public int compareTo(Priority st){  
        if(manpower==st.manpower)  
        return 0;  
        else if(Integer.parseInt(manpower)<Integer.parseInt(st.manpower))  
        return 1;  
        else  
        return -1;  
    }

}