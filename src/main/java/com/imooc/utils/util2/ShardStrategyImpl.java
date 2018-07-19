package com.imooc.utils.util2.utils;

import com.creditease.panda.dao.entity.ContactWayInfoDO;
import com.creditease.panda.facade.constant.InteractConst;
import com.google.code.shardbatis.strategy.ShardStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @Author: shiqi
 * @Descriprion
 * @Date Created in 2018/5/11.
 * @Modified By:
 */
public class ShardStrategyImpl implements ShardStrategy {

	private static final Logger logger = LoggerFactory.getLogger(ShardStrategyImpl.class);

	@Override
	public String getTargetTableName(String s, Object o, String s1) {
		StringBuilder stringBuilder = new StringBuilder(s);
		if(o instanceof ContactWayInfoDO) {
			ContactWayInfoDO contactWayInfoDO = (ContactWayInfoDO) o;
			int tableIndex = indexFor(hash(contactWayInfoDO.getIdNumber()), InteractConst.TABLE_SIZE);
			stringBuilder.append("_").append(tableIndex);
		}
		logger.info("panda数据库中的表名字为{}",stringBuilder.toString());
		return stringBuilder.toString();
	}


	private int hash(String key) {
		int h;
		return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
	}

	private int indexFor(int h,int length){
		return h & length-1;
	}
}
