package com.design.petStoreManagement.service.impl;

import com.design.petStoreManagement.mapper.EmployeeMapper;
import com.design.petStoreManagement.pojo.Employee;
import com.design.petStoreManagement.pojo.Manager;
import com.design.petStoreManagement.mapper.ManagerMapper;
import com.design.petStoreManagement.pojo.Users;
import com.design.petStoreManagement.service.ManagerService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import com.design.petStoreManagement.pojo.res.RestFulBean;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import com.design.petStoreManagement.util.PageUtil;
import com.design.petStoreManagement.dto.Page;
import org.springframework.util.DigestUtils;

/**
 * (Manager)表服务实现类
 *
 * @author makejava
 */
@Service("managerService")
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerMapper managerMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
         /**
     * 分页查询数据
     *
     * @param //前端传来的参数
     * @return 实例对象
     */
    @Override
    public RestFulBean<Map> getList(Page<Manager> page) throws Exception{
        //mysql分页要先在外面计算好从第几条数据开始获取数据
        Integer pageNum =page.getPageNum();
        Integer startNum =(pageNum-1)* page.getPageSize();
        page.setStartNum(startNum);
        //根据前端传来的的条件进行查询  //分页查询
        List<Manager> list= managerMapper.getPageListByCondition(page);
        if(list.size()>0){  //如果数据大于0 用for循环把照片的完整路劲返回前端显示
            for(Manager manager: list){
               
            }
        }
        //根据条件查询数据的条数
        Integer count = managerMapper.getPageListCount(page);
        //拿到总条数进行分页处理
        Map<Object, Object> map = PageUtil.pagingPrepare(page, count);
        //最后把查询到的数据用map返回前显示
        map.put("list",list);
        return RestFulBean.succ(map);
    }
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public RestFulBean<Manager> queryById(Integer id) {
       Manager manager=this.managerMapper.queryById(id);
        manager.setImagesUrl("http://localhost:8087/file/"+manager.getImages());
         return RestFulBean.succ(manager);
    }

    /**
     * 新增数据
     *
     * @param manager 实例对象
     * @return 实例对象
     */
    @Override
    public RestFulBean<String> insert(Manager manager) {
        this.managerMapper.insert(manager);//执行数据库的新增语句
        return RestFulBean.succ("添加成功");
    }

    /**
     * 修改数据
     *
     * @param manager 实例对象
     * @return 实例对象
     */
    @Override
     public RestFulBean<String> update(Manager manager) {
        this.managerMapper.update(manager);//执行数据库的修改语句 根据id 修改
        return RestFulBean.succ("修改成功"); 
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public RestFulBean<String> deleteById(Integer id) {
        this.managerMapper.deleteById(id);//执行数据库的删除语句 根据id 删除
         return RestFulBean.succ("删除成功");
    }

    @Override
    public RestFulBean<String> updatePassword(Users users) {
        if(users.getRole().equals("管理员")){
            Manager manager=this.managerMapper.queryById(users.getId());
            //判断旧密码跟数据库是否一样
            if(!users.getOldPassword().equals(manager.getPassword())){
                return RestFulBean.error("旧密码错误");
            }
            manager.setPassword(users.getNewsPassword());
            this.managerMapper.update(manager);//执行数据库的修改语句 根据id 修改
        }
        if(users.getRole().equals("店员")){
            Employee employee=this.employeeMapper.queryById(users.getId());
            if(!users.getOldPassword().equals(employee.getPassword())){
                return RestFulBean.error("旧密码错误");
            }
            employee.setPassword(users.getNewsPassword());
            this.employeeMapper.update(employee);//执行数据库的修改语句 根据id 修改
        }
        return RestFulBean.succ("修改成功");
    }

}
