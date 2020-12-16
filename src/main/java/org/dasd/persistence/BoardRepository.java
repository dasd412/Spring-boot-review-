package org.dasd.persistence;


import org.dasd.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import java.util.Collection;
import java.util.List;

public interface BoardRepository extends CrudRepository<Board,Long>, QuerydslPredicateExecutor<Board> {

     Board findByBno(long id);

     List<Board>findBoardByTitle(String title);



     //like %
     Collection<Board>findByWriterContaining(String writer);

     //Or를 이용해 title과 content에 특정한 문자열이 있는지 검색
     Collection<Board>findByTitleContainingOrContentContaining(String title,String content);

     //title like %?% and bno>?
     Collection<Board>findByTitleContainingAndBnoGreaterThan(String title, Long num);


     //bno>? order by bno desc
     Collection<Board>findByBnoGreaterThanOrderByBnoDesc(Long bno);

     //bno>? order by bno desc limit ?,?
     List<Board>findByBnoGreaterThanOrderByBnoDesc(Long bno, Pageable paging);


     //noSorting
     Page<Board> findByBnoGreaterThan(Long bno, Pageable paging);

     //제목에 대한 검색 처리
     @Query("SELECT b FROM Board b WHERE b.title LIKE %?1% AND b.bno > 0 ORDER BY b.bno DESC")
     List<Board>findByTitle(String title);

     //내용에 대한 검색 처리
     @Query("SELECT b FROM Board b WHERE b. content LIKE %:content% AND b.bno >0 ORDER BY b.bno DESC")
     List<Board>findByContent(@Param("content")String content);

     //작성자에 대한 검색 처리
@Query("SELECT b from #{#entityName} b WHERE b.writer LIKE %?1% AND b.bno >0 ORDER BY b.bno DESC")
     List<Board>findByWriter(String writer);

    //내용을 제외한 모든 칼럼 조회
     @Query("SELECT b.bno, b.title, b.writer, b.regDate "+" FROM Board b WHERE b.title LIKE %?1% AND b.bno > 0 ORDER BY b.bno DESC")
     List<Object[]>findByTitle2(String title);
     
     //네이티브 쿼리 이용 예시
     @Query(value="select bno, title, writer from tbl_boards where title like CONCAT('%', ?1, '%') and bno>0 order by bno desc",
     nativeQuery =true)
     List<Object[]>findByTitle3(String title);

     //@Query와 페이징 처리
     @Query("SELECT b From Board b WHERE b.bno > 0 ORDER BY b.bno DESC")
     List<Board>findByPage(Pageable pageable);


}
