/**
 * WeatherWebService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.9  Built on : Nov 16, 2018 (12:05:37 GMT)
 */
package cn.com.weather;


/*
 *  WeatherWebService java interface
 */
public interface WeatherWebService {
    /**
     * Auto generated method signature
     * &lt;br /&gt;&lt;h3&gt;获得本天气预报Web Services支持的洲、国内外省份和城市信�?&lt;/h3&gt;&lt;p&gt;输入参数：无�? 返回数据：一个一维字符串数组 String()，内容为洲或国内省份的名称�??&lt;/p&gt;&lt;br /&gt;
     * @param getSupportProvince0
     */
    public cn.com.webxml.GetSupportProvinceResponse getSupportProvince (
            cn.com.webxml.GetSupportProvince getSupportProvince0)
        throws java.rmi.RemoteException;

    /**
     * Auto generated method signature for Asynchronous Invocations
     * &lt;br /&gt;&lt;h3&gt;获得本天气预报Web Services支持的洲、国内外省份和城市信�?&lt;/h3&gt;&lt;p&gt;输入参数：无�? 返回数据：一个一维字符串数组 String()，内容为洲或国内省份的名称�??&lt;/p&gt;&lt;br /&gt;
     * @param getSupportProvince0
     */
    public void startgetSupportProvince (
            cn.com.webxml.GetSupportProvince getSupportProvince0,
            final cn.com.weather.WeatherWebServiceCallbackHandler callback)
        throws java.rmi.RemoteException;

    /**
     * Auto generated method signature
     * &lt;br /&gt;&lt;h3&gt;查询本天气预报Web Services支持的国内外城市或地区信�?&lt;/h3&gt;&lt;p&gt;输入参数：byProvinceName = 指定的洲或国内的省份，若为ALL或空则表示返回全部城市；返回数据：一个一维字符串数组 String()，结构为：城市名�?(城市代码)�?&lt;/p&gt;&lt;br /&gt;
     * @param getSupportCity2
     */
    public cn.com.webxml.GetSupportCityResponse getSupportCity (
            cn.com.webxml.GetSupportCity getSupportCity2)
        throws java.rmi.RemoteException;

    /**
     * Auto generated method signature for Asynchronous Invocations
     * &lt;br /&gt;&lt;h3&gt;查询本天气预报Web Services支持的国内外城市或地区信�?&lt;/h3&gt;&lt;p&gt;输入参数：byProvinceName = 指定的洲或国内的省份，若为ALL或空则表示返回全部城市；返回数据：一个一维字符串数组 String()，结构为：城市名�?(城市代码)�?&lt;/p&gt;&lt;br /&gt;
     * @param getSupportCity2
     */
    public void startgetSupportCity (
            cn.com.webxml.GetSupportCity getSupportCity2,
            final cn.com.weather.WeatherWebServiceCallbackHandler callback)
        throws java.rmi.RemoteException;

    /**
     * Auto generated method signature
     * &lt;br&gt;&lt;h3&gt;获得本天气预报Web Services支持的洲、国内外省份和城市信�?&lt;/h3&gt;&lt;p&gt;输入参数：无；返回：DataSet 。DataSet.Tables(0) 为支持的洲和国内省份数据，DataSet.Tables(1) 为支持的国内外城市或地区数据。DataSet.Tables(0).Rows(i).Item(&quot;ID&quot;) 主键对应 DataSet.Tables(1).Rows(i).Item(&quot;ZoneID&quot;) 外键�?&lt;br /&gt;Tables(0)：ID = ID主键，Zone = 支持的洲、省份；Tables(1)：ID 主键，ZoneID = 对应Tables(0)ID的外键，Area = 城市或地区，AreaCode = 城市或地区代码�??&lt;/p&gt;&lt;br /&gt;
     * @param getSupportDataSet4
     */
    public cn.com.webxml.GetSupportDataSetResponse getSupportDataSet (
            cn.com.webxml.GetSupportDataSet getSupportDataSet4)
        throws java.rmi.RemoteException;

    /**
     * Auto generated method signature for Asynchronous Invocations
     * &lt;br&gt;&lt;h3&gt;获得本天气预报Web Services支持的洲、国内外省份和城市信�?&lt;/h3&gt;&lt;p&gt;输入参数：无；返回：DataSet 。DataSet.Tables(0) 为支持的洲和国内省份数据，DataSet.Tables(1) 为支持的国内外城市或地区数据。DataSet.Tables(0).Rows(i).Item(&quot;ID&quot;) 主键对应 DataSet.Tables(1).Rows(i).Item(&quot;ZoneID&quot;) 外键�?&lt;br /&gt;Tables(0)：ID = ID主键，Zone = 支持的洲、省份；Tables(1)：ID 主键，ZoneID = 对应Tables(0)ID的外键，Area = 城市或地区，AreaCode = 城市或地区代码�??&lt;/p&gt;&lt;br /&gt;
     * @param getSupportDataSet4
     */
    public void startgetSupportDataSet (
            cn.com.webxml.GetSupportDataSet getSupportDataSet4,
            final cn.com.weather.WeatherWebServiceCallbackHandler callback)
        throws java.rmi.RemoteException;

    /**
     * Auto generated method signature
     * &lt;br&gt;&lt;h3&gt;根据城市或地区名称查询获得未来三天内天气情况、现在的天气实况、天气和生活指数（For商业用户�?&lt;/h3&gt;&lt;p&gt;调用方法�? getWeatherbyCityName，输入参数：theUserID = 商业用户ID&lt;/p&gt;&lt;br /&gt;
     * @param getWeatherbyCityNamePro6
     */
    public cn.com.webxml.GetWeatherbyCityNameProResponse getWeatherbyCityNamePro (
            cn.com.webxml.GetWeatherbyCityNamePro getWeatherbyCityNamePro6)
        throws java.rmi.RemoteException;

    /**
     * Auto generated method signature for Asynchronous Invocations
     * &lt;br&gt;&lt;h3&gt;根据城市或地区名称查询获得未来三天内天气情况、现在的天气实况、天气和生活指数（For商业用户�?&lt;/h3&gt;&lt;p&gt;调用方法�? getWeatherbyCityName，输入参数：theUserID = 商业用户ID&lt;/p&gt;&lt;br /&gt;
     * @param getWeatherbyCityNamePro6
     */
    public void startgetWeatherbyCityNamePro (
            cn.com.webxml.GetWeatherbyCityNamePro getWeatherbyCityNamePro6,
            final cn.com.weather.WeatherWebServiceCallbackHandler callback)
        throws java.rmi.RemoteException;

    /**
     * Auto generated method signature
     * &lt;br&gt;&lt;h3&gt;根据城市或地区名称查询获得未来三天内天气情况、现在的天气实况、天气和生活指数&lt;/h3&gt;&lt;p&gt;调用方法如下：输入参数：theCityName = 城市中文名称(国外城市可用英文)或城市代�?(不输入默认为上海�?)，如：上�? �? 58367，如有城市名称重复请使用城市代码查询(可�?�过 getSupportCity �? getSupportDataSet 获得)；返回数据： �?个一维数�? String(22)，共�?23个元素�??&lt;br /&gt;String(0) �? String(4)：省份，城市，城市代码，城市图片名称，最后更新时间�?�String(5) �? String(11)：当天的 气温，概况，风向和风力，天气趋势�?始图片名�?(以下称：图标�?)，天气趋势结束图片名�?(以下称：图标�?)，现在的天气实况，天气和生活指数。String(12) �? String(16)：第二天�? 气温，概况，风向和风力，图标�?，图标二。String(17) �? String(21)：第三天�? 气温，概况，风向和风力，图标�?，图标二。String(22) 被查询的城市或地区的介绍 &lt;br /&gt;&lt;a href=&quot;http://www.webxml.com.cn/images/weather.zip&quot;&gt;下载天气图标&lt;img src=&quot;http://www.webxml.com.cn/images/download_w.gif&quot; border=&quot;0&quot; align=&quot;absbottom&quot; /&gt;&lt;/a&gt;(包含大�?�中、小尺寸) &lt;a href=&quot;http://www.webxml.com.cn/zh_cn/weather_icon.aspx&quot; target=&quot;_blank&quot;&gt;天气图例说明&lt;/a&gt; &lt;a href=&quot;http://www.webxml.com.cn/files/weather_eg.zip&quot;&gt;调用此天气预报Web Services实例下载&lt;/a&gt; (VB ASP.net 2.0)&lt;/p&gt;&lt;br /&gt;
     * @param getWeatherbyCityName8
     */
    public cn.com.webxml.GetWeatherbyCityNameResponse getWeatherbyCityName (
            cn.com.webxml.GetWeatherbyCityName getWeatherbyCityName8)
        throws java.rmi.RemoteException;

    /**
     * Auto generated method signature for Asynchronous Invocations
     * &lt;br&gt;&lt;h3&gt;根据城市或地区名称查询获得未来三天内天气情况、现在的天气实况、天气和生活指数&lt;/h3&gt;&lt;p&gt;调用方法如下：输入参数：theCityName = 城市中文名称(国外城市可用英文)或城市代�?(不输入默认为上海�?)，如：上�? �? 58367，如有城市名称重复请使用城市代码查询(可�?�过 getSupportCity �? getSupportDataSet 获得)；返回数据： �?个一维数�? String(22)，共�?23个元素�??&lt;br /&gt;String(0) �? String(4)：省份，城市，城市代码，城市图片名称，最后更新时间�?�String(5) �? String(11)：当天的 气温，概况，风向和风力，天气趋势�?始图片名�?(以下称：图标�?)，天气趋势结束图片名�?(以下称：图标�?)，现在的天气实况，天气和生活指数。String(12) �? String(16)：第二天�? 气温，概况，风向和风力，图标�?，图标二。String(17) �? String(21)：第三天�? 气温，概况，风向和风力，图标�?，图标二。String(22) 被查询的城市或地区的介绍 &lt;br /&gt;&lt;a href=&quot;http://www.webxml.com.cn/images/weather.zip&quot;&gt;下载天气图标&lt;img src=&quot;http://www.webxml.com.cn/images/download_w.gif&quot; border=&quot;0&quot; align=&quot;absbottom&quot; /&gt;&lt;/a&gt;(包含大�?�中、小尺寸) &lt;a href=&quot;http://www.webxml.com.cn/zh_cn/weather_icon.aspx&quot; target=&quot;_blank&quot;&gt;天气图例说明&lt;/a&gt; &lt;a href=&quot;http://www.webxml.com.cn/files/weather_eg.zip&quot;&gt;调用此天气预报Web Services实例下载&lt;/a&gt; (VB ASP.net 2.0)&lt;/p&gt;&lt;br /&gt;
     * @param getWeatherbyCityName8
     */
    public void startgetWeatherbyCityName (
            cn.com.webxml.GetWeatherbyCityName getWeatherbyCityName8,
            final cn.com.weather.WeatherWebServiceCallbackHandler callback)
        throws java.rmi.RemoteException;

    //
}
