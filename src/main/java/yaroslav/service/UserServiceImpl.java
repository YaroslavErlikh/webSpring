package yaroslav.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yaroslav.db.dao.RoleRepository;
import yaroslav.db.dao.UserRepository;
import yaroslav.model.User;
import yaroslav.model.role.Role;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private RoleRepository roleRepository;

    private UserRepository userRepository;

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.delete(getById(id));
    }

    @Override
    @Transactional
    public boolean add(User user) {

        if (userIsExist(user)) {
            return false;
        }

        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userRepository.saveAndFlush(user);
        return true;
    }

    @Override
    public boolean userIsExist(User user) {
        return getByName(user.getUsername()) != null;
    }

    @Override
    @Transactional
    public void edit(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.saveAndFlush(user);
    }

    @Override
    @Transactional
    public User get(String name, String pass) {
        User user = userRepository.findByUsername(name);
        if (bCryptPasswordEncoder.matches(pass, user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    @Transactional
    public User getById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    @Transactional
    public User getByName(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    @Override
    @Transactional
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
