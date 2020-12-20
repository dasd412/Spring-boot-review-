package org.dasd.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

import java.util.List;

@Getter
@Setter
@ToString(exclude = "replies")//양방햠 참조 시 무한 재귀 위험 있음.
@Entity
@Table(name="tbl_freeboards")
@EqualsAndHashCode(of="bno")

public class FreeBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    private String title;
    private String writer;
    private String content;

    @CreationTimestamp
    private Timestamp regdate;

    @UpdateTimestamp
    private Timestamp updatedate;

    @OneToMany(mappedBy = "board", cascade=CascadeType.ALL,fetch=FetchType.LAZY)//게시글 엔티티가 댓글 엔티티에 매여 있음을 나타냄. 그리고 영속성 전이가 모든 곳에서 이루어짐
   //fetch 타입 EAGER의 경우 종속 엔티티 참조시 즉시 로딩 허용
   
    private List<FreeBoardReply>replies;

}
