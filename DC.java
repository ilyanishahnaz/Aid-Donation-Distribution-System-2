import java.io.IOException;
import java.util.*;

public class DC {
    /**
     * Method that allows user to enter Distribution Center
     * All aids, matching of aids and Collection Center will be displayed on console
     * 
     * @throws IOException throws exception
     */
    public static void DCView() throws IOException {
        ArrayList<Aid> aidList = new ArrayList<>();
        ArrayList<NGO> NGOAccounts = new ArrayList<>();
        ArrayList<Donor> DonorAccounts = new ArrayList<>();

        FileAccess.readAidFile(aidList);
        FileAccess.readAccountFile(NGOAccounts, DonorAccounts);
        Scanner input = new Scanner(System.in);

        int choiceMenu, choiceMatch, choiceQueue, choiceFIFO, choicePriority;
        do {
            System.out.println();
            System.out.println("--------------------------------------");
            System.out.println("Main Menu:");
            System.out.println("1: View all aids");
            System.out.println("2. Matching aids");
            System.out.println("3. Collection Center");
            System.out.println("4: Save to file");
            System.out.println("0: Quit");
            System.out.print("> ");
            choiceMenu = input.nextInt();
            if (choiceMenu == 1) {
                System.out.println("--------------------------------------");
                System.out.println("Displaying all aids...");
                System.out.printf("%-10s%-16s%-15s%-14s%-8s%-10s%-15s%n", "Donor", "Phone", "Aids", "Quantity", "NGO",
                        "Manpower", "Status");
                System.out.printf("%-10s%-16s%-15s%-14s%-8s%-10s%-15s%n", "-----", "-----------------", "-----------",
                        "-----------", "--------",
                        "-------", "-----------");

                for (int i = 0; i < aidList.size(); i++) {
                    String phoneNo = "-", manpower = "-";
                    for (int j = 0; j < DonorAccounts.size(); j++) {
                        if (Objects.equals(DonorAccounts.get(j).getUsername(), aidList.get(i).getAidDonor())) {
                            phoneNo = DonorAccounts.get(j).getPhoneNo();
                        }
                    }
                    for (int j = 0; j < NGOAccounts.size(); j++) {
                        if (Objects.equals(NGOAccounts.get(j).getUsername(), aidList.get(i).getAidRecipient())) {
                            manpower = NGOAccounts.get(j).getManpower();
                        }
                    }
                    System.out.printf("%-10s%-16s%-15s%-14d%-8s%-10s%-15s%n", aidList.get(i).getAidDonor(), phoneNo,
                            aidList.get(i).getAid(), aidList.get(i).getAidQuantity(),
                            aidList.get(i).getAidRecipient(), manpower, aidList.get(i).getStatus());
                }
                pressAnyKeyToContinue();
            } else if (choiceMenu == 2) {
                System.out.println();
                System.out.println("-------------------------------------------------");
                System.out.println("1. Match aids 1-to-1");
                System.out.println("2. Match aids 1-to-many");
                System.out.println("3. Match aids many-to-1");
                System.out.println("4. Match aids many-to-many");
                System.out.println("5. Save to file");
                System.out.println("0. Quit");
                System.out.print("> ");
                choiceMatch = input.nextInt();

                if (choiceMatch == 1) {
                    FileAccess.readAidNeededFile(NGOAccounts);
                    resetAidList(aidList, NGOAccounts, DonorAccounts);
                    System.out.println("--------------------------------------");
                    System.out.println("Matching aids 1-to-1...");
                    for (int i = 0; i < NGOAccounts.size(); i++) {
                        for (int j = 0; j < DonorAccounts.size(); j++) {
                            if (Objects.equals(DonorAccounts.get(j).getMatched(), "false")
                                    && Objects.equals(NGOAccounts.get(i).getMatched(), "false")) {
                                for (int m = 0; m < aidList.size(); m++) {
                                    for (String n : NGOAccounts.get(i).aidNeeded.keySet()) {
                                        if (Objects.equals(aidList.get(m).getAid(), n)) {
                                            if (aidList.get(m).getAidQuantity() == NGOAccounts.get(i).aidNeeded
                                                    .get(n)) {
                                                aidList.get(m).setAidRecipient(NGOAccounts.get(i).getUsername());
                                                aidList.get(m).setStatus("Reserved");
                                                DonorAccounts.get(j).setMatched("true");
                                                NGOAccounts.get(i).setMatched("true");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    Collections.sort(aidList, new AidComparator());
                    System.out.printf("%-10s%-16s%-15s%-14s%-8s%-10s%-15s%n", "Donor", "Phone", "Aids", "Quantity",
                            "NGO",
                            "Manpower", "Status");
                    System.out.printf("%-10s%-16s%-15s%-14s%-8s%-10s%-15s%n", "-----", "-----------------",
                            "-----------", "-----------", "--------",
                            "-------", "-----------");

                    for (int i = 0; i < aidList.size(); i++) {
                        String phoneNo = "-", manpower = "-";
                        for (int j = 0; j < DonorAccounts.size(); j++) {
                            if (Objects.equals(DonorAccounts.get(j).getUsername(), aidList.get(i).getAidDonor())) {
                                phoneNo = DonorAccounts.get(j).getPhoneNo();
                            }
                        }
                        for (int j = 0; j < NGOAccounts.size(); j++) {
                            if (Objects.equals(NGOAccounts.get(j).getUsername(), aidList.get(i).getAidRecipient())) {
                                manpower = NGOAccounts.get(j).getManpower();
                            }
                        }
                        System.out.printf("%-10s%-16s%-15s%-14d%-8s%-10s%-15s%n", aidList.get(i).getAidDonor(), phoneNo,
                                aidList.get(i).getAid(), aidList.get(i).getAidQuantity(),
                                aidList.get(i).getAidRecipient(), manpower, aidList.get(i).getStatus());
                    }
                    FileAccess.saveAccountsToFile(NGOAccounts, DonorAccounts);
                    FileAccess.saveAidToFile(aidList);
                    pressAnyKeyToContinue();
                } else if (choiceMatch == 2) {
                    FileAccess.readAidNeededFile(NGOAccounts);
                    resetAidList(aidList, NGOAccounts, DonorAccounts);
                    System.out.println("--------------------------------------");
                    System.out.println("Matching aids 1-to-many...");

                    System.out.printf("%-15s%-11s%-10s%-10s%n", "Aid Name", "Quantity", "Donor", "NGO");
                    for (int i = 0; i < aidList.size(); i++) {
                        System.out.printf("%-15s%-11s%-10s%-10s%n", aidList.get(i).getAid(),
                                aidList.get(i).getAidQuantity(), aidList.get(i).getAidDonor(),
                                aidList.get(i).getAidRecipient());
                    }
                    System.out.println("--------------------------------------");

                    System.out.println("aidlist size is " + aidList.size());

                    for (int i = 0; i < NGOAccounts.size(); i++) {
                        int extraAid = 0;
                        for (int j = 0; j < DonorAccounts.size(); j++) {
                            for (int m = 0; m < aidList.size(); m++) {
                                for (String n : NGOAccounts.get(i).aidNeeded.keySet()) {
                                    if (Objects.equals(aidList.get(m).getAid(), n)) {
                                        if (Objects.equals(NGOAccounts.get(i).getMatched(), "false")
                                                && Objects.equals(aidList.get(m).getAidRecipient(), "-")
                                                && Objects.equals(aidList.get(m).getAidDonor(),
                                                        DonorAccounts.get(j).getUsername())) {
                                            if (aidList.get(m).getAidQuantity() == NGOAccounts.get(i).aidNeeded
                                                    .get(n)) {
                                                aidList.get(m).setAidRecipient(NGOAccounts.get(i).getUsername());
                                                aidList.get(m).setStatus("Reserved");
                                                NGOAccounts.get(i).setMatched("true");
                                            } else if (aidList.get(m).getAidQuantity() > NGOAccounts.get(i).aidNeeded
                                                    .get(n)) {
                                                extraAid = aidList.get(m).getAidQuantity()
                                                        - NGOAccounts.get(i).aidNeeded.get(n);
                                                aidList.get(m).setAidRecipient(NGOAccounts.get(i).getUsername());
                                                aidList.get(m).setAidQuantity(NGOAccounts.get(i).aidNeeded.get(n));
                                                aidList.get(m).setStatus("Reserved");
                                                System.out.println("Called during " + NGOAccounts.get(i).getUsername()
                                                        + " and " + DonorAccounts.get(j).getUsername() + " and "
                                                        + aidList.get(m).getAidQuantity());
                                                aidList.add(new Aid(aidList.get(m).getAid(), extraAid,
                                                        DonorAccounts.get(j).getUsername(), "-", "Available"));
                                                NGOAccounts.get(i).setMatched("true");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    Collections.sort(aidList, new AidComparator());
                    System.out.printf("%-10s%-16s%-15s%-14s%-8s%-10s%-15s%n", "Donor", "Phone", "Aids", "Quantity", "NGO",
                            "Manpower","Status");
                    System.out.printf("%-10s%-16s%-15s%-14s%-8s%-10s%-15s%n", "-----", "-----------------", "-----------", "-----------", "--------",
                    "-------","-----------");


                    for (int i = 0; i < aidList.size(); i++) {
                        String phoneNo = "-", manpower = "-";
                        for (int j = 0; j < DonorAccounts.size(); j++) {
                            if (Objects.equals(DonorAccounts.get(j).getUsername(), aidList.get(i).getAidDonor())) {
                                phoneNo = DonorAccounts.get(j).getPhoneNo();
                            }
                        }
                        for (int j = 0; j < NGOAccounts.size(); j++) {
                            if (Objects.equals(NGOAccounts.get(j).getUsername(), aidList.get(i).getAidRecipient())) {
                                manpower = NGOAccounts.get(j).getManpower();
                            }
                        }
                        System.out.printf("%-10s%-16s%-15s%-14d%-8s%-10s%-15s%n", aidList.get(i).getAidDonor(), phoneNo,
                                aidList.get(i).getAid(), aidList.get(i).getAidQuantity(),
                                aidList.get(i).getAidRecipient(), manpower, aidList.get(i).getStatus());
                    }
                    FileAccess.saveAidToFile(aidList);
                    pressAnyKeyToContinue();
                }

                else if (choiceMatch == 3) {
                    FileAccess.readAidNeededFile(NGOAccounts);
                    resetAidList(aidList, NGOAccounts, DonorAccounts);
                    int extraAid;
                    System.out.println("--------------------------------------");
                    System.out.println("Matching aids many-to-1...");
                    for (int i = 0; i < NGOAccounts.size(); i++) {
                        for (int j = 0; j < DonorAccounts.size(); j++) {
                            for (int m = 0; m < aidList.size(); m++) {
                                for (String n : NGOAccounts.get(i).aidNeeded.keySet()) {
                                    if (Objects.equals(aidList.get(m).getAid(), n)) {
                                        if (Objects.equals(DonorAccounts.get(j).getMatched(), "false")
                                                && Objects.equals(aidList.get(m).getAidRecipient(), "-")) {
                                            if (aidList.get(m).getAidQuantity() == NGOAccounts.get(i).aidNeeded
                                                    .get(n)) {
                                                aidList.get(m).setAidRecipient(NGOAccounts.get(i).getUsername());
                                                aidList.get(m).setStatus("Reserved");
                                                DonorAccounts.get(j).setMatched("true");
                                            } else if (aidList.get(m).getAidQuantity() < NGOAccounts.get(i).aidNeeded
                                                    .get(n)) {
                                                extraAid = NGOAccounts.get(i).aidNeeded.get(n)
                                                        - aidList.get(m).getAidQuantity();
                                                aidList.get(m).setAidRecipient(NGOAccounts.get(i).getUsername());
                                                aidList.get(m).setStatus("Reserved");
                                                NGOAccounts.get(i).addAidNeeded(n, extraAid);
                                                DonorAccounts.get(j).setMatched("true");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    Collections.sort(aidList, new AidComparator());
                    System.out.printf("%-10s%-16s%-15s%-14s%-8s%-10s%-15s%n", "Donor", "Phone", "Aids", "Quantity", "NGO",
                            "Manpower","Status");
                    System.out.printf("%-10s%-16s%-15s%-14s%-8s%-10s%-15s%n", "-----", "-----------------", "-----------", "-----------", "--------",
                    "-------","-----------");


                    for (int i = 0; i < aidList.size(); i++) {
                        String phoneNo = "-", manpower = "-";
                        for (int j = 0; j < DonorAccounts.size(); j++) {
                            if (Objects.equals(DonorAccounts.get(j).getUsername(), aidList.get(i).getAidDonor())) {
                                phoneNo = DonorAccounts.get(j).getPhoneNo();
                            }
                        }
                        for (int j = 0; j < NGOAccounts.size(); j++) {
                            if (Objects.equals(NGOAccounts.get(j).getUsername(), aidList.get(i).getAidRecipient())) {
                                manpower = NGOAccounts.get(j).getManpower();
                            }
                        }
                        System.out.printf("%-10s%-16s%-15s%-14d%-8s%-10s%-15s%n", aidList.get(i).getAidDonor(), phoneNo,
                                aidList.get(i).getAid(), aidList.get(i).getAidQuantity(),
                                aidList.get(i).getAidRecipient(), manpower, aidList.get(i).getStatus());
                    }
                    pressAnyKeyToContinue();
                }

                else if (choiceMatch == 4) {
                    FileAccess.readAccountFile(NGOAccounts, DonorAccounts);
                    resetAidList(aidList, NGOAccounts, DonorAccounts);
                    int extraAid;
                    System.out.println("--------------------------------------");
                    System.out.println("Matching aids many-to-many...");
                    for (int i = 0; i < NGOAccounts.size(); i++) {
                        for (int j = 0; j < DonorAccounts.size(); j++) {
                            for (int m = 0; m < aidList.size(); m++) {
                                for (String n : NGOAccounts.get(i).aidNeeded.keySet()) {
                                    if (Objects.equals(aidList.get(m).getAid(), n)
                                            && NGOAccounts.get(i).aidNeeded.get(n) > 0) {
                                        if (Objects.equals(aidList.get(m).getAidRecipient(), "-")) {
                                            if (aidList.get(m).getAidQuantity() == NGOAccounts.get(i).aidNeeded
                                                    .get(n)) {
                                                aidList.get(m).setAidRecipient(NGOAccounts.get(i).getUsername());
                                                NGOAccounts.get(i).addAidNeeded(n, 0);
                                                aidList.get(m).setStatus("Reserved");
                                            } else if (aidList.get(m).getAidQuantity() < NGOAccounts.get(i).aidNeeded
                                                    .get(n)) {
                                                extraAid = NGOAccounts.get(i).aidNeeded.get(n)
                                                        - aidList.get(m).getAidQuantity();
                                                aidList.get(m).setAidRecipient(NGOAccounts.get(i).getUsername());
                                                aidList.get(m).setStatus("Reserved");
                                                NGOAccounts.get(i).addAidNeeded(n, extraAid);
                                            } else if (aidList.get(m).getAidQuantity() > NGOAccounts.get(i).aidNeeded
                                                    .get(n)) {
                                                extraAid = aidList.get(m).getAidQuantity()
                                                        - NGOAccounts.get(i).aidNeeded.get(n);
                                                aidList.get(m).setAidRecipient(NGOAccounts.get(i).getUsername());
                                                aidList.get(m).setAidQuantity(NGOAccounts.get(i).aidNeeded.get(n));
                                                aidList.get(m).setStatus("Reserved");
                                                NGOAccounts.get(i).addAidNeeded(n, 0);
                                                aidList.add(new Aid(aidList.get(m).getAid(), extraAid,
                                                        aidList.get(m).getAidDonor(), "-", "Available"));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    Collections.sort(aidList, new AidComparator());

                    System.out.printf("%-10s%-16s%-15s%-14s%-8s%-10s%-15s%n", "Donor", "Phone", "Aids", "Quantity", "NGO",
                            "Manpower","Status");
                    System.out.printf("%-10s%-16s%-15s%-14s%-8s%-10s%-15s%n", "-----", "-----------------", "-----------", "-----------", "--------",
                    "-------","-----------");


                    for (int i = 0; i < aidList.size(); i++) {
                        String phoneNo = "-", manpower = "-";
                        for (int j = 0; j < DonorAccounts.size(); j++) {
                            if (Objects.equals(DonorAccounts.get(j).getUsername(), aidList.get(i).getAidDonor())) {
                                phoneNo = DonorAccounts.get(j).getPhoneNo();
                            }
                        }
                        for (int j = 0; j < NGOAccounts.size(); j++) {
                            if (Objects.equals(NGOAccounts.get(j).getUsername(), aidList.get(i).getAidRecipient())) {
                                manpower = NGOAccounts.get(j).getManpower();
                            }
                        }
                        System.out.printf("%-10s%-16s%-15s%-14d%-8s%-10s%-15s%n", aidList.get(i).getAidDonor(), phoneNo,
                                aidList.get(i).getAid(), aidList.get(i).getAidQuantity(),
                                aidList.get(i).getAidRecipient(), manpower, aidList.get(i).getStatus());
                    }
                    FileAccess.saveAidToFile(aidList);
                    pressAnyKeyToContinue();
                } else if (choiceMatch == 5) {
                    FileAccess.saveAidToFile(aidList);
                    System.out.println("Aid matching saved to file");
                } else if (choiceMatch != 0) {
                    System.out.println("Invalid Input.");
                    pressAnyKeyToContinue();
                }
            }

            else if (choiceMenu == 3) {
                System.out.println();
                System.out.println("--------------------------------------");
                System.out.println("DC - Collection Center");
                System.out.println("1. First-In-First-Out Queue (FIFO)");
                System.out.println("2. Priority Queue");
                System.out.print("> ");
                choiceQueue = input.nextInt();

                if (choiceQueue == 1) {
                    Queue<String> list = new Queue<>();
                    List<String> listQueue = new ArrayList<String>();
                    listQueue.add("test");
                    do {
                        ArrayList<Priority> listNGO = new ArrayList<Priority>();

                        System.out.println();
                        System.out.println("DC - First-In-First-Out Queue (FIFO)");
                        FileAccess.readAidFile(aidList);
                        System.out.println("DC RECORDS");
                        System.out.printf("%-10s%-16s%-15s%-11s%-8s%-10s%-17s%n", "Donor", "Phone", "Aids", "Quantity",
                                "NGO", "Manpower", "Status");
                        System.out.printf("%-10s%-16s%-15s%-11s%-8s%-10s%-17s%n", "--------", "--------", "--------",
                                "--------", "----", "--------", "--------");

                        for (int i = 0; i < aidList.size(); i++) {
                            String phoneNo = "-", manpower = "-";
                            for (int j = 0; j < DonorAccounts.size(); j++) {
                                if (Objects.equals(DonorAccounts.get(j).getUsername(), aidList.get(i).getAidDonor())) {
                                    phoneNo = DonorAccounts.get(j).getPhoneNo();
                                }
                            }
                            for (int j = 0; j < NGOAccounts.size(); j++) {
                                if (Objects.equals(NGOAccounts.get(j).getUsername(),
                                        aidList.get(i).getAidRecipient())) {
                                    manpower = NGOAccounts.get(j).getManpower();
                                }
                            }
                            System.out.printf("%-10s%-16s%-15s%-11d%-8s%-10s%-17s%n", aidList.get(i).getAidDonor(),
                                    phoneNo, aidList.get(i).getAid(), aidList.get(i).getAidQuantity(),
                                    aidList.get(i).getAidRecipient(), manpower, aidList.get(i).getStatus());
                            if (aidList.get(i).getStatus().equals("Reserved")) {
                                listNGO.add(new Priority(aidList.get(i).getAidRecipient(), manpower,
                                        aidList.get(i).getStatus()));
                            }
                        }

                        System.out.println();
                        System.out.println("FIFO Queue: " + list);
                        System.out.println("Option");
                        System.out.println("1. Enqueue an NGO");
                        System.out.println("2. Dequeue an NGO");
                        System.out.println("0. Exit");
                        System.out.print("> ");

                        choiceFIFO = input.nextInt();

                        switch (choiceFIFO) {
                            case 1:
                                int check = 0, check2 = 0;
                                System.out.println("Enqueue an NGO");
                                String queueNGO = input.nextLine().substring(1);
                                System.out.println();
                                for (int i = 0; i < listNGO.size(); i++) {
                                    if (queueNGO.toUpperCase().equals(listNGO.get(i).getNGO().toUpperCase())) {
                                        for (int j = 0; j < listQueue.size(); j++) {
                                            if (queueNGO.toUpperCase().equals(listQueue.get(j))) {
                                                System.out.println(queueNGO.toUpperCase() + "-" + listQueue.get(j));
                                                System.out.println();
                                                check2 = 1;
                                            }
                                        }
                                        check = 1;
                                    }
                                }

                                if (check2 == 0) {
                                    if (check == 1) {
                                        list.addLast(queueNGO);
                                        listQueue.add(queueNGO.toUpperCase());
                                    }
                                } else {
                                    System.out.println(queueNGO + " is a duplicate");
                                    System.out.println("Please insert the other NGO name");
                                }

                                if (check == 0) {
                                    System.out.println(queueNGO + " is not in the system to collect aids");
                                    System.out.println("Please insert the correct NGO name");
                                    System.out.println();
                                }
                                break;

                            case 2:
                                System.out.println("Dequeue an NGO");
                                for (int i = 0; i < aidList.size(); i++) {
                                    System.out.println(aidList.get(i).getAidRecipient().toLowerCase() + "   ----   "
                                            + list.getFirst().toLowerCase());
                                    System.out.println();
                                    if (aidList.get(i).getAidRecipient().toLowerCase()
                                            .equals(list.getFirst().toLowerCase())) {
                                        aidList.get(i).setStatus("Collected");
                                    } else {
                                        aidList.get(i).getStatus();
                                    }
                                }
                                FileAccess.saveAidToFile(aidList);
                                ;
                                list.removeFirst();
                                System.out.println();
                                break;

                            case 0:
                                System.out.println("Quit");
                                break;

                            default:
                                System.out.println("Please enter the right choice");
                        }
                    } while (choiceFIFO != 0);
                } else if (choiceQueue == 2) {
                    Queue<String> list = new Queue<>();
                    Queue<String> listTemp = new Queue<>(); // test
                    List<String> listQueue = new ArrayList<String>();
                    listQueue.add("test");
                    do {
                        ArrayList<Priority> listNGO = new ArrayList<Priority>(); // test
                        System.out.println();
                        System.out.println("DC - Priority Queue");
                        FileAccess.readAidFile(aidList);
                        System.out.println("DC RECORDS");
                        System.out.printf("%-10s%-16s%-15s%-11s%-8s%-10s%-17s%n", "Donor", "Phone", "Aids", "Quantity",
                                "NGO", "Manpower", "Status");
                        System.out.printf("%-10s%-16s%-15s%-11s%-8s%-10s%-17s%n", "--------", "--------", "--------",
                                "--------", "----", "--------", "--------");

                        for (int i = 0; i < aidList.size(); i++) {
                            String phoneNo = "-", manpower = "-";
                            for (int j = 0; j < DonorAccounts.size(); j++) {
                                if (Objects.equals(DonorAccounts.get(j).getUsername(), aidList.get(i).getAidDonor())) {
                                    phoneNo = DonorAccounts.get(j).getPhoneNo();
                                }
                            }
                            for (int j = 0; j < NGOAccounts.size(); j++) {
                                if (Objects.equals(NGOAccounts.get(j).getUsername(),
                                        aidList.get(i).getAidRecipient())) {
                                    manpower = NGOAccounts.get(j).getManpower();
                                }
                            }
                            System.out.printf("%-10s%-16s%-15s%-11d%-8s%-10s%-17s%n", aidList.get(i).getAidDonor(),
                                    phoneNo, aidList.get(i).getAid(), aidList.get(i).getAidQuantity(),
                                    aidList.get(i).getAidRecipient(), manpower, aidList.get(i).getStatus());
                            if (aidList.get(i).getStatus().equals("Reserved")) {
                                listNGO.add(new Priority(aidList.get(i).getAidRecipient(), manpower,
                                        aidList.get(i).getStatus()));
                            }
                        }
                        Collections.sort(listNGO); // test
                        System.out.println(); // test
                        for (int ilistNGO = 0; ilistNGO < listNGO.size(); ilistNGO++) { // test
                            System.out.println(listNGO.get(ilistNGO)); // test
                        } // test

                        System.out.println();
                        System.out.println(list); // test
                        System.out.print("Priority queue: " + list);
                        if (list.getFirst() != null) {
                            System.out.println(" (" + list.getFirst() + " has highest priority)");
                        } else {
                            System.out.println();
                        }
                        System.out.println("Option");
                        System.out.println("1. Enqueue an NGO");
                        System.out.println("2. Dequeue an NGO");
                        System.out.println("0. Exit");
                        System.out.print("> ");

                        choicePriority = input.nextInt();

                        switch (choicePriority) {
                            case 1:

                                int check = 0;
                                int check2 = 0;
                                System.out.println();
                                System.out.println("Enqueue an NGO"); // test

                                String queueNGO = input.nextLine();

                                if (queueNGO.equals("")) {
                                    System.out.println("Please enter NGO name");
                                    break;
                                } else {
                                    queueNGO = queueNGO.substring(1);
                                }

                                for (int i = 0; i < listNGO.size(); i++) {
                                    if (queueNGO.toUpperCase().equals(listNGO.get(i).getNGO().toUpperCase())) {
                                        for (int j = 0; j < listQueue.size(); j++) {
                                            if (queueNGO.toUpperCase().equals(listQueue.get(j).toUpperCase())) {
                                                System.out
                                                        .println(queueNGO.toUpperCase() + "  --  " + listQueue.get(j)); // test
                                                System.out.println(); // test
                                                check2 = 1;
                                            }
                                        }
                                        check = 1;
                                    }
                                }

                                ///////////////////////////////////////////////////////////////////////////////////////////
                                if (check2 == 0) {
                                    if (check == 1) {
                                        listTemp = list;
                                        list = new Queue<String>(); // test
                                        // if (listTemp.getFirst() == null) {
                                        // list.addLast(queueNGO);
                                        // listQueue.add(queueNGO.toUpperCase());
                                        // }

                                        listTemp.addLast(queueNGO);

                                        for (int i = 0; i < listNGO.size(); i++) {
                                            for (int j = 0; j < listTemp.size(); j++) {
                                                System.out.println(listNGO.get(i).getNGO().toUpperCase() + "  ---  "
                                                        + (listTemp.getNGO(j).toUpperCase()));
                                                if (listNGO.get(i).getNGO().toUpperCase()
                                                        .equals(listTemp.getNGO(j).toUpperCase())) {
                                                    list.addLast(listTemp.getNGO(j));
                                                    listQueue.add(listTemp.getNGO(j));
                                                }
                                            }
                                            if (listNGO.get(i).getNGO().toUpperCase().equals(queueNGO.toUpperCase())) {
                                            }
                                        }

                                    }
                                    ////////////////////////////////////////////////////////////////////////////////////////

                                } else {
                                    System.out.println(queueNGO + " is a duplicate");
                                    System.out.println("Please insert the other NGO name");
                                }

                                if (check == 0) {
                                    System.out.println(queueNGO + " is not in the system to collect aids");
                                    System.out.println("Please insert the correct NGO name");
                                    System.out.println();
                                }

                                break;

                            case 2:
                                System.out.println("Dequeue an NGO");
                                for (int i = 0; i < aidList.size(); i++) {
                                    System.out.println(aidList.get(i).getAidRecipient().toLowerCase() + "   ----   "
                                            + list.getFirst().toLowerCase());
                                    System.out.println();
                                    if (aidList.get(i).getAidRecipient().toLowerCase()
                                            .equals(list.getFirst().toLowerCase())) {
                                        aidList.get(i).setStatus("Collected");
                                    } else {
                                        aidList.get(i).getStatus();
                                    }
                                }
                                FileAccess.saveAidToFile(aidList);
                                ;
                                list.removeFirst();
                                System.out.println();
                                break;
                            case 0:
                                System.out.println("Quit"); // test
                                break;

                            default:
                                System.out.println("Please enter the right choice");
                                break;
                        }
                    } while (choicePriority != 0);
                    //
                }


                else if (choiceQueue != 0) {
                    pressAnyKeyToContinue();
                    break;
                }

            }

            else if (choiceMenu == 4) {
                FileAccess.saveAidToFile(aidList);
                ;
            }

        } while (choiceMenu != 0);

    }

    private static void resetAidList(ArrayList<Aid> aidList, ArrayList<NGO> NGOAccounts,
            ArrayList<Donor> DonorAccounts) {
        for (int i = 0; i < aidList.size(); i++) {
            int aidToAdd = aidList.get(i).getAidQuantity();
            for (int j = i + 1; j < aidList.size(); j++) {
                if (Objects.equals(aidList.get(i).getAid(), aidList.get(j).getAid())
                        && Objects.equals(aidList.get(i).getAidDonor(), aidList.get(j).getAidDonor())) {
                    System.out.println("Aid 1 is " + aidList.get(i).getAid() + " from " + aidList.get(i).getAidDonor()
                            + " and Aid 2 is " + aidList.get(j).getAid() + " from " + aidList.get(j).getAidDonor());
                    System.out.println("Aid 1 is " + aidList.get(i).getAid() + " has " + aidList.get(i).getAidQuantity()
                            + " and Aid 2 is " + aidList.get(j).getAid() + " has " + aidList.get(j).getAidQuantity());
                    aidToAdd = aidList.get(i).getAidQuantity() + aidList.get(j).getAidQuantity();
                    System.out.println("Aid to add is " + aidToAdd);
                    aidList.remove(aidList.get(j));
                    break;
                }
            }
            aidList.get(i).setAidQuantity(aidToAdd);
            aidList.get(i).setAidRecipient("-");
            aidList.get(i).setStatus("Available");
        }
        for (int i = 0; i < NGOAccounts.size(); i++) {
            NGOAccounts.get(i).setMatched("false");
        }
        for (int i = 0; i < DonorAccounts.size(); i++) {
            DonorAccounts.get(i).setMatched("false");
        }
    }
    /**
     * Press any key to continue
     */
    public static void pressAnyKeyToContinue() {
        System.out.println("Press Enter key to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }



}