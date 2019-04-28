package com.example.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//---------------------------
	/*
	 * Spring Task：Spring3.0以后自带的task，可以将它看成一个轻量级的Quartz，而且使用起来比Quartz简单许多。
	 * 使用Spring Task
	 * 在主类上使用@EnableScheduling注解开启对定时任务的支持，然后启动项目
	 */
//---------------------------
@Component
public class SchedulerTask {

	private static final SimpleDateFormat dateFormat=new SimpleDateFormat("HH:mm:ss");
	@Scheduled(fixedRate=360000)
	public void reportCurrentTime(){
		//System.out.println("现在时间:"+dateFormat.format(new Date()));
	}
	
	/**
	 * 一个cron表达式至少6个（也可以有7个）有空格分隔的时间元素，按照顺序依次为：
	 * 秒(0-59),分(0-60),时(0-23),天(0-31),月(0-11),星期(),年(1970-2099)
	 */
	@Scheduled(cron="0/5 * * * * *")
	public void scheduled2(){
		//System.out.println("=====>>使用cron{}"+System.currentTimeMillis());
	}
	
	/**
	 *fixedDelay:定义一个按一定频率执行的定时任务，与fixedRate不同的是，该属性可以配合initialDelay,定义该任务的延迟执行时间 
	 */
	@Scheduled(fixedDelay=5000,initialDelay=8000)
	public void scheduled3(){
		/*System.out.println("=========》使用fixedDelay:"+System.currentTimeMillis());*/
	}
	
	/**
	 * fixedRate=5000,定义一个按照一定频率执行的定时任务
	 */
	@Scheduled(fixedRate=5000) 
	public void scheduled4(){
		//System.out.println("=========>>使用fixedRate:"+System.currentTimeMillis());
	}
}

