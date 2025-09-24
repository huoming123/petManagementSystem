package com.design.petStoreManagement.service.impl;

import com.design.petStoreManagement.mapper.PetMapper;
import com.design.petStoreManagement.mapper.ProductMapper;
import com.design.petStoreManagement.pojo.Pet;
import com.design.petStoreManagement.pojo.PetOrder;
import com.design.petStoreManagement.mapper.PetOrderMapper;
import com.design.petStoreManagement.pojo.Product;
import com.design.petStoreManagement.service.PetOrderService;
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
 * (PetOrder)表服务实现类
 *
 * @author makejava
 */
@Service("petOrderService")
public class PetOrderServiceImpl implements PetOrderService {
    @Autowired
    private PetOrderMapper petOrderMapper;
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
    public RestFulBean<Map> getList(Page<PetOrder> page) throws Exception{
        //mysql分页要先在外面计算好从第几条数据开始获取数据
        Integer pageNum =page.getPageNum();
        Integer startNum =(pageNum-1)* page.getPageSize();
        page.setStartNum(startNum);
        //根据前端传来的的条件进行查询  //分页查询
        List<PetOrder> list= petOrderMapper.getPageListByCondition(page);
        if(list.size()>0){  //如果数据大于0 用for循环把照片的完整路劲返回前端显示
            for(PetOrder petOrder: list){
               
            }
        }
        //根据条件查询数据的条数
        Integer count = petOrderMapper.getPageListCount(page);
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
    public RestFulBean<PetOrder> queryById(Integer id) {
       PetOrder petOrder=this.petOrderMapper.queryById(id);
         return RestFulBean.succ(petOrder);
    }

    /**
     * 新增数据
     *
     * @param petOrder 实例对象
     * @return 实例对象
     */
    @Override
    public RestFulBean<String> insert(PetOrder petOrder) {
        if(petOrder.getOrderType().equals("宠物销售")){
            Pet pet=petMapper.getByPetName(petOrder.getPetName());//根据宠物品种获取库存
            if(pet.getTotal()==null && pet.getTotal()==0){
                return RestFulBean.error("库存为0，无法下单");
            }
        }
        if(petOrder.getOrderType().equals("宠物商品")){
            Product product=productMapper.getByProductName(petOrder.getProductName());//根据商品名称获取库存
            if(product.getTotal()==null && product.getTotal()==0){
                return RestFulBean.error("库存为0，无法下单");
            }
        }
        //13位时间戳 不可能重复
        long timeMillis=System.currentTimeMillis(); //当前系统时间戳
        petOrder.setOrderNo("SS"+timeMillis);//自动生成15位订单编号
        this.petOrderMapper.insert(petOrder);//执行数据库的新增语句
        return RestFulBean.succ("SS"+timeMillis); //返回订单编号
    }

    /**
     * 修改数据
     *
     * @param petOrder 实例对象
     * @return 实例对象
     */
    @Override
     public RestFulBean<String> update(PetOrder petOrder) {
        this.petOrderMapper.update(petOrder);//执行数据库的修改语句 根据id 修改
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
        this.petOrderMapper.deleteById(id);//执行数据库的删除语句 根据id 删除
         return RestFulBean.succ("删除成功");
    }
    @Override
    public RestFulBean<PetOrder> queryByOrderNo(String orderNo) {
        PetOrder petOrder =petOrderMapper.queryByOrderNo(orderNo); //根据订单编号获取数据
        return RestFulBean.succ(petOrder);
    }

    @Override
    public RestFulBean<String> salePay(PetOrder petOrder) {
        if(petOrder.getOrderType().equals("宠物销售")){
            Pet pet=petMapper.getByPetName(petOrder.getPetName());//根据宠物品种获取库存
            pet.setTotal(pet.getTotal()-petOrder.getNum()); //已有库存-购买的数量
            petMapper.update(pet);//刷新库存
        }
        if(petOrder.getOrderType().equals("宠物商品")){
            Product product=productMapper.getByProductName(petOrder.getProductName());//根据商品名称获取库存
            product.setTotal(product.getTotal()-petOrder.getNum()); //已有库存-购买的数量
            productMapper.update(product);//刷新库存
        }
        this.petOrderMapper.pay(petOrder);//支付
        return RestFulBean.succ("支付成功");
    }

    @Override
    public RestFulBean<String> saleCancel(PetOrder petOrder) {
        if(petOrder.getOrderType().equals("宠物销售")){
            Pet pet=petMapper.getByPetName(petOrder.getPetName());//根据宠物品种获取库存
            pet.setTotal(pet.getTotal()+petOrder.getNum()); //已有库存-购买的数量
            petMapper.update(pet);//刷新库存
        }
        if(petOrder.getOrderType().equals("宠物商品")){
            Product product=productMapper.getByProductName(petOrder.getProductName());//根据商品名称获取库存
            product.setTotal(product.getTotal()+petOrder.getNum()); //已有库存-购买的数量
            productMapper.update(product);//刷新库存
        }
        this.petOrderMapper.saleCancel(petOrder);//取消订单支付
        return RestFulBean.succ("取消成功");
    }

}
