package app.model;

import app.entities.User;

import java.util.List;

public interface UsersDao  extends  Model<User>{
    List<User> findAllByFirstName(String firstName);
}
