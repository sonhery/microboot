package com.dee.xql.api.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * 文件帮助类
 */
@Slf4j
public class FileHelper {

	/**
	 * 获取文件的后缀名
	 *
	 * @param fileName 文件名
	 * @return 后缀名
	 */
	public static String getFileSuffix(String fileName) {
		String suffix = "";
		suffix = fileName.substring(fileName.lastIndexOf('.'), fileName.length());
		return suffix.toLowerCase();
	}

	public static void deleteRecursive(File file) {
		if (file.exists()) {
			if (file.isDirectory()) {
				for (File f : file.listFiles()) {
					deleteRecursive(f);
				}
			}
			boolean deleted = file.delete();
			if (deleted)
				log.info("    deleted   => " + file.getAbsolutePath());
			else
				log.info("Not deleted   => " + file.getAbsolutePath());
		}
	}

	/**
	 * 读取文件为字符串
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static String readFile(String path) throws Exception {
		File file = new File(path);// 定义一个file对象，用来初始化FileReader
		FileReader reader = new FileReader(file);// 定义一个fileReader对象，用来初始化BufferedReader
		BufferedReader bufferedReader = new BufferedReader(reader);// new 一个 BufferedReader对象，将文件内容读取到缓存
		StringBuilder sb = new StringBuilder();// 定义一个字符串缓存，将字符串存放在缓存中
		String s = "";
		while ((s = bufferedReader.readLine()) != null) {
			sb.append(s + "\n");
		}
		bufferedReader.close();
		return sb.toString();
	}

	/**
	 * 获取指定目录下的所有文件列表路径
	 * 
	 * @param dir
	 * @return
	 */
	public static List<String> getFileList(String dir) {
		File file = new File(dir);
		if (!file.exists() || !file.isDirectory()) {
			return null;
		}
		File[] tmpLists = file.listFiles();
		List<String> lists = new ArrayList<String>();
		for (int x = 0; x < tmpLists.length; x++) {
			if (tmpLists[x].isFile()) {
				lists.add(tmpLists[x].getName());
			}
		}
		return lists;
	}
}
