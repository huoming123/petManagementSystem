package com.design.petStoreManagement.service;

import com.design.petStoreManagement.pojo.Variety;
import com.design.petStoreManagement.pojo.res.RestFulBean;
import com.design.petStoreManagement.dto.Page;
import java.io.IOException;
import java.util.List;
import java.util.Map;
/**
 * (Variety)表服务接口
 *
 * @author makejava
 */
public interface VarietyService {
     
        /**
     * 分页查找数据
     *
     * @param //前端传来的参数
     * @return 实例对象
     */
     RestFulBean<Map> getList(Page<Variety> page)throws Exception;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    RestFulBean<Variety> queryById(Integer id);


    /**
     * 新增数据
     *
     * @param variety 实例对象
     * @return 实例对象
     */
    RestFulBean<String> insert(Variety variety);

    /**
     * 修改数据
     *
     * @param variety 实例对象
     * @return 实例对象
     */
   RestFulBean<String> update(Variety variety);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    RestFulBean<String> deleteById(Integer id);

    RestFulBean<List<Variety>> getVarietyList();
}
