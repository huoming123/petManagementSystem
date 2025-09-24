package com.design.petStoreManagement.mapper;

import com.design.petStoreManagement.pojo.Variety;
import java.util.List;
import com.design.petStoreManagement.dto.Page;
import org.apache.ibatis.annotations.Param;
/**
 * (Variety)表数据库访问层
 *
 * @author makejava
 */
public interface VarietyMapper {
 
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Variety queryById(Integer id);
    
        /**
     * 分页查询数据
     *
     * @param 
     * @return 实例对象
     */
    List<Variety> getPageListByCondition(@Param("page") Page<Variety> page);
    /**
     * 分页查询数据 查询总条数
     *
     * @param 
     * @return 实例对象
     */
    Integer getPageListCount(@Param("page") Page<Variety> page);


    /**
     * 新增数据
     *
     * @param variety 实例对象
     * @return 影响行数
     */
    int insert(Variety variety);




    /**
     * 修改数据
     *
     * @param variety 实例对象
     * @return 影响行数
     */
    int update(Variety variety);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<Variety> getVarietyList();
}

