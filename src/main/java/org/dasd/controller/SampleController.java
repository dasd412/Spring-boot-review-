package org.dasd.controller;

import org.dasd.domain.MemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Timestamp;
import java.util.*;

@Controller
public class SampleController {

    @GetMapping("/sample1")
    public void sample1(Model model){
        //model.addAttribute("greeting","Hello WORLD");

        model.addAttribute("greeting","WHAT THE FUCK IS IT");
    }

    @GetMapping("/sample2")
    public void sample2(Model model){
        MemberVO vo=new MemberVO(123,"u00","p00","wh",new Timestamp(System.currentTimeMillis()));

        model.addAttribute("vo",vo);
    }

    @GetMapping("/sample3")
    public void sample3(Model model){

        List<MemberVO>list=new ArrayList<>();

        for (int i = 0; i <10 ; i++) {
            list.add(new MemberVO(123,"u0"+i,"p0"+i,"wh"+i,new Timestamp(System.currentTimeMillis())));
        }

        model.addAttribute("list",list);

    }
}
