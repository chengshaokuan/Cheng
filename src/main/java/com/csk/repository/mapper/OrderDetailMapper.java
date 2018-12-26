package com.csk.repository.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface OrderDetailMapper {

    @Delete("delete from order_detail where detail_id=#{detailId}")
    int deleteByOrderId(String  detailId);
}
