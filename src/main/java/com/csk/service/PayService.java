package com.csk.service;

import com.csk.dto.OrderDTO;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;

/**
 * 支付
 * Created by 程少宽
 * 2017-07-04 00:53
 */
public interface PayService {

    PayResponse create(OrderDTO orderDTO);

    PayResponse notify(String notifyData);

    RefundResponse refund(OrderDTO orderDTO);
}
