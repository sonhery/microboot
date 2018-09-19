package com.dee.xql.proj.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.dee.xql.api.model.Res;
import com.dee.xql.proj.enums.ProjectFileType;
import com.dee.xql.proj.service.LampsService;
import com.dee.xql.proj.service.MppService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HelloController {

	public static long VERSION = 1000;

	@Autowired
	private LampsService lampsService;
	@Autowired
	private MppService mppService;

	@RequestMapping(value = "/index")
	public @ResponseBody Object index() {
		return "xql-project";
	}

	@RequestMapping(value = "/hello/get")
	public @ResponseBody Res get() {
		Res res = new Res();
		String fileName = "D:\\xql-work\\项目计划\\灯具\\201809-灯具项目.mpp";
		lampsService.addLampsData(fileName, 111L);
		res.setCode(1);
		return res;
	}

	@RequestMapping("/tagTest")
	public String tagTest(Model model) {
		String path = "D:" + File.separator + "tagTest" + File.separator;
		try {
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
			// 获取该文件下所有文件
			File[] listFiles = file.listFiles();
			if (listFiles != null) {
				List<String> names = new ArrayList<String>();
				for (File f : listFiles) {
					names.add(f.getName());
				}
				model.addAttribute("names", names);
				System.out.println(JSON.toJSONString(names));
			} else {
				model.addAttribute("names", new ArrayList<String>());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "tagTest";
	}

	@RequestMapping("/doTagTest")
	public @ResponseBody Res doTagTest(String fileName) {
		Res res = new Res();
		try {
			if (fileName == null || "".equals(fileName)) {
				res.setCode(10001);
				res.setMsg("请选择文件");
			} else {
				System.out.println(fileName);
				if (fileName.contains("资源")) {
					String file = "D:" + File.separator + "tagTest" + File.separator + fileName;
					mppService.saveData(file, ProjectFileType.MPP_RESOURCE, VERSION++);
					res.setMsg("【" + fileName + "】导入成功");
				} else {
					String file = "D:" + File.separator + "tagTest" + File.separator + fileName;
					mppService.saveData(file, ProjectFileType.MPP_TASK, VERSION++);
					res.setMsg("【" + fileName + "】导入成功");
				}
			}
		} catch (Exception e) {
			res.setCode(10001);
			res.setMsg("内部错误");
			log.error("内部错误，", e);
			e.printStackTrace();
		}
		return res;
	}

}
