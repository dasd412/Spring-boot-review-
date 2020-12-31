package org.dasd.persistence;

import org.dasd.domain.WebBoard;
import org.springframework.data.repository.CrudRepository;

public interface CustomCrudRepository extends CrudRepository<WebBoard,Long>,CustomWebBoard {
}
