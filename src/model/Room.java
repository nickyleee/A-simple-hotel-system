package model;

public class Room {
    private int room_no;
    private String room_type;
    private boolean smoking;
    private int floor;
    private boolean available;
    private boolean animal;
    private String view;
    private String room_location;
    private String adjoinRoom;

    public Room() { }

    public Room(int room_no, String room_type, boolean smoking, boolean animal, String view, String room_location,int floor,String adjoinRoom)
    {
        this.animal = animal;
        this.room_location = room_location;
        this.available = true;
        this.floor = floor;
        this.room_no = room_no;
        this.room_type = room_type;
        this.smoking = smoking;
        this.view = view;
        this.adjoinRoom = adjoinRoom;
    }

    public boolean isAnimal() {
        return animal;
    }

    public boolean isAvailable() {
        return available;
    }

    public boolean isSmoking() {
        return smoking;
    }

    public int getFloor() {
        return floor;
    }

    public int getRoom_no() {
        return room_no;
    }

    public String getRoom_type() {
        return room_type;
    }

    public String getRoom_location() {
        return room_location;
    }

    public String getView() {
        return view;
    }

    public void setAnimal(boolean animal) {
        this.animal = animal;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setRoom_location(String room_location) {
        this.room_location = room_location;
    }

    public void setRoom_no(int room_no) {
        this.room_no = room_no;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public void setSmoking(boolean smoking) {
        this.smoking = smoking;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getAdjoinRoom() {
        return adjoinRoom;
    }

    public void setAdjoinRoom(String adjoinRoom) {
        this.adjoinRoom = adjoinRoom;
    }
}
