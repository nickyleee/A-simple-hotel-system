package model.function;

import model.Guest;

import java.util.List;

public interface IGetLimitedTimeRoomStrategy {
    List<Guest> getLimitedTimeRoom(String str, List<Guest> guests);
}
