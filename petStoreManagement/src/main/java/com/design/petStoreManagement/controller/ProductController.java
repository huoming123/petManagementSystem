package com.design.petStoreManagement.controller;

import com.design.petStoreManagement.dto.Page;
import com.design.petStoreManagement.pojo.Product;
import com.design.petStoreManagement.pojo.res.RestFulBean;
import com.design.petStoreManagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * (Product)表控制层
 *
 * @author makejava
 * @since
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    /**
     * 服务对象
     */
    @Autowired
    private ProductService productService;
    
     /**
     * 分页查询
     *
     * @param //前端传来的参数
     * @return 单条数据
     */
   @PostMapping("/get/page/list")
    public RestFulBean<Map> getList(@RequestBody Page<Product>page) throws Exception{
        return this.productService.getList(page);
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
   @PostMapping("/get/by/id")
    public RestFulBean<Product> queryById(@RequestBody Product product) {
        return this.productService.queryById(product.getId());
    }
    /**
     * 通过商品编号获取商品详情数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @PostMapping("/get/by/productNo")
    public RestFulBean<Product> queryByProductNo(@RequestBody Product product) {
        return this.productService.queryByProductNo(product.getProductNo());
    }
    /**
     * 新增数据
     *
     * @param product 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public RestFulBean<String> add(@RequestBody Product product) {
        return this.productService.insert(product);
    }

    /**
     * 编辑数据
     *
     * @param product 实体
     * @return 编辑结果
     */
    @PostMapping("/update")
    public RestFulBean<String> edit(@RequestBody Product product) {
        return this.productService.update(product);
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
   @PostMapping("/delete")
    public RestFulBean<String> deleteById(@RequestBody Product product) {
        return this.productService.deleteById(product.getId());
    }
    /**
     * 获取商品下拉数据
     *
     * @param product 实体
     * @return 编辑结果
     */
    @PostMapping("/get/product/list")
    public RestFulBean<List<Product>> getProductList() {
        return this.productService.getProductList();
    }
}

