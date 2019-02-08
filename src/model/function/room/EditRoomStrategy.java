package model.function.room;

import model.DB;
import model.Room;
import model.function.IRoomStrategy;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class EditRoomStrategy implements IRoomStrategy {
    @Override
    public void setRoom(Room room, DB db){
        try{
            Connection connection = db.getConnection();
            String sql = "UPDATE room_information SET room_type = ?, address = ?, smoking = ?, floor = ?, available = ?, room_view = ?, animal = ?, adjoinRoom = ?"
                    + " WHERE room_no = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, room.getRoom_type());
            preparedStatement.setString(2, room.getRoom_location());
            preparedStatement.setBoolean(3, room.isSmoking());
            preparedStatement.setInt(4, room.getFloor());
            preparedStatement.setBoolean(5, room.isAvailable());
            preparedStatement.setString(6, room.getView());
            preparedStatement.setBoolean(7, room.isAnimal());
            preparedStatement.setString(8, room.getAdjoinRoom());
            preparedStatement.setInt(9, room.getRoom_no());

            preparedStatement.executeUpdate();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
