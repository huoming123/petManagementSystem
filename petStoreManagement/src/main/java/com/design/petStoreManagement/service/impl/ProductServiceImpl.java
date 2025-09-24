package com.design.petStoreManagement.service.impl;

import com.design.petStoreManagement.dto.Page;
import com.design.petStoreManagement.mapper.ProductMapper;
import com.design.petStoreManagement.pojo.Product;
import com.design.petStoreManagement.pojo.res.RestFulBean;
import com.design.petStoreManagement.service.ProductService;
import com.design.petStoreManagement.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * (Product)表服务实现类
 *
 * @author makejava
 * @since
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
         /**
     * 分页查询数据
     *
     * @param //前端传来的参数
     * @return 实例对象
     */
    @Override
    public RestFulBean<Map> getList(Page<Product> page) throws Exception{
        //mysql分页要先在外面计算好从第几条数据开始获取数据
        Integer pageNum =page.getPageNum();
        Integer startNum =(pageNum-1)* page.getPageSize();
        page.setStartNum(startNum);
        //根据前端传来的的条件进行查询  //分页查询
        List<Product> list= productMapper.getPageListByCondition(page);
        if(list.size()>0){  //如果数据大于0 用for循环把照片的完整路劲返回前端显示
            for(Product product: list){
                product.setImagesUrl("http://localhost:8087/file/"+product.getImages());
            }
        }
        //根据条件查询数据的条数
        Integer count = productMapper.getPageListCount(page);
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
    public RestFulBean<Product> queryById(Integer id) {
        Product product=this.productMapper.queryById(id);
        return RestFulBean.succ(product);
    }

    /**
     * 新增数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    @Override
    public RestFulBean<String> insert(Product product) {
        if(product.getProductNo()==null){
            return RestFulBean.error("商品编号不能为空");
        }
        if(product.getProductName()==null){
            return RestFulBean.error("商品名字不能为空");
        }
        if(product.getPrices()==null){
            return RestFulBean.error("销售价不能为空");
        }
        Product producted  =  productMapper.getByProductName(product.getProductName());
        if(producted!=null){
            return RestFulBean.error("该商品已经录入，请不要重复录入");

        }
        Product producted1  =  productMapper.queryByProductNo(product.getProductNo());
        if(producted1!=null){
            return RestFulBean.error("该商品编号已经录入，请不要重复录入");

        }
        this.productMapper.insert(product);//执行数据库的新增语句
        return RestFulBean.succ("添加成功");
    }

    /**
     * 修改数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    @Override
     public RestFulBean<String> update(Product product) {
        this.productMapper.update(product);//执行数据库的修改语句 根据id 修改
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
        this.productMapper.deleteById(id);//执行数据库的删除语句 根据id 删除
         return RestFulBean.succ("删除成功");
    }

    @Override
    public RestFulBean<List<Product>> getProductList() {
        List<Product> list =productMapper.getProductList(); //获取商品下拉数据
        return RestFulBean.succ(list);
    }

    @Override
    public RestFulBean<Product> queryByProductNo(String productNo) {
        Product product=productMapper.queryByProductNo(productNo); //根据商品编号获取数据
        product.setImagesUrl("http://localhost:8087/file/"+product.getImages());
        product.setNum(1); //数量默认为1
        product.setFee(1*product.getPrices()); //价钱
        return RestFulBean.succ(product);
    }
}
