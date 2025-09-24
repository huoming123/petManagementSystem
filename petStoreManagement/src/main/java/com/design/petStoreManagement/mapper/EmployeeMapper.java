package com.design.petStoreManagement.mapper;

import com.design.petStoreManagement.pojo.Employee;
import java.util.List;
import com.design.petStoreManagement.dto.Page;
import org.apache.ibatis.annotations.Param;
/**
 * (Employee)表数据库访问层
 *
 * @author makejava
 */
public interface EmployeeMapper {
 
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Employee queryById(Integer id);
    
        /**
     * 分页查询数据
     *
     * @param 
     * @return 实例对象
     */
    List<Employee> getPageListByCondition(@Param("page") Page<Employee> page);
    /**
     * 分页查询数据 查询总条数
     *
     * @param 
     * @return 实例对象
     */
    Integer getPageListCount(@Param("page") Page<Employee> page);


    /**
     * 新增数据
     *
     * @param employee 实例对象
     * @return 影响行数
     */
    int insert(Employee employee);




    /**
     * 修改数据
     *
     * @param employee 实例对象
     * @return 影响行数
     */
    int update(Employee employee);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);


    Employee getByCardID(String cardId);

    Employee getByUserNo(String userNo);

    Employee getByTelephone(String telephone);
}

