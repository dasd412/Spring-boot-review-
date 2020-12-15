package org.dasd.controller;

import org.dasd.domain.SampleVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/hello")
    public String Hello(){
        return "hello";
    }

    @GetMapping("/sample")
    public SampleVO sample(){
        SampleVO vo=new SampleVO();
        vo.setVal1("1");
        vo.setVal2("2");
        vo.setVal3("3");
        System.out.println(vo.toString());

        return vo;


    }
}
