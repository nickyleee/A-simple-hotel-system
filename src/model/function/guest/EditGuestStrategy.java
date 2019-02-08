package model.function.guest;

import model.DB;
import model.Guest;
import model.Payment;
import model.function.IGuestStrategy;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class EditGuestStrategy implements IGuestStrategy {
    @Override
    public void setGuest(Guest guest, DB db) {
        try{
            Connection connection = db.getConnection();
            String sql = "UPDATE guest_information SET room_NO = ?, id_type = ?, iname = ?, address = ?, tel = ?, email = ?, check_in = ?, check_out = ?, location = ?, total_cost = ?, num_Day_Stay = ?, payment_type = ?, card_No = ?, room_Price = ?, company = ? ,extra_fee = ? ,smoker = ? ,isArrived = ?"
                    + " WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, guest.getRoomNum());
            preparedStatement.setString(2, guest.getId_Type());
            preparedStatement.setString(3, guest.getName());
            preparedStatement.setString(4, guest.getAddress());
            preparedStatement.setInt(5, guest.getTel());
            preparedStatement.setString(6, guest.getEmail());
            preparedStatement.setString(7, guest.getCheck_In());
            preparedStatement.setString(8, guest.getCheck_Out());
            preparedStatement.setString(9, guest.getLocation());
            preparedStatement.setDouble(10, guest.getPayment().getTotal_Cost());
            preparedStatement.setInt(11, guest.getPayment().getNumber_days());
            preparedStatement.setString(12, guest.getPayment().getPayment_Type());
            preparedStatement.setString(13, guest.getPayment().getCard_No());
            preparedStatement.setDouble(14, guest.getPayment().getRoom_Price());
            preparedStatement.setString(15,guest.getCompany());
            preparedStatement.setDouble(16, guest.getPayment().getExtra_fee());
            preparedStatement.setBoolean(17, guest.isSmoker());
            preparedStatement.setBoolean(18, guest.isArrived());
            preparedStatement.setString(19, guest.getId());

            preparedStatement.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
