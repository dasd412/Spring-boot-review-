package org.dasd.controller;

import java.util.List;
import lombok.extern.java.Log;
import org.dasd.domain.WebBoard;
import org.dasd.domain.WebReply;
import org.dasd.persistence.WebReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@Log
@RestController
@RequestMapping("/replies/*")
public class WebReplyController {

    @Autowired
    private WebReplyRepository repository;
    
    @Transactional
    @PostMapping("/{bno}")
    public ResponseEntity<List<WebReply>> addReply(@PathVariable("bno")Long bno, @RequestBody WebReply reply){
        //@PathVariable은 URI의 일부를 파라미터로 받기 위해서 사용함.
        //@RequestBody는 JSON 데이터를 자동으로 객체로 변환해줌.

        log.info("addReply...");
        log.info("BNO"+bno);
        log.info("REPLY: "+reply);
        
        WebBoard board=new WebBoard();
        board.setBno(bno);
        
        reply.setBoard(board);
        
        repository.save(reply);
        

        return new ResponseEntity<>(getListByBoard(board),HttpStatus.CREATED);//<-코드를 이용하여 직접 HTTP Response 상태 코드와 데이터 직접 제어
    }

    private List<WebReply> getListByBoard(WebBoard board) {
        log.info("get list by board..."+board);

        return repository.getReplies(board);
    }


}
