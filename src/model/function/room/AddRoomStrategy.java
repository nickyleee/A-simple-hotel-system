package model.function.room;

import model.DB;
import model.Room;
import model.function.IRoomStrategy;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddRoomStrategy implements IRoomStrategy {
    @Override
    public void setRoom(Room room, DB db){
        try{
            Connection connection = db.getConnection();
            String sql = "INSERT INTO room_information(room_no,room_type,address,smoking,floor,available,room_view,animal, adjoinRoom) "
                    + " VALUES(?, ?, ?, ?, ?, ?, ?, ?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, room.getRoom_no());
            preparedStatement.setString(2, room.getRoom_type());
            preparedStatement.setString(3, room.getRoom_location());
            preparedStatement.setBoolean(4, room.isSmoking());
            preparedStatement.setInt(5, room.getFloor());
            preparedStatement.setBoolean(6, room.isAvailable());
            preparedStatement.setString(7, room.getView());
            preparedStatement.setBoolean(8, room.isAnimal());
            preparedStatement.setString(9, room.getAdjoinRoom());

            preparedStatement.executeUpdate();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
