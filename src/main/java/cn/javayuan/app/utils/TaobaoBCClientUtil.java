package cn.javayuan.app.utils;

import cn.javayuan.app.entity.AppUser;
import cn.javayuan.wechat.utils.GlobalConstants;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.Userinfos;
import com.taobao.api.request.OpenimUsersAddRequest;
import com.taobao.api.request.OpenimUsersDeleteRequest;
import com.taobao.api.request.OpenimUsersUpdateRequest;
import com.taobao.api.response.OpenimUsersAddResponse;
import com.taobao.api.response.OpenimUsersDeleteResponse;
import com.taobao.api.response.OpenimUsersUpdateResponse;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * 淘宝百川接口
 * Created by 李明元 on 2017/3/31.
 */
public class TaobaoBCClientUtil {
    private static Logger logger = Logger.getLogger(TaobaoBCClientUtil.class);
    public static String appKey = GlobalConstants.getInterfaceUrl("bcAppKey");
    public static String appSecret =  GlobalConstants.getInterfaceUrl("bcAppSecret");
    public static String url =  GlobalConstants.getInterfaceUrl("bcUrl");

    /**
     * 添加用户到百川
     * @param user
     */
    public static void addUserToTaobaoBC(AppUser user)  {
        TaobaoClient client = new DefaultTaobaoClient(url, appKey, appSecret);
        OpenimUsersAddRequest req = new OpenimUsersAddRequest();
        List<Userinfos> userinfos = new ArrayList<>();
        Userinfos userinfo = new Userinfos();
        String nick = user.getNickname();
        if(nick.equals("")){
            nick="未设置昵称（"+user.getUsername()+"）";
        }
        userinfo.setNick(nick);
        userinfo.setIconUrl("http://images.javayuan.cn/"+user.getImage());
        userinfo.setMobile(user.getMobile());
        userinfo.setUserid(user.getUsername());
        userinfo.setPassword(user.getPassword());
        userinfos.add(userinfo);
        req.setUserinfos(userinfos);
        OpenimUsersAddResponse rsp = null;
        try {
            rsp = client.execute(req);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        assert rsp != null;
        logger.info(rsp.getBody());
    }

    /**
     * 修改用户
     * @param user
     */
    public static void updateUserToTaoBaoBc(AppUser user)  {
        TaobaoClient client = new DefaultTaobaoClient(url, appKey, appSecret);
        OpenimUsersUpdateRequest req = new OpenimUsersUpdateRequest();
        List<Userinfos> userinfos = new ArrayList<>();
        Userinfos userinfo = new Userinfos();
        String nick = user.getNickname();
        if(nick.equals("")){
            nick="未设置昵称（"+user.getUsername()+"）";
        }
        userinfo.setNick(nick);
        userinfo.setIconUrl("http://images.javayuan.cn/"+user.getImage());
        userinfo.setMobile(user.getMobile());
        userinfo.setUserid(user.getUsername());
        userinfo.setPassword(user.getPassword());
        userinfos.add(userinfo);
        req.setUserinfos(userinfos);
        OpenimUsersUpdateResponse rsp = null;
        try {
            rsp = client.execute(req);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        assert rsp != null;
        logger.info(rsp.getBody());
    }

    /**
     * 删除用户
     * @param user
     */
    public static void delUserToTaoBaoBC(AppUser user){
        TaobaoClient client = new DefaultTaobaoClient(url, appKey, appSecret);
        OpenimUsersDeleteRequest req = new OpenimUsersDeleteRequest();
        req.setUserids(user.getUsername());
        OpenimUsersDeleteResponse rsp = null;
        try {
            rsp = client.execute(req);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        assert rsp != null;
        logger.info(rsp.getBody());
    }
}
