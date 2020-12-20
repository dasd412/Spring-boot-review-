package org.dasd.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@ToString(exclude = "board")
@Entity
@Table(name="tbl_free_reply", indexes = {@Index( unique = false, columnList = "board_bno")})//게시물 번호에 대한 인덱스를 생성해두어 성능 향상시키기
@EqualsAndHashCode(of="rno")

public class FreeBoardReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String reply;

    private String replyer;

    @CreationTimestamp
    private Timestamp replydate;

    @UpdateTimestamp
    private Timestamp updatedate;

    @ManyToOne
    private FreeBoard board;
}
