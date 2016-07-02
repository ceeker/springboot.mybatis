package org.ceeker.web.sbootm;

import javax.annotation.Resource;

import org.ceeker.web.sbootm.domain.User;
import org.ceeker.web.sbootm.domain.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class ApplicationTest {
    
    @Autowired
    private UserRepository user;
    
    @Before
    public void before() {
        user.save(new User("adfjl","adjkljakld"));
    }
    
    
    @Test
    public void testGetUser() {
        System.out.println("----------first:"+user.findByUsername("zxl").getPassword());
        System.out.println("----------second:"+user.findByUsername("zxl").getPassword());
    }
}
