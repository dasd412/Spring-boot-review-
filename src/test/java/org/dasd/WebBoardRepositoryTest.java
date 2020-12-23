package org.dasd;

import lombok.extern.java.Log;
import org.dasd.domain.WebBoard;
import org.dasd.persistence.WebBoardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
@Commit
@Log
public class WebBoardRepositoryTest {

    @Autowired
    private WebBoardRepository repository;

    @Test
    public void insertBoardDummies(){
        IntStream.range(0,300).forEach(i->{
            WebBoard board=new WebBoard();
            board.setTitle("Sample board title : "+i);
            board.setContent("content sample ... "+i+" of board");
            board.setWriter("user0"+(i%10));

            repository.save(board);
        });

    }

    @Test
    public void testList1(){
        Pageable pageable= PageRequest.of(0,20, Sort.Direction.DESC,"bno");

        Page<WebBoard> result=repository.findAll(repository.makePredicate(null,null),pageable);

        log.info("Page : "+result.getPageable());

        log.info("---------");

        result.getContent().forEach(board->log.info(" "+board));


    }

    @Test
    public void testList2(){
        Pageable pageable=PageRequest.of(0,20, Sort.Direction.DESC,"bno");

        Page<WebBoard>result=repository.findAll(repository.makePredicate("t","10"),pageable);

        log.info("page : "+result.getPageable());

        log.info("------------");

        result.getContent().forEach(board->log.info(" "+board));

    }
}
