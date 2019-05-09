package com.example.demo.sys;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import com.example.demo.domain.Img;
import com.example.demo.service.ImgService;
import com.example.demo.service.ZdService;

@Controller
@RequestMapping("/thy/static")
public class StaticController {
	
	@Autowired
	private ZdService zdService;
	
	@Autowired
	private ImgService imgService;
	
	/**
	 * 跳转到未授权页面
	 * @return
	 */
	@RequestMapping("/Unauthorized")
	public String toUnauthorized(){
		return "/system/UnauthorizedPage";
	}
	
	@RequestMapping("/toBackStageLogin")
	public String toBackStageLogin(){
		return "/system/backStageLogin";
	}
	/**
	 * 跳转到商城的登录页
	 * @return
	 */
	@RequestMapping("/toLoginPage")
	public String toLoginPage(){
		return "/system/loginPage";
	}
	
	/**
	 * 跳转到注册页面
	 * @return
	 */
	@RequestMapping("/registerPage")
	public String toReqisterPage(){
		return "/system/registerPage";
	}
	

	
	/**
	 * bootstrap测试页
	 * @return
	 */
	@RequestMapping("/index")
	public String page(){
		return "system/index";
	}
	
	/**
	 * 跳转到首页，加载数据
	 * @param model
	 * @param request
	 * @param webRequest
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/main")
	public String queryShopping(Model model,HttpServletRequest request,WebRequest webRequest){
		Map<String,Object> paramMap=getParamMap(request.getParameterMap());
		List<Img> list=(List<Img>)imgService.getImgByParamMap(paramMap);
		List<Img> lbtList=(List<Img>)imgService.getImgLBTByParamMap(paramMap);
		if(list!=null && !list.isEmpty()){
			model.addAttribute("Items",list);
		}
		if(lbtList !=null && !lbtList.isEmpty()){
			model.addAttribute("Lbts",lbtList);
		}
		if("1".equals(paramMap.get("czbs"))){
			return "sys/preview";
		}else{		
			return "shoppingPage";
		}
		
	}
	
	/**
	 * 获取图片文件
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/getfile")
	public void getFile(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map<String,Object> paramMap=getParamMap(request.getParameterMap());
		Img img=(Img)imgService.getImgByParamMap2(paramMap);
		byte[] data=img.getImg();
		InputStream is=new ByteArrayInputStream(data);
		response.setContentType("image/jpeg");
		OutputStream os=response.getOutputStream();
		response.reset();
		int num;
		byte buf[] =new byte[1024];
		while((num=is.read(buf))!=-1){
			os.write(buf, 0, num);
		}
		is.close();
		os.close();
	}
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/getZd")
	@ResponseBody
	public List getZdInfo(HttpServletRequest request){
		Map<String,Object> resultMap=new HashMap<String,Object>();
		Map<String,Object> paramMap=getParamMap(request.getParameterMap());
		List list=zdService.getZdInfo(paramMap);
		resultMap.put("data",list);
		resultMap.put("success",true);
		resultMap.put("msg","请求成功");
		return list;
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
}
