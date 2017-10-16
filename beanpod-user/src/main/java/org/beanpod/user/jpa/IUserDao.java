package org.beanpod.user.jpa;

import org.beanpod.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IUserDao extends JpaRepository<User, String> {
	User findByName(String name);
	
    @Query("from User u where u.name=:name")
    User findUser(@Param("name") String name);
}
