package cn.javayuan.app.interceptor;

import cn.javayuan.app.entity.AppUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * app登录拦截器
 * Created by 李明元 on 2017/3/20.
 */
public class AppInitInterceptor implements HandlerInterceptor {
    private final Logger log = LoggerFactory.getLogger(AppInitInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url = requestUri.substring(contextPath.length());
        if (url.contains("/app/login")||url.contains("/app/message")||url.contains("/app/login/openid")||url.contains("/app/check/username")
                ||url.contains("/app/check/mobile")||url.contains("/lib")||url.contains("/static")||url.contains("/app/save/location")
                ||url.contains("/app/regist")||url.contains("/app/rem")||url.contains("/app/wechat")) {
            return true;
        }
        AppUser user = (AppUser) request.getSession().getAttribute("nowAppUser");
        if (user == null) {
            log.info("未登录：跳转到login页面！");
            request.getRequestDispatcher("/app/login").forward(request, response);
            return false;
        }
        return true;

    }
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }


}