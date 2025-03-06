package com.sandyz.bobtimerserver.repo;

import com.sandyz.bobtimerserver.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUserId(String userId);

}
