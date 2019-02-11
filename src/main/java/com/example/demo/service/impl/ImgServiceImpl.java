package com.example.demo.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dao.ImgMapper;
import com.example.demo.service.ImgService;
import com.example.demo.util.CommonUtil;

@Service
public class ImgServiceImpl  implements ImgService{
	
	@Autowired
	private ImgMapper imgMapper;

	@Override
	public void insertImgRecord(Map<String, Object> paramMap) {
		paramMap.put("id",CommonUtil.getID());
		paramMap.put("gxsjc",CommonUtil.getTstamp());
		paramMap.put("created",CommonUtil.getNowDate());
		paramMap.put("yxx","1");
		imgMapper.insertImgRecord(paramMap);
	}

	@Override
	public List getImgByParamMap(Map<String, Object> paramMap) {
		paramMap.put("yxx",1);
		return imgMapper.getImgByParamMap(paramMap);
	}

	@Override
	public Object getImgByParamMap2(Map<String, Object> paramMap) {
		return imgMapper.getImgByParamMap2(paramMap);
	}

	@Override
	public List getImgLBTByParamMap(Map<String, Object> paramMap) {
		paramMap.put("yxx","1");
		paramMap.put("imglx","2");
		return imgMapper.getImgLBT(paramMap);
	}

	@Override
	public void updateLbtByParam(Map<String, Object> paramMap) {
		paramMap.put("yxx","0");
		paramMap.put("imglx","2");
		paramMap.put("gxsjc",CommonUtil.getTstamp());
		imgMapper.updateById(paramMap);
	}
	
	
}
