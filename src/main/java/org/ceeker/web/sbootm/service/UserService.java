//package org.ceeker.web.sbootm.service;
//
//import org.ceeker.web.sbootm.entity.User;
//import org.ceeker.web.sbootm.entity.mapper.UserMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//
//@Service
//@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
//public class UserService implements CommandLineRunner {
//    @Autowired
//    private UserMapper userMapper;
//
//    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
//    public User getByUserName(String userName) {
//        return userMapper.findByUserName(userName);
//
//    }
//
//    @Override
//    public void run(String... arg0) throws Exception {
//        User user= getByUserName("test");
//        System.err.println(user);
//    }
//
//}
