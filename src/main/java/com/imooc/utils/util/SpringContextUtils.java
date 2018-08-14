package com.imooc.utils.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Description:以静态变量保存Spring ApplicationContext.
 * @author WangShutao
 * @version 1.0
 * <pre>
 * Modification History:
 * Date         Author       Version     Description
------------------------------------------------------------------
 * 2012-12-20      WangShutao    1.0        1.0 Version
 * </pre>
 */
public class SpringContextUtils implements ApplicationContextAware {
    /**
     * 上下文
     */
    private static ApplicationContext applicationContext;

    /**
     * Description:实现ApplicationContextAware接口的context注入函数, 将其存入静态变量.
     * @param context 上下文
     * @return
     * @throws
     * @Author WangShutao
     * Create Date: 2012-12-20 下午4:03:05
     */
    public void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    /**
     * Description:取得存储在静态变量中的ApplicationContext.
     * @param
     * @return ApplicationContext
     * @throws
     * @Author WangShutao
     * Create Date: 2012-12-20 下午4:03:21
     */
    public static ApplicationContext getApplicationContext() {
        if (applicationContext == null)
        {
            throw new IllegalStateException(
                    "applicaitonContext未注入,请在applicationContext.xml中定义SpringContextUtil");
        }
        return applicationContext;
    }

    /**
     * Description:从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
     * @param name 类名称。
     * @param <T> 泛型
     * @return <T> 返回bean对象。
     * @throws
     * @Author WangShutao
     * Create Date: 2012-12-20 下午4:03:51
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }
}
