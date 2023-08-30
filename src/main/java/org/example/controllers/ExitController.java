package org.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@Controller
@RequestMapping("/exit")
public class ExitController {
    @GetMapping
    public String exitMake(HttpServletRequest request) {
//        LOG.info("ExitService is starting");
        HttpSession session = request.getSession();
        session.invalidate();
        return "index";
    }

}
