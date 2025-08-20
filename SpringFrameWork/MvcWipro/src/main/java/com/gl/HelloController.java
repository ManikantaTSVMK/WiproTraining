package com.gl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

    @RequestMapping("/home")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        mav.addObject("message", "Welcome to Spring MVC!");
        return mav;
    }

	
}


//view resolver        > prefix webapp/  login  .html  suffix
//url mapping