package com.design.petStoreManagement.mapper;

import com.design.petStoreManagement.pojo.PetOrder;
import java.util.List;
import com.design.petStoreManagement.dto.Page;
import org.apache.ibatis.annotations.Param;
/**
 * (PetOrder)表数据库访问层
 *
 * @author makejava
 */
public interface PetOrderMapper {
 
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PetOrder queryById(Integer id);
    
        /**
     * 分页查询数据
     *
     * @param 
     * @return 实例对象
     */
    List<PetOrder> getPageListByCondition(@Param("page") Page<PetOrder> page);
    /**
     * 分页查询数据 查询总条数
     *
     * @param 
     * @return 实例对象
     */
    Integer getPageListCount(@Param("page") Page<PetOrder> page);


    /**
     * 新增数据
     *
     * @param petOrder 实例对象
     * @return 影响行数
     */
    int insert(PetOrder petOrder);




    /**
     * 修改数据
     *
     * @param petOrder 实例对象
     * @return 影响行数
     */
    int update(PetOrder petOrder);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    PetOrder queryByOrderNo(String orderNo);

    void pay(PetOrder petOrder);

    void saleCancel(PetOrder petOrder);
}

