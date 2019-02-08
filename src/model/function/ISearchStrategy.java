package model.function;

import model.*;

import java.util.List;

public interface ISearchStrategy {
    List<Guest> searchGuest(String str, DB db);
    List<Room> searchRoom(String str, DB db);
}
