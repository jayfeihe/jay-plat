package com.jay.controller.base;

import com.jay.base.User;
import com.jay.util.encrypt.EncryptAndDecryptUtils;
import com.jay.vo.base.common.CommonConstants;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hetiewei on 2017/2/28.
 */
@Controller
public class BaseController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取Request对象
     * @return
     */
    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取Session对象
     * @return
     */
    protected Session getSession() {
        Session session = SecurityUtils.getSubject().getSession();
        return session;
    }

    /**
     * 获取登录者信息
     * @return
     */
    protected User getUser() {
        Session session = getSession();
        User user = (User) session.getAttribute(CommonConstants.SESSION_LOGIN_KEY);
        return user;
    }


    protected Long getUserId() {
        Long id=0l;
        User user=getUser();
        if(user!=null){
            id=user.getId();
        }
        return id;
    }

    protected String getUserName() {
        String userName=null;
        User user=getUser();
        if(user!=null){
            userName=user.getUsername();
        }
        return userName;
    }

    /**
     * 获取登录者IP
     * @return
     */
    protected String getUserIp() {
        String value = getRequest().getHeader("X-Real-IP");
        if (StringUtils.isNotBlank(value) && !"unknown".equalsIgnoreCase(value)) {
            return value;
        } else {
            return getRequest().getRemoteAddr();
        }
    }

    /**
     * 获取加盐的MD5密码
     * @param password
     * @return
     */
    protected String getPwd(String password, String salt){
        try {
            String pwd = EncryptAndDecryptUtils.md5Encrypt(password+salt);
            return pwd;
        } catch (Exception e) {
            logger.error("密码加密异常：",e);
        }
        return null;
    }

//    /**
//     * Cookie签名
//     * @param value
//     * @return
//     */
//    protected String cookieSign(String value){
//        try{
//            value = value + CommonConstants.SESSION_LOGIN_KEY;
//            String sign = Des3EncryptionUtil.encode(Const.DES3_KEY,value);
//            return sign;
//        }catch (Exception e){
//            logger.error("cookie签名异常：",e);
//        }
//        return null;
//    }

    @GetMapping("/test")
    public String test(Model model){
        model.addAttribute("recipient", "recipient");
        return "test";
    }
}
