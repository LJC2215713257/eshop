package cn.edu.jxufe.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpUtils {
    public static String doGet(String url){
        HttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        try {
            HttpResponse response = client.execute(get);
            System.out.println(response.getEntity().toString());
            return EntityUtils.toString(response.getEntity(),"utf-8");
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }finally {
            get.releaseConnection();
        }
    }

    public static String doPost(String url,String jsonStr){
        HttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        post.addHeader("content-type","application/json;charset=utf-8");
        post.setEntity(new StringEntity(jsonStr,"utf-8"));
        try {
            HttpResponse response = client.execute(post);
            return response.getEntity().toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }finally {
            post.releaseConnection();
        }
    }
}
