package ro.agitman.moe.middle.service;

import ro.agitman.moe.middle.model.UserProfile;

import java.util.List;



public interface UserProfileService {

    UserProfile findById(int id);

    UserProfile findByType(String type);

    List<UserProfile> findAll();

}
