package com.dee.xql.a8.config;

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
@ConfigurationProperties(prefix = "dee.xql")
@PropertySource("classpath:commons.properties")
public class CommonsConfig {

	private String svn_url;
	private String svn_username;
	private String svn_password;
	private String svn_export_path;
	private String svn_version_file;
	private String svn_resource_dir;
	private String dydsms_accessKeyId;
	private String dydsms_accessKeySecret;
	private String a8_upload_dir;
	private String a8_temporary_dir;
	private String project_server;

}
