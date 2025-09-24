package com.design.petStoreManagement.service.impl;

import com.design.petStoreManagement.mapper.ProductMapper;
import com.design.petStoreManagement.pojo.Pet;
import com.design.petStoreManagement.mapper.PetMapper;
import com.design.petStoreManagement.pojo.Product;
import com.design.petStoreManagement.service.PetService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import com.design.petStoreManagement.pojo.res.RestFulBean;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import com.design.petStoreManagement.util.PageUtil;
import com.design.petStoreManagement.dto.Page;
/**
 * (Pet)表服务实现类
 *
 * @author makejava
 */
@Service("petService")
public class PetServiceImpl implements PetService {
    @Autowired
    private PetMapper petMapper;
    @Autowired
    private ProductMapper productMapper;
         /**
     * 分页查询数据
     *
     * @param //前端传来的参数
     * @return 实例对象
     */
    @Override
    public RestFulBean<Map> getList(Page<Pet> page) throws Exception{
        //mysql分页要先在外面计算好从第几条数据开始获取数据
        Integer pageNum =page.getPageNum();
        Integer startNum =(pageNum-1)* page.getPageSize();
        page.setStartNum(startNum);
        //根据前端传来的的条件进行查询  //分页查询
        List<Pet> list= petMapper.getPageListByCondition(page);
        //根据条件查询数据的条数
        Integer count = petMapper.getPageListCount(page);
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
    public RestFulBean<Pet> queryById(Integer id) {
       Pet pet=this.petMapper.queryById(id);
         return RestFulBean.succ(pet);
    }

    /**
     * 新增数据
     *
     * @param pet 实例对象
     * @return 实例对象
     */
    @Override
    public RestFulBean<String> insert(Pet pet) {
        if(pet.getImages()==null){
            return RestFulBean.error("宠物照片不能为空");
        }
        if(pet.getVariety()==null){
            return RestFulBean.error("宠物品种不能为空");
        }
        if(pet.getAge()==null){
            return RestFulBean.error("宠物年龄不能为空");
        }
        if(pet.getSex()==null){
            return RestFulBean.error("宠物性别不能为空");
        }
        if(pet.getPrices()==null){
            return RestFulBean.error("宠物价钱不能为空");
        }
        this.petMapper.insert(pet);//执行数据库的新增语句
        return RestFulBean.succ("添加成功");
    }

    /**
     * 修改数据
     *
     * @param pet 实例对象
     * @return 实例对象
     */
    @Override
     public RestFulBean<String> update(Pet pet) {
        this.petMapper.update(pet);//执行数据库的修改语句 根据id 修改
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
        this.petMapper.deleteById(id);//执行数据库的删除语句 根据id 删除
         return RestFulBean.succ("删除成功");
    }

    @Override
    public RestFulBean<Map> getIndexList() {
        Map result =  new HashMap();//定义一个map对象
        List<Pet> petList =  petMapper.getIndexPetList(); //获取首页宠物的数据
        result.put("petList",petList); //把宠物的数据加到map对象里面 返回给前端
        List<Product> productList =  productMapper.getIndexProductList(); //获取首页推荐商品的数据

        result.put("productList",productList); //把推荐的数据加到map对象里面 返回给前端
        return RestFulBean.succ(result); //返回数据
    }
}
