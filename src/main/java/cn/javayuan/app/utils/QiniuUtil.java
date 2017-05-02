package cn.javayuan.app.utils;

import cn.javayuan.wechat.utils.GlobalConstants;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

/**
 * 七牛云工具类
 * Created by 李明元 on 2017/3/27.
 */
public class QiniuUtil {
    private static String accessKey = GlobalConstants.getInterfaceUrl("qnAccessKey");
    private static String secretKey =  GlobalConstants.getInterfaceUrl("qnSecretKey");
    private static String bucket =  GlobalConstants.getInterfaceUrl("qnBucket");

    /**
     * 上传到七牛云
     * @param uploadBytes
     * @return
     */
    public static String uploadToQiniu(byte[] uploadBytes) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone1());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        try {
            Response response = uploadManager.put(uploadBytes, null, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return putRet.hash;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                ex2.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 删除七牛云存储
     * @param key
     */
    public static void deleteToQiniu(String key){
        //不删除初始突图片
        if(!key.equals("default.png")){
            //构造一个带指定Zone对象的配置类
            Configuration cfg = new Configuration(Zone.zone1());
            Auth auth = Auth.create(accessKey, secretKey);
            BucketManager bucketManager = new BucketManager(auth, cfg);
            try {
                bucketManager.delete(bucket, key);
            } catch (QiniuException ex) {
                //如果遇到异常，说明删除失败
                System.err.println(ex.code());
                System.err.println(ex.response.toString());
            }
        }
    }
}
