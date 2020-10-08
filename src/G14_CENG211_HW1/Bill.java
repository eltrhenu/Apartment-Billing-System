package G14_CENG211_HW1;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Bill {
    private int billId;
    private int flatId;
    private int amount;
    private String type;
    private String paymentInfo;
    private Date deadlineDate;
    private Date lastUpdate;

    public Bill(int billId,int flatId , int amount, String type ,String paymentInfo, Date deadlineDate, Date lastUpdate){
        this.billId = billId;
        this.flatId = flatId;
        this.amount = amount;
        this.type = type;
        this.paymentInfo = paymentInfo;
        this.deadlineDate = deadlineDate;
        this.lastUpdate = lastUpdate ;

    }

    public Bill(){

        this.billId = 0;
        this.flatId = 0;
        this.amount = 0;
        this.type = null;
        this.paymentInfo = null;
        this.deadlineDate = null;
        this.lastUpdate = null ;

    }


    public void setBillId(int billId) {
        this.billId = billId;
    }

    public void setFlatId(int flatId) {
        this.flatId = flatId;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public int getBillId() {

        return billId;
    }

    public int getFlatId() {
        return flatId;
    }

    public int getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public String getPaymentInfo() {
        return paymentInfo;
    }
    

    
    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return
                "Bill Id = " + billId +
                ", Flat Id = " + flatId +
                ", Amount = " + amount +
                ", Type = '" + type + '\'' +
                ", Payment Info = '" + paymentInfo + '\'' +
                ", Deadline Date = " + format.format(deadlineDate) +
                ", Last Update = " + format.format(lastUpdate) +"\n";
    }

    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

}