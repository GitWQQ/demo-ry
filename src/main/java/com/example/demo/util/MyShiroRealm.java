package com.example.demo.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.domain.Permission;
import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.service.PermissionService;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;

public class MyShiroRealm extends AuthorizingRealm{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PermissionService permissionService;
	
	/**
	 *权限 
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		System.err.println("权限配置=====> MyShiroRealm.doGetAuthorizationInfo");
		SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
		User user=(User)principal.getPrimaryPrincipal();
		List<Role> roles=new ArrayList<Role>();
		List<Permission> permissions=new ArrayList<Permission>();
		roles=roleService.getRolesByParam(user.getUsername());
		for (Role role : roles) {
			authorizationInfo.addRole(role.getRoleName().toString());
			permissions=permissionService.getPermissionByParam(role.getRoleName().toString());
			for (Permission permission : permissions) {
				authorizationInfo.addStringPermission(permission.getPermission().toString());
			}
		}
		return authorizationInfo;
	}

	/**
	 *认证
	 */
	@SuppressWarnings("unchecked")
	
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		System.err.println("认证配置========> MyShiroRealm.doGetAuthenticationInfo");
		UserInfoLoginToken userInfoToken=(UserInfoLoginToken)authenticationToken;
		String userName=userInfoToken.getUsername();
		String password=userInfoToken.getPassword().toString();
		SimpleAuthenticationInfo info=null;
		if(!"".equals(userName)){
			Map<String,Object> param=new HashMap<String,Object>();
			param.put("username",userName);
			//根据 userName查找数据记录
			List<User> resultList=userService.getUserByParam(param);
			System.out.println("resultList:"+resultList);
			if(resultList==null || resultList.size()<=0){
				throw new UnknownAccountException("No account found for user [" + userName + "]");
			}else{
				for (User user: resultList) {
					if(!"".equals(password) || password!=null){
						//从数据库中查询出来的密码
						password=user.getPassword();
						info=new SimpleAuthenticationInfo(user,password.toCharArray(),getName());
						if(!"".equals(user.getSalt())){
							info.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));
						}
					}
				}
			}
		}
		return info;
	}
}