package model.function;

import model.DB;
import model.Guest;
import model.Payment;

public interface IGuestStrategy {
    void setGuest(Guest guest, DB db);

}
