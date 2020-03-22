package yaroslav.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import yaroslav.model.User;
import yaroslav.model.role.Role;

import java.util.List;

public interface UserService extends UserDetailsService {

    <T> List<T> getAll();

    void delete(Long id);

    boolean add(User user);

    void edit(User user);

    User get(String name, String pass);

    User getById(Long id);

    User getByName(String name);

    UserDetails loadUserByUsername(String username);

    List<Role> getAllRoles();

    boolean userIsExist(User user);
}
