package com.dee.xql.proj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dee.xql.api.model.Res;
import com.dee.xql.proj.service.ProjProductionPlanService;

import lombok.extern.slf4j.Slf4j;

/**
 * 项目生产计划文件上传
 * YSH
 */
@Slf4j
@RestController
public class ProjProductionPlanController {

    @Autowired
    private ProjProductionPlanService projProductionPlanService;

    @RequestMapping("/vbaProject/saveProjProductionPlanData")
    public Res saveProjData(String data) {
        Res res = new Res();
        try {
            boolean bSuccess = projProductionPlanService.saveProjData(data);
            if (!bSuccess) {
                res.setCode(10001);
                res.setMsg("数据保存失败。");
                return res;
            }
            res.setCode(1);
            res.setMsg("数据保存成功");
            res.setValue(bSuccess);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            res.setCode(10001);
            res.setMsg("内部错误");
            log.error("saveProjData 内部错误", e);
            return res;
        }
    }

    @RequestMapping("/vbaProject/SaveSingleProjData")
    public Res SaveSingleProjData(String data) {
        Res res = new Res();
        try {
            boolean bSuccess = projProductionPlanService.saveProjData(data);
            if (!bSuccess) {
                res.setCode(10001);
                res.setMsg("数据保存失败。");
                return res;
            }
            res.setCode(1);
            res.setValue(bSuccess);
            res.setMsg("数据保存成功");
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            res.setCode(10001);
            res.setMsg("内部错误");
            log.error("saveProjData 内部错误", e);
            return res;
        }
    }
}
