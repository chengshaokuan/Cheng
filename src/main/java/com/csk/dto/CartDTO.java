package com.csk.dto;

import lombok.Data;

/**
 * 购物车
 * Created by 程少宽
 * 2017-06-11 19:37
 */
@Data
public class CartDTO {

    /** 商品Id. */
    private String productId;

    /** 数量. */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
