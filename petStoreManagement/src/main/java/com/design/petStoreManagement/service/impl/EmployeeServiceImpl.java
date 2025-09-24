package com.design.petStoreManagement.service.impl;

import com.design.petStoreManagement.pojo.Employee;
import com.design.petStoreManagement.mapper.EmployeeMapper;
import com.design.petStoreManagement.service.EmployeeService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import com.design.petStoreManagement.pojo.res.RestFulBean;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.design.petStoreManagement.util.PageUtil;
import com.design.petStoreManagement.dto.Page;
/**
 * (Employee)表服务实现类
 *
 * @author makejava
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
         /**
     * 分页查询数据
     *
     * @param //前端传来的参数
     * @return 实例对象
     */
    @Override
    public RestFulBean<Map> getList(Page<Employee> page) throws Exception{
        //mysql分页要先在外面计算好从第几条数据开始获取数据
        Integer pageNum =page.getPageNum();
        Integer startNum =(pageNum-1)* page.getPageSize();
        page.setStartNum(startNum);
        //根据前端传来的的条件进行查询  //分页查询
        List<Employee> list= employeeMapper.getPageListByCondition(page);
        if(list.size()>0){  //如果数据大于0 用for循环把照片的完整路劲返回前端显示
            for(Employee employee: list){
                employee.setImagesUrl("http://localhost:8087/file/"+employee.getImages());
            }
        }
        //根据条件查询数据的条数
        Integer count = employeeMapper.getPageListCount(page);
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
    public RestFulBean<Employee> queryById(Integer id) {
       Employee employee=this.employeeMapper.queryById(id);
        employee.setImagesUrl("http://localhost:8087/file/"+employee.getImages());
         return RestFulBean.succ(employee);
    }

    /**
     * 新增数据
     *
     * @param employee 实例对象
     * @return 实例对象
     */
    @Override
    public RestFulBean<String> insert(Employee employee) {
        if(employee.getUserName()==null){//对前端传过来的值进行判空处理，如果为空，就返回对应的报错信息
            return RestFulBean.error("用户名不能为空");
        }
        if(employee.getTelephone()==null)
        {
            return RestFulBean.error("手机号码不能为空");
        }
        if(employee.getUserNo()==null)
        {
            return RestFulBean.error("员工编号不能为空");
        }
        if(employee.getCardId()==null)
        {
            return RestFulBean.error("身份证不能为空");
        }
        if(employee.getCardId().length()!=18)
        {
            return RestFulBean.error("请输入18位身份证");
        }
        if(employee.getTelephone().trim().length()!=11){
            return RestFulBean.error("请输入11位手机号码");
        }
        //手机号码只能是数字 用正则法则判断
        Pattern pattern = Pattern.compile("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
        Matcher isNum = pattern.matcher(employee.getTelephone().trim());
        if(!isNum.matches()){  //如果不等于true 则进去条件
            return RestFulBean.error("手机号码只能是数字"); //不是数字
        }
        Employee employeed =employeeMapper.getByUserNo(employee.getUserNo());//根据员工编号判断是否录入
        if(employeed!=null){ //如果不为空 说明身份证已录入
            return RestFulBean.error("该员工编号已录入");
        }
        employee.setPassword("123456"); //默认密码
        this.employeeMapper.insert(employee);//执行数据库的新增语句
        return RestFulBean.succ("录入成功,该员工编号为"+employee.getUserNo()+"密码默认为123456");
    }

    /**
     * 修改数据
     *
     * @param employee 实例对象
     * @return 实例对象
     */
    @Override
     public RestFulBean<String> update(Employee employee) {
        this.employeeMapper.update(employee);//执行数据库的修改语句 根据id 修改
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
        this.employeeMapper.deleteById(id);//执行数据库的删除语句 根据id 删除
         return RestFulBean.succ("删除成功");
    }

    @Override
    public RestFulBean<String> retrievePassword(Employee employee) {
        Employee usersed =employeeMapper.getByTelephone(employee.getTelephone()); //根据手机号获取数据
        if(usersed==null){
            return RestFulBean.error("请输入正确的手机号码");
        }
        return RestFulBean.succ("您的系统密码为"+usersed.getPassword());
    }
}
