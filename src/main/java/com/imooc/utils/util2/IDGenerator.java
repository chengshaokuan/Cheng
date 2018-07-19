package com.imooc.utils.util2.utils;

import com.creditease.panda.engine.component.RedisClient;
import com.creditease.panda.facade.constant.EnumConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.creditease.panda.facade.constant.RedisKeyConst.*;

/**
 * Created by Nyankosensei on 2017/6/9.
 */

/**
 * IDGenerator 用来生成ID
 * 规则：
 * 1000 0003 20170609153245097 0000008
 * 模块  渠道     时间戳         序列
 * <p>
 * 返回32位的String型数字ID
 */

public class IDGenerator {

    private static final Logger LOG = LoggerFactory.getLogger(IDGenerator.class);
    private static final RedisClient redisClient;
    private static NumberFormat nf = NumberFormat.getInstance();

    static {
        redisClient = SpringUtil.getBean("redisClient", RedisClient.class);
    }


    private static String generateId(String... strs) {
        StringBuffer sb = new StringBuffer();
        for (String str : strs) {
            sb.append(str);
        }
        return sb.toString();

    }


    /**
     * @param clueChannel 渠道枚举
     * @return clueID
     */
    public static String generateClueId(Integer clueChannel) {
        // TODO 测试！！！！！
        clueChannel = null == clueChannel ? -1 : clueChannel;
        //时间戳 17位
        String timeStr = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime());
        nf.setGroupingUsed(false);
        //模块名称 4位
        String moduleStr = EnumConst.PandaModel.CLUE.getContext();
        //渠道名称 4位
        nf.setMaximumIntegerDigits(4);
        nf.setMinimumIntegerDigits(4);
        String channelStr = nf.format(clueChannel);
        //sequence 7位
        Long seq = redisClient.incr(CLUE_INRC);
        nf.setMaximumIntegerDigits(7);
        nf.setMinimumIntegerDigits(7);
        String seqStr = nf.format(seq);
        String clueId = generateId(moduleStr, channelStr, timeStr, seqStr);
        LOG.debug("生成ClueID：" + clueId);
        return clueId;
    }

    public static String generateCustomerId(Integer channel) {
        // TODO 测试！！！！！
        channel = null == channel ? -1 : channel;
        //时间戳 17位
        String timeStr = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime());
        nf.setGroupingUsed(false);
        //模块名称 4位
        String moduleStr = EnumConst.PandaModel.CUSTOMER.getContext();
        //渠道名称 4位
        nf.setMaximumIntegerDigits(4);
        nf.setMinimumIntegerDigits(4);
        String channelStr = nf.format(channel);
        //sequence 7位
        Long seq = redisClient.incr(CUSTOMER_INRC);
        nf.setMaximumIntegerDigits(7);
        nf.setMinimumIntegerDigits(7);
        String seqStr = nf.format(seq);
        String clueId = generateId(moduleStr, channelStr, timeStr, seqStr);
        LOG.debug("生成CustomerID：" + clueId);
        return clueId;
    }

    public static String generateMessageId(Integer channel) {
        // TODO 测试！！！！！
        channel = null == channel ? -1 : channel;
        //时间戳 17位
        String timeStr = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime());
        nf.setGroupingUsed(false);
        //模块名称 4位
        String moduleStr = EnumConst.PandaModel.MESSAGE.getContext();
        //渠道名称 4位
        nf.setMaximumIntegerDigits(4);
        nf.setMinimumIntegerDigits(4);
        String channelStr = nf.format(channel);
        //sequence 7位
        Long seq = redisClient.incr(MESSAGE_INRC);
        nf.setMaximumIntegerDigits(7);
        nf.setMinimumIntegerDigits(7);
        String seqStr = nf.format(seq);
        String clueId = generateId(moduleStr, channelStr, timeStr, seqStr);
        LOG.debug("生成MessageID：" + clueId);
        return clueId;
    }

}
