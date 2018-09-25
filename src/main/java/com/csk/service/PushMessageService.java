package com.csk.service;

import com.csk.dto.OrderDTO;

/**
 * 推送消息
 * Created by 程少宽
 * 2017-07-30 22:08
 */
public interface PushMessageService {

    /**
     * 订单状态变更消息
     * @param orderDTO
     */
    void orderStatus(OrderDTO orderDTO);
}
