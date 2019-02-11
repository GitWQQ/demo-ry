package com.example.demo.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.relation.RoleInfo;

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

import com.example.demo.domain.shiroEntity.SysPermission;
import com.example.demo.domain.shiroEntity.SysRole;
import com.example.demo.domain.User;
import com.example.demo.domain.shiroEntity.UserInfo;
import com.example.demo.service.UserService;

public class MyShiroRealm extends AuthorizingRealm{

	@Autowired
	private UserService userService;
	
	/**
	 *权限 
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		System.out.println("权限配置==> MyShiroRealm.doGetAuthorizationInfo");
		SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
		UserInfo userInfo=(UserInfo)principal.getPrimaryPrincipal();
		for(SysRole role:userInfo.getRoleList()){
			authorizationInfo.addRole(role.getRole());
			for(SysPermission permission:role.getPermissions()){
				authorizationInfo.addStringPermission(permission.getPermission());
			}
		}
		return authorizationInfo;
	}

	/**
	 *认证
	 */
	@SuppressWarnings("unchecked")
	
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
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
						String password2=user.getPassword();
						info=new SimpleAuthenticationInfo(user,password2.toCharArray(),getName());
						//ByteSource credentialsSalt=ByteSource.Util.bytes(user.getUsername()+user.getPassword());
						/*info=new SimpleAuthenticationInfo(
								user.getUsername(),
								user.getPassword(),
								credentialsSalt,
								getName()
								);*/
						if(user.getSalt()!=null && !"".equals(user.getSalt())){
							info.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));
						}
					}
				}
			}
		}
		return info;
	}

}
