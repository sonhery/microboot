package com.dee.xql.proj.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
@ConfigurationProperties(prefix = "sms.tpl.code")
@PropertySource("classpath:smscode.properties")
public class SmsCodeConfig {

	private String add_task;
	private String upt_task;
	private String gen_proj_tpl;
}
