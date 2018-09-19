package com.dee.xql.api.utils;

public class ReplaceProjectName {
	
	public static String sub(String projectName){
		if(projectName.length()>20){//判断长度是否大于20
			if(projectName.contains("-")){//判断是否包含“-”
				String str1 = projectName.substring(projectName.indexOf("-")+1);
				if(str1.length()<=20){
					return str1;
				}else{
					return str1.substring(0,20);
				}
			}else{
				return projectName.substring(projectName.length()-20, projectName.length());
			}
		}else{
			return projectName;
		}
	}
}
