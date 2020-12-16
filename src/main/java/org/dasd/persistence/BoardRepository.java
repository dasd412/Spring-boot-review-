package org.dasd.persistence;


import org.dasd.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;


import java.util.Collection;
import java.util.List;

public interface BoardRepository extends CrudRepository<Board,Long> {

     Board findByBno(long id);

     List<Board>findBoardByTitle(String title);

     Collection<Board>findByWriter(String writer);

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

}
