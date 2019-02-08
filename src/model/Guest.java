package model;

public class Guest {
    private String id_Type;
    private String id;
    private String name;
    private String address;
    private String company;
    private int tel;
    private String email;
    private int roomNum;
    private String location;
    private Payment payment = null;
    private String check_In;
    private String check_Out;
    private boolean arrived;
    private boolean isSmoker;

    public Guest() { }

    public Guest(String id_Type, String id, String name, String address, int tel, String email, int room, String check_In, String check_Out, String location, String company, boolean isSmoker, Payment payment)
    {
        this.id = id;
        this.id_Type = id_Type;
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.email = email;
        this.roomNum = room;
        this.payment = payment;
        this.check_In = check_In;
        this.check_Out = check_Out;
        this.location = location;
        this.company = company;
        this.arrived = false;
        this.isSmoker = isSmoker;
    }

    public String getId() {
        return id;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public String getCompany() {
        return company;
    }

    public int getTel() {
        return tel;
    }

    public Payment getPayment() {
        return payment;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getCheck_In() {
        return check_In;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getCheck_Out() {
        return check_Out;
    }

    public String getId_Type() {
        return id_Type;
    }

    public boolean isArrived() {
        return arrived;
    }

    public boolean isSmoker() {
        return isSmoker;
    }

    public void setArrived(boolean arrived) {
        this.arrived = arrived;
    }

    public void setSmoker(boolean smoker) {
        isSmoker = smoker;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCheck_In(String check_In) {
        this.check_In = check_In;
    }

    public void setCheck_Out(String check_Out) {
        this.check_Out = check_Out;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setId_Type(String id_Type) {
        this.id_Type = id_Type;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void setRoomNum(int room) {
        this.roomNum = room;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
