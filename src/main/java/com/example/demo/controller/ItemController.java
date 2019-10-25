package com.example.demo.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ItemService;
import springfox.documentation.annotations.Cacheable;

@Controller
@RequestMapping("/item")
public class ItemController {

	private static final Logger logger= LoggerFactory.getLogger(ItemController.class);

	@Autowired
	private ItemService itemService;
	
	
	/**
	 * 插入商品信息操作
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequiresPermissions(value={"add"})
	@RequestMapping("/insertItemRecord")
	@ResponseBody
	public Map<String,Object> insertItemRecord(HttpServletRequest request
	) throws IOException{
		Map<String,Object> paramMap=getParamMap(request.getParameterMap());
		Map<String,Object> resultMap=new HashMap<String,Object>();
		itemService.insertItemRecord(paramMap);
		resultMap.put("success",true);
		resultMap.put("msg","新增商品成功!");
		return resultMap;
	}
	
	/**
	 * 修改商品信息
	 * @param request
	 * @return
	 */
	@RequiresPermissions(value={"update"})
	@RequestMapping("/updateItemRecord")
	@ResponseBody
	public Map<String,Object> updateItemRecord(HttpServletRequest request){
		Map<String,Object> resultMap=new HashMap<String,Object>();
		Map<String,Object> paramMap=getParamMap(request.getParameterMap());
		itemService.updateByParamsSelective(paramMap);
		resultMap.put("success",true);
		resultMap.put("msg","修改成功!");
		return resultMap;
	}
	
	/**
	 * 删除商品信息
	 * @param request
	 * @return
	 */
	@RequiresPermissions(value="delete")
	@RequestMapping("/deleteById")
	@ResponseBody
	public Map<String,Object> deleteById(HttpServletRequest request){
		Map<String,Object> paramMap=getParamMap(request.getParameterMap());
		Map<String,Object> resultMap=new HashMap<String,Object>();
		itemService.updateById(paramMap);
		resultMap.put("msg","删除成功！");
		return resultMap;
	}
	
	
	/**
	 * 获取所以商品信息
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")

	@RequestMapping(value="/getAllItemRecord",method=RequestMethod.GET)
	@ResponseBody
	@Cacheable(value="getAllItem")
	public List getAllItemRecord(HttpServletRequest request){
		logger.info("没有走缓存");
		Map<String,Object> resultMap=new HashMap<String,Object>();
    	Map<String, Object> paramMap=getParamMap(request.getParameterMap());
    	List result=itemService.getAllItemRecord(paramMap);
    	resultMap.put("success",true);
    	resultMap.put("data",result);
    	return result;
	}
	/**
	 * 根据参数获取商品信息
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	@RequestMapping("/getItemRecordByParam")
	@ResponseBody
	public List getItemRecordByParam(HttpServletRequest request){
		Map<String,Object> resultMap=new HashMap<String,Object>();
		Map<String,Object> paramMap=getParamMap(request.getParameterMap());
		List result=itemService.getItemRecordByParam(paramMap);
		return result;
	}
	
	/**
	 * 商品上架下架操作
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateByParamsSelective")
	@ResponseBody
	public Map<String,Object> updateByParams(HttpServletRequest request){
		Map<String,Object> paramMap=getParamMap(request.getParameterMap());
		Map<String,Object> resultMap=new HashMap<>();
		itemService.updateByParamsSelective(paramMap);
		if(paramMap.get("status")=="0"){
			resultMap.put("msg","下架成功");
		}else{
			resultMap.put("msg","上架成功");
		}
		return resultMap;
	}
	
	/**
	 * 获取商品详细信息
	 * @param request
	 * @param model
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/getRecordDetailOrEdit")
	public String getRecordDetail(HttpServletRequest request,Model model){
		Map<String,Object> paramMap=getParamMap(request.getParameterMap());
		List list=null;
		if (paramMap.get("id")!=null && !"".equals(paramMap.get("id"))) {
			 list=itemService.getAllItemRecord(paramMap);
			if(list!=null && !list.isEmpty()){
				model.addAttribute("list",list);
			}			
		}
		if("detail".equals(paramMap.get("action"))){
			return "item/item_detail";
		}else{
			return "item/item_edit";
		}	
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
