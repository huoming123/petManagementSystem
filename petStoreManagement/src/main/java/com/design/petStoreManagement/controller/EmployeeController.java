package com.design.petStoreManagement.controller;

import com.design.petStoreManagement.pojo.Employee;
import com.design.petStoreManagement.service.EmployeeService;
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
 * (Employee)表控制层
 *
 * @author makejava
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    /**
     * 服务对象
     */
    @Autowired
    private EmployeeService employeeService;
    
     /**
     * 分页查询
     *
     * @param //前端传来的参数
     * @return 单条数据
     */
   @PostMapping("/get/page/list")
    public RestFulBean<Map> getList(@RequestBody Page<Employee>page) throws Exception{
        return this.employeeService.getList(page);
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
   @PostMapping("/get/by/id")
    public RestFulBean<Employee> queryById(@RequestBody Employee employee) {
        return this.employeeService.queryById(employee.getId());
    }

    /**
     * 新增数据
     *
     * @param employee 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public RestFulBean<String> add(@RequestBody Employee employee) {
        return this.employeeService.insert(employee);
    }

    /**
     * 编辑数据
     *
     * @param employee 实体
     * @return 编辑结果
     */
    @PostMapping("/update")
    public RestFulBean<String> edit(@RequestBody Employee employee) {
        return this.employeeService.update(employee);
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
   @PostMapping("/delete")
    public RestFulBean<String> deleteById(@RequestBody Employee employee) {
        return this.employeeService.deleteById(employee.getId());
    }
    /**
     * 密码找回
     *
     * @param //前端传来的参数
     * @return 单条数据
     */
    @PostMapping("/retrievePassword")
    public RestFulBean<String>  retrievePassword(@RequestBody Employee employee) throws Exception{
        return this.employeeService.retrievePassword(employee);
    }
}

