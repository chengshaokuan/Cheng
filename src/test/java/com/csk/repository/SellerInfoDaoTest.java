package com.csk.repository;

import com.csk.dataobject.SellerInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoDaoTest {
    @Autowired
    private SellerInfoRepository sellerInfoDao;

    @Test
    public void save () {
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId("");
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("admin");
        sellerInfo.setOpenid("abc");
        SellerInfo result = sellerInfoDao.save(sellerInfo);
        System.out.println(result);
    }

    @Test
    public void findByOpenid () throws Exception {
        SellerInfo result = sellerInfoDao.findByOpenid("abc");
        Assert.assertEquals("abc", result.getOpenid());
    }

}