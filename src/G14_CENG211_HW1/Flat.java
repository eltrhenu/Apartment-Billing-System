package G14_CENG211_HW1;

import java.util.ArrayList;



public class Flat {
    private int id;
    private int floorNumber;
    private int flatNum;
    private int numOfRooms;
    private int squareMeter;
    private ArrayList<Bill> listOfBills;

    public Flat(int id,int floorNumber,int flatNum,int numOfRooms,int squareMeter,ArrayList<Bill> listOfBills){
        this.id = id;
        this.floorNumber = floorNumber;
        this.flatNum = flatNum;
        this.numOfRooms = numOfRooms;
        this.squareMeter = squareMeter;
        this.listOfBills = listOfBills;

    }

    public Flat (ArrayList<Integer> intAr){
        this.id = intAr.get(0);
        this.floorNumber = intAr.get(1);
        this.flatNum = intAr.get(2);
        this.numOfRooms = intAr.get(3);
        this.squareMeter = intAr.get(4);
        this.listOfBills = null;
    }

    public void setListOfBills(ArrayList<Bill> listOfBills) {
        this.listOfBills = listOfBills;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public int getFlatNum() {
        return flatNum;
    }

    public int getNumOfRooms() {
        return numOfRooms;
    }

    public int getSquareMeter() {
        return squareMeter;
    }

    public ArrayList<Bill> getListOfBills() {
        return listOfBills;
    }

    public int getId() {
        return id;

    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", floorNumber=" + floorNumber +
                ", flatNum=" + flatNum +
                ", numOfRooms=" + numOfRooms +
                ", squareMeter=" + squareMeter +
                ", listOfBills=" + toStringBills(listOfBills)
                +"\n"
                ;
    }

    private String toStringBills(ArrayList<Bill> listOfBills ){
        ArrayList<String> bills = new ArrayList<String>();

        for (int i = 0; i < listOfBills.size() ; i++) {

                bills.add(listOfBills.get(i).toString());

            }

        return bills.toString();
    }
}
