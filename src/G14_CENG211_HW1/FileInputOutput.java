package G14_CENG211_HW1;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.StringTokenizer;

public class FileInputOutput {

    Apartment apartment  = new Apartment();;
    // to read file and create flat object
    public  Flat[][] readFlat(String file){

        String temp;
        int count = 0;
        try{
            BufferedReader countFile = new BufferedReader (new FileReader(file));
            while (countFile.readLine() != null){
                count +=  1;
            }
            countFile.close();
            Flat[] flats = new Flat[count];
            BufferedReader flatFile = new BufferedReader(new FileReader(file));
            int j =0;
            while ( (temp = flatFile.readLine()) != null){
                //String[] tempArray = temp.split(",");
            	StringTokenizer tempLine = new StringTokenizer(temp,",");
                ArrayList<Integer> intAr = new ArrayList<Integer>();
                while(tempLine.hasMoreTokens()){
                    intAr.add(Integer.parseInt(tempLine.nextToken()));
                }

                Flat flat = new Flat(intAr);
                flats[j]= flat;
                j++;
            }
            int floorNum =findFloor(flats);
            int flatNum =findFlat(floorNum, flats);
            flatFile.close();
            return toApartment(floorNum,flatNum,flats);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    //to create apartment object
    private Flat[][] toApartment(int floor,int flat, Flat[] flatArray){
    	
        Flat[][] apart = new Flat[floor][flat];
        int k = 0;
        for (int i =0;i<floor;i++){
            for(int j=0;j<flat;j++){
                apart[i][j]= flatArray[k];
                k++;
            }
        }
        apartment.setApartment(apart);
        return apart;
    }
    // to find floor number for two dimensional array
    private int findFloor(Flat[] flatArray){
        int[] floorNums =  new int[flatArray.length];
        for (int i=0; i<flatArray.length;i++){
            floorNums[i] = flatArray[i].getFloorNumber();
        }

        Arrays.sort(floorNums);
        int max = floorNums[floorNums.length-1];
        return max;

    }
	// to find flat number for two dimensional array
    private int findFlat(int floorNum,Flat[] flatArray){
        int flatNum = flatArray.length / floorNum;
        return flatNum;
    }
    // to read file and create bill object
    public Bill[] readBill(String file){
        String temp;
        int count = 0;
        try {
            BufferedReader countFile = new BufferedReader(new FileReader(file));
            while (countFile.readLine()!=null) {
                count += 1;
            }
            countFile.close();
            Bill[] billList = new Bill[count];
            BufferedReader billFile = new BufferedReader(new FileReader(file));
            int j = 0;
            while ((temp =billFile.readLine())!=null) {

                Bill aBill = new Bill();
                String[] tempArray = temp.split(",");
                aBill.setBillId(Integer.parseInt(tempArray[0]));
                aBill.setFlatId(Integer.parseInt(tempArray[1]));
                aBill.setAmount(Integer.parseInt(tempArray[2]));
                aBill.setType(tempArray[3]);
                aBill.setPaymentInfo(tempArray[4]);

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                Date deadlineDate = format.parse(tempArray[5]);
                Date updateDate = format.parse(tempArray[6]);
                aBill.setDeadlineDate(deadlineDate);
                aBill.setLastUpdate(updateDate);

                billList[j] = aBill;
                j++;
            }
            billsOfFlat(billList);
            return billList;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }




    // to set flats billsOfFlat 
    private void billsOfFlat(Bill[] billList){
            Flat[][] flats = apartment.getApartment();
            for (int j = 0; j < flats.length; j++) {
                for (int i = 0; i < flats[j].length; i++) {
                    ArrayList<Bill> listOfBills = new ArrayList();
                    for (int k = 0; k < billList.length; k++) {
                        if (flats[j][i].getId() == billList[k].getFlatId()) {
                            listOfBills.add(billList[k]);
                        }
                    }
                    flats[j][i].setListOfBills(listOfBills);
                }

            }
        }
    /*private ArrayList<String> findBill(ArrayList<Bill> billList) {
    	ArrayList<String> temp = new ArrayList<String>();
    	for (int i = 0; i<billList.size();i++) {
    		temp.add(toString(billList.get(i)));
    	}
    	return temp;
    }*/
    public String toString(Bill bill) {
    	 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
         return
                 bill.getBillId() +
                 "," + bill.getFlatId() +
                 "," + bill.getAmount() +
                 "," + bill.getType() + 
                 "," + bill.getPaymentInfo() +
                 "," + format.format(bill.getDeadlineDate()) +
                 "," + format.format(bill.getLastUpdate()) ;
    }
    
    // to write changed bill info to file
    public void writeBill(String file, Apartment apartment){
        try {
            Flat[][] flats = apartment.getApartment();
            BufferedWriter writeFile = new BufferedWriter(new FileWriter(file));
            for (int j = 0; j < flats.length; j++) {
                for (int i = 0; i < flats[j].length; i++) {
                    for (int k = 0; k < flats[j][i].getListOfBills().size(); k++) {
                    	
                        writeFile.write(toString(flats[j][i].getListOfBills().get(k)));
                        writeFile.newLine();
                    }
                }
            }
            writeFile.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }

}

