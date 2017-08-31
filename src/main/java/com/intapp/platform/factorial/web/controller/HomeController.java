package com.intapp.platform.factorial.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * The type Home controller.
 */
@Controller
public class HomeController {

    private static final String VIEW_INDEX = "index.html";


    /**
     * Home string.
     *
     * @return the string
     */
    @GetMapping("/")
    public String home() {
        return VIEW_INDEX;
    }
}

