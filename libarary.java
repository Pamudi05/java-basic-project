import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class libarary {
    static final int MAX_MEMBERS = 200;
    static final int MAX_BOOKS = 300;
    static final int MAX_BORROW_TRANSACTIONS = 500;
    static final int MAX_MEMBER_BORROWS = 3;
    static final int MAX_RESERVATION = 100;

    //Mmenbers Array
    static int[] membersIds = new int[MAX_MEMBERS];
    static String[] membersNames = new String[MAX_MEMBERS];
    static String[] membersAddresses = new String[MAX_MEMBERS];
    static String[] membersPhoneNumbers = new String[MAX_MEMBERS];
    static String[] membersMembershipType = new String[MAX_MEMBERS];
    static int[] memberBorrowCount  = new int[MAX_MEMBERS];
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
    static boolean[] transactionActive  = new boolean[MAX_BORROW_TRANSACTIONS];
    static int borrowsCount = 0;

    //Reservation array
    static int[] reservationsIds = new int[MAX_RESERVATION];
    static int[] reservationsMembersIds = new int[MAX_RESERVATION];
    static String[] reservationsBooksIds = new String[MAX_RESERVATION];
    static String[] reservationsDate = new String[MAX_RESERVATION];
    static String[] reservationsStatus = new String[MAX_RESERVATION];
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
                    generateReport();
                    break;
                case 6:
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
           System.out.println("5. Back to Main Menu");
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

//========Manage Books==============
    static void manageBooks() {
        while(true) {
           System.out.println("===Books MANAGEMENT===");
           System.out.println("1. Add Book");
           System.out.println("2. View All Books");
           System.out.println("3. Search Book");
           System.out.println("4. Update Book");
           System.out.println("5. Book Availability");
           System.out.println("6. Back to Main Menu");
           System.out.println("Enter Your choice: ");

           int choice = scanner.nextInt();
           scanner.nextLine();
           switch (choice) {
            case 1:
                addBook();
                break;
            case 2:
                viewAllBooks();
                break;
            case 3:
                searchBook();
                break;
            case 4:
                updateBook();
                break;
            case 5:
                bookAvailability();
                break;
            case 6:
                return;
            default:
                System.out.println("Invalid choice. please try again");
           }
        }
    }

    static void addBook(){
        if(booksCount >= MAX_BOOKS){
            System.out.println("Books limit reached!");
            return;
        }

        System.out.println("Enter Book Id (ex: P001) : ");
        String bookId = scanner.nextLine();

        for(int i =0; i<booksCount; i++){
            if(bookId.equals(booksIds[i])){
                System.out.println("Book Id Already Exists");
                return;
            }
        }

        System.out.println("Enter Book Title: ");
        String title = scanner.nextLine();

        System.out.println("Enter Book Author");
        String author = scanner.nextLine();

        System.out.println("Enter Book Genre");
        String genre = scanner.nextLine();

        System.out.println("Enter Book year");
        int year = scanner.nextInt();

        System.out.println("Enter Book copies");
        int copy = scanner.nextInt();

        booksIds[booksCount] = bookId;
        booksTitles[booksCount] = title;
        booksAuthors[booksCount] = author;
        booksGenres[booksCount] = genre;
        booksPublishYear[booksCount] = year;
        booksAvailableCopies[booksCount] = copy;
        booksCount++;

        System.out.println("Book added successfully!");
    }

    static void viewAllBooks(){
        if (booksCount == 0) {
            System.out.println("There are No Books");
            return;
        }

        System.out.println("\n===All Bookd");
        System.out.printf("%-5s %-20s %-30s %-10s %-20s%n", "Id", "Title", "Author", "Genre", "Copies");
        System.out.println("-".repeat(100));

        for(int i=0; i<booksCount;i++){
            System.out.printf("%-5s %-20s %-30s %-10s %-20s%n", booksIds[i],booksTitles[i],booksAuthors[i],booksGenres[i],booksAvailableCopies[i]);
        }
    }

    static void searchBook(){
        System.out.println("Enter Book Id , title or author to search");
        String input = scanner.nextLine();

        for(int i=0; i<booksCount; i++){
            if((input.equalsIgnoreCase(booksIds[i])) || input.equalsIgnoreCase(booksTitles[i]) || input.equalsIgnoreCase(booksAuthors[i])){
                System.out.println("\nBook Found");
                System.out.println("ID: " + booksIds[i]);
                System.out.println("Title: " + booksTitles[i]);
                System.out.println("Author: " + booksAuthors[i]);
                System.out.println("Genre: " + booksGenres[i]);
                System.out.println("Available copies: " + booksAvailableCopies[i]);
                return;
            }
        }
        System.out.println("Book Not Found");
    }

    static void updateBook(){
        System.out.println("Enter Book Id to update");
        String bookId = scanner.nextLine();

        for(int i=0; i<booksCount; i++){
            if(bookId.equals(booksIds[i])){
                System.out.println("Current Details:");
                System.out.println("Title: " + booksTitles[i]);
                System.out.println("Author: " + booksAuthors[i]);
                System.out.println("Publish Year: " + booksPublishYear[i]);
                System.out.println("Genre: " + booksGenres[i]);
                System.out.println("Available copy: " + booksAvailableCopies[i]);

                System.out.println("Enter new Title (or press Enter to keep the current) ");
                String title = scanner.nextLine();
                if (!title.trim().isEmpty()) {
                    booksTitles[i] = title;
                }

                System.out.println("Enter new Author (or press Enter to keep the current) ");
                String author = scanner.nextLine();
                if (!author.trim().isEmpty()) {
                    booksAuthors[i] = author;
                }

                System.out.println("Enter new Genre (or press Enter to keep the current) ");
                String genre = scanner.nextLine();
                if (!genre.trim().isEmpty()) {
                    booksGenres[i] = genre;
                }
                
                System.out.println("Enter new Year (or -1 to keep the current) ");
                int year = scanner.nextInt();
                if (year != 0) {
                    booksPublishYear[i] = year;
                }

                System.out.println("Enter new Available copies (or -1 to keep the current) ");
                int copy = scanner.nextInt();
                if (copy != -1) {
                    booksAvailableCopies[i] = copy;
                }

                System.out.println("Book Updated Successfully!");
                return;
            }
        }
        System.out.println("Book Not Found");
    }

    static void bookAvailability(){
        System.out.println("Enter Book Id");
        String bookId = scanner.nextLine();

        for(int i=0; i < booksCount; i++){
            if(bookId.equals(booksIds[i])){
                System.out.println("Current Available Books : " + booksAvailableCopies[i]);
                System.out.println("Enter book copy to add (or remove substact)");
                int change = scanner.nextInt();

                if(booksAvailableCopies[i] + change < 0){
                    System.out.println("Insufficient Book");
                    return;
                }
                booksAvailableCopies[i] += change;
                System.out.println("Book Available Copies Updated!" + booksAvailableCopies[i]);
                return;
            }
        }
        System.out.println("Book Not Found");
    }

//========Manage Books==============

//========Borrow Books==============
    static void manageBrowingTransaction() {
        while(true) {
           System.out.println("===Books Borrow MANAGEMENT===");
           System.out.println("1. Borrow Book");
           System.out.println("2. Return Books");
           System.out.println("3. Back to Main Menu");
           System.out.println("Enter Your choice: ");

           int choice = scanner.nextInt();
           scanner.nextLine();
           switch (choice) {
            case 1:
                borrowBook();
                break;
            case 2:
                returnBooks();
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid choice. please try again");
           }
        }
    }

    static int nextTransactionId = 1;
    static void borrowBook(){
        if(borrowsCount >= MAX_BORROW_TRANSACTIONS){
            System.out.println("Book borrow limit reached!");
            return;
        }

        if(membersCount == 0){
            System.out.println("There is no Member! Please add one");
            return;
        }

        if(booksCount == 0){
            System.out.println("There is no Book! Please add one");
            return;
        }

        int transactionBooksIds = nextTransactionId++;
        System.out.println("Transaction Id: " + transactionBooksIds);

        System.out.println("Enter Member Id: ");
        int memberId = scanner.nextInt();
        scanner.nextLine();

        int memberIndex = -1;
        for(int i=0; i < membersCount; i++){
            if(memberId == membersIds[i]){
                memberIndex= i;
                break;
            }
        }

        if(memberIndex == -1){
            System.out.println("Member does not exist!");
            return;
        }

        if(memberBorrowCount[memberIndex] >= MAX_MEMBER_BORROWS){
            System.out.println("This member has already borrowed the maximum number of books (" + MAX_MEMBER_BORROWS + ").");
            return;
        }

        System.out.println("Enter book Id: ");
        String bookId = scanner.nextLine();
        
        int bookIndex = -1;
        for(int i=0; i < booksCount; i++){
            if(bookId.equalsIgnoreCase(booksIds[i])){
                bookIndex= i;
                break;
            }
        }

        if(bookIndex == -1){
            System.out.println("Book does not exist!");
            return;
        }

        if (booksAvailableCopies[bookIndex] <= 0) {
            System.out.println("Book is not available!");
            return;
        }

        booksAvailableCopies[bookIndex]--;
        memberBorrowCount[memberIndex]++;
        borrowsCount++;

        LocalDate borrowDate = LocalDate.now();
        LocalDate dueDate = borrowDate.plusDays(14);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
        
        System.out.println("Book borrowed successfully!");
        System.out.println("Transaction ID: " + transactionIds);
        System.out.println("Borrow Date: " + borrowDate.format(formatter));
        System.out.println("Due Date: " + dueDate.format(formatter));
        System.out.println("Remaining copies of " + bookId + ": " + booksAvailableCopies[bookIndex]);
        System.out.println("Books currently borrowed by Member " + memberId + ", " + memberBorrowCount[memberIndex] + " books");

    }
    
    static void returnBooks(){
        System.out.println("Enter Member Id: ");
        int memberId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter Book Id: ");
        String bookId = scanner.nextLine();

        int transactionIndex = -1;
        for(int i=0; i<borrowsCount; i++){
            if(memberId == transactionMembersIds[i] &&
                bookId.equalsIgnoreCase(transactionBooksIds[i]) &&
                transactionActive[i]){
                    transactionIndex = i;
                    break;
            }
        }

        if(transactionIndex == -1){
            System.out.println("No active borrowing transaction found!");
            return;
        }

        String returnDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        transactionReturnDate[transactionIndex] = returnDate;
        transactionActive[transactionIndex] = false;

        for(int i=0; i<booksCount; i++){
            if(bookId.equalsIgnoreCase(booksIds[i])){
                booksAvailableCopies[i]++;
                break;
            }
        }

        for(int i=0; i<membersCount; i++){
            if(memberId == membersIds[i]) {
                memberBorrowCount[i]++;
                break;
            }
        }

        LocalDate dateDue = LocalDate.now().plusDays(14);
        transactionDueDate[transactionIndex] = dateDue.toString();
        
        LocalDate dateReturn = LocalDate.now();

        long lateDays = ChronoUnit.DAYS.between(dateDue, dateReturn);
        long fine = (lateDays > 0) ? lateDays : 0;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println("Book returned successfully!");
        System.out.println("Return Date: " + dateReturn.format(formatter));

        if (fine > 0) {
            System.out.println("This book was returned late. Fine: $" + fine);
        } else {
            System.out.println("No fine. Returned on time.");
        }
    }
//========Borrow Books==============

//========Reservation Books==============
    static void manageReservation() {
        while(true) {
            System.out.println("===Reserve Books MANAGEMENT===");
            System.out.println("1. Reserve Book");
            System.out.println("2. View Reservation");
            System.out.println("3. Back to Main Menu");
            System.out.println("Enter Your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
            case 1:
                reserveBook();
                break;
            case 2:
                viewReservation();
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid choice. please try again");
            }
        }
    }

    static int nextReservationsId = 1;
    static void reserveBook(){
        if(membersCount == 0){
            System.out.println("There is no Member! Please add one");
            return;
        }

        if(booksCount == 0){
            System.out.println("There is no Book! Please add one");
            return;
        }

        int reservationsIds = nextReservationsId++;
        System.out.println("Reservations Id: " + reservationsIds);

        System.out.print("Enter Member Id: ");
        int memberId = scanner.nextInt();
        scanner.nextLine();

         int memberIndex = -1;
        for(int i=0; i < membersCount; i++){
            if(memberId == membersIds[i]){
                memberIndex= i;
                break;
            }
        }

        if(memberIndex == -1){
            System.out.println("Member does not exist!");
            return;
        }

        System.out.print("Enter book Id: ");
        String bookId = scanner.nextLine();
        
        int bookIndex = -1;
        for(int i=0; i < booksCount; i++){
            if(bookId.equalsIgnoreCase(booksIds[i])){
                bookIndex= i;
                break;
            }
        }

        if(bookIndex == -1){
            System.out.println("Book does not exist!");
            return;
        }

        if (booksAvailableCopies[bookIndex] <= 0) {
            reservationsBooksIds[reservationsCount] = bookId;
            reservationsMembersIds[reservationsCount] = memberId;
            reservationsStatus[reservationsCount] = "active";
            reservationsCount++;
            System.out.println("Book is not available right now" );
            System.out.println("Reservation created for Member " + memberId);
            System.out.println("Reservation created for Book " + bookId);
            System.out.println("Reservation status " + reservationsStatus[reservationsCount - 1]);
            System.out.println("Reservation count " + reservationsCount);
            return;
        }
    }

    static void viewReservation(){}
//========Reservation Books==============

//========Generate Reports==============
    static void generateReport(){}
//========Generate Reports==============

    //What display on main menu
    static void displayMainMenu() {
       System.out.println("===LIBARARY SYSTEM MAIN MENU===");
       System.out.println("1. Manage Members");
       System.out.println("2. Manage Books");
       System.out.println("3. Process Borrowing Transaction");
       System.out.println("4. View Reservation");
       System.out.println("5. View Report");
       System.out.println("6. Exit");
       System.out.println("Enter Your choice: ");
    }

    //initialize Simple Data
    public static void initializeSimpleData() {
        // sample members
        membersIds[0] = 1; membersNames[0] = "Kamal Silva"; membersAddresses[0] = "Colombo"; membersPhoneNumbers[0] = "071567276";membersMembershipType[0] = "Regular";memberBorrowCount[0] = 3;
        membersIds[1] = 2;membersNames[1] = "Jagath Kumara";membersAddresses[1] = "Kalutara";membersPhoneNumbers[1] = "0712563738";membersMembershipType[1] = "Premium";memberBorrowCount[1] = 2;
        membersIds[2] = 3;membersNames[2] = "Nihal Bandara";membersAddresses[2] = "Kandy";membersPhoneNumbers[2] = "07723762763";membersMembershipType[2] = "Student";memberBorrowCount[2] = 1;
        membersIds[3] = 4;membersNames[3] = "Janaka Silva";membersAddresses[3] = "Galle";membersPhoneNumbers[3] = "03733763763";membersMembershipType[3] = "Premium";memberBorrowCount[3] = 3;
        membersIds[4] = 5;membersNames[4] = "Sumana Perera";membersAddresses[4] = "Anuradapura";membersPhoneNumbers[4] = "04743764763";membersMembershipType[4] = "Regular";memberBorrowCount[4] = 0;
        membersCount = 5;

        // sample books
        booksIds[0] = "P001";booksTitles[0] = "Laptop";booksAuthors[0] = "asj";booksGenres[0] = "romance";booksPublishYear[0] = 2000;booksTotalCopies[0]=1;booksAvailableCopies[0]=0;
        booksIds[1] = "P002";booksTitles[1] = "Mouse";booksAuthors[1] = "ddfdsf";booksGenres[1] = "thriller";booksPublishYear[1] = 2001;booksTotalCopies[1]=10;booksAvailableCopies[1]=5;
        booksIds[2] = "P003";booksTitles[2] = "USB Keyboard";booksAuthors[2] = "sdsf";booksGenres[2] = "fantacy";booksPublishYear[2] = 2002;booksTotalCopies[2]=20;booksAvailableCopies[2]=10;
        booksIds[3] = "P004";booksTitles[3] = "Printers";booksAuthors[3] = "fdgdf";booksGenres[3] = "horror";booksPublishYear[3] = 2003;booksTotalCopies[3]=40;booksAvailableCopies[3]=20;
        booksIds[4] = "P005";booksTitles[4] = "Monitor";booksAuthors[4] = "dfgdfg";booksGenres[4] = "anime";booksPublishYear[4] = 2004;booksTotalCopies[4]=34;booksAvailableCopies[4]=30;
        booksIds[5] = "P006";booksTitles[5] = "Bottle";booksAuthors[5] = "657";booksGenres[5] = "cartoon";booksPublishYear[5] = 2005;booksTotalCopies[5]=35;booksAvailableCopies[5]=40;
        booksIds[6] = "P007";booksTitles[6] = "Pen";booksAuthors[6] = "hjty";booksGenres[6] = "comedy";booksPublishYear[6] = 2006;booksTotalCopies[6]=36;booksAvailableCopies[6]=50;
        booksIds[7] = "P008";booksTitles[7] = "Stick";booksAuthors[7] = "bvn";booksGenres[7] = "cultural";booksPublishYear[7] = 2007;booksTotalCopies[7]=37;booksAvailableCopies[7]=6;
        booksIds[8] = "P009";booksTitles[8] = "Show";booksAuthors[8] = "iuk";booksGenres[8] = "romance";booksPublishYear[8] = 2008;booksTotalCopies[8]=38;booksAvailableCopies[8]=70;
        booksIds[9] = "P010";booksTitles[9] = "Flower";booksAuthors[9] = "78iuiy";booksGenres[9] = "fight";booksPublishYear[9] = 2009;booksTotalCopies[9]=39;booksAvailableCopies[9]=8;
        booksCount = 10;

        // sample borrow transaction
        transactionIds[0] = 1;transactionMembersIds[0] =1;transactionBooksIds[0] = "P003";transactionBorrowDate[0] = "2025-07-01";transactionDueDate[0] ="2025-07-15";transactionReturnDate[0]="2025-07-15";transactionFineAmount[0]=1;transactionActive[0]=true;
        transactionIds[1] = 2;transactionMembersIds[1] =3;transactionBooksIds[1] = "P006";transactionBorrowDate[1] = "2025-08-01";transactionDueDate[1] ="2025-08-15";transactionReturnDate[1]="2025-08-10";transactionFineAmount[1]=1;transactionActive[1]=false;
        transactionIds[2] = 3;transactionMembersIds[2] =5;transactionBooksIds[2] = "P003";transactionBorrowDate[2] = "2025-09-02";transactionDueDate[2] ="2025-09-25";transactionReturnDate[2]="2025-10-15";transactionFineAmount[1]=1;transactionActive[2]=true;
        borrowsCount = 3;

       System.out.println("sample data initialized!");
       System.out.println("5 members, 3 borrows and 10 books addedd!.");
    }
}
