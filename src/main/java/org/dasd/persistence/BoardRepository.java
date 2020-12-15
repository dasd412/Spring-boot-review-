package org.dasd.persistence;


import org.dasd.domain.Board;
import org.springframework.data.repository.CrudRepository;

public interface BoardRepository extends CrudRepository<Board,Long> {

     Board findByBno(long id);

}
