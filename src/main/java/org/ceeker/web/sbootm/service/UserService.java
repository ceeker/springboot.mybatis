package org.ceeker.web.sbootm.service;

import org.ceeker.web.sbootm.domain.User;
import org.ceeker.web.sbootm.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserService implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public User getByUserName(String userName) {
        return userRepository.findByUsername("zxl");

    }

    @Override
    public void run(String... arg0) throws Exception {
        User user = getByUserName("test");
        System.err.println(user);
    }

}
