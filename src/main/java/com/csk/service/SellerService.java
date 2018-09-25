package com.csk.service;

import com.csk.dataobject.SellerInfo;

/**
 * 卖家端
 * Created by 程少宽
 * 2017-07-29 23:14
 */
public interface SellerService {

    /**
     * 通过openid查询卖家端信息
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);
}
