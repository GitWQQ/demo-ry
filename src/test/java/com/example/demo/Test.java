package com.example.demo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.validator.internal.util.privilegedactions.GetResource;

public class Test {

	//获取参数
		private static Map<String,Object> getParamMap(Map<String, String[]> map){
			Map<String,Object> paramMap=new HashMap<String,Object>();
			for(String key:paramMap.keySet()){
				if(map.get(key) instanceof String[]){
					if(((String[])map.get(key)).length>0){
						paramMap.put(key,((String[])map.get(key))[0]);
					}
				}
			}
			return paramMap;
		}
		
		public static void main(String[] args){
			//fileTest();
			try {
				IOTest();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public static void fileTest(){
			String path=GetResource.class.getClassLoader().getResource("item_Img").getPath();
			System.out.println("path:"+path);
			File file=new File("F://Img");
			 if(!file.exists() || !file.isDirectory()){
				 file.mkdirs();
				 System.out.println("创建文件夹");
			 }
			 File file2=new File(file,"1234.jpg");
			 System.out.println("file2="+file2);
		}
		
		public void inOutTest() throws IOException{
			BufferedInputStream in=new BufferedInputStream(new FileInputStream("F:\\照片\\5.jpg"));
			BufferedOutputStream out=new BufferedOutputStream(new FileOutputStream("F:/照片2/6.jpg"));
			String file1="classpath:\\item_Img\\20181224";
			int i=0;
			while((i=in.read())!=-1){
				out.write(i);
			}
			out.flush();
			out.close();
			in.close();
		}
		
		public static void IOTest() throws IOException{
			FileInputStream fis=new FileInputStream("F:\\照片2\\1.jpg");
			FileOutputStream fos=new FileOutputStream("2.jpg");
			byte[] data=new byte[1024];
			int len=-1;
			long start=System.currentTimeMillis();
			System.out.println("start:"+start);
			while((len=fis.read(data))!=-1){
					//System.out.println(data);
					//System.out.println(Arrays.toString(data));
					fos.write(data,0,len);
			}
			long end=System.currentTimeMillis();
			System.out.println("end:"+end);
			System.out.println("耗时："+(end-start));
			fis.close();
			fos.close();
		}
		
}
