package org.dasd.persistence;

import org.dasd.domain.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface MemberRepository extends CrudRepository<Member,String> {

    @Query("SELECT m.uid , count(p) FROM Member m LEFT OUTER JOIN Profile p "+" ON m.uid = p.member WHERE m.uid =?1 GROUP BY m" )
    List<Object[]>getMemberWithProfileCount(String uid);//List는 결과의 Row 수를, Object[]는 칼럼들을 의미한다.


    @Query("SELECT m, p FROM Member m LEFT OUTER JOIN Profile p "+" ON m.uid = p.member WHERE m.uid = ?1 AND p.current = true")
    List<Object[]>getMemberWithCurrentProfile(String uid);
}
