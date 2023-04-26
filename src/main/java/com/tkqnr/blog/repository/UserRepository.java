package com.tkqnr.blog.repository;

import com.tkqnr.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// DAO
// 자동으로 bean 등록이 되서 @Repository 생략 가능
public interface UserRepository extends JpaRepository<User, Integer> { //User 테이블을 관리하는 repository , PK는 Integer
}
