package org.ceeker.web.sbootm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebSocketController {

    @RequestMapping("/login")
    public String login() {
        return "common/login";
    }


}
