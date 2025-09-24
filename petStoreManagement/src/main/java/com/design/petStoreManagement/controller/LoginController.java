package com.design.petStoreManagement.controller;


import com.design.petStoreManagement.pojo.Users;
import com.design.petStoreManagement.pojo.res.RestFulBean;
import com.design.petStoreManagement.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;
    /**
     *
     * @param Users 需要name和password
     * @return data token
     */
    @PostMapping("/login")
    public RestFulBean<Users> login(@RequestBody  Users Users) throws Exception {
        return loginService.login(Users);
    }
}
