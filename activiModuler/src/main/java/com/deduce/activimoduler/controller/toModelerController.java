package com.deduce.activimoduler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/modeler")
@Controller
public class toModelerController {

    @GetMapping(value = "/tomodeler")
    public String toModeler() {
        System.out.println("-----");
        return "modeler";
    }
}
