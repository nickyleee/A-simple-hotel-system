package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/HotelSystem?verifyServerCertificate=false&useSSL=true";


    private static final String USER = "root";
    private static final String PASSWORD = "newpass";

    private static Connection connection_guest = null;

    public DB() {
        try{
            Class.forName(JDBC_DRIVER);
            connection_guest = (Connection) DriverManager.getConnection(DB_URL, USER,PASSWORD);


        }catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static Connection getConnection()
    {
        return connection_guest;
    }


    public  List<Guest> GetGuest(PreparedStatement preparedStatement) {
        try{
            List<Guest> guests = new ArrayList<>();

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                Payment payment = new Payment(resultSet.getInt("num_Day_Stay"),resultSet.getDouble("total_cost"),resultSet.getString("payment_type") ,resultSet.getString("card_No")
                        ,resultSet.getDouble("room_Price"));
                Guest g = new Guest(resultSet.getString("id_type"),resultSet.getString("id"),resultSet.getString("iname"),resultSet.getString("address"),
                        resultSet.getInt("tel"),resultSet.getString("email"),resultSet.getInt("room_No"),resultSet.getString("check_in"),resultSet.getString("check_out"),resultSet.getString("location"), resultSet.getString("company"),resultSet.getBoolean("smoker"),payment);
                guests.add(g);
            }
            return guests;
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public List<Room> GetRoom(PreparedStatement preparedStatement)
    {
        try{
            List<Room> rooms = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                Room room = new Room(resultSet.getInt("Room_no"),resultSet.getString("room_type"),resultSet.getBoolean("smoking"),resultSet.getBoolean("animal"),
                        resultSet.getString("room_view"),resultSet.getString("address"),resultSet.getInt("floor"), resultSet.getString("adjoinRoom"));
                room.setAvailable(resultSet.getBoolean("available"));
                rooms.add(room);
            }
            return rooms;
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    }
