package com.design.petStoreManagement.service;

import com.design.petStoreManagement.dto.Page;
import com.design.petStoreManagement.pojo.Product;
import com.design.petStoreManagement.pojo.res.RestFulBean;

import java.util.List;
import java.util.Map;

/**
 * (Product)表服务接口
 *
 * @author makejava
 * @since
 */
public interface ProductService {
     
        /**
     * 分页查找数据
     *
     * @param //前端传来的参数
     * @return 实例对象
     */
     RestFulBean<Map> getList(Page<Product> page)throws Exception;
    /**
     * 通过ID查询单条数据
     queryByProductNo
     * @param id 主键
     * @return 实例对象
     */
    RestFulBean<Product> queryById(Integer id);


    /**
     * 新增数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    RestFulBean<String> insert(Product product);

    /**
     * 修改数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
   RestFulBean<String> update(Product product);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    RestFulBean<String> deleteById(Integer id);

    RestFulBean<List<Product>> getProductList();

    RestFulBean<Product> queryByProductNo(String productNo);
}
