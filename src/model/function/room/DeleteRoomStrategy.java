package model.function.room;

import model.DB;
import model.Room;
import model.function.IRoomStrategy;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DeleteRoomStrategy implements IRoomStrategy {
    @Override
    public void setRoom(Room room, DB db){
        try{
            Connection connection = db.getConnection();
            String sql = "DELETE FROM room_information WHERE room_no = ? And address = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, room.getRoom_no());
            preparedStatement.setString(2, room.getRoom_location());


            preparedStatement.execute();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
