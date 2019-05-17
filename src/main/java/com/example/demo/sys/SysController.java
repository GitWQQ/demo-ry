package com.example.demo.sys;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.session.HttpServletSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import com.example.demo.service.ZdService;
import com.example.demo.util.CommonUtil;
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
	 */
	@RequestMapping("/toLogin")
	@ResponseBody
	public Map<String,Object> login(HttpServletRequest httpRequest,HttpServletResponse httpResponse,Model model){
		System.out.println("==================登录============================");
		Subject subject=SecurityUtils.getSubject();
		Map<String,Object> paramMap=getParamMap(httpRequest.getParameterMap());
		Map<String,Object> result=new HashMap<>();
		String username=paramMap.get("username").toString();
		String password=paramMap.get("password").toString();
		String bs=paramMap.get("bs").toString();
		/*boolean remember=Boolean.parseBoolean(paramMap.get("remember").toString());
		System.out.println("remember:========"+remember);*/
		UserInfoLoginToken token=null;
			try{
				if(!"".equals(username)&& username !=null){
					token=new UserInfoLoginToken(username, password);
					subject.login(token);
					User currentUser=(User)subject.getPrincipal();
					//存session
					subject.getSession().setAttribute("token",token);
					subject.getSession().setAttribute("username",currentUser.getUsername());
					subject.getSession().setAttribute("user",currentUser);
					subject.getSession().setTimeout(30*60*1000);//设置session过期时间30分钟
					subject.getSession().setAttribute("ip",getIpValue(subject.getSession().getHost(),httpRequest));
					result.put("status",200);
					result.put("message","登录成功");
				}
			}catch(UnknownAccountException uae){
					System.out.println("用户不存在");
					log.error("用户不存在");
					result.put("status",201);
					result.put("message","用户不存在");
			}catch(IncorrectCredentialsException ice){
					System.out.println("密码不正确");
					log.error("密码不正确");
					result.put("status",202);
					result.put("message","密码不正确");
			}catch(LockedAccountException lae){
					System.out.println("账号被锁定");
					log.error("账号被锁定");
					result.put("status",203);
					result.put("message","账号被锁定");
			}catch(ExcessiveAttemptsException eae){
					log.error("密码尝试限制");
					result.put("status",204);
					result.put("message","密码尝试限制");
			}
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
		boolean result=true;
		List<User> users=userService.getAllUsers();
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
		Map<String,Object> paramMap=getParamMap(request.getParameterMap());
		Map<String,Object> result=new HashMap<>();
		userService.addUserInfoselective(paramMap);
		result.put("status",200);
		result.put("msg","注册成功!");
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
		}
		//request.getRequestDispatcher("/thy/shoppingPage").forward(request, response);
		response.sendRedirect("/thy/shoppingPage");
	}
	
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
				password=CommonUtil.encodePassphrase(user.getSalt(),password);
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
	
	
	//获取参数
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
