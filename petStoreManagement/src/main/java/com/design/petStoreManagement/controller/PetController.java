package com.design.petStoreManagement.controller;

import com.design.petStoreManagement.pojo.Pet;
import com.design.petStoreManagement.service.PetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import com.design.petStoreManagement.pojo.res.RestFulBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.design.petStoreManagement.dto.Page;
import java.io.IOException;
import java.util.Map;
/**
 * (Pet)表控制层
 *
 * @author makejava
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    /**
     * 服务对象
     */
    @Autowired
    private PetService petService;
    
     /**
     * 分页查询
     *
     * @param //前端传来的参数
     * @return 单条数据
     */
   @PostMapping("/get/page/list")
    public RestFulBean<Map> getList(@RequestBody Page<Pet>page) throws Exception{
        return this.petService.getList(page);
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
   @PostMapping("/get/by/id")
    public RestFulBean<Pet> queryById(@RequestBody Pet pet) {
        return this.petService.queryById(pet.getId());
    }

    /**
     * 新增数据
     *
     * @param pet 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public RestFulBean<String> add(@RequestBody Pet pet) {
        return this.petService.insert(pet);
    }

    /**
     * 编辑数据
     *
     * @param pet 实体
     * @return 编辑结果
     */
    @PostMapping("/update")
    public RestFulBean<String> edit(@RequestBody Pet pet) {
        return this.petService.update(pet);
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
   @PostMapping("/delete")
    public RestFulBean<String> deleteById(@RequestBody Pet pet) {
        return this.petService.deleteById(pet.getId());
    }
    /**
     * 获取首页数据
     *
     * @param
     * @return
     */
    @PostMapping("/get/index/list")
    public RestFulBean<Map> getIndexList() throws Exception{
        return this.petService.getIndexList();
    }
}

