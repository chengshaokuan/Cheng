package com.csk.repository;

import com.csk.dataobject.ProductInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by 程少宽
 * 2017-05-09 11:39
 */
@Mapper
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    List<ProductInfo> findByProductStatus(Integer productStatus);

//    @Select("select productId from product_info where productId=#{productId}")
//    List<ProductInfo> findByproductId (String s);

    List<ProductInfo> findByproductId(String productId);
}
