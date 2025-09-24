package com.design.petStoreManagement.service.impl;

import com.design.petStoreManagement.dto.Page;
import com.design.petStoreManagement.mapper.AddressMapper;
import com.design.petStoreManagement.pojo.Address;
import com.design.petStoreManagement.pojo.res.RestFulBean;
import com.design.petStoreManagement.service.AddressService;
import com.design.petStoreManagement.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * (Address)表服务实现类
 *
 * @author makejava
 */
@Service("addressService")
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressMapper addressMapper;
         /**
     * 分页查询数据
     *
     * @param //前端传来的参数
     * @return 实例对象
     */
    @Override
    public RestFulBean<Map> getList(Page<Address> page) throws Exception{
        //mysql分页要先在外面计算好从第几条数据开始获取数据
        Integer pageNum =page.getPageNum();
        Integer startNum =(pageNum-1)* page.getPageSize();
        page.setStartNum(startNum);
        //根据前端传来的的条件进行查询  //分页查询
        List<Address> list= addressMapper.getPageListByCondition(page);
        if(list.size()>0){  //如果数据大于0 用for循环把照片的完整路劲返回前端显示
            for(Address address: list){
               
            }
        }
        //根据条件查询数据的条数
        Integer count = addressMapper.getPageListCount(page);
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
    public RestFulBean<Address> queryById(Integer id) {
       Address address=this.addressMapper.queryById(id);
         return RestFulBean.succ(address);
    }

    /**
     * 新增数据
     *
     * @param address 实例对象
     * @return 实例对象
     */
    @Override
    public RestFulBean<String> insert(Address address) {
        this.addressMapper.insert(address);//执行数据库的新增语句
        return RestFulBean.succ("添加成功");
    }

    /**
     * 修改数据
     *
     * @param address 实例对象
     * @return 实例对象
     */
    @Override
     public RestFulBean<String> update(Address address) {
        this.addressMapper.update(address);//执行数据库的修改语句 根据id 修改
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
        this.addressMapper.deleteById(id);//执行数据库的删除语句 根据id 删除
         return RestFulBean.succ("删除成功");
    }
    @Override
    public RestFulBean<List<Address>> queryByUserId(Integer userId) {
        List<Address> list =addressMapper.queryByUserId(userId); //根据用户id获取地址
        return RestFulBean.succ(list);
    }
}
