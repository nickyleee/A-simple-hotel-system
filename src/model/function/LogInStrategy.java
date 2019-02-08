package model.function;

import model.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LogInStrategy implements ILogInStrategy {
    @Override
    public boolean login(String username, String password, DB db) {
        try{
            Connection connection = db.getConnection();
            String sql = "SELECT * FROM User WHERE UserName = ? AND Password = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
