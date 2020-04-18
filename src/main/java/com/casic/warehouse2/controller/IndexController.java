package com.casic.warehouse2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/toIndex")
    public String toIndex(){
        return "views/index";
    }

    @RequestMapping("/toConsole")
    public String toConsole(){
        return "views/home/console";
    }
}
