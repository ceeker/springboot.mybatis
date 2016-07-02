package org.ceeker.web.sbootm.controller;

import javax.annotation.Resource;

import org.ceeker.web.sbootm.domain.User;
import org.ceeker.web.sbootm.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class UserController {

    @Resource
    UserService userService;

    @RequestMapping("/all")
    @ResponseBody
    public User all() {
        return userService.getByUserName("");
    }

    @RequestMapping("{id}")
    @ResponseBody
    public User getByid(@PathVariable int id) {
        return userService.getById(id);
    }

    @RequestMapping("save")
    @ResponseBody
    public User save(User user) {
        return userService.save(user);
    }

}
