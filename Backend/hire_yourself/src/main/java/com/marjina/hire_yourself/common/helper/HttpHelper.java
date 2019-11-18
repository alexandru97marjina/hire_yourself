package com.marjina.hire_yourself.common.helper;

import com.marjina.hire_yourself.common.util.RequestUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

import static com.marjina.hire_yourself.common.util.consts.GlobalConst.CONTENT_TYPE_HEADER;

@Component
public class HttpHelper {

    private CloseableHttpClient httpClient = HttpClientBuilder.create().build();

    /**
     * Get httpMethod
     *
     * @param uri    Uri which is needed to be accessed
     * @param params Params of get request
     * @return String response
     * @throws IOException I/O exception
     */
    public String get(String uri, Map<String, String> params) throws IOException {
        URIBuilder uriBuilder = RequestUtil.fromString(uri);
        if (params != null) {
            params.forEach(uriBuilder::setParameter);
        }

        HttpResponse response = httpClient.execute(new HttpGet(uriBuilder.toString()));

        return EntityUtils.toString(response.getEntity());
    }

    /**
     * Post httpMethod
     *
     * @param uri    Uri which is needed to be accessed
     * @param params Params of get request
     * @return String response
     * @throws IOException I/O exception
     */
    public String post(String uri, Map<String, String> params) throws IOException {
        HttpPost httpPost = new HttpPost(uri);
        JSONObject jsonData = new JSONObject();

        if (params != null) {
            params.forEach((i, val) -> {
                try {
                    jsonData.put(i, val);
                } catch (JSONException e) {
                    System.out.println(e);
                }
            });
        }

        StringEntity entity = new StringEntity(jsonData.toString());
        httpPost.setEntity(entity);
        httpPost.setHeader(CONTENT_TYPE_HEADER, "application/json");

        try {
            HttpResponse response = httpClient.execute(httpPost);

            return EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            System.out.println(e);

            return null;
        }
    }

}
