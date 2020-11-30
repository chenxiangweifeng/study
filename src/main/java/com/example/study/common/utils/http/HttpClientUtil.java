package com.example.study.common.utils.http;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author 沉香微风
 * httpclient工具类
 * 增删改查 put post delete get 四种常用的请求方式工具类
 */
public class HttpClientUtil {

    private static String doPost(String url, String jsonText) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        HttpResponse response = null;
        String result = "";
        try {
            System.out.println("发送post请求");
//            这里需要将一个Java中的对象转换为web前端发送的json字符串对象，
            StringEntity entity = new StringEntity(jsonText, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity());
            }
        } catch (IOException e) {
            System.out.println("http get io 异常发生了");
        } finally {
            try {
                System.out.println("释放资源、关闭连接");
                if (httpPost != null) {
                    httpPost.releaseConnection();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    ((CloseableHttpResponse) response).close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    /**
     * 发起httpget请求
     * @param url
     * @return
     */
    public static String doGet(String url) {
        System.out.println(url);
        String result = "";
        HttpGet httpGet = new HttpGet(url);
        HttpClient httpClient = HttpClients.createDefault();
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity(), "utf-8");
                System.out.println(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                System.out.println("释放资源、关闭httpget连接");
                if (httpClient != null) {
                    httpGet.releaseConnection();
                }
                if (httpClient != null) {
                    ((CloseableHttpClient) httpClient).close();
                }
                if (response != null) {
                    ((CloseableHttpResponse) response).close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 发起httpdelete请求
     *
     * @param url
     * @return
     */
    public static String doDelete(String url) {
        String result = "";
        HttpDelete httpDelete = new HttpDelete(url);
        HttpClient httpClient = HttpClients.createDefault();
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpDelete);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity(), "utf-8");
                System.out.println(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                System.out.println("释放资源、关闭httpdelete连接");
                if (httpClient != null) {
                    httpDelete.releaseConnection();
                }
                if (httpClient != null) {
                    ((CloseableHttpClient) httpClient).close();
                }
                if (response != null) {
                    ((CloseableHttpResponse) response).close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * http put 请求
     * @return
     * @throws Exception
     */
    private static String doPut(String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut(url);
        HttpResponse response = null;
        String result = "";
        try {
            System.out.println("发送put请求");
            response = httpClient.execute(httpPut);
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                result = EntityUtils.toString(response.getEntity());
            }
        } catch (IOException e) {
            System.out.println("http put io 异常发生了");
        }finally {
            try {
                System.out.println("释放资源、关闭连接 put请求");
                if(httpPut != null){
                    httpPut.releaseConnection();
                }
                if(httpClient != null){
                    httpClient.close();
                }
                if(response != null){
                    ((CloseableHttpResponse) response).close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


}