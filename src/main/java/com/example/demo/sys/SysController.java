package com.example.demo.sys;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.session.HttpServletSession;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.OnlineMesg;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import com.example.demo.service.ZdService;
import com.example.demo.util.AESUtils;
import com.example.demo.util.CommonUtil;
import com.example.demo.util.CookieUtils;
import com.example.demo.util.EncryptUtil;
import com.example.demo.util.TokenManager;
import com.example.demo.util.UserInfoLoginToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/thy/sys")
public class SysController {
	
	private static final Logger log=LoggerFactory.getLogger(SysController.class);

	@Autowired
	private ZdService zdService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DefaultWebSessionManager sessionManager;
	/**
	 * 获取字典信息
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/getZdInfo")
	@ResponseBody
	public List getZdInfo(HttpServletRequest request,Model model){
		Map<String,Object> paramMap=getParamMap(request.getParameterMap());
		Map<String,Object> resultMap=new HashMap<>();
		List list=zdService.getZdInfo(paramMap);
		String zddm=paramMap.get("zddm").toString();
		if(zddm!=null &&"XZQH".equals(zddm)){
			
		}
		
		System.out.println("list:"+list);
		return list;
	}
	
	/**
	 * 执行登录
	 * @param httpRequest
	 * @return
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidKeyException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 */
	@RequestMapping("/toLogin")
	@ResponseBody
	public Map<String,Object> login(HttpServletRequest httpRequest,HttpServletResponse httpResponse,Model model) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException{
		Subject subject=SecurityUtils.getSubject();
		Map<String,Object> paramMap=getParamMap(httpRequest.getParameterMap());
		Map<String,Object> result=new HashMap<>();
		String username=paramMap.get("username").toString();
		String password=paramMap.get("password").toString();
		String bs=paramMap.get("bs").toString();
		boolean rememberMe=Boolean.parseBoolean(paramMap.get("remember").toString());
		UserInfoLoginToken token=null;
			try{
				if(!"".equals(username)&& username !=null){
					token=new UserInfoLoginToken(username, password);
					token.setRememberMe(rememberMe);
					subject.login(token);
					User currentUser=(User)subject.getPrincipal();
					//cookie
					CookieUtils.createCookie("sso_cookie_U",EncryptUtil.AESencode(username,"666666"),httpRequest,httpResponse);
					CookieUtils.createCookie("sso_cookie_P",EncryptUtil.AESencode(password,"666666"),httpRequest,httpResponse);
					//CookieUtils.createCookie("sso_token",token.toString(),httpRequest,httpResponse);
					//存session
					subject.getSession().setAttribute("token",token);
					subject.getSession().setAttribute("username",currentUser.getUsername());
					subject.getSession().setAttribute("phone",currentUser.getPhone());
					subject.getSession().setAttribute("email", currentUser.getEmail());
					subject.getSession().setAttribute("realname",currentUser.getRealname());
					subject.getSession().setAttribute("user",currentUser);
					subject.getSession().setTimeout(30*60*1000);//设置session过期时间30分钟
					subject.getSession().setAttribute("ip",getIpValue(subject.getSession().getHost(),httpRequest));
					result.put("status",200);
					result.put("message","登录成功");
				}
			}catch(UnknownAccountException uae){
					log.info("用户【"+username+"】不存在");
					String msg=CommonUtil.lockAccount();
					result.put("status",201);
					result.put("message","用户不存在,"+msg);
			}catch(IncorrectCredentialsException ice){
					log.info("用户【"+username+"】密码不正确");
					String msg=CommonUtil.lockAccount();
					result.put("status",202);
					result.put("message","密码不正确,"+msg);
			}catch(LockedAccountException lae){
					log.info("用户【"+username+"】账号被锁定");
					result.put("status",203);
					result.put("message","账号被锁定");
			}catch(ExcessiveAttemptsException eae){
					log.error("密码尝试限制");
					result.put("status",204);
					result.put("message","密码尝试限制");
			}
			result.put("username",username);
			result.put("password",password);
		return result;
	}
	
	
	/**
	 * 注册时验证用户名是否已经存在
	 * @param username
	 * @return
	 */
	@RequestMapping("/checkNameExist")
	@ResponseBody
	public String checkNameExist(@RequestParam String username){
		Map<String,Object> paramMap=new HashMap<>();
		boolean result=true;
		List<User> users=userService.getAllUsersByParam(paramMap);
		for (User user : users) {
			if (user.getUsername().equals(username)) {
				result=false;
				break;
			}
		}
		Map<String,Boolean> map=new HashMap<>();
		map.put("valid", result);
		ObjectMapper mapper=new ObjectMapper();
		String resultString="";
		try {
			resultString=mapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultString;
	}
	
	/**
	 * 注册前检查用户名是否已经存在
	 * @param request
	 * @return
	 */
	@RequestMapping("/checkNameExist2")
	@ResponseBody
	public String checkNameExist2(HttpServletRequest request){
		boolean result=true;
		Map<String,Object> paramMap=getParamMap(request.getParameterMap());
		List<User> users=userService.getUserByParam(paramMap);
		Map<String,Boolean> map=new HashMap<>();
		if(users !=null){
			result=false;
		}
		map.put("valid", result);
		ObjectMapper mapper=new ObjectMapper();
		String resultString="";
		try {
			resultString=mapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultString;
	}
	
	/**
	 * 注册
	 * @param request
	 * @return
	 */
	@RequestMapping("/toRegist")
	@ResponseBody
	public Map<String,Object> toRegist(HttpServletRequest request){
		//获取Session中的验证码
		String yzm=TokenManager.getYZM();
		Map<String,Object> paramMap=getParamMap(request.getParameterMap());
		Map<String,Object> result=new HashMap<>();
		String vcode=paramMap.get("vcode").toString();
		if(vcode==null||"".equals(vcode)){
			result.put("status",201);
			result.put("msg","验证码不能为空");
		}else{
			if(yzm.equalsIgnoreCase(vcode)){
				System.out.println(yzm+"===="+vcode);
				//验证码相同
				try {
					userService.addUserInfoselective(paramMap);
				} catch (AddressException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				result.put("status",200);
				result.put("msg","注册成功!");
			}else{
				//验证码不同
				result.put("status",201);
				result.put("msg","验证码错误");
			}
		}	
		return result;
	}
	
	/**
	 * 登出
	 * @return 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping("/logout")
	public void logout(HttpServletRequest request,HttpServletResponse response) throws  ServletException, IOException{
		Subject subject=SecurityUtils.getSubject();
		if(subject!=null && subject.getSession()!=null){
			subject.logout();
			Cookie[] cookies=request.getCookies();
			if(cookies !=null){
				for (Cookie cookie : cookies) {
					if(cookie.getName().equals("sso_cookie_U")){
						cookie.setValue("");
						cookie.setMaxAge(0);
						cookie.setPath("/");
						response.addCookie(cookie);
					}
					if(cookie.getName().equals("sso_cookie_P")){
						cookie.setValue("");
						cookie.setMaxAge(0);
						cookie.setPath("/");
						response.addCookie(cookie);
					}
				}
			}
		}
		response.sendRedirect("/thy/shoppingPage");
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/modifyPass")
	@ResponseBody
	public Map<String,Object> modifyPass(HttpServletRequest request){
		Map<String,Object> result=new HashMap<>();
		//获取当前登录用户信息
		Subject subject=SecurityUtils.getSubject();
		User user=(User)subject.getPrincipal();
		Map<String,Object>  paramMap=getParamMap(request.getParameterMap());
		//获取前台传来的参数password（明文密码），进行加密加盐
		String password=paramMap.get("password").toString();
		if(password !=null && !"".equals(password)){
			if(user.getSalt()!=null && !"".equals(user.getSalt())){
				password=CommonUtil.md5(user.getSalt(),password);
			}	
		}
		//获取当登录前用户的密码
		String oldPassWord="";
		if(user !=null){
			oldPassWord=user.getPassword();
		}
		//前台传来的密码加密后与当前用户密码对比，如果相等，则允许修改密码，否则不能修改密码
		if(oldPassWord.equals(password)){
			//如果输入的原始密码和从数据库查询出来的密码一致，则允许修改密码
			userService.updateUserInfoByParam(paramMap);
			result.put("msg","修改成功!");
			return result;
		}else{
			result.put("msg","原始密码不正确!");
			return result;
		}
	}
	
	/**ModifyPass
	 * @param ip
	 * @param request
	 * @return
	 */
	public static String getIpValue(String ip,HttpServletRequest request){
		String resip = ip;
        	resip = getIpAddr(request);
		return resip;
	}
	/**
	 * 获取IP
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}
		}
		System.out.println("ip---all:"+ipAddress);
		if (ipAddress != null && ipAddress.indexOf(",") > 0) { // "***.***.***.***".length() = 15
			ipAddress = ipAddress.split(",")[0];
		}
		System.out.println("ip---0:"+ipAddress);
		return ipAddress;
	}
	
	
	/**
	 * 获取所有当前登录用户的信息
	 * @return
	 */
	@RequestMapping("/session")
	@ResponseBody
	public Object getOnlineUsers(){
		Set<Map<String,Object>> onlineUsers=new LinkedHashSet<>();
		sessionManager.validateSessions();
		Collection<Session> sessions= sessionManager.getSessionDAO().getActiveSessions();
		for (Session session : sessions) {
			if(session.getAttribute("username") !=null &&  session.getTimeout()>0){
				Map<String,Object> map=new HashMap<>();
				map.put("username",session.getAttribute("username"));
				map.put("realname",session.getAttribute("realname"));
				map.put("ip",session.getAttribute("ip"));
				map.put("phone",session.getAttribute("phone"));
				map.put("email",session.getAttribute("email"));
				map.put("loginTime", CommonUtil.dateToString(session.getStartTimestamp()));

				onlineUsers.add(map);
			}
		}
		return onlineUsers;
	}
	
	/**
	 * 获取当前登录用户数，调用getOnlineUsers()方法，
	 * 获取返回值List<Map<String,String>>的长度
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/getOnlineNum")
	@ResponseBody
	public int getOnlineNum(){
		Set<Map<String,Object>> result=(HashSet<Map<String, Object>>) getOnlineUsers();
		return result.size();
	}
	
	

	/**
	 * 跳转到在线用户信息页面
	 */
	@RequestMapping("/showOnlineUser")
	public String toOnlineUsersPage(Model model){
		return "/sys/onlineUsersPage";
	}
	
	
	/**
	 * 获取在线人员信息
	 * @return
	 */
	@RequestMapping("/getOnLineUsersInfo")
	@ResponseBody
	public Set<Map<String,Object>> getOnlineUsersInfo(){
		Set<Map<String,Object>> result=(HashSet<Map<String, Object>>) getOnlineUsers();
		return result;
	}
	
	
	/**
	 * 检查是否已经登录，如果已经登录过了，刷新登录页面直接跳转到
	 * 登录后的首页。如果没登录，则还是登录页面
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping("/checked_login")
	@ResponseBody
	public Map checked_login2(HttpServletRequest request,HttpServletResponse response) 
			throws IOException, ServletException{
		Map<String,Object> map=new HashMap<>();
		Subject subject=SecurityUtils.getSubject();
		System.err.println(subject.isAuthenticated());
		if(subject.isAuthenticated()){
			map.put("status",200);
		}else{
			map.put("status",201);
		}
		return map;
	}

	
	/**
	 * 获取request里的参数，
	 * 并且封装到Map集合中
	 * @param map
	 * @return
	 */
	private Map<String, Object> getParamMap(Map<String, String[]> map){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		for (String key : map.keySet()) {
			if (map.get(key) instanceof String[]) {
				if (((String[]) map.get(key)).length > 0) {					
					paramMap.put(key, ((String[]) map.get(key))[0]);
				}
			}
		} 
		return paramMap;
	}
		
	
	public static void main(String[] args) {
		String salt=CommonUtil.getSalt();
		System.out.println("salt:"+salt);
		System.out.println(CommonUtil.encodePassphrase("4bV+oJuheKcajXKPujbBYw==","123456"));
	}
	
}
