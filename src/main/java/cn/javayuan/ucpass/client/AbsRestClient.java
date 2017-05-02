package cn.javayuan.ucpass.client;

import cn.javayuan.ucpass.utils.EncryptUtil;
import cn.javayuan.wechat.utils.GlobalConstants;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayInputStream;


public abstract class AbsRestClient {
    public String server=GlobalConstants.getInterfaceUrl("ucApiUrl");
    public String version= GlobalConstants.getInterfaceUrl("ucVersion");
    public abstract String templateSMS(String accountSid, String authToken,String appId,String templateId,String to,String param);
    StringBuffer getStringBuffer() {
        StringBuffer sb = new StringBuffer("https://");
        sb.append(server);
        return sb;
    }
    DefaultHttpClient getDefaultHttpClient(){
        DefaultHttpClient httpclient=null;
        httpclient=new DefaultHttpClient();
        return httpclient;
    }
    String getSignature(String accountSid, String authToken, String timestamp, EncryptUtil encryptUtil) throws Exception{
        String sig = accountSid + authToken + timestamp;
        return encryptUtil.md5Digest(sig);
    }
    public HttpResponse get(String cType,String accountSid,String authToken,String timestamp,String url,DefaultHttpClient httpclient,EncryptUtil encryptUtil) throws Exception{
        HttpGet httppost = new HttpGet(url);
        httppost.setHeader("Accept", cType);//
        httppost.setHeader("Content-Type", cType+";charset=utf-8");
        String src = accountSid + ":" + timestamp;
        String auth = encryptUtil.base64Encoder(src);
        httppost.setHeader("Authorization",auth);
        return httpclient.execute(httppost);
    }
    public HttpResponse post(String cType,String accountSid,String authToken,String timestamp,String url,DefaultHttpClient httpclient,EncryptUtil encryptUtil,String body) throws Exception{
        HttpPost httppost = new HttpPost(url);
        httppost.setHeader("Accept", cType);
        httppost.setHeader("Content-Type", cType+";charset=utf-8");
        String src = accountSid + ":" + timestamp;
        String auth = encryptUtil.base64Encoder(src);
        httppost.setHeader("Authorization", auth);
        BasicHttpEntity requestBody = new BasicHttpEntity();
        requestBody.setContent(new ByteArrayInputStream(body.getBytes("UTF-8")));
        requestBody.setContentLength(body.getBytes("UTF-8").length);
        httppost.setEntity(requestBody);
        // 执行客户端请求
        return httpclient.execute(httppost);
    }
    public HttpResponse delete(String cType,String accountSid,String authToken,String timestamp,String url,DefaultHttpClient httpclient,EncryptUtil encryptUtil) throws Exception{
        HttpDelete httpDelete=new HttpDelete(url);
        httpDelete.setHeader("Accept", cType);
        httpDelete.setHeader("Content-Type", cType+";charset=utf-8");
        String src = accountSid + ":" + timestamp;
        String auth = encryptUtil.base64Encoder(src);
        httpDelete.setHeader("Authorization", auth);
        return httpclient.execute(httpDelete);
    }
    public HttpResponse put(String cType,String accountSid,String authToken,String timestamp,String url,DefaultHttpClient httpclient,EncryptUtil encryptUtil,String body) throws Exception{
        HttpPut httpPut = new HttpPut(url);
        httpPut.setHeader("Accept", cType);
        httpPut.setHeader("Content-Type", cType+";charset=utf-8");
        String src = accountSid + ":" + timestamp;
        String auth = encryptUtil.base64Encoder(src);
        httpPut.setHeader("Authorization", auth);
        BasicHttpEntity requestBody = new BasicHttpEntity();
        requestBody.setContent(new ByteArrayInputStream(body.getBytes("UTF-8")));
        requestBody.setContentLength(body.getBytes("UTF-8").length);
        httpPut.setEntity(requestBody);
        return httpclient.execute(httpPut);
    }
}
