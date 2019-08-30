package com.example.demo.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import com.example.demo.domain.User;

public class ExcelUtil2017 {

	public static void exportExcel(String []titles,String sheetname,String filename,Object[][] exportData,
			HttpServletResponse response) throws IOException{
		
		
		//创建工作簿
		HSSFWorkbook workbook=new HSSFWorkbook();
		//创建Sheet
		HSSFSheet sheet=workbook.createSheet(sheetname);
		
		
		
		
	}
	
	public static void main(String[] args){
		
	
	}
}
