package com.jay.shiro;

import com.jay.base.User;
import com.jay.service.base.UserService;
import com.jay.vo.base.common.CommonConstants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Shiro的登录与认证模块
 * Created by hetiewei on 2017/2/27.
 */
public class UserRealm extends AuthorizingRealm{

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    public UserRealm() {
        setName("UserRealm");
        //采用MD5加密
        setCredentialsMatcher(new HashedCredentialsMatcher("md5"));
    }

    /**
     * 用户授权，基于ACL的角色授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
//        if (principals == null) {
//            return null;
//        }
//
//        AuthorizationInfo info = null;
//
//        info = (AuthorizationInfo) UserUtils.getCache(UserUtils.CACHE_AUTH_INFO);
//
//        if (info == null) {
//            info = doGetAuthorizationInfo(principals);
//            if (info != null) {
//                UserUtils.putCache(UserUtils.CACHE_AUTH_INFO, info);
//            }
//        }
//
//        return info;
    }

//    /**
//     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
//     */
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        Principal principal = (Principal) getAvailablePrincipal(principals);
//        // 获取当前已登录的用户
//        if (!Global.TRUE.equals(Global.getConfig("user.multiAccountLogin"))){
//            Collection<Session> sessions = getSystemService().getSessionMapper().getActiveSessions(true, principal, UserUtils.getSession());
//            if (sessions.size() > 0){
//                // 如果是登录进来的，则踢出已在线用户
//                if (UserUtils.getSubject().isAuthenticated()){
//                    for (Session session : sessions){
//                        getSystemService().getSessionMapper().delete(session);
//                    }
//                }
//                // 记住我进来的，并且当前用户已登录，则退出当前用户提示信息。
//                else{
//                    UserUtils.getSubject().logout();
//                    throw new AuthenticationException("msg:账号已在其它地方登录，请重新登录。");
//                }
//            }
//        }
//        User user = getSystemService().getUserByLoginName(principal.getLoginName());
//        if (user != null) {
//            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//            List<Menu> list = UserUtils.getMenuList();
//            for (Menu menu : list){
//                if (StringUtils.isNotBlank(menu.getPermission())){
//                    // 添加基于Permission的权限信息
//                    for (String permission : StringUtils.split(menu.getPermission(),",")){
//                        info.addStringPermission(permission);
//                    }
//                }
//            }
//            // 添加用户权限
//            info.addStringPermission("user");
//            // 添加用户角色信息
//            for (Role role : user.getRoleList()){
//                info.addRole(role.getEnname());
//            }
//            // 更新登录IP和时间
//            getSystemService().updateUserLoginInfo(user);
//            // 记录登录日志
//            LogUtils.saveLog(Servlets.getRequest(), "系统登录");
//            return info;
//        } else {
//            return null;
//        }
//    }

    /**
     * 用户认证
     * @param authcToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

        if (logger.isDebugEnabled()){
            logger.debug("login submit, username: {}", token.getUsername());
        }

        // 校验用户名密码
        User user = userService.findByUsername(token.getUsername());

        if (user ==null){
            throw new UnknownAccountException();
        }
        //将当前用户信息设置到Session中
       setSession(CommonConstants.SESSION_LOGIN_KEY, user);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
        return info;
    }

    /**
     * 将一些数据放到ShiroSession中,以便于其它地方使用
     * 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
     * @see
     */
    private void setSession(Object key, Object value) {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            Session session = currentUser.getSession();
            System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
            if (null != session) {
                session.setAttribute(key, value);
            }
        }
    }


}
