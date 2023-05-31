package com.tkqnr.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private int id;

    @Column(nullable = false,length = 100)
    private String title;

    @Lob // 대용량 데이터
    private String content; //섬머노트 라이브러리 <html>태그가 섞여서 디자인이 됨.

    private int count; //조회수

    @ManyToOne(fetch = FetchType.EAGER) // Many = Board , User = One
    @JoinColumn(name = "userId") // 객체의 필드값 설정. 자동으로 FK 생성.
    private User user; // DB는 오브젝트 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.

    @OneToMany(mappedBy = "board",fetch = FetchType.EAGER) //mappedBy 연관관계의 주인이 아니다. (FK키가 아니다.) DB에 컬럼 만들지 마세요. ""는 필드값.
    @JsonIgnoreProperties({"board"})
    private List<Reply> replys; // Board를 select할 때 join문을 통해서 값을 얻기 위해서만 필요한 것. DB에 컬럼 X

    @CreationTimestamp
    private Timestamp createDate;
}
