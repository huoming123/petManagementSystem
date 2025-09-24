package com.design.petStoreManagement.controller;

import com.design.petStoreManagement.pojo.Peter;
import com.design.petStoreManagement.service.PeterService;
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
 * (Peter)表控制层
 *
 * @author makejava
 */
@RestController
@RequestMapping("/peter")
public class PeterController {
    /**
     * 服务对象
     */
    @Autowired
    private PeterService peterService;
    
     /**
     * 分页查询
     *
     * @param //前端传来的参数
     * @return 单条数据
     */
   @PostMapping("/get/page/list")
    public RestFulBean<Map> getList(@RequestBody Page<Peter>page) throws Exception{
        return this.peterService.getList(page);
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
   @PostMapping("/get/by/id")
    public RestFulBean<Peter> queryById(@RequestBody Peter peter) {
        return this.peterService.queryById(peter.getId());
    }

    /**
     * 新增数据
     *
     * @param peter 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public RestFulBean<String> add(@RequestBody Peter peter) {
        return this.peterService.insert(peter);
    }

    /**
     * 编辑数据
     *
     * @param peter 实体
     * @return 编辑结果
     */
    @PostMapping("/update")
    public RestFulBean<String> edit(@RequestBody Peter peter) {
        return this.peterService.update(peter);
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
   @PostMapping("/delete")
    public RestFulBean<String> deleteById(@RequestBody Peter peter) {
        return this.peterService.deleteById(peter.getId());
    }

}

