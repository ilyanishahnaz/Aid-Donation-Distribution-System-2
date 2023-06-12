import java.io.IOException;
import java.util.*;

/**
 * main console
 */
public class Main {
/**
 * Main program to run
 * @param args passes args as parameter
 * @throws IOException throws exception
 */
    public static void main(String[] args) throws IOException {
        ArrayList<NGO> NGOAccounts = new ArrayList<>();
        ArrayList<Donor> DonorAccounts = new ArrayList<>();
        ArrayList<Aid> aidList = new ArrayList<>();

        FileAccess.readAidFile(aidList);
        FileAccess.readAccountFile(NGOAccounts, DonorAccounts);
        FileAccess.readAidFile(aidList);
        FileAccess.readAidNeededFile(NGOAccounts);

        Scanner input = new Scanner(System.in);
        int choiceMain,choiceSignIn,choiceMenu;

        do {
            System.out.println();
            System.out.println("--------------------------------------");
            System.out.println("Welcome to Aid Distribution System");
            System.out.println("Enter your choice:");
            System.out.println("1: Donor");
            System.out.println("2: NGO");
            System.out.println("3: Distribution center");
            System.out.println("0: Quit");
            System.out.print("> ");
            choiceMain = input.nextInt();

            if (choiceMain != 0&&choiceMain != 1&&choiceMain != 2&&choiceMain != 3){
                System.out.println("Invalid Input!");
                pressAnyKeyToContinue();
            }
        }while(choiceMain != 0&&choiceMain != 1&&choiceMain != 2&&choiceMain != 3);

        int currentUser = -1;
        switch (choiceMain){
            case 1:
                do{
                    System.out.println();
                    System.out.println("--------------------------------------");
                    System.out.println("Enter your choice:");
                    System.out.println("1: Login");
                    System.out.println("2: Register");
                    System.out.println("0: Exit");
                    System.out.print("> ");
                    choiceSignIn = input.nextInt();

                    if (choiceSignIn == 1) {
                        currentUser = login(NGOAccounts, DonorAccounts, "Donor");
                    }
                    else if (choiceSignIn == 2){
                        register(NGOAccounts, DonorAccounts, "Donor");
                    }
                    else if(choiceSignIn == 0){
                        pressAnyKeyToContinue();
                    }
                    else{
                        System.out.println("Invalid Input.");
                        pressAnyKeyToContinue();
                    }
                    if (currentUser >= 0){
                        choiceSignIn = 0;
                    }
                }while (choiceSignIn != 0);

                do{
                    System.out.println();
                    System.out.println("--------------------------------------");
                    System.out.println("Welcome Donor!");
                    System.out.println("Username: " + DonorAccounts.get(currentUser).getUsername());
                    System.out.println("Phone No: " + DonorAccounts.get(currentUser).getPhoneNo());
                    System.out.println("--------------------------------------");
                    System.out.println("Main Menu:");
                    System.out.println("1: Add an aid donation");
                    System.out.println("2: View aids donated");
                    System.out.println("0: Save and Quit");
                    System.out.print("> ");
                    choiceMenu = input.nextInt();

                    if (choiceMenu == 1){
                        System.out.println();
                        System.out.println("--------------------------------------");
                        System.out.println("Donating aid...");
                        System.out.print("Enter aid donation: ");
                        String aidName = input.next();
                        System.out.print("Enter quantity of donation: ");
                        int aidQuantity = input.nextInt();
                        System.out.println();

                        aidName = capitalizeFirstLetter(aidName);
                        aidList.add(new Aid(aidName,aidQuantity,DonorAccounts.get(currentUser).getUsername(),"-","Available"));
                        FileAccess.saveAidToFile(aidList);
                        System.out.println();

                        System.out.println("Aid added successfully.");
                        pressAnyKeyToContinue();
                    }

                    else if (choiceMenu == 2){
                        System.out.println("--------------------------------------");
                        System.out.println("Displaying aids donated...");
                        System.out.println();
                        System.out.printf("%-15s%-13s%-14s%-15s%n","Aid Name","Quantity","Assigned NGO","Status");
                        System.out.printf("%-15s%-13s%-14s%-15s%n", "--------","---------","---------","----------");
                        for (int i = 0;i <aidList.size();i++){
                            if(Objects.equals(DonorAccounts.get(currentUser).getUsername(), aidList.get(i).getAidDonor())){
                                System.out.printf("%-15s%-13d%-14s%-15s%n",aidList.get(i).getAid(),aidList.get(i).getAidQuantity(),aidList.get(i).getAidRecipient(),aidList.get(i).getStatus());
                            }
                        }
                        pressAnyKeyToContinue();
                    }
                    else if (choiceMenu == 0){
                        FileAccess.saveAccountsToFile(NGOAccounts,DonorAccounts);
                        FileAccess.saveAidToFile(aidList);
                        pressAnyKeyToContinue();
                        
                    }
                    else{
                        System.out.println("Invalid Input.");
                        pressAnyKeyToContinue();
                    }
                }while (choiceMenu != 0);
                break;
            case 2:
                do{
                    System.out.println();
                    System.out.println("--------------------------------------");
                    System.out.println("Enter your choice:");
                    System.out.println("1: Login");
                    System.out.println("2: Register");
                    System.out.print("> ");
                    choiceSignIn = input.nextInt();

                    if (choiceSignIn == 1) {
                        currentUser = login(NGOAccounts, DonorAccounts, "NGO");
                    }
                    else if (choiceSignIn == 2){
                        register(NGOAccounts, DonorAccounts, "NGO");
                    }
                    else{
                        System.out.println("Invalid Input.");
                        pressAnyKeyToContinue();
                    }
                    if(currentUser >= 0){
                        choiceSignIn = 0;
                    }
                }while (choiceSignIn != 0);

                do{
                    System.out.println();
                    System.out.println("--------------------------------------");
                    System.out.println("Welcome NGO!");
                    System.out.println("Username: " + NGOAccounts.get(currentUser).getUsername());
                    System.out.println("Manpower: " + NGOAccounts.get(currentUser).getManpower());
                    System.out.println("--------------------------------------");
                    System.out.println("Main Menu:");
                    System.out.println("1: Request an aid");
                    System.out.println("2: View aids received");
                    System.out.println("0: Save and Quit");
                    System.out.print("> ");
                    choiceMenu = input.nextInt();

                    if (choiceMenu == 1){
                        System.out.println("--------------------------------------");
                        System.out.println("Requesting aid...");
                        System.out.print("Enter aid to request: ");
                        String aidName = input.next();
                        System.out.print("Enter quantity of request: ");
                        int aidQuantity = input.nextInt();
                        System.out.println();

                        aidName = capitalizeFirstLetter(aidName);
                        NGOAccounts.get(currentUser).addAidNeeded(aidName,aidQuantity);
                        FileAccess.saveAidNeededToFile(NGOAccounts);
                        System.out.println();
                        System.out.println("Aid has been requested.");
                        pressAnyKeyToContinue();
                    }

                    else if (choiceMenu == 2){
                        System.out.println("--------------------------------------");
                        System.out.println("Displaying aids received...");
                        System.out.println();
                        System.out.printf("%-15s%-11s%-10s%-12s%n","Aid Name","Quantity","Donor","Status");
                        System.out.printf("%-15s%-11s%-10s%-15s%n", "--------","---------","---------","----------");

                        for (int i = 0;i < aidList.size();i++){
                            if(Objects.equals(NGOAccounts.get(currentUser).getUsername(), aidList.get(i).getAidRecipient())){
                                System.out.printf("%-15s%-11d%-10s%-15s%n",aidList.get(i).getAid(),aidList.get(i).getAidQuantity(),aidList.get(i).getAidDonor(),aidList.get(i).getStatus());
                            }
                        }
                        pressAnyKeyToContinue();
                    }
                    else if (choiceMenu == 0){
                        FileAccess.saveAccountsToFile(NGOAccounts,DonorAccounts);
                        FileAccess.saveAidNeededToFile(NGOAccounts);
                        pressAnyKeyToContinue();

                    }
                    else{
                        System.out.println("Invalid Input.");
                        pressAnyKeyToContinue();
                    }
                }while (choiceMenu != 0);
                break;
            case 3:
                DC.DCView();
    }
}

    private static int login(ArrayList<NGO> NGOAccounts, ArrayList<Donor> DonorAccounts, String type){
        int currentUser = -1;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter username:");
        String userName = input.nextLine();
        System.out.println("Enter password:");
        String passWord = input.nextLine();
        System.out.println();
        String loginID = (userName + "," + passWord + "," + type);
        if (Objects.equals(type, "Donor")){
            for (int i = 0; i < DonorAccounts.size(); i++) {
                if (loginID.equals(DonorAccounts.get(i).getDonorLogin())) {
                    currentUser = i;
                    System.out.println("Login Successful");
                    break;
                }
            }
        }
        else if (Objects.equals(type, "NGO")){
            for (int i = 0; i < NGOAccounts.size(); i++) {
                if (loginID.equals(NGOAccounts.get(i).getNGOLogin())) {
                    currentUser = i;
                    System.out.println("Login Successful");
                    break;
                }
            }
        }
        if (currentUser == -1){
            System.out.println("Incorrect credentials. Please try again.");
        }
        return currentUser;
    }

    /**
     * To register a new account
     * @param NGOAccounts passes NGO ArrayList as parameter
     * @param DonorAccounts passes Donor ArrayList as parameter
     * @param type passes account type as parameter
     */
    protected static void register(ArrayList<NGO> NGOAccounts, ArrayList<Donor> DonorAccounts, String type){
        boolean exist = false;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter username:");
        String userName = input.nextLine();
        System.out.println("Enter password:");
        String passWord = input.nextLine();
        System.out.println();
        String loginID = (userName + "," + passWord + "," + type);
        if (Objects.equals(type, "Donor")){
            System.out.println("Enter phone number:");
            String phoneNo = input.nextLine();
            for (int i = 0; i < DonorAccounts.size(); i++) {
                if (loginID.equals(DonorAccounts.get(i).getDonorLogin())) {
                    exist = true;
                    break;
                }
            }
            if (!exist){
                DonorAccounts.add(new Donor(userName,passWord,type,phoneNo,"false"));
                System.out.println("Account successfully registered! Please proceed to log in.");
            }
        }
        else if (Objects.equals(type, "NGO")){
            System.out.println("Enter manpower:");
            String manpower = input.nextLine();
            for (int i = 0; i < NGOAccounts.size(); i++) {
                if (loginID.equals(NGOAccounts.get(i).getNGOLogin())) {
                    exist = true;
                    break;
                }
            }
            if (!exist){
                NGOAccounts.add(new NGO(userName,passWord,type,manpower,"false"));
                System.out.println("Account successfully registered! Please proceed to log in.");
            }
        }
        if (exist){
            System.out.println("User already exists. Please try again.");
        }
    }

    /**
     * 
     * @param str passes string as a parameter
     * @return returns the string with first letter capitalized
     */
    public static String capitalizeFirstLetter(String str) {
        str = str.toLowerCase();
        if(str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * Press any key to continue
     */
    public static void pressAnyKeyToContinue()
    {
        System.out.println("Press Enter key to continue...");
        try
        {
            System.in.read();
        }
        catch(Exception e)
        {}
    }
}