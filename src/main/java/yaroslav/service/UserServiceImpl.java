package yaroslav.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yaroslav.db.dao.UserDAO;
import yaroslav.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public List<User> getAll() {
        return userDAO.getAllUsers();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userDAO.deleteUser(getById(id));
    }

    @Override
    @Transactional
    public void add(User user) {
        userDAO.addUser(user);
    }

    @Override
    @Transactional
    public void edit(User user) {
        userDAO.updateUser(user);
    }

    @Override
    @Transactional
    public User get(String name, String pass) {
        return userDAO.getUser(name, pass);
    }

    @Override
    @Transactional
    public User getById(Long id) {
        return userDAO.getUserById(id);
    }

    @Override
    @Transactional
    public User getByName(String name) {
        return userDAO.getByName(name);
    }
}
