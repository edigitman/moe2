package ro.agitman.moe.middle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.agitman.moe.middle.dao.ExamGroupDao;
import ro.agitman.moe.middle.model.ExamGroup;
import ro.agitman.moe.middle.model.User;
import ro.agitman.moe.middle.service.ExamGroupService;
import ro.agitman.moe.middle.service.UserService;
import ro.agitman.moe.web.dto.ExamGroupDTO;

import java.util.Date;
import java.util.List;

/**
 * Created by d-uu31cq on 19.09.2016.
 */
@Service("examGroupService")
@Transactional
public class ExamGroupServiceImpl implements ExamGroupService {

    @Autowired
    private ExamGroupDao groupDao;
    @Autowired
    private UserService userService;

    @Override
    public List<ExamGroup> findAll(User user) {
        return groupDao.findForOwner(user);
    }

    @Override
    public ExamGroup createGroup(User user, ExamGroupDTO groupDTO) {
        ExamGroup examGroup = new ExamGroup();
        examGroup.setOwner(user);
        examGroup.setName(groupDTO.getGroupName());
        examGroup.setDatecreated(new Date());
        examGroup.setLocked(false);

        groupDao.persist(examGroup);

        return examGroup;
    }


    @Override
    public void remove(Integer groupId) {
        groupDao.delete(groupDao.getByKey(groupId));
    }

    @Override
    public void addStudent(User student, User user) {


    }
}
