package org.ceeker.web.sbootm.domain;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
@CacheConfig(cacheNames = "users")
public interface UserRepository extends JpaRepository<User, Integer> {

    @Cacheable
    User findByUsername(String username);

    @Query("from User u where u.name=:name")
    User findUser(@Param("name") String name);
}
