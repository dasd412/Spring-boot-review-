package org.dasd.persistence;

import org.dasd.domain.PDSBoard;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface PDSBoardRepository extends CrudRepository<PDSBoard,Long> {


    @Modifying//<-insert, update, delete 작업 지원 어노테이션
    @Query("UPDATE PDSFile f set f.pdsfile = ?2 WHERE f.fno = ?1 ")//@Query는 기본적으로 select만 지원.
    int updatePDSFile(Long fno, String newFileName);

    @Modifying
    @Query("DELETE FROM PDSFile f where f.fno = ?1")
    int deletePDSFile(Long fno);

    @Query("SELECT p, count(f) FROM PDSBoard p LEFT OUTER JOIN p.files f WHERE p.pid > 0 GROUP BY p ORDER BY p.pid DESC")
    List<Object[]>getSummary();
}
