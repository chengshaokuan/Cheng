package com.csk.repository;

import com.csk.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by 程少宽
 * 2017-06-11 17:28
 */
public interface OrderDetailRepository extends Repository<OrderDetail, String> {

    List<OrderDetail> findByOrderId(String orderId);
}
