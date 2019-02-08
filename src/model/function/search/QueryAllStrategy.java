package model.function.search;

import model.DB;
import model.Guest;
import model.Payment;
import model.Room;
import model.function.ISearchStrategy;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QueryAllStrategy implements ISearchStrategy
{
    @Override
    public List<Guest> searchGuest(String str, DB db) {
        List<Guest> guests = new ArrayList<>();
        Connection connection = db.getConnection();
        String sql = "SELECT * FROM guest_information";

        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next())
            {
                Payment payment = new Payment(resultSet.getInt("num_Day_Stay"),resultSet.getDouble("total_cost"),resultSet.getString("payment_type") ,resultSet.getString("card_No")
                        ,resultSet.getDouble("room_Price"));
                Guest guest = new Guest(resultSet.getString("id_type"),resultSet.getString("id"),resultSet.getString("iname"),resultSet.getString("address"),
                        resultSet.getInt("tel"),resultSet.getString("email"),resultSet.getInt("room_No"),resultSet.getString("check_in"),resultSet.getString("check_out"),resultSet.getString("location"), resultSet.getString("company"),resultSet.getBoolean("smoker"),payment);
                guests.add(guest);
            }

            return guests;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return guests;
    }

    @Override
    public List<Room> searchRoom(String str, DB db) {
        List<Room> rooms = new ArrayList<>();
        Connection connection = db.getConnection();
        String sql = "SELECT * FROM room_information ";
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next())
            {
                Room room = new Room(resultSet.getInt("room_no"),resultSet.getString("room_type"),resultSet.getBoolean("smoking"),resultSet.getBoolean("animal"),
                        resultSet.getString("room_view"),resultSet.getString("address"),resultSet.getInt("floor"), resultSet.getString("adjoinRoom"));
                room.setAvailable(resultSet.getBoolean("available"));
                rooms.add(room);
            }
            return rooms;

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
