package G14_CENG211_HW1;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

//to execute all billquery class methods
public class QueryExecutions {
    private  Scanner in = new Scanner(System.in);
    
    public  void findUnpaidBills(Apartment apartment){
        BillQuery query = new BillQuery();
        int temp = query.unpaidBills(apartment);
        System.out.println();
        System.out.print("The total amount of unpaid bills is "+ temp + ".");
    }
    public  void findUnpaidCertainType(Apartment apartment){
        BillQuery query = new BillQuery();
        System.out.println();
        System.out.print("Please write a type for bill (water,electric,heating,cleaning)");
        boolean check = false;

        while(!check) {
            String billType = in.nextLine();
            billType = billType.toLowerCase();
            if (!isValidType(billType)) {
                System.out.println("Please enter a valid type (water,electric,heating,cleaning)");
                check = false;
            }
            else{
                int temp = query.unpaidCertainType(billType,apartment);
                System.out.println();
                System.out.print("The total amount of unpaid " + billType + " bills is "+ temp + ".");
                check = true;
            }
        }
    }
    public  void findUnpaidCertainFloor(Apartment apartment){
        BillQuery query = new BillQuery();
        System.out.println();
        System.out.print("Please write a floor number for bill (1,2,3)");
        boolean check = false;

        while(!check) {
            String floorNum = in.nextLine();
            if (!isInteger(floorNum) ) {
                System.out.println("Please enter a valid floor number. (1,2,3)");
                check = false;
            }
            else if(!isValidFloor(floorNum,apartment)){
                System.out.println("Please enter a valid floor number. (1,2,3)");
                check = false;
            }
            else{
                int temp = query.unpaidCertainFloor(floorNum,apartment);
                System.out.println();
                System.out.print("The total amount of unpaid bills for " + floorNum +"th floor is " + temp + ".");
                check = true;
            }
        }
    }

    public  void findUnpaidRemainingTime(Apartment apartment) throws ParseException {
        BillQuery query = new BillQuery();
        System.out.println();


        
        Date date = new Date();


        String[] temp = query.unpaidRemainingTime(date,apartment);
        System.out.print("The list of bills:\n");
        for (int i = 0; i < temp.length; i++) {
            System.out.println(temp[i]);
        }
        System.out.println();



    }
    public  void findPaidCertainDate(Apartment apartment) throws ParseException {
        BillQuery query = new BillQuery();
        System.out.println();
        System.out.print("Please write a date for bills to check in yyyy-MM-dd format.");
        boolean check = false;

        while(!check) {
            String date = in.nextLine();
            if ( !checkDate(date)) {
                System.out.println("Please enter a valid date  in yyyy-MM-dd format.");
                check = false;
            }
            else{
                int[] temp = query.paidCertainDate(date,apartment);

                System.out.println();
                System.out.print("The total amount of paid bills  " + temp[0] +" of " + temp[1] +  " bills.");
                check = true;
            }
        }

    }
    // to check bill type is valid
    private  boolean isValidType(String billType){
        ArrayList<String> types = new ArrayList<String>();
        types.add("water");
        types.add("electric");
        types.add("cleaning");
        types.add("heating");
        if (!(types.contains(billType))){
            return false;
        }
        return true;

    }
    // to check if input is valid
    private  boolean isValidRoom(String roomNum,Apartment apartment){
        ArrayList<String> types = new ArrayList<String>();
        Flat[][] temp = apartment.getApartment();
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j <temp[i].length ; j++) {
                String strRoom = Integer.toString(temp[i][j].getNumOfRooms());
                types.add(strRoom);
            }
        }

        if (!(types.contains(roomNum))){
            return false;
        }
        return true;

    }
    // to check if input is valid
    private  boolean isValidFloor(String floorNum,Apartment apartment){
        ArrayList<String> types = new ArrayList<String>();
        Flat[][] temp = apartment.getApartment();
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j <temp[i].length ; j++) {
                String strFloor = Integer.toString(temp[i][j].getFloorNumber());
                types.add(strFloor);
            }
        }

        if (!(types.contains(floorNum))){
            return false;
        }
        return true;

    }
    public  void findUnpaidCertainTypeLate(Apartment apartment){
        BillQuery query = new BillQuery();
        System.out.println();
        System.out.print("Please write a type for bill (water,electric,heating,cleaning)");
        boolean check = false;

        while(!check) {
            String billType = in.nextLine();
            billType = billType.toLowerCase();
            if (!isValidType(billType)) {
                System.out.println("Please enter a valid type (water,electric,heating,cleaning)");
                check = false;
            }
            else{
                int[] temp = query.unpaidCertainTypeLate(billType,apartment);

                System.out.println();
                if (temp[0]==0 && temp[0] == 0){
                    System.out.println("There is no bill for required type.");
                }
                else {
                    System.out.print("The total amount of paid bills  " + temp[0] + " of " + temp[1] + " bills.");
                }
                check = true;
            }
        }

    }
    public  void findAverageCertainRoom(Apartment apartment){
        BillQuery query = new BillQuery();
        System.out.println();
        System.out.print("Please write a room number between 1 and 3.");
        boolean check = false;

        while(!check) {
            String num = in.nextLine();
            if (!isInteger(num)) {
                System.out.println("Please enter a valid room.");
                check = false;
            }
            else if(!isValidRoom(num,apartment)){
                System.out.println("Please enter a valid room.");
                check = false;

            }
            else{
                int temp = query.averageCertainRoom(num,apartment);
                System.out.println();
                System.out.print("Average total amount of bills of " + num + " room flats is " + temp + ".");
                check = true;
            }
        }
    }
    // to check if input is valid
    private  boolean isValidSquare(String square, Apartment apartment){
  
    	int sqr = Integer.parseInt(square);
        if (0<= sqr && sqr <= 180){
            return true;
        }
        return false;

    }
    public  void findAverageSquare(Apartment apartment){
        BillQuery query = new BillQuery();
        System.out.println();
        System.out.print("Please write a square meter from this list (150,110,50,70,180,100):");
        boolean check = false;

        while(!check) {
            String square = in.nextLine();
            if (!isInteger(square)) {
                System.out.println("Please enter a valid square meter:");
                check = false;
            }
            else if(!isValidSquare(square,apartment)){
                System.out.println("Please enter a valid square meter:");
                check = false;
            }
            else{
                int temp = query.averageSquare(square,apartment);

                System.out.println();
                System.out.print("Average total amount of bills of flats square meter greater than " + square + " is " + temp + ".");
                check = true;
            }
        }
    }

    public  void doChangePaymentInfo(Apartment apartment){
        BillQuery query = new BillQuery();
        FileInputOutput fileIO = new FileInputOutput(); 
        System.out.println();
        System.out.print("Please write the bill ID that you had paid:");
        boolean check = false;
        while(!check) {
            String billId = in.nextLine();
            if (!isInteger(billId)) {
                System.out.println("Please enter a valid bill ID:");
                check = false;
            }
            else if(!isValidBillId(billId,apartment)) {
                System.out.println("Please enter a valid bill ID:");
                check = false;

            }

            else {
                boolean isTrue = query.changePaymentInfo(apartment, billId);
               
                if(isTrue) {
                	fileIO.writeBill("HW1-BillingInfo-2018-10-01.csv",apartment);
	                System.out.println("Bill payment info has changed.");
	                
                }
                else {
                	 System.out.println("That bill already had paid.");
                	 
                }
                check = true;
                
            }
        }

    }
    // to check if input is valid
    private  boolean isValidBillId(String id,Apartment apartment){
        Bill[] bills = apartment.getBills();
        int temp = Integer.parseInt(id);
        for (int i = 0; i < bills.length ; i++) {
          if(bills[i].getBillId() == temp) {
              return true;

          }
        }
        return false;
    }
    // to check if input is valid	
    private  boolean checkDate (String date){
        String[] tempArray = date.split("-");
        int len =tempArray.length;
        int[] intAr = new int[len];
        int i =0;
        int j =len-1;
       
        boolean check = true;
		if(len!=3) {
		        return false;	
		        }
        while (i<=j) {
            if(isInteger(tempArray[i])){
                intAr[i] = Integer.parseInt(tempArray[i]);

                i++;
                check = true;

            }

            else{

                check = false;
            }
        }
        if(!(isValidMonth(intAr[1])&&isValidDay(intAr[2])&&isValidYear(intAr[0]))){

            check = false;

        }
        return check;
    }

    public  void toDisplayBills(Apartment apartment){
        BillQuery query = new BillQuery();
        System.out.println();
        System.out.println(query.displayBills(apartment));


    }
    // to check if year is valid
    private boolean isValidYear(int year) {
    	if(year == 2018) {
    		return true;
    	}
    	return false;
    }
    // to check if month is valid
    private  boolean isValidMonth(int month){
        if((1 <= month) &&(month <= 12)){
            return true;
        }
        
        return false;
        
    }
    // to check if day is valid
    private  boolean isValidDay(int day){
        if((1 <= day) &&(day <= 31)){
            return true;
        }
  
        return false;
        
    }
    // to check if input is integer
    private  boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        if (str.isEmpty()) {
            return false;
        }
        if(str.charAt(0)=='-'){
            return false;

        }
        for (int i =0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }

        }
        return true;
    }

}

