package org.ceeker.web.sbootm.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @Value(value = "${server.port}")
    private String port;

    @RequestMapping(value={"/","index"})
    public String index() {
        int a=1/0;
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/port")
    @ResponseBody
    public String device() {
        return "web server run at port:" + port;
    }

}
