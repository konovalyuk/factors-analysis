package com.intapp.tms.web.restcontroller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Exception handling controller.
 */
//@ControllerAdvice
@RestController
public class ExceptionHandlingController {

    /**
     * Handle exception model and view.
     *
     * @param req the req
     * @param ex  the ex
     * @return the model and view
     */
//    @ExceptionHandler(Exception.class)
//    public ModelAndView handleException(HttpServletRequest req, Exception ex) {
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("exception", ex);
//        mav.addObject("url", req.getRequestURL());
//        mav.setViewName("error");
//        return mav;
//    }
}
