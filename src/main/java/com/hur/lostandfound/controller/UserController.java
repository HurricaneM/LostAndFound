package com.hur.lostandfound.controller;

import com.hur.lostandfound.bean.Msg;
import com.hur.lostandfound.bean.User;
import com.hur.lostandfound.service.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @PostMapping("/user")
    public Msg registered(User user){
        System.out.println("userPost");
        System.out.println(user.getUsername());
        userService.insertUser(user);
        return Msg.success();
    }

    @GetMapping("/user")
    public Msg getAllUser(){
        System.out.println("no password");
        List<User> users = userService.selectAllUser();
        return Msg.success().add("users",users);
    }

    @GetMapping("/user/{username}/{password}")
    public Msg getAllUser(@PathVariable String username, @PathVariable String password, HttpServletRequest request){
        System.out.println("password");
        System.out.println("username="+username);
        System.out.println("password="+password);

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        User user1 = userService.selectOneByExp(user);
        if (user1==null){
            System.out.println("user1 is null");
            return Msg.fail();
        }else {
            request.getSession().setAttribute("user",username);
            System.out.println(user1.toString());
            return Msg.success().add("user",user1);
        }
    }

    @GetMapping("/user/{username}")
    public Msg getUserCompetence(@PathVariable String username){
        System.out.println("findCompetence");
        User user = new User();
        user.setUsername(username);
        User user1 = userService.selectOneByExp(user);
        System.out.println(user1.toString());
        return Msg.success().add("user",user1);
    }
}
