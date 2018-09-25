package com.csk.repository;

import com.csk.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by 程少宽
 * 2017-06-11 17:24
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {


    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
