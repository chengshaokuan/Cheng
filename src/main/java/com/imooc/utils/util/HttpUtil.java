package com.imooc.utils.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jiecui1 on 2017/5/27.
 */
public class HttpUtil {
    private static final Logger LOG = LoggerFactory.getLogger(HttpUtil.class);

    private static int kMAXCONNECTION = 2000;

    private static int kMAX_RETRY_TIMES = 3;

    private static int kSTATUS_OK = 200;

    private static int CONNECTTIMEOUT = 5000;
    private static int CONNECTIONREQUESTTIMEOUT = 1000;
    private static int SOCKETTIMEOUT = 5000;

    private static PoolingHttpClientConnectionManager connectionManager;

    private static HttpClientBuilder httpBuilder;

    static {
        connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(kMAXCONNECTION);
        connectionManager.setDefaultMaxPerRoute(kMAXCONNECTION);
        httpBuilder = HttpClients.custom();
        httpBuilder.setConnectionManager(connectionManager);
        // setConnectTimeout：设置连接超时时间，单位毫秒。
        // setConnectionRequestTimeout：设置从connect Manager获取Connection
        // 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
        // setSocketTimeout：请求获取数据的超时时间，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECTTIMEOUT).setConnectionRequestTimeout(CONNECTIONREQUESTTIMEOUT)
                .setSocketTimeout(SOCKETTIMEOUT).build();
        httpBuilder.setDefaultRequestConfig(requestConfig);
    }

    public static HttpClient getHttpClient() {
        return httpBuilder.build();
    }

    private static List<NameValuePair> convertParams(final Map<String, String> params) {
        if (params == null || params.isEmpty()) {   return null;    }
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            LOG.info("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return pairs;
    }

    public static JsonNode convertEntity2JSON(final HttpEntity httpEntity) {
        LOG.info("convertEntity2JSON httpEntity : {}", httpEntity);
        if (httpEntity == null) {   return null;    }
        ObjectMapper mapper = new ObjectMapper();
        try {
            String content = EntityUtils.toString(httpEntity);
            LOG.info("convertEntity2JSON http entity content: {}", content);
            return mapper.readTree(content);
        } catch (IOException ie) {
            LOG.debug("convertEntity2JSON failed to parse");
            return null;
        }
    }

    public static HttpEntity sendRequest(final String url, final Map<String, String> params, boolean isGet) {
        LOG.info("sendRequest init ; url : {}; params : {}; isGet : {}", url, params, isGet);
        int retry = 0;
        HttpEntity entity = null;
        List<NameValuePair> paramList = convertParams(params);

        RequestBuilder requestBuilder = isGet ? RequestBuilder.get() : RequestBuilder.post();
        HttpUriRequest httpUriRequest = (paramList == null) ? requestBuilder.setUri(url).build()
                : requestBuilder.setUri(url)
                .addParameters((NameValuePair[]) paramList.toArray(new NameValuePair[paramList.size()]))
                .build();
        if (LOG.isDebugEnabled()) {
            LOG.debug(httpUriRequest.toString());
        }
        do {
            HttpClient client = getHttpClient();
            if (client == null) {
                LOG.warn("null client");
                continue;
            }
            try {
                HttpResponse response = client.execute(httpUriRequest);
                LOG.info("http response : {}", response);
                if (response != null && response.getStatusLine().getStatusCode() == kSTATUS_OK) {
                    entity = response.getEntity();
                } else {
                    LOG.warn(response.toString());
                }
                break;
            } catch (IOException e) {
                LOG.error(e.getMessage());
            }
        } while (++retry < kMAX_RETRY_TIMES);

        if (LOG.isDebugEnabled() && entity != null) {
            LOG.debug(entity.toString());
        }

        return entity;
    }

    public static JsonNode getJSONResponse(final String url, final Map<String, String> params, boolean isGet) {
        return convertEntity2JSON(sendRequest(url, params, isGet));
    }

    /**
     * 将返回结果 转json
     *
     * @param url
     * @param params
     * @param isGet
     * @return
     */
    public static JsonNode getJSONResult(final String url, final Map<String, String> params, boolean isGet) {
        JsonNode jsonNode = null;
        try {
            HttpEntity entity = sendRequest(url, params, isGet);
            if (entity != null) {
                String strResult = EntityUtils.toString(entity);
                /** 把json字符串转换成json对象 **/
                ObjectMapper mapper = new ObjectMapper();
                jsonNode = strResult == null ? null : mapper.readTree(strResult);
            }
        } catch (IOException e) {
            LOG.debug("to json fail", e);
        }
        return jsonNode;
    }
}
