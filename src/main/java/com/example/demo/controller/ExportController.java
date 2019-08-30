package com.example.demo.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Order;
import com.example.demo.domain.OrderItem;
import com.example.demo.domain.User;
import com.example.demo.service.OrderItemService;
import com.example.demo.util.ExcelException;
import com.example.demo.util.ExcelUtil2013;

@Controller
@RequestMapping("/export")
public class ExportController {

	@Autowired
	private OrderItemService  orderItemService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/saleRanking")
	public void exportSaleRanking(HttpServletRequest request,
			HttpServletResponse response) throws FileNotFoundException, ExcelException{
		List<Map<String,Object>> list=orderItemService.exportSaleRanking();
		System.out.println(list);
		if(list!=null){
			LinkedHashMap<String, String> fieldMap=new LinkedHashMap();
			fieldMap.put("title","商品名称");
			fieldMap.put("num", "销售总量(件)");
			fieldMap.put("total_fee","销售总额(元)");
			String sheetName="商品销统计表";
			ExcelUtil2013.listToExcel(list, fieldMap, sheetName, response);
		}
	}
	
	public static void main(String[] args) {
		int a=100;
		int b=100;
		System.out.println(a==b); //true
		
		String s1 = "Monday";
		String s2 = "Monday";
		if (s1 == s2)
		{
			 System.out.println("s1 == s2");}
			else{
			System.out.println("s1 != s2");
		}
	}	
}
