package ro.agitman.moe.middle.dao;


import java.util.List;

import ro.agitman.moe.middle.model.User;

/**
 * Created by d-uu31cq on 19.08.2016.
 */
public interface UserDao {

    User findById(int id);

    User findBySSO(String sso);

    void save(User user);

    void deleteBySSO(String sso);

    List<User> findAllUsers();

}
