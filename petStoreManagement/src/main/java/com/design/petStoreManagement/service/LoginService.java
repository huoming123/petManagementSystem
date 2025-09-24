package com.design.petStoreManagement.service;

import com.design.petStoreManagement.pojo.Users;
import com.design.petStoreManagement.pojo.res.RestFulBean;

import java.util.Map;

public interface LoginService {

    RestFulBean<Users> login(Users users) throws Exception;
}
