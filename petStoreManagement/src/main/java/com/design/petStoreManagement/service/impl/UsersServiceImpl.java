package com.design.petStoreManagement.service.impl;

import com.design.petStoreManagement.pojo.Users;
import com.design.petStoreManagement.mapper.UsersMapper;
import com.design.petStoreManagement.service.UsersService;
import com.design.petStoreManagement.util.FileUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import com.design.petStoreManagement.pojo.res.RestFulBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.design.petStoreManagement.util.PageUtil;
import com.design.petStoreManagement.dto.Page;
import org.springframework.web.multipart.MultipartFile;

/**
 * (Users)表服务实现类
 *
 * @author makejava
 */
@Service("usersService")
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersMapper usersMapper;
         /**
     * 分页查询数据
     *
     * @param //前端传来的参数
     * @return 实例对象
     */
    @Override
    public RestFulBean<Map> getList(Page<Users> page) throws Exception{
        //mysql分页要先在外面计算好从第几条数据开始获取数据
        Integer pageNum =page.getPageNum();
        Integer startNum =(pageNum-1)* page.getPageSize();
        page.setStartNum(startNum);
        //根据前端传来的的条件进行查询  //分页查询
        List<Users> list= usersMapper.getPageListByCondition(page);
        if(list.size()>0){  //如果数据大于0 用for循环把照片的完整路劲返回前端显示
            for(Users users: list){
                users.setImagesUrl("http://localhost:8087/file/"+users.getImages()); //照片
            }
        }
        //根据条件查询数据的条数
        Integer count = usersMapper.getPageListCount(page);
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
    public RestFulBean<Users> queryById(Integer id) {
       Users users=this.usersMapper.queryById(id);
        users.setImagesUrl("http://localhost:8087/file/"+users.getImages()); //照片
         return RestFulBean.succ(users);
    }

    /**
     * 新增数据
     *
     * @param users 实例对象
     * @return 实例对象
     */
    @Override
    public RestFulBean<String> insert(Users users) {
        if(users.getTelephone()==null){
            return RestFulBean.error("手机号码不能为空");
        }
        if(users.getUserName()==null){
            return RestFulBean.error("用户名不能为空");
        }
        if(users.getTelephone().trim().length()!=11){
            return RestFulBean.error("请输入11位手机号码");
        }
        //手机号码只能是数字 用正则法则判断
        Pattern pattern = Pattern.compile("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
        Matcher isNum = pattern.matcher(users.getTelephone().trim());
        if(!isNum.matches()){  //如果不等于true 则进去条件
            return RestFulBean.error("手机号码只能是数字"); //不是数字
        }
        Users usersed =usersMapper.getByTelephone(users.getTelephone());
        if(usersed!=null){
            return RestFulBean.error("该手机号已注册");
        }
        this.usersMapper.insert(users);//执行数据库的新增语句
        return RestFulBean.succ("添加成功");
    }

    /**
     * 修改数据
     *
     * @param users 实例对象
     * @return 实例对象
     */
    @Override
     public RestFulBean<String> update(Users users) {
        this.usersMapper.update(users);//执行数据库的修改语句 根据id 修改
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
        this.usersMapper.deleteById(id);//执行数据库的删除语句 根据id 删除
         return RestFulBean.succ("删除成功");
    }
    @Override
    public RestFulBean<Map> uploadPicture(Integer id, MultipartFile coverFile) {
        String destPath ="D:\\design\\images\\";//自定义一个路径
        // 存图片
        try{//用一个file文件上传的方法上传照片
            FileUtil.saveFile(coverFile, destPath,coverFile.getOriginalFilename());//上传图片的方法
        }
        catch(Exception e){
            return RestFulBean.error("请求异常");
        }
        Map map =new HashMap();//定义一个map对象
        //返回一个完整的本地照片路径用于前端显示
        map.put("url","http://localhost:8087/file/"+coverFile.getOriginalFilename());
        Users users =new Users();
        users.setId(id);
        users.setImages(coverFile.getOriginalFilename());  //修改照片
        this.usersMapper.update(users);//执行数据库的修改语句 根据id 修改
        return RestFulBean.succ(map);
    }
    @Override
    public RestFulBean<String> retrievePassword(Users users) {
        Users usersed =usersMapper.getByTelephone(users.getTelephone()); //根据手机号获取数据
        if(usersed==null){
            return RestFulBean.error("请输入正确的手机号码");
        }
        return RestFulBean.succ("您的系统密码为"+usersed.getPassword());
    }
}
