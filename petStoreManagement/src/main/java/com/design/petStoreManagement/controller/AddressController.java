package com.design.petStoreManagement.controller;

import com.design.petStoreManagement.dto.Page;
import com.design.petStoreManagement.pojo.Address;
import com.design.petStoreManagement.pojo.res.RestFulBean;
import com.design.petStoreManagement.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * (Address)表控制层
 *
 * @author makejava
 */
@RestController
@RequestMapping("/address")
public class AddressController {
    /**
     * 服务对象
     */
    @Autowired
    private AddressService addressService;
    
     /**
     * 分页查询
     *
     * @param //前端传来的参数
     * @return 单条数据
     */
   @PostMapping("/get/page/list")
    public RestFulBean<Map> getList(@RequestBody Page<Address>page) throws Exception{
        return this.addressService.getList(page);
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
   @PostMapping("/get/by/id")
    public RestFulBean<Address> queryById(@RequestBody Address address) {
        return this.addressService.queryById(address.getId());
    }

    /**
     * 新增数据
     *
     * @param address 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public RestFulBean<String> add(@RequestBody Address address) {
        return this.addressService.insert(address);
    }

    /**
     * 编辑数据
     *
     * @param address 实体
     * @return 编辑结果
     */
    @PostMapping("/update")
    public RestFulBean<String> edit(@RequestBody Address address) {
        return this.addressService.update(address);
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
   @PostMapping("/delete")
    public RestFulBean<String> deleteById(@RequestBody Address address) {
        return this.addressService.deleteById(address.getId());
    }
    /**
     * 根据登陆人获取收获地址数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @PostMapping("/get/by/userId")
    public RestFulBean<List<Address>> queryByUserId(@RequestBody Address address) {
        return this.addressService.queryByUserId(address.getUserId());
    }
}

