package com.design.petStoreManagement.service.impl;

import com.design.petStoreManagement.pojo.Variety;
import com.design.petStoreManagement.mapper.VarietyMapper;
import com.design.petStoreManagement.service.VarietyService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import com.design.petStoreManagement.pojo.res.RestFulBean;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import com.design.petStoreManagement.util.PageUtil;
import com.design.petStoreManagement.dto.Page;
/**
 * (Variety)表服务实现类
 *
 * @author makejava
 */
@Service("varietyService")
public class VarietyServiceImpl implements VarietyService {
    @Autowired
    private VarietyMapper varietyMapper;
         /**
     * 分页查询数据
     *
     * @param //前端传来的参数
     * @return 实例对象
     */
    @Override
    public RestFulBean<Map> getList(Page<Variety> page) throws Exception{
        //mysql分页要先在外面计算好从第几条数据开始获取数据
        Integer pageNum =page.getPageNum();
        Integer startNum =(pageNum-1)* page.getPageSize();
        page.setStartNum(startNum);
        //根据前端传来的的条件进行查询  //分页查询
        List<Variety> list= varietyMapper.getPageListByCondition(page);
        //根据条件查询数据的条数
        Integer count = varietyMapper.getPageListCount(page);
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
    public RestFulBean<Variety> queryById(Integer id) {
       Variety variety=this.varietyMapper.queryById(id);
         return RestFulBean.succ(variety);
    }

    /**
     * 新增数据
     *
     * @param variety 实例对象
     * @return 实例对象
     */
    @Override
    public RestFulBean<String> insert(Variety variety) {
        if(variety.getName()==null){
            return RestFulBean.error("宠物品种不能为空");
        }
        if(variety.getDescb()==null){
            return RestFulBean.error("描述不能为空");
        }
        this.varietyMapper.insert(variety);//执行数据库的新增语句
        return RestFulBean.succ("添加成功");
    }

    /**
     * 修改数据
     *
     * @param variety 实例对象
     * @return 实例对象
     */
    @Override
     public RestFulBean<String> update(Variety variety) {
        this.varietyMapper.update(variety);//执行数据库的修改语句 根据id 修改
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
        this.varietyMapper.deleteById(id);//执行数据库的删除语句 根据id 删除
         return RestFulBean.succ("删除成功");
    }

    @Override
    public RestFulBean<List<Variety>> getVarietyList() {
        List<Variety> list =varietyMapper.getVarietyList();
        return RestFulBean.succ(list);
    }
}
