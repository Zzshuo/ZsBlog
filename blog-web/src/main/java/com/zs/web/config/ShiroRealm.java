package com.zs.web.config;

import com.zs.common.consts.CommonConst;
import com.zs.web.service.RoleService;
import com.zs.web.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

/**
 * 认证和授权具体实现
 *
 * @author zshuo
 * @date 2018/10/10
 **/
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    /**
     * 权限认证，为当前登录的Subject授予角色和权限（角色的权限信息集合）
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        Integer userId = (Integer) SecurityUtils.getSubject().getPrincipal();
//        Integer userId = (Integer) principalCollection.getPrimaryPrincipal();

        User user = userService.getUserById(userId);
        Role role = roleService.getRoleById(user.getRoleId());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole(role.getRole());
        info.addStringPermissions(Arrays.asList(role.getPermission().split(",")));
        return info;
    }

    /**
     * 认证登陆subject身份
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户的输入的账号.
        String username = (String) authenticationToken.getPrincipal();
        User user = userService.getByUserName(username);
        if (user == null) {
            throw new UnknownAccountException("账号不存在！");
        }

        // principal参数使用用户Id，方便动态刷新用户权限
        return new SimpleAuthenticationInfo(
                user.getId(),
                user.getPassword(),
                //加密的盐值
                ByteSource.Util.bytes(CommonConst.SHIRO_SALT),
                //realm name
                getName()
        );
    }
}
