package ro.agitman.moe.middle.dao;

import java.util.List;

import ro.agitman.moe.middle.model.UserProfile;


public interface UserProfileDao {

    List<UserProfile> findAll();

    UserProfile findByType(String type);

    UserProfile findById(int id);
}
