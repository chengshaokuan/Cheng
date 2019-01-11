package com.csk.dataObject.dao;

import com.csk.dataobject.ProductCategory;
import com.csk.repository.mapper.OrderDetailMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @program: cheng
 * @description:
 * @author: Mr.Cheng
 * @create: 2019-01-08 16:31
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderDetailMapperTest {

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Test
    public void findByCategoryName() throws Exception {
        int i = orderDetailMapper.deleteByOrderId("12345678");
        System.out.println(i);
    }
}
