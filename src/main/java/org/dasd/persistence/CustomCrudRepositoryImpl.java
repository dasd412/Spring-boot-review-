package org.dasd.persistence;

import lombok.extern.java.Log;
import org.dasd.domain.WebBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

@Log

public class CustomCrudRepositoryImpl extends QuerydslRepositorySupport implements CustomWebBoard {

    public CustomCrudRepositoryImpl() {
        super(WebBoard.class);
    }

    @Override
    public Page<Object[]> getCustomPage(String type, String keyword, Pageable page) {

        log.info("=====================");

        log.info("TYPE: "+type);
        log.info("Keyword: "+keyword);
        log.info("Page"+page);

        log.info("=====================");

        return null;
    }
}
