package org.dasd;

import lombok.extern.java.Log;
import org.dasd.domain.FreeBoard;
import org.dasd.domain.FreeBoardReply;
import org.dasd.persistence.FreeBoardReplyRepository;
import org.dasd.persistence.FreeBoardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class FreeBoardTest {

    @Autowired
    FreeBoardRepository freeBoardRepository;

    @Autowired
    FreeBoardReplyRepository freeBoardReplyRepository;

    @Test
    public void insertDummy(){

        IntStream.range(1,200).forEach(i->{
            FreeBoard board=new FreeBoard();

            board.setTitle("TITLE : "+i);
            board.setContent("content : "+i);
            board.setWriter("user : "+i%10);

            freeBoardRepository.save(board);

        });

    }

    @Transactional//댓글쪽에도 변경이 있으므로 트랜잭션 처리
    @Test
    public void insertReply2Way(){//양방향 처리
        Optional<FreeBoard> result=freeBoardRepository.findById(199L);

        result.ifPresent(board->{

            List<FreeBoardReply>replyList=board.getReplies();

            FreeBoardReply reply=new FreeBoardReply();
            reply.setReply("reply...");
            reply.setReplyer("replyer 00");
            reply.setBoard(board);

            replyList.add(reply);

            board.setReplies(replyList);

            freeBoardRepository.save(board);

        });

    }

    @Test
    public void testInsert1Way(){//단 방향 처리

        FreeBoard board=new FreeBoard();
        board.setBno(199L);

        FreeBoardReply reply=new FreeBoardReply();
        reply.setReply("reply ...");
        reply.setReplyer("replyer 01");
        reply.setBoard(board);

        freeBoardReplyRepository.save(reply);

    }

    @Test
    public void testList1(){

        Pageable page= PageRequest.of(0,10, Sort.Direction.DESC, "bno");

        freeBoardRepository.findByBnoGreaterThan(0L,page).forEach(board->{

            log.info(board.getBno()+": "+board.getTitle());

        });

    }

    @Transactional//지연 로딩을 이용하면서 댓글을 같이 가져오고 싶으면 트랜잭션 적용
    @Test
    public void testList2(){

        Pageable page=PageRequest.of(0,10, Sort.Direction.DESC,"bno");

        freeBoardRepository.findByBnoGreaterThan(0L,page).forEach(board->{
            log.info(board.getBno()+": "+board.getTitle()+": "+board.getReplies().size());

        });
    }

    @Test
    public void testList3(){

        Pageable page=PageRequest.of(0,10,Sort.Direction.DESC, "bno");

        freeBoardRepository.getPage(page).forEach(arr->
                log.info(Arrays.toString(arr)));

    }
}
