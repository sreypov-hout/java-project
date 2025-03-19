package Library_Management_System;

import java.util.*;
import java.util.Scanner;
import java.util.regex.*;

import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.Table;

class Library_Management {

    static boolean status = true;
    static Book[] books = new Book[100];
    static int bookCount;
    static Scanner sc = new Scanner(System.in);
    static String invalidYearActive() {
        Pattern y = Pattern.compile("^(\\d{4})-(\\d{4})$");

        while (true) {
            String inputYear = sc.nextLine().trim();
            Matcher m_y = y.matcher(inputYear);
            if (m_y.matches()) {
                return inputYear;
            } else {
                System.out.print("=> Wrong!! Plz Enter Author Year Active Again: ");
            }
        }
    }
    static String invalidtext() {
        Pattern t = Pattern.compile("^[A-Za-z ]+$");

        while (true) {
            String inputText = sc.nextLine().trim();
            Matcher m_T = t.matcher(inputText);
            if (m_T.matches()) {
                return inputText;
            } else {
                System.out.print("=> Plz Try Again : ");
            }
        }
    }
    static String invalidYearPublisher() {

        Pattern y_p = Pattern.compile("^(\\d{4})$");
        while (true) {
            String inputYearPub = sc.nextLine();
            Matcher m_y = y_p.matcher(inputYearPub);
            if (m_y.matches()) {
                return inputYearPub;
            }else{
                System.out.print("=> Wrong!! Plz Enter Year Published Again: ");
            }
        }

    }
    static int invalidNumber(){
        Pattern p = Pattern.compile("\\d+");
        while (true) {
            String inputnum = sc.nextLine();
            Matcher mat = p.matcher(inputnum);

            return mat.matches() ? Integer.parseInt(inputnum) : invalidNumber();
        }
    }
    static String libraryName;
    static String libraryAddress;
    public static void SetupLibrary() {
        System.out.println("======== SET UP LIBRARY =======");
        System.out.print("=> Enter Library's Name : ");
        libraryName = invalidtext();
        System.out.print("=> Enter Library's Address : ");
        libraryAddress = invalidtext();

        Date date  = new Date();
        System.out.println("\""+libraryName + "\" Library is already created in \""+ libraryAddress +"\" address successfully on "+date);
    }
    public static void Display() {
        while (true) {
            System.out.println("========= "+libraryName+","+libraryAddress+" ========");
            System.out.println("1. Add Book");
            System.out.println("2. Show All Books");
            System.out.println("3. Show Available Books");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");

            System.out.print("=>Choose an option(1-6): ");
            int choice = invalidNumber();
            switch (choice) {
                case 1:
                    addbook();
                    break;
                case 2:
                    showAllBook();
                    break;
                    case 3:
                    showAvailableBook();
                    break;
                case 4:
                    borrowBook();
                    break;
                case 5:
                    returnBook();
                    break;
                case 6: {
                    System.out.println("\033[1;32mGood luck! byee...\033[0m");
                    System.exit(0);
                }
                break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }
    public static void addbook() {
        System.out.println("==========ADD BOOK INFO=========");
        System.out.print("=> Enter Book's Name : ");
        String title = sc.nextLine();
        System.out.print("=> Enter Book's Author Name : ");
        String author = sc.nextLine();
        System.out.print("=> Enter Author Year Active : ");
        String activeyear = invalidYearActive();
        System.out.print("=> Enter Year Published : ");
        String publisher = invalidYearPublisher();

        books[bookCount++] = new Book(title,author,activeyear,publisher,status);
        System.out.println("\033[1;32mBook is added succesfully.\033[0m");
//        System.out.println(books[bookCount-1]);
    }
    public static void showAllBook() {
        System.out.println("========== ALL BOOKS INFO =========");

        if (bookCount == 0) {
            System.out.println("\033[1;32mNo Books Available.\033[0m");
            return;
        }

        Table t = new Table(5, BorderStyle.UNICODE_BOX);

        t.addCell("Book ID");
        t.addCell("Title");
        t.addCell("Author");
        t.addCell("Publisher");
        t.addCell("Status");

        for (int i = 0; i < bookCount; i++){
            if(!(books[i] == null)){
                String authorActiveYear = books[i].getAuthor() + " (" + books[i].getActiveyear() + ")";

                t.addCell(String.valueOf((books[i].getId())));
                t.addCell(String.valueOf((books[i].getTitle())));
                t.addCell(authorActiveYear);
                t.addCell(String.valueOf(books[i].getPublisher()));
                t.addCell(books[i].getStatus() ? "Available" : "Unavailable");
            }
        }
        System.out.println(t.render());
        System.out.println("Press 'Enter' to continue...");
        sc.nextLine();
    }
    public static void showAvailableBook() {
        System.out.println("========== AVAILABLE BOOKS INFO ==========");
        boolean availableCound = false;
        Table table = new Table(5, BorderStyle.UNICODE_BOX);
        table.addCell("Book ID");
        table.addCell("Title");
        table.addCell("Author");
        table.addCell("Publisher");
        table.addCell("Status");

        for (int i = 0; i < bookCount; i++) {
            if (books[i] != null && books[i].getStatus()) {
                String authorActiveYear = books[i].getAuthor() + " (" + books[i].getActiveyear() + ")";

                table.addCell(String.valueOf(books[i].getId()));
                table.addCell(books[i].getTitle());
                table.addCell(authorActiveYear);
                table.addCell(String.valueOf(books[i].getPublisher()));
                table.addCell("Available");

                availableCound = true;
            }
        }
        if (!availableCound) {
            System.out.println("\033[1;32mAvailable books not found!\033[0m");
        } else {
            System.out.println(table.render());
        }
    }
    public static void borrowBook() {
        System.out.println("========= BORROW BOOK INFO =========");
        System.out.print("=> Enter Book ID to Borrow : ");
        int bookId = invalidNumber();

        boolean borrow = false;
        for (int i = 0; i < bookCount; i++) {
            if (books[i] != null && books[i].getId() == bookId) {
                if (!books[i].getStatus()) {
                    System.out.println("\033[1;32mSorry, this book is already borrowed.\033[0m");
                    return;
                }
                books[i].setStatus(false);
                System.out.println("Book ID : " + books[i].getId());
                System.out.println("Book Title : " + books[i].getTitle());
                System.out.println("Book Author : " + books[i].getAuthor() + " (" + books[i].getActiveyear() + ")");
                System.out.println("Published Year : " + books[i].getPublisher());
                System.out.println("\033[1;32mBook is borrowed successfully...\033[0m");
                borrow = true;
                break;
            }
        }
        if (!borrow) {
            System.out.println("\033[1;32mBook ID : " + bookId + " not Exist...\033[0m");
        }
    }
    public static void returnBook() {
        System.out.println("========= RETURN BOOK INFO =========");
        System.out.print("=> Enter Book ID to Borrow : ");
        int bookId = invalidNumber();


        boolean returnbook = false;
        for (int i = 0; i < bookCount; i++) {
            if (books[i] != null && books[i].getId() == bookId) {
                if (books[i].getStatus()) {
                    System.out.println("\033[1;32mThis book is already available. No need to return.\033[0m");
                    return;
                }
                books[i].setStatus(true);
                System.out.println("Book ID : " + books[i].getId());
                System.out.println("Book Title : " + books[i].getTitle());
                System.out.println("Book Author : " + books[i].getAuthor() + " (" + books[i].getActiveyear() + ")");
                System.out.println("Published Year : " + books[i].getPublisher());
                System.out.println("\033[1;32mThe book returned successfully!\033[0m");
                returnbook = true;
                break;
            }
        }
        if (!returnbook) {
            System.out.println("\033[1;32mBook ID : "+bookId+" Is failed to return.... \033[0m");
        }
    }

    public static void main( String[] args ) {

        SetupLibrary();
        Display();

    }
}
