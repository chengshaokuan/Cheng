package com.imooc.utils.util2.utils;

import com.creditease.panda.facade.dataTransfer.CustomerIdentityDTO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import java.util.List;

/**
 * Created by xiaguangmeng on 17/7/11.
 */
public class CreateEntityUtil {

    public static CustomerIdentityDTO getMajorIdentity(List<CustomerIdentityDTO> dtos) {
        return (CustomerIdentityDTO) CollectionUtils.find(dtos, new Predicate() {
            @Override
            public boolean evaluate(Object o) {
                return null != ((CustomerIdentityDTO) o).getIsMajorCard() && ((CustomerIdentityDTO) o).getIsMajorCard() == 1;
            }
        });
    }
}
