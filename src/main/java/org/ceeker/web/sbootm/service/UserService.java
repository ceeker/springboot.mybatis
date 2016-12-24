package org.ceeker.web.sbootm.service;

import org.ceeker.web.sbootm.domain.User;
import org.ceeker.web.sbootm.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserService {
    
    @Resource
    private UserRepository userRepository;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class,isolation=Isolation.REPEATABLE_READ)
    public List<User> getByUserName(String userName) {
        return userRepository.findByUsername("adjjaljdl");

    }

    //    @Override
//    public void run(String... arg0) throws Exception {
//        User user = getByUserName("test");
//        System.err.println(user.getPassword());
//    }
    public User getById(int id) {
        return userRepository.findOne(id);
    }
    
    public User save(User user) {
        return userRepository.save(user);
    }

}
