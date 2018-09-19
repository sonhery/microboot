package com.dee.xql.proj.uitls;

import org.springframework.stereotype.Component;

import com.dee.xql.api.utils.WindowsHelper;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JacobHelper {

	private ActiveXComponent msProj;// MPP对象

	public synchronized void openMpp(String path) {
		this.initComponents();
		msProj = new ActiveXComponent("MSProject.Application");

		msProj.setProperty("Visible", new Variant(false));
		msProj.setProperty("AutomationSecurity", new Variant(1));
		msProj.setProperty("DisplayAlerts", new Variant(false));
		Dispatch.put(msProj, "Visible", new Variant(false));
		Variant obj = Dispatch.call(msProj, "FileOpen", new Object[] { path });
		log.info("Open mpp ：" + path + "   " + obj.getBoolean());
	}

	public void closeProj(boolean bQuit) {
		try {
			if (msProj != null) {
				msProj.invoke("FileCloseAllEx", new Variant[] {});
				msProj.invoke("Quit", new Variant[] {});
				log.info("mpp File closed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bQuit) {
				releaseSourceProject();
			}
		}
	}

	public void callMacro(String macroName, Object param1, Object param2) {
		Dispatch.call(msProj, "Run", new Variant(macroName), new Variant(param1), new Variant(param2));
	}

	private void callMacro(String macroName, Object param1) {
		Dispatch.call(msProj, "Run", new Variant(macroName), new Variant(param1));
	}

	/**
	 * 执行指定路径的MPP宏
	 */
	public synchronized boolean executeVBA(String filePath, String macroName, Object param1) {
		boolean bRes = false;
		try {
			// 打开
			String dir = filePath;
			log.info("Open dir: " + dir);
			WindowsHelper.killProcess("WINPROJ.EXE");
			Thread.sleep(1000);
			this.openMpp(dir);
			Thread.sleep(1000);
			// 调用Excel宏
			log.info("调用宏：" + macroName + " 开始");
			this.callMacro(macroName, param1);
			log.info("调用宏：" + macroName + " 成功！结束");
			bRes = true;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("executeVBA异常", e);
			bRes = false;
		} finally {
			// 关闭并保存，释放对象
			log.info("关闭并保存，释放对象 ");
			closeProj(true);
			WindowsHelper.killProcess("WINPROJ.EXE");
		}
		return bRes;
	}

	private void releaseSourceProject() {
		if (msProj != null) {
			msProj = null;
		}
		ComThread.Release();
		System.gc();
	}

	private void initComponents() {
		msProj = null;// 清空原始变量
		ComThread.InitSTA();// 仅允许同时运行一个线程，其他线程锁住
	}
}
