package model.function.search;

import model.DB;
import model.*;
import model.function.ISearchStrategy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchByDepartureListStrategy implements ISearchStrategy {
    @Override
    public List<Guest> searchGuest(String str, DB db) {
        try{
            Connection connection = db.getConnection();
            String sql = "SELECT * FROM guest_information WHERE check_out = ? ";
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setString(1,  str);

            return db.GetGuest(preparedStatement);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Room> searchRoom(String str, DB db) {
        return null;
    }
}
