package yaroslav.service;

import yaroslav.model.User;

import java.util.List;

public interface UserService {

    <T> List<T> getAll();

    void delete(Long id);

    void add(User user);

    void edit(User user);

    User get(String name, String pass);

    User getById(Long id);

    User getByName(String name);
}
