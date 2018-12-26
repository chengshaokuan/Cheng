package com.csk.repository.mapper;

import com.csk.dataobject.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public class ProductCategoryMyBatisDao {
    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    public int insertByMap(Map<String,Object> map){
        return productCategoryMapper.insertByMap(map);
    }

    public List<ProductCategory> findByCategoryName(String categoryName){
        return productCategoryMapper.findByCategoryName(categoryName);
    }

}
