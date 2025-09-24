package com.design.petStoreManagement.service;

import com.design.petStoreManagement.pojo.Pet;
import com.design.petStoreManagement.pojo.res.RestFulBean;
import com.design.petStoreManagement.dto.Page;
import java.io.IOException;
import java.util.Map;
/**
 * (Pet)表服务接口
 *
 * @author makejava
 */
public interface PetService {
     
        /**
     * 分页查找数据
     *
     * @param //前端传来的参数
     * @return 实例对象
     */
     RestFulBean<Map> getList(Page<Pet> page)throws Exception;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    RestFulBean<Pet> queryById(Integer id);


    /**
     * 新增数据
     *
     * @param pet 实例对象
     * @return 实例对象
     */
    RestFulBean<String> insert(Pet pet);

    /**
     * 修改数据
     *
     * @param pet 实例对象
     * @return 实例对象
     */
   RestFulBean<String> update(Pet pet);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    RestFulBean<String> deleteById(Integer id);

    RestFulBean<Map> getIndexList();
}
