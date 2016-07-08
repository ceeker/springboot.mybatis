package org.ceeker.web.sbootm.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.ceeker.web.sbootm.domain.User;
import org.ceeker.web.sbootm.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
@RequestMapping("user")
public class UserController extends BaseController{

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
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, String[]> map = req.getParameterMap();
        int a=1/0;
        log.info("request params="+req.getParameter("id"));
        return userService.getById(id);
    }

    @RequestMapping("save")
    @ResponseBody
    public User save(@Valid User user,BindingResult result) {
        if(result.hasFieldErrors()){
            List<ObjectError> errors=   result.getAllErrors();
            log.info("request params="); 
        }
        return user;
//        return userService.save(user);
    }

}
