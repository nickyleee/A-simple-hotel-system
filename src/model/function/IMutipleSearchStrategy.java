package model.function;

import model.DB;
import model.Guest;
import model.Room;

import java.util.List;

public interface IMutipleSearchStrategy {
    List<Room> searchGuest(Guest guest, Room room, DB db);

}
