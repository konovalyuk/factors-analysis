package com.intapp.tms.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The type Home controller.
 */
@Controller
public class HomeController {

    private static final String VIEW_INDEX = "redirect:swagger-ui.html";

//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public ModelAndView index(ModelAndView modelAndView) {
//        modelAndView.setViewName(VIEW_INDEX);
//        return modelAndView;
//    }

    /**
     * Home string.
     *
     * @return the string
     */
    @RequestMapping("/")
    public String home() {
        return VIEW_INDEX;
    }
}

