package com.imooc.utils.util;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description:
 * 
 * @author WangShutao
 * @version 1.0
 * 
 *          <pre>
 * Modification History:
 * Date         Author       Version     Description
 * ------------------------------------------------------------------
 * 2012-12-20      WangShutao    1.0        1.0 Version
 * </pre>
 */
public class DateJsonValueProcessor implements JsonValueProcessor {
    /**
     *
     */
    private String format = "yyyy-MM-dd";

    /**
     *
     */
    public DateJsonValueProcessor() {
    }

    /**
     * @param format
     *            String类型输入参数。
     */
    public DateJsonValueProcessor(String format) {
        this.format = format;
    }

    /**
     * Description:
     * 
     * @param value
     *            输入参数
     * @param jsonConfig
     *            json参数
     * @return Object 返回对象。
     * @throws
     * @Author WangShutao Create Date: 2012-12-20 下午3:52:27
     */
    @Override
    public Object processArrayValue(Object value, JsonConfig jsonConfig) {
        String[] obj = {};
        if (value != null) {
            if (value instanceof Date[] || value instanceof java.sql.Date) {
                SimpleDateFormat sf = new SimpleDateFormat(format);
                Date[] dates = (Date[]) value;
                obj = new String[dates.length];
                for (int i = 0; i < dates.length; i++) {
                    obj[i] = sf.format(dates[i]);
                }
            }
        }
        return obj;
    }

    /**
     * Description:
     * 
     * @param key
     *            输入参数
     * @param value 输入参数。
     * @param jsonConfig
     *            json参数
     * @return Object 返回对象。
     * @throws
     * @Author WangShutao Create Date: 2012-12-20 下午3:53:08
     */
    @Override
    public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
        if (value != null) {
            if (value instanceof Date) {
                String str = new SimpleDateFormat(format).format((Date) value);
                return str;
            }
        }
        return value;
    }

    /**
     * Description:.
     * 
     * @param
     * @return String
     * @throws
     * @Author WangShutao Create Date: 2012-12-18 下午4:36:35
     */
    public String getFormat() {
        return format;
    }

    /**
     * Description:
     * @param format 输入参数。
     * @throws
     * @Author WangShutao
     * Create Date: 2012-12-20 下午3:53:49
     */
    public void setFormat(String format) {
        this.format = format;
    }

}
