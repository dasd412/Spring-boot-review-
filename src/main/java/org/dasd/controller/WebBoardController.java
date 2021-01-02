package org.dasd.controller;

import lombok.extern.java.Log;
import org.dasd.domain.WebBoard;
import org.dasd.persistence.CustomCrudRepository;
import org.dasd.persistence.WebBoardRepository;
import org.dasd.vo.PageMaker;
import org.dasd.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/boards/")
@Log
public class WebBoardController {

    @Autowired
   // private WebBoardRepository repository;

    private CustomCrudRepository repository;

    @GetMapping("/list")
    public void list(@ModelAttribute("pageVO") PageVO vo , Model model){

        Pageable page=vo.makePageable(0,"bno");
        Page<Object[]>result=repository.getCustomPage(vo.getType(),vo.getKeyword(),page);

        log.info(""+page);
        log.info(""+result);

        log.info("TOTAL PAGE NUMBER : "+result.getTotalPages());
        model.addAttribute("result",new PageMaker<>(result));
    }

    @GetMapping("/register")
    public void registerGet(@ModelAttribute("vo")WebBoard vo){
        log.info("register get");
    }

    @PostMapping("/register")
    public String registerPost(@ModelAttribute("vo")WebBoard vo, RedirectAttributes rttr){
        log.info("register post");
        log.info(""+vo);

        repository.save(vo);
        rttr.addFlashAttribute("msg","success");

        return "redirect:/boards/list";
    }

    @GetMapping("/view")
    public void view(Long bno, @ModelAttribute("pageVO")PageVO vo, Model model){
        log.info("BNO : "+bno);

        repository.findById(bno).ifPresent(board->model.addAttribute("vo",board));
    }

    @GetMapping("/modify")
    public void modify(Long bno, @ModelAttribute("pageVO")PageVO vo, Model model){//수정하는 화면만을 보여주므로 GET
        log.info("Modify BNO : "+bno);

        repository.findById(bno).ifPresent(board->model.addAttribute("vo",board));
    }

    @PostMapping("/delete")
    public String delete(Long bno, PageVO vo, RedirectAttributes rttr){//실제 삭제해야하므로 POST

        log.info("DELETE BNO : "+bno);

        repository.deleteById(bno);

        rttr.addFlashAttribute("msg","success");

        //페이징과 검색했던 결과로 이동하는 경우

        rttr.addAttribute("page",vo.getPage());
        rttr.addAttribute("size",vo.getSize());
        rttr.addAttribute("type",vo.getType());
        rttr.addAttribute("keyword",vo.getKeyword());

        return "redirect:/boards/list";

    }

    @PostMapping("/modify")
    public String modifyPost(WebBoard board,PageVO vo, RedirectAttributes rttr){//실제로 수정하므로 POST

        log.info("Modify webBoard: "+board);

        repository.findById(board.getBno()).ifPresent(origin->{

            origin.setTitle(board.getTitle());
            origin.setContent(board.getContent());

            repository.save(origin);

            rttr.addFlashAttribute("msg","success");
            rttr.addAttribute("bno",origin.getBno());


        });


        //페이징과 검색했던 결과로 이동하는 경우
        rttr.addAttribute("page",vo.getPage());
        rttr.addAttribute("size",vo.getSize());
        rttr.addAttribute("type",vo.getType());
        rttr.addAttribute("keyword",vo.getKeyword());

        return "redirect:/boards/view";

    }

}
