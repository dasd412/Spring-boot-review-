package org.dasd;

import lombok.extern.java.Log;
import org.dasd.domain.WebBoard;
import org.dasd.domain.WebReply;
import org.dasd.persistence.WebReplyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit

public class WebReplyRepositoryTest {

    @Autowired
    private WebReplyRepository repository;


    @Test
    public void testInsertReplies(){

        Long []arr={303L,302L, 301L};

        Arrays.stream(arr).forEach(num->{

            WebBoard board=new WebBoard();
            board.setBno(num);

            IntStream.range(0,10).forEach(i->{

                WebReply reply=new WebReply();
                reply.setReplyText("reply : "+i);
                reply.setReplyer("replyer :"+i);
                reply.setBoard(board);

                repository.save(reply);
            });

        });

    }
}
