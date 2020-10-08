package G14_CENG211_HW1;

import java.util.ArrayList;
import java.util.Arrays;

public class Apartment {

    //private statement
    private Flat[][] apartment;
    private Bill[] bills;
    private FileInputOutput reader;

  
  

    public Bill[] getBills() {
        return bills;
    }

    public void setApartment(Flat[][] apartment) {

        this.apartment = apartment;
    }

    public Apartment(){
        this.apartment = null;
        this.bills = null;
        this.reader = null;
    }

    public Apartment(String fileFlats,String fileBills){
        this.reader = new FileInputOutput();
        this.apartment = this.reader.readFlat(fileFlats);
        this.bills = this.reader.readBill(fileBills);
    }



    public String toString() {
        ArrayList<String> flats = new ArrayList<String>();

        for (int i = 0; i < apartment.length ; i++) {
            for (int j = 0; j < apartment[i].length ; j++) {
                flats.add(apartment[i][j].toString());

            }

        }
        return


                "Apartment=" + flats.toString() +

                '}';
    }

	public Flat[][] getApartment() {
	      return apartment;
	    
	}


}
