package com.jay.controller.cms.user;

import com.jay.vo.base.BaseResponseVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户登录相关
 * Created by hetiewei on 2017/2/27.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    /**
     * 登录页
     * @return
     */
    @GetMapping(value="")
    public String loginPage(){
        return "user/login";
    }

    /**
     * 登录检查
     * @param username
     * @param password
     * @param captcha  验证码
     * @return
     */
    @PostMapping(value="")
    @ResponseBody
    public BaseResponseVo login(String username, String password, String captcha){

        BaseResponseVo<String> responseVo = new BaseResponseVo<>(false);

        Subject currentUser = SecurityUtils.getSubject();
        //返回信息
        String msg = checkCaptcha(captcha);
        //1.验证码校验通过
        if (StringUtils.isEmpty(msg)){
            //2.用户名和密码校验
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            try{
                currentUser.login(token);
                if (currentUser.isAuthenticated()){
                    msg = "success";
                }
                responseVo.setStatus(true);
            }catch (IncorrectCredentialsException e) {
                msg = "登录密码错误. Password for account " + token.getPrincipal() + " was incorrect.";
                System.out.println(msg);
                msg = "usererror";// 身份验证失败！
            } catch (ExcessiveAttemptsException e) {
                msg = "登录失败次数过多";
                System.out.println(msg);
                msg = "usererror";// 身份验证失败！
            } catch (LockedAccountException e) {
                msg = "帐号已被锁定. The account for username " + token.getPrincipal() + " was locked.";
                System.out.println(msg);
                msg = "usererror";// 身份验证失败！
            } catch (DisabledAccountException e) {
                msg = "帐号已被禁用. The account for username " + token.getPrincipal() + " was disabled.";
                System.out.println(msg);
                msg = "usererror";// 身份验证失败！
            } catch (ExpiredCredentialsException e) {
                msg = "帐号已过期. the account for username " + token.getPrincipal() + "  was expired.";
                System.out.println(msg);
                msg = "usererror";// 身份验证失败！
            } catch (UnknownAccountException e) {
                msg = "帐号不存在. There is no user with username of " + token.getPrincipal();
                System.out.println(msg);
                msg = "usererror";// 身份验证失败！
            } catch (UnauthorizedException e) {
                msg = "您没有得到相应的授权！" + e.getMessage();
                System.out.println(msg);
                msg = "usererror";// 身份验证失败！
            }
        }

        responseVo.setMessage(msg);
        return responseVo;
    }

    /**
     * 验证码校验
     * @param captcha
     * @return
     */
    private String checkCaptcha(String captcha){
//        String msg = "";
//        Subject currentUser = SecurityUtils.getSubject();
//        //获取Session中验证码
//        Session session = currentUser.getSession();
//        String code = session.getAttribute("captcha").toString();
//        //1.校验验证码
//        if (StringUtils.isEmpty(captcha)) {
//            msg = "验证码为空！";// 验证码为空！
//        } else if (!captcha.equalsIgnoreCase(code)) {
//            msg = "验证码错误！";// 验证码错误！
//        }
//        return msg;
        return "";
    }

}
