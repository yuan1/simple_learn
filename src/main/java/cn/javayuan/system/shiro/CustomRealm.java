package cn.javayuan.system.shiro;

import cn.javayuan.system.dto.UseUserDto;
import cn.javayuan.system.entity.SysPermission;
import cn.javayuan.system.entity.SysUser;
import cn.javayuan.system.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 自定义realm实现动态权限控制
 * Created by 李明元 on 2016/11/22.
 */
public class CustomRealm extends AuthorizingRealm {
    //注入service
    @Autowired
    private SysUserService sysUserService;

    // 设置realm的名称
    @Override
    public void setName(String name) {
        super.setName("customRealm");
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//从 principals获取主身份信息
        //将getPrimaryPrincipal方法返回值转为真实身份类型（在上边的doGetAuthenticationInfo认证通过填充到SimpleAuthenticationInfo中身份类型），
        UseUserDto useUserDto = (UseUserDto) principalCollection.getPrimaryPrincipal();
        //根据身份信息获取权限信息
        //从数据库获取到权限数据
        List<SysPermission> permissionList = null;
        try {
            permissionList = sysUserService.findPermissionListByUserId(useUserDto.getSysUser().getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //单独定一个集合对象
        List<String> permissions = new ArrayList<>();
        if (permissionList != null) {
            for (SysPermission sysPermission : permissionList) {
                //将数据库中的权限标签 符放入集合
                permissions.add(sysPermission.getPercode());
            }
        }


        //查到权限数据，返回授权信息(要包括 上边的permissions)
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //将上边查询到授权信息填充到simpleAuthorizationInfo对象中
        simpleAuthorizationInfo.addStringPermissions(permissions);

        return simpleAuthorizationInfo;
    }

    //清除缓存
    public void clearCached() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // token是用户输入的用户名和密码
        // 第一步从token中取出用户名
        String userCode = (String) authenticationToken.getPrincipal();
        // 第二步：根据用户输入的userCode从数据库查询
        SysUser sysUser = sysUserService.findSysUserByUserCode(userCode);

        // 如果查询不到返回null
        if (sysUser == null) {//
            return null;
        }
        // 从数据库查询到密码
        String password = sysUser.getPassword();

        //盐
        String salt = sysUser.getSalt();

        // 如果查询到返回认证信息AuthenticationInfo

        //activeUser就是用户身份信息
        UseUserDto useUserDto = new UseUserDto();

        useUserDto.setSysUser(sysUser);

        useUserDto.setSysRole(sysUserService.findRoleByUserId(sysUser.getId()));

        useUserDto.setSysDept(sysUserService.findDeptByUserId(sysUser.getId()));
        //..
        //根据用户id取出菜单
        Map<SysPermission, List<SysPermission>> menus = null;
        try {
            //通过service取出菜单
            menus = sysUserService.getUserMapMenu(sysUser.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //将用户菜单 设置到activeUser
        useUserDto.setMenus(menus);

        //将activeUser设置simpleAuthenticationInfo

        return new SimpleAuthenticationInfo(
                useUserDto, password, ByteSource.Util.bytes(salt), this.getName());
    }
}
