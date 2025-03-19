package Hotel_Management_System;
//Assignment001

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hotel_System {

    static int[][] hotels;

    static Scanner keyboard = new Scanner(System.in);

    static final String NUMBER_REGEX = "\\d+";

    static int invalidInput() {
        Pattern pat = Pattern.compile(NUMBER_REGEX);
        while (true) {
            String input = keyboard.nextLine();
            Matcher mat = pat.matcher(input);

            return mat.matches() ? Integer.parseInt(input) : invalidInput();
        }
    }

    static void setupHotel() {
//    setup Bus
        System.out.println("--------- Setting up Hotel ---------");
        System.out.print("Enter number of Hotel : ");
        int num_ho = invalidInput();
        System.out.print("Enter number of Rooms per Hotel : ");
        int room_per_ho = invalidInput();
        hotels = new int[num_ho][room_per_ho];
    }

    static void menu() {
        //Display Hotel
        while (true) {
            System.out.println("--------- Hotel Management System ---------");
            System.out.println("1.Check Hotel");
            System.out.println("2.Book Room");
            System.out.println("3.Cancel Booking");
            System.out.println("4.Reset Hotel");
            System.out.println("5.Exit");
            System.out.println("--------------------------------------------");
            System.out.print("->Choose option(1-5) : ");
            int choice = invalidInput();
            switch (choice) {
                case 1: {
                    checkHotel();
                    System.out.print("-> Enter 0 to back or Hotel id to see detail : ");
                    int id = invalidInput();
                    if (id == 0) {
                        menu();
                    } else {
                        seeDetails(hotels, id);
                    }
                }
                break;
                case 2: {
                    System.out.println("--------- Book Room ---------");
                    System.out.print("Enter Hotel id : ");
                    int id = invalidInput();
                    System.out.print("Enter room number to book : ");
                    int room = invalidInput();
                    bookRoom(hotels, room, id);
                }
                break;
                case 3: {
                    System.out.println("--------- Cancel Booking ---------");
                    System.out.print("Enter Hotel id : ");
                    int id = invalidInput();
                    System.out.print("Enter room number to book : ");
                    int room = invalidInput();
                    cancelBooking(hotels, room, id);
                }
                break;
                case 4: {
                    System.out.println("--------- Reset Hotel ---------");
                    resetHotel();
                }
                break;
                case 5: {
                    System.out.println("\033[1;32mGoodbye!\033[0m");
                    System.exit(0);
                }
                break;
                default:
                    System.out.println("\033[1;32mInvalid option. Try again.\033[0m");
            }

        }

    }

    static void checkHotel() {
        System.out.println("--------- Display All Hotel Information ---------");
        System.out.println("Id \t\t Rooms \t\t Available \t\t Unavailable");
        for (int i = 0; i < hotels.length; i++) {
            int available = 0, unavailable = 0;
            for (int j = 0; j < hotels[i].length; j++) {
                if (hotels[i][j] == 0) { //prus ah hotel[i][j] = 0 rhot ng prus vea ot mean value te
                    available++;
                } else {
                    unavailable++;
                }
            }
            System.out.println((i + 1) + "\t\t\t" + hotels[i].length + "\t\t\t" + available + "\t\t\t\t" + unavailable);
        }
    }

    static void seeDetails(int[][] hotel, int id) {

        for (int j = 0; j < hotel[id - 1].length; j++) {
            System.out.print((hotel[id - 1][j] == 0) ? "(+)" : "(-)");
            System.out.print((j + 1) + "\t");
            if (j == 4) {
                System.out.println();
            }
        }
        System.out.println();
    }

    static void bookRoom(int[][] hotel, int room, int id) {
        if (hotel[id - 1][room - 1] == 0) {
            hotel[id - 1][room - 1] = 1;
            System.out.println("\033[1;32mRoom book successfully!\033[0m");
        } else {
//            hotel[id - 1][room - 1] = 1;
            System.out.println("\033[1;32mOpp!! This room has been booked already!\033[0m");
        }
    }

    static void cancelBooking(int[][] hotel, int room, int id) {
        if (hotel[id - 1][room - 1] == 0) {
            hotel[id - 1][room - 1] = 0;
            System.out.println("\033[1;32mYou have not booked this room yet!\033[0m");
        } else {
            hotel[id - 1][room - 1] = 0;
            System.out.println("\033[1;32mYou canceled successfully!\033[0m");
        }
    }

    static void resetHotel() {
        for (int i = 0; i < hotels.length; i++) {
            for (int j = 0; j < hotels[i].length; j++) {
                if (hotels[i][j] != 0) {
                    hotels[i][j] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        setupHotel();
        menu();

    }
}