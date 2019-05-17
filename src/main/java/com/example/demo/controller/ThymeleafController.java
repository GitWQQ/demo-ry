package com.example.demo.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.domain.Img;
import com.example.demo.service.ImgService;
import com.example.demo.service.ItemService;
import com.example.demo.service.ZdService;
import com.example.demo.util.BaseController;
import com.example.demo.util.CommonUtil;

@Controller
@RequestMapping("/thy")
public class ThymeleafController extends BaseController{

	private static Logger logger=LoggerFactory.getLogger(ThymeleafController.class);
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ImgService imgService;
	
	/**
	 * 登录后台管理主页
	 * @return
	 */
	@RequestMapping("/backstage")
	public String toIndex(){
		return "backstage";
	}
	/**
	 * 购物车
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/gwc")
	public String toGwc(HttpServletRequest request,Model model){
		Map<String,Object> paramMap=getParamMap(request.getParameterMap());
		List item=itemService.getItemRecordByParam(paramMap);
		if(item !=null && !"".equals(item)){
			model.addAttribute("items",item);
		}
		String img_id=paramMap.get("img_id").toString();
		model.addAttribute("img_id",img_id);
		return "gwc";
	}
	
	
	@SuppressWarnings({ "rawtypes", "unused" })
	@RequestMapping("/getRecordEdit")
	public String getRecordEdit(HttpServletRequest request,Model model){
		Map<String,Object> paramMap=getParamMap(request.getParameterMap());
		Map<String,Object> resultMap=getParamMap(request.getParameterMap());
		List list=null;
		if (paramMap.get("id")!=null && !"".equals(paramMap.get("id"))) {
			 list=itemService.getAllItemRecord(paramMap);
			if(list!=null && !list.isEmpty()){
				model.addAttribute("list",list);
			}			
		}
		//resultMap.put("item",list);
		//resultMap.put("success",true);
		System.out.println("list:"+list);
		return "item/item_detail";
	}
	
	
	@RequiresPermissions(value="add")
	@RequestMapping("/fileUpload")
	@ResponseBody
	public Map<String,Object> uploadImg(@RequestParam(value="img_file",required=false)MultipartFile file,HttpServletRequest request) throws IOException{
		Map<String,Object> resultMap=new HashMap<>();
		String img_id=CommonUtil.getID();
		if(file==null){
			logger.info("文件不存在");
		}else{
			Map<String,Object> paramMap=getParamMap(request.getParameterMap());
			byte[] bytes=file.getBytes();
			paramMap.put("img",bytes);
			//获取文件名
			String fileName=file.getOriginalFilename();
			paramMap.put("img_name",fileName);
			if(fileName !=null && !fileName.isEmpty()){
				//获取文件名后缀
				String fileF=fileName.substring(fileName.lastIndexOf("."),fileName.length());
				//创建新文件名
				fileName=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"_"+new Random().nextInt(1000)+fileF;
				paramMap.put("img_name",fileName);
				paramMap.put("img_id",img_id);
			}
			imgService.insertImgRecord(paramMap);
			resultMap.put("fileName",fileName);
			resultMap.put("img_id",img_id);
		}
		resultMap.put("success",true);
		resultMap.put("msg","上传成功了");
		return resultMap;
	}
	
	@RequiresPermissions(value="add")
	@RequestMapping("/LbtFileUpload")
	@ResponseBody
	public Map<String,Object> uploadLBTImg(@RequestParam(value="lbt_file",required=false)MultipartFile file,HttpServletRequest request) throws IOException{
		Map<String,Object> resultMap=new HashMap<>();
		if(file==null){
			logger.info("文件不存在");
		}else{
			Map<String,Object> paramMap=getParamMap(request.getParameterMap());
			byte[] bytes=file.getBytes();
			paramMap.put("img",bytes);
			//获取文件名
			String fileName=file.getOriginalFilename();
			paramMap.put("img_name",fileName);
			if(fileName !=null && !fileName.isEmpty()){
				//获取文件名后缀
				String fileF=fileName.substring(fileName.lastIndexOf("."),fileName.length());
				//创建新文件名
				fileName=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"_"+new Random().nextInt(1000)+fileF;
				paramMap.put("img_name",fileName);
			}
			resultMap.put("fileName",fileName);
			imgService.insertImgRecord(paramMap);
		}
		
		resultMap.put("success",true);
		resultMap.put("msg","上传成功了");
		return resultMap;
	}
	
	/*@RequestMapping("/getfile")
	public String getFile(HttpServletRequest request,HttpServletResponse response) throws IOException{
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
		return "";
	}*/
	/*@RequestMapping("/fileUpload")
	@ResponseBody
	public void uploadImg(@RequestParam(value="file",required=false)MultipartFile file,HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("fileUpload");
		System.out.println("request.getRequestUrl="+request.getRequestURI());
		ResponseResult result=new ResponseResult();
		Map<String,Object> map=new HashMap<>();
		File targetFile=null;
		String url="";//返回存储路径
		int code=1;
		System.out.println(file);
		//获取文件名加后缀
		String fileName=file.getOriginalFilename();
		System.out.println("fileName:"+fileName);
		if(fileName!=null && fileName!=""){
			String returnUrl=request.getScheme()+"://"
					+request.getServerName()+":"
					+request.getServerPort()
					+request.getContextPath()
					+GetResource.class.getClassLoader().getResource("item_Img").getPath();
					//存储路径
			System.out.println("returnUrl:"+returnUrl);
			//文件存储位置
			String path=GetResource.class.getClassLoader().getResource("item_Img").getPath();
			//String path=request.getSession().getServletContext().getRealPath("upload/imgs");
			//String path="E:\\WORKSPACE_RUYI\\demo\\src\\main\\resources\\static";
			//文件后缀
			System.out.println("path="+path);
			String fileF=fileName.substring(fileName.lastIndexOf("."),fileName.length());
			System.out.println("fileF="+fileF);
			//新文件名
			fileName=new Date().getTime()+"_"+new Random().nextInt(1000)+fileF;
			System.out.println("fileName="+fileName);
			//先判断文件是否存在
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
			String fileAdd=sdf.format(new Date());
			System.out.println("fileAdd="+fileAdd);
			//获取文件夹路径
			File file1=new File(path+"/"+fileAdd);
			System.out.println("file1="+file1);
			//如果文件夹不存在则创建文件夹
			if(!file1.exists() && !file1.isDirectory()){
				file1.mkdirs();
			}
			//将图片存入文件夹
			targetFile=new File(file1,fileName);
			try{
				System.out.println("targetFile="+targetFile);
				//将上传的文件写到服务器上指定的文件夹
				file.transferTo(targetFile);
				url=returnUrl+fileAdd+"/"+fileName;
				code=0;
				result.setCode(code);
				result.setMessage("图片上传成功");
				map.put("url",url);
				result.setResult(map);
			}catch(Exception e){
				e.printStackTrace();
				result.setMessage("系统异常，图片上传失败");
			}
		}
		writeJson(response,result);
	}
	*/

	/**
	 * 
	 * @param model
	 * @param request
	 * @param webRequest
	 * @return
	 */
	/*@SuppressWarnings("unchecked")
	@RequestMapping("/shoppingPage")
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
		
	}*/
	/**
	 * 获取字典
	 * @param request
	 * @return
	 */
	
	@RequiresPermissions(value="update")
	@RequestMapping("/updateLbtByParam")
	@ResponseBody
	public Map<String,Object> updateLbtByParam(HttpServletRequest request){
		Map<String, Object> resultMap=new HashMap<>();
		Map<String, Object> paramMap=getParamMap(request.getParameterMap());
		imgService.updateLbtByParam(paramMap);
		resultMap.put("msg","删除成功!");
		return resultMap;
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
