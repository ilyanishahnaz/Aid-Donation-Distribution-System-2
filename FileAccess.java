import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
/**
* public class of FileAccess created
*/
public class FileAccess {
    
    /**
     * Reads aids.csv file
     * @param aidList Reads ArrayList of Aid 
     * @throws IOException
     */
    public static void readAidFile(ArrayList<Aid> aidList) throws IOException {
        aidList.clear();
        List<String> lines = Files.readAllLines(Paths.get("aid.csv"));
        for (int i = 0; i < lines.size(); i++) {
            String[] items = lines.get(i).split(",");
            aidList.add(new Aid(items[0], Integer.parseInt(items[1]), items[2], items[3], items[4]));
        }
        for (int i = 0; i < aidList.size(); i++) {
            Collections.sort(aidList,new AidComparator());
        }
    }

    /**
     * Reads aidNeeded.csv file
     * @param NGOAccounts Reads from NGO Accounts ArrayList
     * @throws IOException if invalid
     */
    public static void readAidNeededFile(ArrayList<NGO> NGOAccounts) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("aidNeeded.csv"));
        for (int i = 0; i < lines.size(); i++) {
            String[] items = lines.get(i).split(",");
            for (int j = 0;j < NGOAccounts.size();j++){
                if (Objects.equals(NGOAccounts.get(j).getUsername(), items[0])){
                    NGOAccounts.get(j).addAidNeeded(items[1],Integer.valueOf(items[2]));
                }
            }
        }
    }

    /**
     * Saves data to aidNeeded.csv file
     * @param NGOAccounts Passes NGO ArrayList as parameter
     * @throws IOException if invalid
     */
    public static void saveAidNeededToFile(ArrayList<NGO> NGOAccounts) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < NGOAccounts.size(); i++)
            for (String j: NGOAccounts.get(i).aidNeeded.keySet()){
                sb.append(NGOAccounts.get(i).getUsername()+","+j+","+NGOAccounts.get(i).aidNeeded.get(j)+"\n");
            }
        Files.write(Paths.get("aidNeeded.csv"), sb.toString().getBytes());
    }

    /**
     * Reads accounts.csv
     * @param NGOAccounts Passes NGO ArrayList as parameter
     * @param DonorAccounts Passes Donor ArrayList as parameter
     * @throws IOException if invalid
     */
    public static void readAccountFile(ArrayList<NGO> NGOAccounts, ArrayList<Donor> DonorAccounts) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("accounts.csv"));
        for (int i = 0; i < lines.size(); i++) {
            String[] items = lines.get(i).split(",");
            if (Objects.equals(items[2], "NGO")){
                NGOAccounts.add(new NGO(items[0], items[1], items[2], items[3], items[4]));
            }
            else if (Objects.equals(items[2], "Donor")){
                DonorAccounts.add(new Donor(items[0], items[1], items[2], items[3], items[4]));
            }
        }
    }

    /**
     * Saves data to accounts.csv
     * @param NGOAccounts Passes NGO ArrayList as parameter
     * @param DonorAccounts Passes Donor ArrayList as parameter
     * @throws IOException if invalid
     */
    public static void saveAccountsToFile(ArrayList<NGO> NGOAccounts, ArrayList<Donor> DonorAccounts) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < DonorAccounts.size(); i++)
            sb.append(DonorAccounts.get(i).toCSVString() + "\n");
        for (int i = 0; i < NGOAccounts.size(); i++)
            sb.append(NGOAccounts.get(i).toCSVString() + "\n");
        Files.write(Paths.get("accounts.csv"), sb.toString().getBytes());
    }

    /**
     * Saves data to aid.csv file
     * @param aidList Passes aidList ArrayList as parameter
     * @throws IOException if invalid
     */

    public static void saveAidToFile(ArrayList<Aid> aidList) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < aidList.size(); i++)
            sb.append(aidList.get(i).toCSVString() + "\n");
        Files.write(Paths.get("aid.csv"), sb.toString().getBytes());
    }
    
}