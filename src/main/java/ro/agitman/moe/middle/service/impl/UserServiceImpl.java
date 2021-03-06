package ro.agitman.moe.middle.service.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.agitman.moe.middle.dao.UserDao;
import ro.agitman.moe.middle.model.User;
import ro.agitman.moe.middle.service.UserService;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findById(int id) {
        return dao.findById(id);
    }

    public User findBySSO(String sso) {
        User user = dao.findBySSO(sso);
        return user;
    }

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.save(user);
    }

    /*
     * Since the method is running with Transaction, No need to call hibernate update explicitly.
     * Just fetch the entity from db and update it with proper values within transaction.
     * It will be updated in db once transaction ends.
     */
    public void updateUser(User user) {
        User entity = dao.findById(user.getId());
        if (entity != null) {
            if (user.getPassword() != null && !user.getPassword().equals(entity.getPassword())) {
                entity.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            entity.setFirstName(user.getFirstName());
            entity.setLastName(user.getLastName());
            entity.setEmail(user.getEmail());
            entity.setUserProfiles(user.getUserProfiles());
        }
    }


    public void deleteUserBySSO(String sso) {
        dao.deleteBySSO(sso);
    }

    public List<User> findAllUsers() {
        return dao.findAllUsers();
    }

    public List<User> findAllUsersWithRoles() {
        List<User> users = dao.findAllUsers();
        users.forEach(u -> Hibernate.initialize(u.getUserProfiles()));
        return users;
    }

    public boolean isUserSSOUnique(Integer id, String sso) {
        User user = findBySSO(sso);
        return (user == null || ((id != null) && (user.getId() == id)));
    }

}
