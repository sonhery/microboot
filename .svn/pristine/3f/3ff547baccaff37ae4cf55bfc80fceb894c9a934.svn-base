package com.dee.xql.proj.config;

import java.util.concurrent.Executors;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
public class SchedulerConfig implements SchedulingConfigurer {

//	// 多个线程同时执行多个定时任务
//	@Bean
//	public TaskScheduler taskScheduler() {
//		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
//		// 线程池大小
//		taskScheduler.setPoolSize(10);
//		taskScheduler.setThreadNamePrefix("dee-task");
//		return taskScheduler;
//	}

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setScheduler(Executors.newScheduledThreadPool(100));
	}
}
