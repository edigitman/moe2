package ro.agitman.moe.middle.service;

import ro.agitman.moe.middle.model.User;

import java.util.List;


public interface UserService {

    User findById(int id);

    User findBySSO(String sso);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserBySSO(String sso);

    List<User> findAllUsers();

    boolean isUserSSOUnique(Integer id, String sso);

    List<User> findAllUsersWithRoles();

    List<User> find();
}
