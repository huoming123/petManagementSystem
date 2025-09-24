package com.design.petStoreManagement.service;

import com.design.petStoreManagement.pojo.Peter;
import com.design.petStoreManagement.pojo.res.RestFulBean;
import com.design.petStoreManagement.dto.Page;
import java.io.IOException;
import java.util.Map;
/**
 * (Peter)表服务接口
 *
 * @author makejava
 */
public interface PeterService {
     
        /**
     * 分页查找数据
     *
     * @param //前端传来的参数
     * @return 实例对象
     */
     RestFulBean<Map> getList(Page<Peter> page)throws Exception;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    RestFulBean<Peter> queryById(Integer id);


    /**
     * 新增数据
     *
     * @param peter 实例对象
     * @return 实例对象
     */
    RestFulBean<String> insert(Peter peter);

    /**
     * 修改数据
     *
     * @param peter 实例对象
     * @return 实例对象
     */
   RestFulBean<String> update(Peter peter);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    RestFulBean<String> deleteById(Integer id);

}
