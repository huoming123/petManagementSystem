package com.design.petStoreManagement.service.impl;

import com.design.petStoreManagement.mapper.EmployeeMapper;
import com.design.petStoreManagement.mapper.ManagerMapper;
import com.design.petStoreManagement.mapper.UsersMapper;
import com.design.petStoreManagement.pojo.Employee;
import com.design.petStoreManagement.pojo.Manager;
import com.design.petStoreManagement.pojo.Users;
import com.design.petStoreManagement.pojo.res.RestFulBean;
import com.design.petStoreManagement.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Map;

@Service("LoginService")
public class  LoginServiceImpl implements LoginService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private ManagerMapper managerMapper;
    @Autowired
    private UsersMapper usersMapper;
    @Override
    public RestFulBean<Users> login(Users users) throws Exception {
        if(users.getTelephone()==null)
        {
            return RestFulBean.error("手机号码不能为空");
        }
        if(users.getPassword()==null)
        {
            return RestFulBean.error("密码不能为空");
        }
        if(users.getRole()==null)
        {
            return RestFulBean.error("角色不能为空");
        }
        Users user =new Users(); //new一个用户对象
        user.setRole(users.getRole());
        if(users.getRole().equals("用户")) //判断角色是否等于用户
        {
            Users usered =usersMapper.getByTelephone(users.getTelephone());//根据手机号码获取店员数据
            if(usered==null){
                return RestFulBean.error("用户账户不存在");
            }
            //赋值
            user.setId(usered.getId());
            user.setUserName(usered.getUserName());
            user.setTelephone(usered.getTelephone());
            user.setPassword(usered.getPassword());
        }
        if(users.getRole().equals("员工")) //判断角色是否等于员工
        {
            Employee employee =employeeMapper.getByTelephone(users.getTelephone());//根据手机号码获取店员数据
            if(employee==null){
                return RestFulBean.error("员工账户不存在");
            }
            //赋值
            user.setId(employee.getId());
            user.setUserName(employee.getUserName());
            user.setPassword(employee.getPassword());
        }
        if(users.getRole().equals("管理员")) //判断角色是否等于管理员
        {
            Manager manager =managerMapper.getByTelephone(users.getTelephone());//根据手机号码获取管理员数据
            if(manager==null){
                return RestFulBean.error("该管理员用户不存在");
            }
            //赋值
            user.setId(manager.getId());
            user.setUserName(manager.getUserName());
            user.setPassword(manager.getPassword());
        }
        //判断密码跟数据库是否一样
        if(users.getPassword().equals(user.getPassword())){
            return RestFulBean.succ(user); //如果密码一样将用户对象放回到前端
        }
        else{
            return RestFulBean.error("密码错误");
        }
    }
}
