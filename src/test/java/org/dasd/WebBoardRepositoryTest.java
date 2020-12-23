package org.dasd;

import lombok.extern.java.Log;
import org.dasd.domain.WebBoard;
import org.dasd.persistence.WebBoardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
}
