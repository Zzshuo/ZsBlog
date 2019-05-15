package com.zs.blog.config;

import com.zs.blog.consts.CommonConst;
import com.zs.blog.model.Role;
import com.zs.blog.model.User;
import com.zs.blog.service.RoleService;
import com.zs.blog.service.UserService;
import com.zs.blog.util.JwtUtil;
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
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * 认证和授权具体实现
 *
 * @author zshuo
 * @date 2018/10/10
 **/
@Service
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private ShiroProperties shiroProperties;

    /**
     * 用户名信息验证
     * 主要是用来进行身份认证的，即验证用户输入的账号和密码是否正确
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        // 获取用户的输入的账号.
        String username = (String) token.getPrincipal();

        User user = userService.getByUserName(username);
        if (user == null) {
            throw new AuthenticationException("User didn't existed!");
        }
        // 用户名当做盐值
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);
        return new SimpleAuthenticationInfo(username, user.getPassword(), credentialsSalt, user.getNickname());
    }

    /**
     * 检查用户权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Integer userId = (Integer) SecurityUtils.getSubject().getPrincipal();
        String username = JwtUtil.getClaim(principals.toString(), CommonConst.USERNAME);
        User user = userService.getByUserName(username);

        //获取用户角色
        Role role = roleService.getRoleById(user.getRoleId());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole(role.getRole());
        info.addStringPermissions(Arrays.asList(role.getPermission().split(",")));
        return info;
    }
}
