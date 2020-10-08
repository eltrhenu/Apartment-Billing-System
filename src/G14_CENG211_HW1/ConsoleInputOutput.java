package G14_CENG211_HW1;
import java.text.ParseException;
import java.util.Scanner;

public class ConsoleInputOutput {

    private static Apartment apartment = new Apartment("HW1-ApartmentInfo.csv","HW1-BillingInfo-2018-10-01.csv");

    private static Scanner in = new Scanner(System.in);
    //To display Menu
    public static void MenuStart() {
    	
        System.out.println();
        System.out.println("[1] Total Amount of Unpaid Bills");
        System.out.println("[2] Total amount of unpaid bills of a certain bill type");
        System.out.println("[3] Total bill amount of a certain floor");
        System.out.println("[4] List of the unpaid bills with the information of the remaining time (in days) before their" +
                "deadlines");
        System.out.println("[5] Total amount and number of paid bills before a certain date ");
        System.out.println("[6] Total amount and number of unpaid bills of a certain type that passed deadline (to be executed ");
        System.out.println("[7] Average total amount of bills of N room flats ");
        System.out.println("[8] Average total amount of bills of flats with square meter greater than N ");
        System.out.println("[9] Change Payment Info if you had paid the bill.");
        System.out.println("[10] Show the bills list.");
        System.out.println("[11] Quit.");
        System.out.println();
        System.out.print(">> Please select a command to execute: ");
    }
    // to execute billquery methods
    public static boolean getAndExecuteMainMenu() throws  ParseException {
        QueryExecutions execution = new QueryExecutions();
        int cmdId = in.nextInt();
        in.nextLine();
        switch (cmdId) {
            case 1:
                execution.findUnpaidBills(apartment);
                return true;
            case 2:
                execution.findUnpaidCertainType(apartment);
                return true;
            case 3:
                execution.findUnpaidCertainFloor(apartment);
                return true;
            case 4:
               execution.findUnpaidRemainingTime(apartment);
                return true;
            case 5:
                execution.findPaidCertainDate(apartment);
                return true;
            case 6:
                execution.findUnpaidCertainTypeLate(apartment);
                return true;
            case 7:
                execution.findAverageCertainRoom(apartment);
                return true;
            case 8:
                execution.findAverageSquare(apartment);
                return true;
            case 9:
                execution.doChangePaymentInfo(apartment);
                return true;
            case 10:
                execution.toDisplayBills(apartment);
                return true;
            case 11:
            	System.out.println();
            	System.out.println("GoodBye.");
                return false;
            default:
                System.err.println();
                System.err.println("I do not know what to do for command id " + cmdId);
                System.err.println("Please try again!");
                System.err.println();
                return true;
        }
    }
}
