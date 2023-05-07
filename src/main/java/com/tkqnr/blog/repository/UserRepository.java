package com.tkqnr.blog.repository;

import com.tkqnr.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// DAO
// 자동으로 bean 등록이 되서 @Repository 생략 가능
public interface UserRepository extends JpaRepository<User, Integer> { //User 테이블을 관리하는 repository , PK는 Integer
    //JPA Naming 쿼리 전략
    //SELECT * FROM user WHERE username = ? AND password = ?;
    User findByUsernameAndPassword(String username, String password);

//    @Query(value = "SELECT * FROM user WHERE username=?1 AND password=?2",nativeQuery = true)
//    User login(String username,String password);
}
