package org.dasd.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.List;
import java.sql.Timestamp;

@Getter
@Setter
@ToString(exclude = "replies")
@Entity
@Table(name="tbl_webboards")
@EqualsAndHashCode(of="bno")
public class WebBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    private String title;
    private String writer;
    private String content;

    @CreationTimestamp
    private Timestamp regDate;

    @UpdateTimestamp
    private Timestamp updateDate;

    @OneToMany(mappedBy = "board" ,fetch=FetchType.LAZY)
    private List<WebReply> replies;

}
