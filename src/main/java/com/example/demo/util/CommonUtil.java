package com.example.demo.util;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.subject.Subject;

import com.example.demo.domain.User;

public class CommonUtil {
	/**
	 * 获取当前时间 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getNowDate(){
		Date date=new Date();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		String str=simpleDateFormat.format(date.getTime());
		return str;
	}
	/**
	 * 获取随机的数值。
	 */
	public static String getRandom620(Integer length){
		   String result = "";
		   Random rand = new Random();
		   int n = 20;
		   if(null != length && length > 0){
			   n = length;
		   }
	       boolean[]  bool = new boolean[n];
	       int randInt = 0;
	       for(int i = 0; i < length ; i++) {
	            do {
	                randInt  = rand.nextInt(n);

	            }while(bool[randInt]);

	           bool[randInt] = true;
	           result += randInt;
	       }
	       return result;
	}
	
	/**
	 * MD5 加密
	 * @param str
	 * @return
	 * @throws Exception
	 */
	 public static String  getMD5(String str) {  
	        MessageDigest messageDigest = null;  
	            try {
					messageDigest = MessageDigest.getInstance("MD5");
					messageDigest.reset();
					messageDigest.update(str.getBytes("UTF-8"));
				} catch (Exception e) {
					new Throwable("DM5错误");
				}  
				
	        byte[] byteArray = messageDigest.digest();  
	        StringBuffer md5StrBuff = new StringBuffer();  
	        for (int i = 0; i < byteArray.length; i++) {              
	            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)  
	                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));  
	            else  
	                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));  
	        }  
	        return md5StrBuff.toString();  
	}
	 
	 
	 public static String getSaltPassword(String password){
		 Subject subject=SecurityUtils.getSubject();
		 //获取身份信息
		 User userinfo=(User)subject.getPrincipal();
		 //User userinfo=new User();
		 //生成盐
		 SecureRandomNumberGenerator secureRandomNumberGenerator=new SecureRandomNumberGenerator();
		 String salt=secureRandomNumberGenerator.nextBytes().toHex();
		 Md5Hash md5=new Md5Hash(password,getSalt(),1024);
		 System.out.println("md5:"+md5);
		 String newPassword=md5.toHex();
		 System.out.println("newPassword:"+newPassword);
		 //设置新密码
		 userinfo.setPassword(newPassword);
		 //设置盐
		 userinfo.setSalt(salt);
		 //更新密码
		 //userInfoService.updatePassword(userinfo);
		 return newPassword;
	 }
	 
	 public static final Integer getIterations(){
		 return Integer.parseInt("1024");
	 }
	 /**
	  *  獲取密碼
	  * @param salt
	  * @param rawPassphrase
	  * @return
	  */
	 public static final String encodePassphrase(String salt,String rawPassphrase){
		 return new Sha512Hash(rawPassphrase,salt,getIterations()).toBase64();
	 }
	 
	 /**
	  *生成加密盐
	  * @return
	  */
	 public static String getSalt(){
		 SecureRandomNumberGenerator secureRandomNumberGenerator=new SecureRandomNumberGenerator();
		 String salt=secureRandomNumberGenerator.nextBytes().toBase64();
		 return salt;
	 }
	 
	 /**
	  * 加密密码=MD5(明文加密)+盐
	  * @param args
	  */
	 public static String getSaltPassword(String salt,String password){
		 Md5Hash md5=new Md5Hash(password,salt,1024);
		 String newPassword=md5.toHex();
		 return newPassword;
	 }
	 
	 /**
	  * id=从1970年开始到目前的毫秒数+随机五位数
	  * 用户ID
	  * @param args
	  */
	 public static String getId(){
		 String time=String.valueOf(new Date().getTime());
		 String a="";
		 Random random=new Random();
		 for(int i=0;i<5;i++){
			a=a+random.nextInt(9);
		 }
		 return (time+a);
	 }
	 
	 /**
	  * Id=年月日+四位随机数
	  * @return
	  */
	 public static String getID(){
		 Date date=new Date();
		 SimpleDateFormat format=new SimpleDateFormat("yyMMdd");
		 String id=format.format(date.getTime());
		 Random random=new Random();
		 for(int i=0;i<4;i++){
			 id=id+random.nextInt(9);
		 }
		 return id;
	 }
	 
	 /**
	  * 获取更新时间戳
	  * @param args
	  */
	 
	 public static String getTstamp(){
		 Date date=new Date();
		 SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
		 String tstamp=format.format(date);
		 return tstamp;
	 }
	public static void main(String[] args) {
		String salt=getSalt();
		System.out.println("salt:"+salt);
		String password=encodePassphrase(salt,"123456");
		System.out.println("passWord:"+password);
	}
}	
