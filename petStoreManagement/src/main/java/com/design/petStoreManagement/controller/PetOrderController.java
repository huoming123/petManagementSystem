package com.design.petStoreManagement.controller;

import com.design.petStoreManagement.pojo.PetOrder;
import com.design.petStoreManagement.service.PetOrderService;
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
 * (PetOrder)表控制层
 *
 * @author makejava
 */
@RestController
@RequestMapping("/petOrder")
public class PetOrderController {
    /**
     * 服务对象
     */
    @Autowired
    private PetOrderService petOrderService;
    
     /**
     * 分页查询
     *
     * @param //前端传来的参数
     * @return 单条数据
     */
   @PostMapping("/get/page/list")
    public RestFulBean<Map> getList(@RequestBody Page<PetOrder>page) throws Exception{
        return this.petOrderService.getList(page);
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
   @PostMapping("/get/by/id")
    public RestFulBean<PetOrder> queryById(@RequestBody PetOrder petOrder) {
        return this.petOrderService.queryById(petOrder.getId());
    }



    /**
     * 编辑数据
     *
     * @param petOrder 实体
     * @return 编辑结果
     */
    @PostMapping("/update")
    public RestFulBean<String> edit(@RequestBody PetOrder petOrder) {
        return this.petOrderService.update(petOrder);
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
   @PostMapping("/delete")
    public RestFulBean<String> deleteById(@RequestBody PetOrder petOrder) {
        return this.petOrderService.deleteById(petOrder.getId());
    }
    /**
     * 新增数据
     *
     * @param petOrder 实体
     * @return 新增结果
     */
    @PostMapping("/sale/add")
    public RestFulBean<String> add(@RequestBody PetOrder petOrder) {
        return this.petOrderService.insert(petOrder);
    }
    /**
     * 通过订单编号查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @PostMapping("/get/by/orderNo")
    public RestFulBean<PetOrder> queryByOrderNo(@RequestBody PetOrder petOrde) {
        return this.petOrderService.queryByOrderNo(petOrde.getOrderNo());
    }

    /**
     * 宠物销售支付
     *
     * @param id 主键
     */
    @PostMapping("/sale/pay")
    public RestFulBean<String> salePay(@RequestBody PetOrder petOrder) throws Exception {
        return this.petOrderService.salePay(petOrder);
    }

    /**
     * 宠物订单取消支付
     *
     * @param id 主键
     */
    @PostMapping("/sale/cancel")
    public RestFulBean<String> saleCancel(@RequestBody PetOrder petOrder) {
        return this.petOrderService.saleCancel(petOrder);
    }

}

