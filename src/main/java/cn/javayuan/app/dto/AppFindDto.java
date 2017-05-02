package cn.javayuan.app.dto;

import cn.javayuan.app.entity.AppFind;
import cn.javayuan.app.entity.AppUser;
import cn.javayuan.app.entity.AppUserEva;

import java.util.List;

/**
 * findDto
 * Created by 李明元 on 2017/4/21.
 */
public class AppFindDto {
    private AppFind find;
    private AppUser user;
    private List<AppEvaDto> evas;

    public AppFind getFind() {
        return find;
    }

    public void setFind(AppFind find) {
        this.find = find;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public List<AppEvaDto> getEvas() {
        return evas;
    }

    public void setEvas(List<AppEvaDto> evas) {
        this.evas = evas;
    }
}
