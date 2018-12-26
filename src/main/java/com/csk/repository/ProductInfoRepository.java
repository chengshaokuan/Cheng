package com.csk.repository;

import com.csk.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Created by 程少宽
 * 2017-05-09 11:39
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    /**
     * @Description:  通过产品状态查询产品目录
     * @param: productStatus
     * @return: java.util.List<com.csk.dataobject.ProductInfo>
     * @Author: Mr.Cheng
     * @Date: 16:43 2018/12/25
     */
    List<ProductInfo> findByProductStatus(Integer productStatus);

    List<ProductInfo> findByproductId(String productId);
}
