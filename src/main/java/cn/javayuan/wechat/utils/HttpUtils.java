package cn.javayuan.wechat.utils;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;
/**
 * http请求工具类
 * Created by 李明元 on 2016/12/2.
 */
public class HttpUtils {

    /**
     * http get请求共用方法
     * @param reqUrl
     * @param params
     * @return
     * @throws Exception
     */
    public static String sendGet(String reqUrl, Map<String, String> params)
            throws Exception {
        InputStream inputStream = null;
        HttpGet request = new HttpGet();
        try {
            String url = buildUrl(reqUrl, params);
            HttpClient client = new DefaultHttpClient();
            request.setHeader("Accept-Encoding", "gzip");
            request.setURI(new URI(url));
            HttpResponse response = client.execute(request);
            inputStream = response.getEntity().getContent();
            return getJsonStringFromGZIP(inputStream);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            request.releaseConnection();
        }
    }

    public static  byte[]  sendGetImageByte(String reqUrl, Map<String, String> params)
            throws Exception {
        InputStream inputStream = null;
        HttpGet request = new HttpGet();
        try {
            String url = buildUrl(reqUrl, params);
            HttpClient client = new DefaultHttpClient();
            request.setURI(new URI(url));
            HttpResponse response = client.execute(request);
            inputStream = response.getEntity().getContent();
            return readInputStream(inputStream);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            request.releaseConnection();
        }
    }
    private static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while( (len=inStream.read(buffer)) != -1 ){
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }

    /**
     * http post请求共用方法
     * @param reqUrl
     * @param params
     * @return
     * @throws Exception
     */
    public static String sendPost(String reqUrl, Map<String, String> params)
            throws Exception {
        try {
            Set<String> set = params.keySet();
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            for (String key : set) {
                list.add(new BasicNameValuePair(key, params.get(key)));
            }
            if (list.size() > 0) {
                try {
                    HttpClient client = new DefaultHttpClient();
                    HttpPost request = new HttpPost(reqUrl);

                    request.setHeader("Accept-Encoding", "gzip");
                    request.setEntity(new UrlEncodedFormEntity(list, HTTP.UTF_8));

                    HttpResponse response = client.execute(request);

                    try (InputStream inputStream = response.getEntity().getContent()) {

                        return getJsonStringFromGZIP(inputStream);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    throw new Exception("网络连接失败,请连接网络后再试");
                }
            } else {
                throw new Exception("参数不全，请稍后重试");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("发送未知异常");
        }
    }

    /**
     * http post请求json数据
     * @param urls
     * @param params
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String sendPostBuffer(String urls, String params)
            throws IOException {
        HttpPost request = new HttpPost(urls);

        StringEntity se = new StringEntity(params, HTTP.UTF_8);
        request.setEntity(se);
        // 发送请求
        HttpResponse httpResponse = new DefaultHttpClient().execute(request);
        // 得到应答的字符串，这也是一个 JSON 格式保存的数据
        String retSrc = EntityUtils.toString(httpResponse.getEntity());
        request.releaseConnection();
        return retSrc;

    }

    /**
     *  http请求发送xml内容
     * @param urlStr
     * @param xmlInfo
     * @return
     */
    public static String sendXmlPost(String urlStr, String xmlInfo) {
        // xmlInfo xml具体字符串

        try {
            URL url = new URL(urlStr);
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
            con.setRequestProperty("Pragma:", "no-cache");
            con.setRequestProperty("Cache-Control", "no-cache");
            con.setRequestProperty("Content-Type", "text/xml");
            OutputStreamWriter out = new OutputStreamWriter(
                    con.getOutputStream());
            out.write(new String(xmlInfo.getBytes("utf-8")));
            out.flush();
            out.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String lines = "";
            for (String line = br.readLine(); line != null; line = br
                    .readLine()) {
                lines = lines + line;
            }
            return lines; // 返回请求结果
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "fail";
    }

    private static String getJsonStringFromGZIP(InputStream is) {
        String jsonString = null;
        try {
            BufferedInputStream bis = new BufferedInputStream(is);
            bis.mark(2);
            // 取前两个字节
            byte[] header = new byte[2];
            int result = bis.read(header);
            // reset输入流到开始位置
            bis.reset();
            // 判断是否是GZIP格式
            int headerData = getShort(header);
            // Gzip 流 的前两个字节是 0x1f8b
            if (result != -1 && headerData == 0x1f8b) {
                // LogUtil.i("HttpTask", " use GZIPInputStream  ");
                is = new GZIPInputStream(bis);
            } else {
                // LogUtil.d("HttpTask", " not use GZIPInputStream");
                is = bis;
            }
            InputStreamReader reader = new InputStreamReader(is, "utf-8");
            char[] data = new char[100];
            int readSize;
            StringBuffer sb = new StringBuffer();
            while ((readSize = reader.read(data)) > 0) {
                sb.append(data, 0, readSize);
            }
            jsonString = sb.toString();
            bis.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonString;
    }

    private static int getShort(byte[] data) {
        return (data[0] << 8) | data[1] & 0xFF;
    }

    /**
     * 构建get方式的url
     * @param reqUrl
     * @param params
     * @return
     */
    private static String buildUrl(String reqUrl, Map<String, String> params) {
        StringBuilder query = new StringBuilder();
        Set<String> set = params.keySet();
        for (String key : set) {
            query.append(String.format("%s=%s&", key, params.get(key)));
        }
        return reqUrl + "?" + query.toString();
    }

}