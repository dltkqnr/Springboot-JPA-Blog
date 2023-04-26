package com.tkqnr.blog.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @ColumnDefault("'user'") // "' '" 홑따옴표 varchar
    private String role; // Enum을 쓰는게 좋음. 도메인을 설정할 수 있음 (범위). // admin,user,manager

    @CreationTimestamp // 시간이 자동으로 입력
    private Timestamp createDate;
}
