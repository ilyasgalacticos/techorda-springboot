package kz.bitlab.springboot.techboot.controller;

import kz.bitlab.springboot.techboot.beans.TestA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @Autowired
    private TestA testA;

    @GetMapping(value = "/test-b")
    public String testB(Model model){

        model.addAttribute("testA", testA);
        return "test";
    }

    @GetMapping(value = "/change-test-b")
    public String changeTestB(){
        testA.setName("Ilyas");
        testA.setPrice(77777);
        return "redirect:/test-b";
    }

}