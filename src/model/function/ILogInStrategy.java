package model.function;

import model.DB;

public interface ILogInStrategy {
    boolean login(String username, String password, DB db);
}
