package model.function;

import model.DB;
import model.Guest;
import model.Room;
import model.function.IMutipleSearchStrategy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MutipleSearchStrategy implements IMutipleSearchStrategy {
    @Override
    public List<Room> searchGuest(Guest guest, Room room, DB db) {
        try {
            Connection connection = db.getConnection();
            String sqlCheckIn = "SELECT * FROM room_information WHERE room_type = ? AND address = ?";
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sqlCheckIn);
            preparedStatement.setString(1, room.getRoom_type());
            preparedStatement.setString(2, room.getRoom_location());

            return db.GetRoom(preparedStatement);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}