package com.design.petStoreManagement.mapper;

import com.design.petStoreManagement.pojo.Peter;
import java.util.List;
import com.design.petStoreManagement.dto.Page;
import org.apache.ibatis.annotations.Param;
/**
 * (Peter)表数据库访问层
 *
 * @author makejava
 */
public interface PeterMapper {
 
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Peter queryById(Integer id);
    
        /**
     * 分页查询数据
     *
     * @param 
     * @return 实例对象
     */
    List<Peter> getPageListByCondition(@Param("page") Page<Peter> page);
    /**
     * 分页查询数据 查询总条数
     *
     * @param 
     * @return 实例对象
     */
    Integer getPageListCount(@Param("page") Page<Peter> page);


    /**
     * 新增数据
     *
     * @param peter 实例对象
     * @return 影响行数
     */
    int insert(Peter peter);




    /**
     * 修改数据
     *
     * @param peter 实例对象
     * @return 影响行数
     */
    int update(Peter peter);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

