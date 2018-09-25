package com.csk.utils.util;

import com.csk.utils.ResourceBundle.ResourceBundleHelper;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * Description: 处理Boolean类型的数据。
 * @author WangShutao
 * @version 1.0
 * <pre>
 * Modification History:
 * Date         Author       Version     Description
------------------------------------------------------------------
 * 2013-1-4      WangShutao    1.0        1.0 Version
 * </pre>
 */
public class BooleanJsonValueProcessor implements JsonValueProcessor {
    /**
     * Description:处理array类型的数据。
     * @param value 需要处理的数据
     * @param jsonConfig jsonConfig
     * @return jsonConfig jsonConfig
     * @throws
     * @Author WangShutao
     * Create Date: 2013-1-6 上午10:17:16
     */
    @Override
    public Object processArrayValue(Object value, JsonConfig jsonConfig) {
        if (value != null && value instanceof Boolean[]) {
            Boolean[] newValue = (Boolean[]) value;
            String[] newObject = new String[newValue.length];
            
            for (int i = 0; i < newValue.length; i++) {
                newObject[i] = (String)dealProcess(newValue[i]);
            }
            
            return newObject;
        }
        return value;
    }

    /**
     * Description:处理数据。
     * @param key 
     * @param value 需要处理的数据
     * @param jsonConfig jsonConfig
     * @return Object Object
     * @throws
     * @Author WangShutao
     * Create Date: 2013-1-6 上午10:18:04
     */
    @Override
    public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
        Object newValue = dealProcess(value);
        return newValue;
    }

    /**
     * Description:处理数据类型格式化。
     * @param value value
     * @return Object
     * @throws
     * @Author WangShutao
     * Create Date: 2013-1-6 上午10:13:37
     */
    private Object dealProcess(Object value)
    {
        if (value != null && value instanceof Boolean) {
            if(value.equals(Boolean.TRUE))
            {
                value = ResourceBundleHelper.getString("multi.true");
            }
            else if(value.equals(Boolean.FALSE))
            {
                value = ResourceBundleHelper.getString("multi.false");
            }
        }
        
        return value;
    }
}
