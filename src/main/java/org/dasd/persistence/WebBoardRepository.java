package org.dasd.persistence;

import com.querydsl.core.BooleanBuilder;
import org.dasd.domain.QWebBoard;
import org.dasd.domain.WebBoard;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.querydsl.core.types.Predicate;

public interface WebBoardRepository extends CrudRepository<WebBoard,Long> , QuerydslPredicateExecutor<WebBoard> {

    public default Predicate makePredicate(String type, String keyword){
        BooleanBuilder builder=new BooleanBuilder();

        QWebBoard board=QWebBoard.webBoard;

        //type if ~ else

        if(type==null){
            return builder;
        }

        switch (type){
            case "t":
                builder.and(board.title.like("%"+keyword+"%"));
                break;

            case "c":
                builder.and(board.content.like("%"+keyword+"%"));
                break;

            case "w":
                builder.and(board.writer.like("%"+keyword+"%"));
                break;
        }

        //bno>0
        builder.and(board.bno.gt(0));
        return builder;
    }
}
