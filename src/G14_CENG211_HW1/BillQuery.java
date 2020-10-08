package G14_CENG211_HW1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;



public class BillQuery {

    //Total amount of unpaid bills.
    public int unpaidBills(Apartment apartment) {
        Flat[][] temp = apartment.getApartment();
        int counter = 0;
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[i].length; j++) {
                ArrayList<Bill> billList = temp[i][j].getListOfBills();
                for (int k = 0; k < billList.size(); k++) {
                    if (billList.get(k).getPaymentInfo().equals("false")) {
                        counter += billList.get(k).getAmount();
                    }
                }

            }
        }
        return counter;
    }

    //Total amount of unpaid bills of a certain bill type .
    public  int unpaidCertainType(String type, Apartment apartment) {
        Flat[][] temp = apartment.getApartment();
        int counter = 0;
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[i].length; j++) {
                ArrayList<Bill> billList = temp[i][j].getListOfBills();
                for (int k = 0; k < billList.size(); k++) {
                    if (billList.get(k).getPaymentInfo().equals("false")) {
                        if (billList.get(k).getType().equals(type)) {
                            counter += billList.get(k).getAmount();

                        }
                    }
                }

            }
        }
        return counter;


    }

    //Total bill amount of a certain floor .
    public  int unpaidCertainFloor(String floor, Apartment apartment) {
        int N = Integer.parseInt(floor);
        Flat[][] temp = apartment.getApartment();
        int counter = 0;
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[i].length; j++) {
                ArrayList<Bill> billList = temp[i][j].getListOfBills();
                for (int k = 0; k < billList.size(); k++) {
                    if (temp[i][j].getFloorNumber() == N) {
                        counter += billList.get(k).getAmount();
                    }
                }

            }
        }
        return counter;

    }

    //List of the unpaid bills with the information of the remaining time (in days) before their
    //deadlines.
    public  String[] unpaidRemainingTime(Date date, Apartment apartment) {

        Flat[][] temp = apartment.getApartment();
        ArrayList<Bill> unpaid = new ArrayList<Bill>();
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[i].length; j++) {
                ArrayList<Bill> billList = temp[i][j].getListOfBills();
                for (int k = 0; k < billList.size(); k++) {
                    if (billList.get(k).getPaymentInfo().equals("false")) {
                        unpaid.add(billList.get(k));
                    }
                }
            }
        }

        String[] unpaidString = new String[unpaid.size()];
        long difference = 0;
        for (int l = 0; l < unpaid.size(); l++) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            difference = unpaid.get(l).getDeadlineDate().getTime() - date.getTime();
            if (TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS) < 0) {
                unpaidString[l] = unpaid.get(l).toString() + " Deadline date has passed.";
            } else {
                unpaidString[l] = unpaid.get(l).toString() + " Remaining Time = " + TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS) + " days.";
            }

        }

        return unpaidString;

    }

    //Total amount and number of paid bills before a certain date .
    //from Scanner).
    public  int[] paidCertainDate(String date, Apartment apartment) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date inputDate = format.parse(date);
        Flat[][] temp = apartment.getApartment();
        int numOfPaidBills = 0;
        int totalPaidBills = 0;
        int[] numAndTotal = new int[2];
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[i].length; j++) {
                ArrayList<Bill> billList = temp[i][j].getListOfBills();
                for (int k = 0; k < billList.size(); k++) {
                    if (billList.get(k).getPaymentInfo().equals("true")) {
                        if (billList.get(k).getLastUpdate().before(inputDate)) {
                            numOfPaidBills++;
                            totalPaidBills += billList.get(k).getAmount();
                        }
                    }
                }
            }
        }
        numAndTotal[0] = totalPaidBills;
        numAndTotal[1] = numOfPaidBills;
        return numAndTotal;

    }

    //Total amount and number of unpaid bills of a certain type that passed deadline.
    //by taking input from Scanner).
    public  int[] unpaidCertainTypeLate(String type, Apartment apartment) {
        Flat[][] temp = apartment.getApartment();
        int[] numAndTotal = new int[2];
        int total = 0;
        int count = 0;
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[i].length; j++) {
                ArrayList<Bill> billList = temp[i][j].getListOfBills();
                for (int k = 0; k < billList.size(); k++) {
                    Bill bill = billList.get(k);
                    if (bill.getPaymentInfo().equals("false")) {
                        if (bill.getType().equals(type)) {
                            Date maxDate = findLastUpdate(billList);
                            if (bill.getDeadlineDate().after(maxDate)) {
                                total += bill.getAmount();
                                count++;
                            }
                        }
                    }
                }
            }
        }
        numAndTotal[0] = total;
        numAndTotal[1] = count;
        return numAndTotal;
    }


    // To find the last update date.
    private  Date findLastUpdate(ArrayList<Bill> bills) {
        ArrayList<Date> dates = new ArrayList<Date>();
        for (int i = 0; i < bills.size(); i++) {
            dates.add(bills.get(i).getLastUpdate());


        }
        Date maxDate;
        maxDate = Collections.max(dates);
        return maxDate;

    }

    //Average total amount of bills of N room flats .
    public  int averageCertainRoom(String num, Apartment apartment) {
        int N = Integer.parseInt(num);
        Flat[][] temp = apartment.getApartment();
        int total = 0;
        int count = 0;

        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[i].length; j++) {
                ArrayList<Bill> billList = temp[i][j].getListOfBills();
                if (temp[i][j].getNumOfRooms() == N) {
                    count++;
                    for (int k = 0; k < billList.size(); k++) {
                        total += billList.get(k).getAmount();
                    }
                }
            }
        }

        return total / count;

    }

    //Average total amount of bills of flats with square meter greater than N .
    public int averageSquare(String square, Apartment apartment) {
        int N = Integer.parseInt(square);
        Flat[][] temp = apartment.getApartment();
        int total = 0;
        int count = 0;

        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[i].length; j++) {
                ArrayList<Bill> billList = temp[i][j].getListOfBills();
                if (temp[i][j].getSquareMeter() > N) {
                    count++;
                    for (int k = 0; k < billList.size(); k++) {
                        total += billList.get(k).getAmount();
                    }
                }
            }
        }
        return total / count;
    }
    // to change the PaymentInfo of a bill, list all bills, list all flats, execute queries
    public  boolean changePaymentInfo(Apartment apartment, String billId) {
        int bill = Integer.parseInt(billId);
        Bill[] bills = apartment.getBills();
        for (int i = 0; i < bills.length; i++) {
            if (bills[i].getBillId() == bill) {
            	if(bills[i].getPaymentInfo().equals("true")) {
            		return false;
            	}
                bills[i].setPaymentInfo("true");
                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                Date date = new Date();
                bills[i].setLastUpdate(date);
                
            }
        }
        return true;
    }
    // list all the flats and the bills of the flats by an apartment object.
    public  String displayBills(Apartment apartment) {
        Flat[][] flats = apartment.getApartment();

        String a = null;
        for (int i = 0; i < flats.length; i++) {

            for (int j = 0; j < flats[i].length; j++) {
                a = flats.toString() + apartment.toString();
            }
        }
        return a;
    }
}



