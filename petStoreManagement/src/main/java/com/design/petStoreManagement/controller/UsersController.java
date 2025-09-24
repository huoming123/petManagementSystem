package com.design.petStoreManagement.controller;

import com.design.petStoreManagement.pojo.Users;
import com.design.petStoreManagement.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import com.design.petStoreManagement.pojo.res.RestFulBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.design.petStoreManagement.dto.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
/**
 * (Users)表控制层
 *
 * @author makejava
 */
@RestController
@RequestMapping("/users")
public class UsersController {
    /**
     * 服务对象
     */
    @Autowired
    private UsersService usersService;
    
     /**
     * 分页查询
     *
     * @param //前端传来的参数
     * @return 单条数据
     */
   @PostMapping("/get/page/list")
    public RestFulBean<Map> getList(@RequestBody Page<Users>page) throws Exception{
        return this.usersService.getList(page);
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
   @PostMapping("/get/by/id")
    public RestFulBean<Users> queryById(@RequestBody Users users) {
        return this.usersService.queryById(users.getId());
    }

    /**
     * 新增数据
     *
     * @param users 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public RestFulBean<String> add(@RequestBody Users users) {
        return this.usersService.insert(users);
    }

    /**
     * 编辑数据
     *
     * @param users 实体
     * @return 编辑结果
     */
    @PostMapping("/update")
    public RestFulBean<String> edit(@RequestBody Users users) {
        return this.usersService.update(users);
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
   @PostMapping("/delete")
    public RestFulBean<String> deleteById(@RequestBody Users users) {
        return this.usersService.deleteById(users.getId());
    }
    //修改头像
    @PostMapping("/upload/picture")
    public RestFulBean<Map> uploadPicture(@RequestParam(value = "id",required = false) Integer id,@RequestParam(value = "coverFile",required = false) MultipartFile coverFile ) throws Exception {
        return usersService.uploadPicture(id,coverFile);
    }
    /**
     * 密码找回
     *
     * @param //前端传来的参数
     * @return 单条数据
     */
    @PostMapping("/retrievePassword")
    public RestFulBean<String>  retrievePassword(@RequestBody Users users) throws Exception{
        return this.usersService.retrievePassword(users);
    }
}

