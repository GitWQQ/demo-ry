package com.example.demo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ItemMapper;
import com.example.demo.service.ItemService;
import com.example.demo.util.CommonUtil;
@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private  ItemMapper itemMapper; 
	
	@Override
	public List getAllItemRecord(Map<String, Object> paramMap) {
		paramMap.put("yxx","1");
		if(paramMap.get("mhcx")!=null && !"".equals(paramMap.get("mhcx"))){
			if(Boolean.parseBoolean((String) paramMap.get("mhcx"))==true){
				paramMap.put("title_like",paramMap.get("title"));
				paramMap.put("title","");
			}	
		}
		List result=itemMapper.getAllItemRecord(paramMap);
		return result;
	}

	@Override
	public Object updateById(Map<String, Object> paramMap) {
		paramMap.put("yxx","0");
		return itemMapper.updateById(paramMap);
		
	}

	@Override
	public void updateByParamsSelective(Map<String, Object> paramMap) {
		
		if(paramMap !=null && !paramMap.isEmpty()){ //上/下架
			if("1".equals(paramMap.get("czbs"))){
				if("0".equals(paramMap.get("status"))){
					paramMap.put("status","1");
				}else{
					paramMap.put("status","0");
				}
				paramMap.put("yxx","1");
				paramMap.put("updated",CommonUtil.getNowDate());
				itemMapper.updateByParamsSelective(paramMap);
				
			}else if("2".equals(paramMap.get("czbs"))){//修改
				paramMap.put("price",Integer.parseInt(paramMap.get("price").toString())*100);
				itemMapper.updateByParamsSelective(paramMap);
			}
			
		}
	
	}

	@Override
	public List getItemRecordByParam(Map<String, Object> paramMap) {
		paramMap.put("yxx","1");
		return itemMapper.getItemRecordByParam(paramMap);
	}

	@Override
	public void insertItemRecord(Map<String, Object> paramMap) {
		if(paramMap.get("id")==null){
			paramMap.put("id",CommonUtil.getID());
			paramMap.put("yxx","1");
			paramMap.put("price",Integer.parseInt(paramMap.get("price").toString())*100 );
			paramMap.put("created",CommonUtil.getNowDate());
			paramMap.put("updated",CommonUtil.getNowDate());
			paramMap.put("status","1");
			itemMapper.insertItemRecord(paramMap);
		}
	}
	
}
