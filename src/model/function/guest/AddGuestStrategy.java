package model.function.guest;

import model.DB;
import model.Guest;
import model.Payment;
import model.function.IGuestStrategy;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddGuestStrategy implements IGuestStrategy {
    @Override
    public void setGuest(Guest guest, DB db) {
        try{
            Connection connection = db.getConnection();
            String sql = "INSERT INTO guest_information( room_No, id,id_type,iname, address, tel, email, check_in, check_out, location, total_cost, num_Day_Stay, payment_type, card_No, room_Price, company,extra_fee,smoker,isArrived) "
                    + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, guest.getRoomNum());
            preparedStatement.setString(2, guest.getId());
            preparedStatement.setString(3, guest.getId_Type());
            preparedStatement.setString(4, guest.getName());
            preparedStatement.setString(5, guest.getAddress());
            preparedStatement.setInt(6, guest.getTel());
            preparedStatement.setString(7, guest.getEmail());
            preparedStatement.setString(8, guest.getCheck_In());
            preparedStatement.setString(9, guest.getCheck_Out());
            preparedStatement.setString(10, guest.getLocation());
            preparedStatement.setDouble(11, guest.getPayment().getTotal_Cost());
            preparedStatement.setInt(12, guest.getPayment().getNumber_days());
            preparedStatement.setString(13, guest.getPayment().getPayment_Type());
            preparedStatement.setString(14, guest.getPayment().getCard_No());
            preparedStatement.setDouble(15, guest.getPayment().getRoom_Price());
            preparedStatement.setString(16,guest.getCompany());
            preparedStatement.setDouble(17, guest.getPayment().getExtra_fee());
            preparedStatement.setBoolean(18, guest.isSmoker());
            preparedStatement.setBoolean(19, guest.isArrived());

            preparedStatement.executeUpdate();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
