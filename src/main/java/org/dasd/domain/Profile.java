package org.dasd.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString(exclude = "member")//<-양방향 참조 시에 무한 재귀 방지
@Entity
@Table(name="tbl_profile")
@EqualsAndHashCode(of="fno")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fno;

    private String fName;
    private boolean current;

    @ManyToOne//Profile 입장에서는 Member에 대해 다대일 관계!
    private Member member;//Profile -> Member 참조


}
