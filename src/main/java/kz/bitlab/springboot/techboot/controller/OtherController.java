package kz.bitlab.springboot.techboot.controller;

import kz.bitlab.springboot.techboot.beans.TestA;
import kz.bitlab.springboot.techboot.beans.TestB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OtherController {

    @Autowired
    private TestA testA;

    @Autowired
    @Qualifier("ilyasBean")
    private TestB asdasdasd;

    @GetMapping(value = "/test-a")
    public String testA(Model model){

        System.out.println(testA.getName() + " " + testA.getPrice());
        model.addAttribute("testA", testA);

        System.out.println(asdasdasd.getCode() + " " + asdasdasd.getVolume());
        return "test";
    }

}