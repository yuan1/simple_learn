package cn.javayuan.app.dto;

import cn.javayuan.app.entity.AppFind;
import cn.javayuan.app.entity.AppUser;
import cn.javayuan.app.entity.AppUserOrder;

/**
 * AppOrderDto
 * Created by 李明元 on 2017/4/21.
 */
public class AppOrderDto {
    private AppUserOrder order;
    private AppUser fromeUser;
    private AppUser toUser;
    private AppFind find;

    public AppUserOrder getOrder() {
        return order;
    }

    public void setOrder(AppUserOrder order) {
        this.order = order;
    }

    public AppUser getFromeUser() {
        return fromeUser;
    }

    public void setFromeUser(AppUser fromeUser) {
        this.fromeUser = fromeUser;
    }

    public AppUser getToUser() {
        return toUser;
    }

    public void setToUser(AppUser toUser) {
        this.toUser = toUser;
    }

    public AppFind getFind() {
        return find;
    }

    public void setFind(AppFind find) {
        this.find = find;
    }
}
