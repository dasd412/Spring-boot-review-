package org.dasd.domain;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString(exclude = "files")
@Entity
@Table(name="tbl_pds")
@EqualsAndHashCode(of="pid")
public class PDSBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pid;

    private String pname;
    private String pwriter;

    @OneToMany(cascade = CascadeType.ALL)//일대다의 관계. 자료실이 여러개의 첨부 파일을 갖고 있다. 모든 변경에 대해 영속성을 전이한다.
    @JoinColumn(name="pdsno")
    private List<PDSFile> files;
}
