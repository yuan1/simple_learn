package cn.javayuan.app.dto;

import cn.javayuan.app.entity.AppFind;
import cn.javayuan.app.entity.AppUser;
import cn.javayuan.app.entity.AppUserEva;

/**
 * eva dto
 * Created by 李明元 on 2017/4/21.
 */
public class AppEvaDto {
    private AppUser user;
    private AppFind find;
    private AppUserEva eva;

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public AppFind getFind() {
        return find;
    }

    public void setFind(AppFind find) {
        this.find = find;
    }

    public AppUserEva getEva() {
        return eva;
    }

    public void setEva(AppUserEva eva) {
        this.eva = eva;
    }
}
