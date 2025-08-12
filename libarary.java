import java.util.Scanner;

public class libarary {
    static final int MAX_MEMBERS = 200;
    static final int MAX_BOOKS = 300;
    static final int MAX_BORROW_TRANSACTIONS = 500;
    static final int MAX_RESERVATION = 100;

    //Mmenbers Array
    static int[] membersIds = new int[MAX_MEMBERS];
    static String[] membersNames = new String[MAX_MEMBERS];
    static String[] membersAddresses = new String[MAX_MEMBERS];
    static String[] membersPhoneNumbers = new String[MAX_MEMBERS];
    static String[] membersMembershipType = new String[MAX_MEMBERS];
    static int membersCount = 0;

    //Books Array
    static String[] booksIds = new String[MAX_BOOKS];
    static String[] booksTitles = new String[MAX_BOOKS];
    static String[] booksAuthors = new String[MAX_BOOKS];
    static String[] booksGenres = new String[MAX_BOOKS];
    static int[] booksPublishYear = new int[MAX_BOOKS];
    static int[] booksTotalCopies = new int[MAX_BOOKS];
    static int[] booksAvailableCopies = new int[MAX_BOOKS];
    static int booksCount = 0;

    //Browing Transaction arrays
    static int[] transactionIds = new int[MAX_BORROW_TRANSACTIONS];
    static int[] transactionMembersIds = new int[MAX_BORROW_TRANSACTIONS];
    static String[] transactionBooksIds = new String[MAX_BORROW_TRANSACTIONS];
    static String[] transactionBorrowDate = new String[MAX_BORROW_TRANSACTIONS];
    static String[] transactionDueDate = new String[MAX_BORROW_TRANSACTIONS];
    static String[] transactionReturnDate = new String[MAX_BORROW_TRANSACTIONS];
    static double[] transactionFineAmount = new double[MAX_BORROW_TRANSACTIONS];
    static int borrowsCount = 0;

    //Reservation array
    static int[] reservationsIds = new int[MAX_RESERVATION];
    static int[] reservationsMembersIds = new int[MAX_RESERVATION];
    static String[] reservationsBooksIds = new String[MAX_BORROW_TRANSACTIONS];
    static String[] reservationsDate = new String[MAX_RESERVATION];
    static String[] reservationsSatatus = new String[MAX_RESERVATION];
    static int reservationsCount = 0;

    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        initializeSimpleData();

        while(true) {
            displayMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    manageMembers();
                    break;
                case 2:
                    manageBooks();
                    break;
                case 3:
                    manageBrowingTransaction();
                    break;
                case 4:
                    manageReservation();
                    break;
                case 5:
                    System.out.println("Thank you for using Library system!");
                    return;
                default:
                    System.out.println("Invalid choice. please try again");
            }
        }
    }

//========Manage Members==============
    static void manageMembers() {
        while(true) {
           System.out.println("===Member MANAGEMENT===");
           System.out.println("1. Add Member");
           System.out.println("2. View All Members");
           System.out.println("3. Search Member");
           System.out.println("4. Update Member");
           System.out.println("6. Back to Main Menu");
           System.out.println("Enter Your choice: ");

           int choice = scanner.nextInt();
           scanner.nextLine();
           switch (choice) {
            case 1:
                addMember();
                break;
            case 2:
                viewAllMembers();
                break;
            case 3:
                searchMember();
                break;
            case 4:
                updateMember();
                break;
            case 5:
                return;
            default:
                System.out.println("Invalid choice. please try again");
           }
        }
    }

    static void addMember(){
        if(membersCount >= MAX_MEMBERS){
            System.out.println("Member limit reached!");
            return;
        }

        System.out.println("Enter Member Id : ");
        int membersId = scanner.nextInt();
        scanner.nextLine();

        for(int i =0; i<membersCount; i++){
            if(membersId == membersIds[i]){
                System.out.println("Member Id Already Exists");
                return;
            }
        }

        System.out.println("Enter Member Name");
        String name = scanner.nextLine();

        System.out.println("Enter Member Address");
        String address = scanner.nextLine();

        System.out.println("Enter Member Phone Number");
        String phone_number = scanner.nextLine();

        System.out.println("Enter Membership Type");
        String membership_type = scanner.nextLine();

        membersIds[membersCount] = membersId;
        membersNames[membersCount] = name;
        membersAddresses[membersCount] = address;
        membersPhoneNumbers[membersCount] = phone_number;
        membersMembershipType[membersCount] = membership_type;
        membersCount++;

        System.out.println("Member added successfully!");
    }

    static void viewAllMembers(){
        if (membersCount == 0) {
            System.out.println("There are No Members");
            return;
        }

        System.out.println("\n===All Members");
        System.out.printf("%-5s %-20s %-30s %-10s %-20s%n", "Id", "Name", "Address", "Phone Number", "Membership Type");
        System.out.println("-".repeat(100));

        for(int i=0; i<membersCount;i++){
            System.out.printf("%-5d %-20s %-30s %-10s %-20s%n", membersIds[i],membersNames[i],membersAddresses[i],membersPhoneNumbers[i],membersMembershipType[i]);
        }
    }
    
    static void searchMember(){
        System.out.println("Enter Member Id or name to search");
        String input = scanner.nextLine();

        for(int i=0; i<membersCount; i++){
            if((input.matches("\\d+") && Integer.parseInt(input) == membersIds[i]) || input.equalsIgnoreCase(membersNames[i])){
                System.out.println("\nMember Found");
                System.out.println("ID: " + membersIds[i]);
                System.out.println("Name: " + membersNames[i]);
                System.out.println("Address: " + membersAddresses[i]);
                System.out.println("Phone Number: " + membersPhoneNumbers[i]);
                System.out.println("Membership Type: " + membersMembershipType[i]);
                return;
            }
        }
        System.out.println("Member Not Found");
    }

    static void updateMember(){
        System.out.println("Enter Member Id to update");
        int memberId = scanner.nextInt();
        scanner.nextLine();

        for(int i=0; i<membersCount; i++){
            if(memberId == membersIds[i]){
                System.out.println("Current Details:");
                System.out.println("Name: " + membersNames[i]);
                System.out.println("Address: " + membersAddresses[i]);
                System.out.println("Phone Number " + membersPhoneNumbers[i]);
                System.out.println("Membershhip Type " + membersMembershipType[i]);

                System.out.println("Enter new Name (or press Enter to keep the current) ");
                String name = scanner.nextLine();
                if (!name.trim().isEmpty()) {
                    membersNames[i] = name;
                }

                System.out.println("Enter new Address (or press Enter to keep the current) ");
                String address = scanner.nextLine();
                if (!address.trim().isEmpty()) {
                    membersAddresses[i] = address;
                }

                System.out.println("Enter new Phone Number (or press Enter to keep the current) ");
                String number = scanner.nextLine();
                if (!number.trim().isEmpty()) {
                    membersPhoneNumbers[i] = number;
                }

                System.out.println("Enter new Membership Type (or press Enter to keep the current) ");
                String membershipType = scanner.nextLine();
                if (!membershipType.trim().isEmpty()) {
                    membersMembershipType[i] = membershipType;
                }

                System.out.println("Member Updated Successfully!");
                return;
            }
        }
        System.out.println("Member Not Found");
    }
//========Manage Members==============

    static void manageBooks() {}
    static void manageBrowingTransaction() {}
    static void manageReservation() {}

    //What display on main menu
    static void displayMainMenu() {
       System.out.println("===LIBARARY SYSTEM MAIN MENU===");
       System.out.println("1. Manage Members");
       System.out.println("2. Manage Books");
       System.out.println("3. Process Borrowing Transaction");
       System.out.println("4. View Reservation");
       System.out.println("5. Exit");
       System.out.println("Enter Your choice: ");
    }

    //initialize Simple Data
    public static void initializeSimpleData() {
        // sample members
        membersIds[0] = 1; membersNames[0] = "Kamal Silva"; membersAddresses[0] = "Colombo"; membersPhoneNumbers[0] = "071567276";membersMembershipType[0] = "Regular";
        membersIds[1] = 2;membersNames[1] = "Jagath Kumara";membersAddresses[1] = "Kalutara";membersPhoneNumbers[1] = "0712563738";membersMembershipType[1] = "Premium";
        membersIds[2] = 3;membersNames[2] = "Nihal Bandara";membersAddresses[2] = "Kandy";membersPhoneNumbers[2] = "07723762763";membersMembershipType[2] = "Student";
        membersCount = 3;

        // sample books
        booksIds[0] = "P001";booksTitles[0] = "Laptop";booksAuthors[0] = "asj";booksGenres[0] = "romance";booksPublishYear[0] = 2000;booksTotalCopies[0]=1;booksAvailableCopies[0]=0;
        booksIds[1] = "P002";booksTitles[1] = "Mouse";booksAuthors[1] = "ddfdsf";booksGenres[1] = "thriller";booksPublishYear[1] = 2001;booksTotalCopies[1]=10;booksAvailableCopies[1]=5;
        booksIds[2] = "P003";booksTitles[2] = "USB Keyboard";booksAuthors[2] = "sdsf";booksGenres[2] = "fantacy";booksPublishYear[2] = 2002;booksTotalCopies[2]=20;booksAvailableCopies[2]=10;
        booksIds[3] = "P004";booksTitles[3] = "Printers";booksAuthors[3] = "fdgdf";booksGenres[3] = "horror";booksPublishYear[3] = 2003;booksTotalCopies[3]=40;booksAvailableCopies[3]=20;
        booksIds[4] = "P005";booksTitles[4] = "Monitor";booksAuthors[4] = "dfgdfg";booksGenres[4] = "anime";booksPublishYear[4] = 2004;booksTotalCopies[4]=34;booksAvailableCopies[4]=30;
        booksCount = 5;

       System.out.println("sample data initialized!");
       System.out.println("3 members and 5 books addedd!.");
    }
}
