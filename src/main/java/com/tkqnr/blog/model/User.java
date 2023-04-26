package com.tkqnr.blog.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
// @DynamicInsert // Insert 할 때 null인 필드 제외
//ORM -> JAVA Object 를 테이블로 Mapping
@Entity // User 클래스를 읽어서 자동으로 mysql에 테이블 생성
public class User {

    @Id //Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라감.
    private int id; //oracle 경우 시퀸스, mysql 경우 auto_increment

    @Column(nullable = false,length = 30)
    private String username; // 아이디

    @Column(nullable = false,length = 100) // 1234->해쉬(비밀번호 암호화) 넉넉히.
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    //@ColumnDefault("'user'")
    //DB는 RoleType 이라는게 없다. Enum이 string 이라고 알려줌.
    @Enumerated(EnumType.STRING)
    private RoleType role; // Enum을 쓰면 데이터를 넣을 때 어떤 도메인 안에서 값을 강제할 수 있음. / ADMIN,USER 강제됨

    @CreationTimestamp // 시간이 자동으로 입력
    private Timestamp createDate;
}
