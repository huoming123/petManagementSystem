package com.design.petStoreManagement.mapper;

import com.design.petStoreManagement.pojo.Pet;
import java.util.List;
import com.design.petStoreManagement.dto.Page;
import org.apache.ibatis.annotations.Param;
/**
 * (Pet)表数据库访问层
 *
 * @author makejava
 */
public interface PetMapper {
 
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Pet queryById(Integer id);
    
        /**
     * 分页查询数据
     *
     * @param 
     * @return 实例对象
     */
    List<Pet> getPageListByCondition(@Param("page") Page<Pet> page);
    /**
     * 分页查询数据 查询总条数
     *
     * @param 
     * @return 实例对象
     */
    Integer getPageListCount(@Param("page") Page<Pet> page);


    /**
     * 新增数据
     *
     * @param pet 实例对象
     * @return 影响行数
     */
    int insert(Pet pet);




    /**
     * 修改数据
     *
     * @param pet 实例对象
     * @return 影响行数
     */
    int update(Pet pet);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<Pet> getIndexPetList();

    Pet getByPetName(String petName);
}

