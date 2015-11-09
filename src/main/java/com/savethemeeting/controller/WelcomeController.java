package com.savethemeeting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Michaela on 09.11.2015.
 */
@Controller
public class WelcomeController {
    @RequestMapping("/home")
    public ModelAndView welcome() {
        return new ModelAndView("index");
    }
}
