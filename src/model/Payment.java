package model;

public class Payment {

    private int number_days;
    private double total_Cost;
    private String payment_Type;
    private String card_No;
    private double room_Price;
    private double extra_fee;
    private double refund;

    public Payment() { }

    public Payment(int number_days, double total_Cost, String payment_Type, String card_No, double room_Price)
    {

        this.card_No = card_No;
        this.number_days = number_days;
        this.total_Cost = total_Cost;
        this.payment_Type = payment_Type;
        this.room_Price = room_Price;
        this.refund = 0;
    }
    public double getTotal_Cost() {
        return total_Cost;
    }

    public String getCard_No() {
        return card_No;
    }

    public int getNumber_days() {
        return number_days;
    }

    public String getPayment_Type() {
        return payment_Type;
    }

    public double getRoom_Price() {
        return room_Price;
    }

    public double getExtra_fee() {
        return extra_fee;
    }

    public double getRefund() {
        return refund;
    }

    public void setRefund(double refund) {
        this.refund = refund;
    }

    public void setCard_No(String card_No) {
        this.card_No = card_No;
    }

    public void setNumber_days(int number_days) {
        this.number_days = number_days;
    }

    public void setPayment_Type(String payment_Type) {
        this.payment_Type = payment_Type;
    }

    public void setTotal_Cost(double total_Cost) {
        this.total_Cost = total_Cost;
    }

    public void setRoom_Price(double room_Price) {
        this.room_Price = room_Price;
    }

    public void setExtra_fee(double extra_fee) {
        this.extra_fee = extra_fee;
    }
}
