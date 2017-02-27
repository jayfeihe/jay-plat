package com.jay.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * Shiro的登录与认证模块
 * Created by hetiewei on 2017/2/27.
 */
public class UserRealm extends AuthorizingRealm{

    public UserRealm() {
        setName("UserRealm");
        //采用MD5加密
        setCredentialsMatcher(new HashedCredentialsMatcher("md5"));
        this.setCachingEnabled(false);
    }

    /**
     * 用户授权，基于ACL的角色授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 用户认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        return null;
    }
}
