package cn.javayuan.system.utils;

import cn.javayuan.system.dto.UseUserDto;
import cn.javayuan.system.shiro.CustomRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;

/**
 * Shiro 工具
 * Created by 李明元 on 2016/11/23.
 */
public class ShiroUtil {
    /**
     * 从shiro session中获取actiiveUser
     * @return
     */
    public static UseUserDto getSessionActiveUser(){
        //从shiro的session中取activeUser
        Subject subject = SecurityUtils.getSubject();
        //取身份信息
        return (UseUserDto) subject.getPrincipal();
    }

    /**
     * 删除缓存用于修改权限生成
     */
    public static void clearCached(){
        new CustomRealm().clearCached();
    }


    /**
     * 生成加密过的密码
     * @param password 默认密码
     * @param salt  盐
     * @return
     */
    public static String  createEncodedPassword( String password,String salt){
        String algorithmName = "md5";
        int hashIterations = 1;
        SimpleHash hash = new SimpleHash(algorithmName, password, salt, hashIterations);
        String encodedPassword;
        encodedPassword = hash.toHex();
        return encodedPassword;
    }

    /**
     * 生成盐
     * @return
     */
    public static String createSalt(){
        return  new SecureRandomNumberGenerator().nextBytes().toHex();
    }
}
