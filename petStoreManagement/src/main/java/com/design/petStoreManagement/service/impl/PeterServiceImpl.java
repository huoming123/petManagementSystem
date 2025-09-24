package com.design.petStoreManagement.service.impl;

import com.design.petStoreManagement.pojo.Peter;
import com.design.petStoreManagement.mapper.PeterMapper;
import com.design.petStoreManagement.service.PeterService;
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
 * (Peter)表服务实现类
 *
 * @author makejava
 */
@Service("peterService")
public class PeterServiceImpl implements PeterService {
    @Autowired
    private PeterMapper peterMapper;
         /**
     * 分页查询数据
     *
     * @param //前端传来的参数
     * @return 实例对象
     */
    @Override
    public RestFulBean<Map> getList(Page<Peter> page) throws Exception{
        //mysql分页要先在外面计算好从第几条数据开始获取数据
        Integer pageNum =page.getPageNum();
        Integer startNum =(pageNum-1)* page.getPageSize();
        page.setStartNum(startNum);
        //根据前端传来的的条件进行查询  //分页查询
        List<Peter> list= peterMapper.getPageListByCondition(page);
        if(list.size()>0){  //如果数据大于0 用for循环把照片的完整路劲返回前端显示
            for(Peter peter: list){
                peter.setImagesUrl("http://localhost:8087/file/"+peter.getImages());
            }
        }
        //根据条件查询数据的条数
        Integer count = peterMapper.getPageListCount(page);
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
    public RestFulBean<Peter> queryById(Integer id) {
       Peter peter=this.peterMapper.queryById(id);
         return RestFulBean.succ(peter);
    }

    /**
     * 新增数据
     *
     * @param peter 实例对象
     * @return 实例对象
     */
    @Override
    public RestFulBean<String> insert(Peter peter) {
        if(peter.getUserName()==null)
        {
            return RestFulBean.error("铲屎官姓名不能为空");
        }
        if(peter.getTelephone()==null)
        {
            return RestFulBean.error("铲屎官手机号码不能为空");
        }
        this.peterMapper.insert(peter);//执行数据库的新增语句
        return RestFulBean.succ("添加成功");
    }

    /**
     * 修改数据
     *
     * @param peter 实例对象
     * @return 实例对象
     */
    @Override
     public RestFulBean<String> update(Peter peter) {
        this.peterMapper.update(peter);//执行数据库的修改语句 根据id 修改
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
        this.peterMapper.deleteById(id);//执行数据库的删除语句 根据id 删除
         return RestFulBean.succ("删除成功");
    }
}
