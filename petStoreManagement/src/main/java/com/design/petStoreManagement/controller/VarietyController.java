package com.design.petStoreManagement.controller;

import com.design.petStoreManagement.pojo.Variety;
import com.design.petStoreManagement.service.VarietyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import com.design.petStoreManagement.pojo.res.RestFulBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.design.petStoreManagement.dto.Page;
import java.io.IOException;
import java.util.List;
import java.util.Map;
/**
 * (Variety)表控制层
 *
 * @author makejava
 */
@RestController
@RequestMapping("/variety")
public class VarietyController {
    /**
     * 服务对象
     */
    @Autowired
    private VarietyService varietyService;
    
     /**
     * 分页查询
     *
     * @param //前端传来的参数
     * @return 单条数据
     */
   @PostMapping("/get/page/list")
    public RestFulBean<Map> getList(@RequestBody Page<Variety>page) throws Exception{
        return this.varietyService.getList(page);
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
   @PostMapping("/get/by/id")
    public RestFulBean<Variety> queryById(@RequestBody Variety variety) {
        return this.varietyService.queryById(variety.getId());
    }

    /**
     * 新增数据
     *
     * @param variety 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public RestFulBean<String> add(@RequestBody Variety variety) {
        return this.varietyService.insert(variety);
    }

    /**
     * 编辑数据
     *
     * @param variety 实体
     * @return 编辑结果
     */
    @PostMapping("/update")
    public RestFulBean<String> edit(@RequestBody Variety variety) {
        return this.varietyService.update(variety);
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
   @PostMapping("/delete")
    public RestFulBean<String> deleteById(@RequestBody Variety variety) {
        return this.varietyService.deleteById(variety.getId());
    }
    @PostMapping("/get/variety/list")
    public RestFulBean<List<Variety>> getVarietyList() throws Exception{
        return this.varietyService.getVarietyList();
    }

}

