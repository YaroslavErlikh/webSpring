package yaroslav.db.dao;

import yaroslav.model.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(User user);

    User getUserById(Long id);

    User getUser(String name, String pass);

    User getByName(String name);
}
