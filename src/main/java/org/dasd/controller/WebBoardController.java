package org.dasd.controller;

import lombok.extern.java.Log;
import org.dasd.domain.WebBoard;
import org.dasd.persistence.WebBoardRepository;
import org.dasd.vo.PageMaker;
import org.dasd.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/boards/")
@Log
public class WebBoardController {

    @Autowired
    private WebBoardRepository repository;


    @GetMapping("/list")
    public void list(PageVO vo , Model model){

        Pageable page=vo.makePageable(0,"bno");
        Page<WebBoard>result=repository.findAll(repository.makePredicate(null,null),page);

        log.info(""+page);
        log.info(""+result);

        log.info("TOTAL PAGE NUMBER : "+result.getTotalPages());
        model.addAttribute("result",new PageMaker<>(result));
    }
}
