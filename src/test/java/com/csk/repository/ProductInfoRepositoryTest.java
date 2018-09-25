package com.csk.repository;

import com.csk.dataobject.ProductInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by 程少宽
 * 2017-05-09 11:42
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;

    @Test
    public void saveTest() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1234567");
        productInfo.setProductName("皮蛋粥");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("很好喝的粥");
        productInfo.setProductIcon("http://xxxxx.jpg");
        productInfo.setProductStatus(1);
        productInfo.setCategoryType(2);

        ProductInfo result = repository.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByProductStatus() throws Exception {

        List<ProductInfo> productInfoList = repository.findByProductStatus(0);
        Assert.assertNotEquals(0, productInfoList.size());
        for(ProductInfo productInfo:productInfoList){
            System.out.println(productInfo.toString());
        }

    }
    @Test
    public void findByproductId() throws Exception {

        List<ProductInfo> productInfoList = repository.findByproductId("123456");


//        Assert.assertNotEquals(0, productInfoList.size());
        for(ProductInfo productInfo:productInfoList){
            System.out.println(productInfo.toString());
        }

    }

}