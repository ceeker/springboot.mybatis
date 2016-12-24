package org.ceeker.web.sbootm;

import org.ceeker.web.sbootm.domain.User;
import org.ceeker.web.sbootm.domain.UserRepository;
import org.junit.Before;
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
        user.save(User.builder().password("dajfk").username("jdklajf").build());
    }

}
