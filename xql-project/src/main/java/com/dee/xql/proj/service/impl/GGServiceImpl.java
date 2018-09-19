package com.dee.xql.proj.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.alibaba.fastjson.JSON;
import com.dee.xql.api.model.GGData;
import com.dee.xql.api.model.GGFrontTask;
import com.dee.xql.api.model.GGProject;
import com.dee.xql.api.model.GGResource;
import com.dee.xql.api.model.GGTaskAllot;
import com.dee.xql.api.model.GGTaskWork;
import com.dee.xql.api.utils.DataHelper;
import com.dee.xql.api.utils.DateHelper;
import com.dee.xql.api.utils.UUIDLong;
import com.dee.xql.proj.dao.GGMapper;
import com.dee.xql.proj.service.GGService;

import lombok.extern.slf4j.Slf4j;
import net.sf.mpxj.ProjectFile;
import net.sf.mpxj.Relation;
import net.sf.mpxj.Resource;
import net.sf.mpxj.ResourceAssignment;
import net.sf.mpxj.ResourceContainer;
import net.sf.mpxj.Task;
import net.sf.mpxj.TaskContainer;
import net.sf.mpxj.mpp.MPPReader;

@Slf4j
@Service
public class GGServiceImpl implements GGService {

	protected String m_fileName;
	protected Long m_svnLastVersion;
	protected String m_yearMonth;
	protected GGProject m_currProj;
	protected List<GGTaskWork> m_allWorks;
	protected List<GGTaskAllot> m_allAllots;
	protected List<GGFrontTask> m_allFronts;
	protected List<GGResource> m_allResources;

	@Autowired
	private GGMapper ggMapper;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean addGGData(String path, Long svnLastVersion) {
		try {
			m_allWorks = new ArrayList<GGTaskWork>();
			m_allAllots = new ArrayList<GGTaskAllot>();
			m_allFronts = new ArrayList<GGFrontTask>();
			m_allResources = new ArrayList<GGResource>();
			GGData data = this.readGGMpp(path, svnLastVersion);
			if (data == null) {
				log.info("****** GGServiceImpl addGGData: data == null ******");
				return false;
			}
			List<GGProject> projs = data.getProjs();
			if (projs == null || projs.size() <= 0) {
				log.info("****** GGServiceImpl addGGData: projs == null || projs.size() <= 0 ******");
				return false;
			}
			System.out.println(JSON.toJSON(projs));
			// 先存项目数据
			int num = ggMapper.batchProjs(projs);
			if (num <= 0) {
				log.info("****** GGServiceImpl addGGData: num <= 0 ******");
				return false;
			}
			log.info("****** addGGData ggMapper.batchProjs Num:" + num + " ******");
			this.batchWorks();
			this.batchResources();
			this.batchAllots();
			this.batchFrontTasks();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("addGGData Error", e);
			return false;
		}
	}

	private int batchWorks() {
		// 删除文件数据库里多出的数据，当文件编码一样时
		// 1、获取所有的工作任务
		List<GGTaskWork> allWorks = m_allWorks;
		if (allWorks == null || allWorks.size() <= 0) {
			return 0;
		}
		Iterator<GGTaskWork> itor = allWorks.iterator();
		List<String> ids = new ArrayList<String>();
		while (itor.hasNext()) {
			GGTaskWork work = itor.next();
			ids.add(work.getId());
		}
		// 2、先删除表里有，但是project里没有的任务数据
		int num = ggMapper.delNotInGGTaskWork(ids);
		log.info("****** batchWorks ggMapper.delNotInGGTaskWork Num: " + num + " ******");
		// 3、然后更新或增加工作任务
		num = ggMapper.batchWorks(allWorks);
		log.info("****** batchWorks ggMapper.batchWorks Num: " + num + " ******");
		return num;
	}

	private int batchAllots() {
		List<GGTaskAllot> allots = m_allAllots;
		if (allots == null || allots.size() <= 0) {
			return 0;
		}
		Iterator<GGTaskAllot> itor = allots.iterator();
		List<String> ids = new ArrayList<String>();
		while (itor.hasNext()) {
			GGTaskAllot allot = itor.next();
			ids.add(allot.getId());
		}
		int num = ggMapper.delNotInGGTaskAllot(ids);
		log.info("****** batchWorks ggMapper.delNotInGGTaskWork Num: " + num + " ******");
		num = ggMapper.batchAllots(allots);
		log.info("****** batchWorks ggMapper.batchAllots Num: " + num + " ******");
		return num;
	}

	private int batchFrontTasks() {
		List<GGFrontTask> fronts = m_allFronts;
		if (fronts == null || fronts.size() <= 0) {
			return 0;
		}
		Iterator<GGFrontTask> itor = fronts.iterator();
		List<String> ids = new ArrayList<String>();
		while (itor.hasNext()) {
			GGFrontTask front = itor.next();
			ids.add(front.getId());
		}
		int num = ggMapper.delNotInGGFrontTask(ids);
		log.info("****** batchWorks ggMapper.delNotInGGFrontTask Num: " + num + " ******");
		num = ggMapper.batchFrontTasks(fronts);
		log.info("****** batchWorks ggMapper.batchFrontTasks Num: " + num + " ******");
		return num;
	}

	private int batchResources() {
		List<GGResource> resources = m_allResources;
		if (resources == null || resources.size() <= 0) {
			return 0;
		}
		Iterator<GGResource> itor = resources.iterator();
		List<String> ids = new ArrayList<String>();
		while (itor.hasNext()) {
			GGResource res = itor.next();
			ids.add(res.getId());
		}
		int num = ggMapper.batchResources(resources);
		log.info("****** batchWorks ggMapper.batchResources Num: " + num + " ******");
		return num;
	}

	@Override
	public GGData readGGMpp(String path, Long svnLastVersion) {
		try {
			MPPReader reader = new MPPReader();
			File file = new File(path);
			if (!this.isValidFile(file)) {
				log.info("****** 文件名不合格，无法生成项目和任务 ******");
				return null;
			}
			GGData data = new GGData();
			ProjectFile pFile = reader.read(file);
			TaskContainer tasks = pFile.getTasks();
			Iterator<Task> iter = tasks.iterator();
			List<GGProject> projs = new ArrayList<GGProject>();
			this.m_fileName = file.getName();
			this.m_svnLastVersion = svnLastVersion;
			while (iter.hasNext()) {
				Task t = iter.next();
				String wbs = t.getWBS();
				if (wbs.equals("0")) {
					continue;
				}
				String[] wbss = wbs.split("\\.");
				if (wbss.length == 1) {
					m_currProj = this.getGGProject(t);
					projs.add(m_currProj);
				}
				if (m_currProj == null) {
					continue;
				}
				m_currProj.getWorks().add(this.getGGTaskWorks(t));
				m_currProj.getAllots().addAll(this.getGGTaskAllots(t));
				m_currProj.getFrontTasks().addAll(this.getGGFrontTasks(t));
			}
			data.setResources(this.getGGResources(pFile));
			data.setProjs(projs);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("****** GGServiceImpl readGGMpp Error******", e);
			return null;
		}
	}

	private GGProject getGGProject(Task task) {
		GGProject proj = new GGProject();
		Date date = new Date();
		proj.setId(UUIDLong.longUUID());
		proj.setState(1);
		proj.setStartMemberId("-4082026761752392672");// 广告发起人
		proj.setStartDate(date);
		proj.setApproveDate(date);
		proj.setModifyMemberId("-4082026761752392672");
		proj.setModifyDate(date);

		proj.setProjectId(this.getProjectId(task));
		proj.setProjectName(task.getName());
		proj.setProjectIndustry("8165314299605700514");// 其他行业
		proj.setPlanStartDate(task.getStart());
		proj.setPlanFinishDate(task.getFinish());
		proj.setProjectStage("5727642710514615250");
		proj.setSalePrincipal(task.getContact());
		proj.setSalePrincipalCompay("4550994842860142846");// 新千里广告部
		proj.setMainDutyCompanyId("fz012");
		proj.setProjectBelongToCompanyId("fz012");
		proj.setProjectBelongToCompanyName("新千里广告部");
		proj.setAuthDeptCode("fz012");
		proj.setAuthDeptId("4550994842860142846");
		proj.setEmpCode("广告");
		proj.setCustomerId(task.getText(7));
//		proj.setMainContact(task.getText(8));
//		proj.setMainContactPhone(task.getText(9));
//		proj.setProvince(task.getText(6));
		if (m_fileName.contains("测试")) {// 测试数据
			proj.setDataNature("-2501477783932944247");
		} else {
			proj.setDataNature("6811984181973109103");
		}
		return proj;
	}

	private GGTaskWork getGGTaskWorks(Task task) {
		Task t = task;
		String projectId = m_currProj.getProjectId();
		GGTaskWork work = new GGTaskWork();
		work.setId(DigestUtils.md5DigestAsHex((m_fileName + projectId + t.getGUID()).getBytes()));
		work.setYearMonth(m_yearMonth);
		work.setFileName(m_fileName);
		work.setGuid(t.getGUID().toString());
		work.setTag(t.getID());
		work.setUniqueId(t.getUniqueID());
		work.setWbs(t.getWBS());
		work.setModel(t.getTaskMode().getValue() + "");
		work.setName(t.getName());
		work.setContact(t.getContact());
		work.setStartDate(t.getStart());
		work.setFinishDate(t.getFinish());
		work.setActualStart(t.getActualStart());
		work.setActualFinish(t.getActualFinish());
		work.setActive(t.getActive() ? 1 : 0);
		if (t.getParentTask() != null) {
			work.setSuperiorTaskId(
					DigestUtils.md5DigestAsHex((m_fileName + projectId + t.getParentTask().getGUID()).getBytes()));
		}
		if (t.getCalendar() != null) {
			work.setCalendarUniqueId(t.getCalendar().getUniqueID());
			work.setCalendarName(t.getCalendar().getName());
		}
		work.setPriority(t.getPriority().getValue());
		work.setCreateDate(t.getCreateDate());
		work.setCost(t.getCost().doubleValue());
		work.setText1(t.getText(1));
		work.setText2(t.getText(2));
		work.setText4(t.getText(4));
		work.setText5(t.getText(5));
		work.setText6(t.getText(6));
		work.setText7(t.getText(7));
		work.setText10(t.getText(10));
		work.setText11(t.getText(11));
		work.setText14(projectId);
		work.setDate1(t.getDate(1));
		work.setDate2(t.getDate(2));
		work.setCost1(t.getCost(1).doubleValue());
		work.setCost2(t.getCost(2).doubleValue());
		work.setNumber1(t.getNumber(1).doubleValue());
		work.setFinish1(t.getFinish(1));
		work.setFinish2(t.getFinish(2));
		work.setFinish3(t.getFinish(3));
		work.setFinish4(t.getFinish(4));
		work.setFinish5(t.getFinish(5));
		work.setFinish6(t.getFinish(6));
		work.setFinish7(t.getFinish(7));
		List<ResourceAssignment> ras = t.getResourceAssignments();
		String resourceNames = "";
		for (int j = 0; j < ras.size(); j++) {
			ResourceAssignment ra = ras.get(j);
			if (ra.getResource() != null && ra.getResource().getCode() != null
					&& !ra.getResource().getCode().equals("")) {
				resourceNames += ra.getResource().getName();
				resourceNames += ",";
			}
		}
		if (resourceNames.length() > 0) {
			resourceNames = resourceNames.substring(0, resourceNames.length() - 1);
		} else {
			resourceNames = null;
		}
		work.setResourceNames(resourceNames);
		work.setFileCode(DigestUtils.md5DigestAsHex(m_fileName.getBytes()));
		work.setSvnLastVersion(m_svnLastVersion);
		m_allWorks.add(work);
		return work;
	}

	private List<GGTaskAllot> getGGTaskAllots(Task task) {
		Task t = task;
		String projectId = m_currProj.getProjectId();
		List<GGTaskAllot> allots = new ArrayList<GGTaskAllot>();
		List<ResourceAssignment> resourceAssignments = t.getResourceAssignments();
		if (resourceAssignments == null || resourceAssignments.size() <= 0) {
			return allots;
		}
		List<String> reses = new ArrayList<String>();
		for (int i = 0; i < resourceAssignments.size(); i++) {
			ResourceAssignment ra = resourceAssignments.get(i);
			if (ra.getResource() == null) {
				continue;
			}
			String resId = DigestUtils.md5DigestAsHex((ra.getResource().getUniqueID().toString()).getBytes());
			String taskId = DigestUtils.md5DigestAsHex((m_fileName + projectId + ra.getTask().getGUID()).getBytes());
			String str = taskId + resId;
			if (reses.contains(str)) {
				continue;
			}
			reses.add(str);
			GGTaskAllot allot = new GGTaskAllot();
			allot.setId(DigestUtils.md5DigestAsHex(str.getBytes()));
			allot.setTaskId(taskId);
			allot.setResId(resId);
			allot.setUnits(ra.getUnits().doubleValue());
			allot.setWork(ra.getWork().getDuration());
			allot.setStartDate(ra.getStart());
			allot.setFinishDate(ra.getFinish());
			allot.setActualStart(ra.getActualStart());
			allot.setActualFinish(ra.getActualFinish());
			allot.setCostRateTable(ra.getCostRateTableIndex() + "");
			allot.setCost(ra.getCost().doubleValue());
			allot.setFileCode(DigestUtils.md5DigestAsHex(m_fileName.getBytes()));
			allot.setSvnLastVersion(m_svnLastVersion);
			allots.add(allot);
			m_allAllots.add(allot);
		}
		return allots;
	}

	private List<GGFrontTask> getGGFrontTasks(Task task) {
		Task t = task;
		String projectId = m_currProj.getProjectId();
		List<GGFrontTask> tasks = new ArrayList<GGFrontTask>();
		List<Relation> predecessors = t.getPredecessors();
		if (predecessors != null && predecessors.size() > 0) {
			Iterator<Relation> iterator = predecessors.iterator();
			while (iterator.hasNext()) {
				Relation r = (Relation) iterator.next();
				GGFrontTask ft = new GGFrontTask();
				Task st = r.getSourceTask();
				Task ct = r.getTargetTask();// 前置任务
				String taskId = DigestUtils.md5DigestAsHex((m_fileName + projectId + st.getGUID()).getBytes());
				String frontTaskId = DigestUtils.md5DigestAsHex((m_fileName + projectId + ct.getGUID()).getBytes());
				ft.setId(DigestUtils.md5DigestAsHex((taskId + frontTaskId).getBytes()));
				ft.setType(r.getType().name());
				ft.setTaskId(taskId);
				ft.setFrontTaskId(frontTaskId);
				ft.setDelayTime(r.getLag().getDuration());
				ft.setFileCode(DigestUtils.md5DigestAsHex(m_fileName.getBytes()));
				ft.setSvnLastVersion(m_svnLastVersion);
				tasks.add(ft);
				m_allFronts.add(ft);
			}
		}
		return tasks;
	}

	private List<GGResource> getGGResources(ProjectFile pFile) {
		if (pFile == null) {
			return new ArrayList<GGResource>();
		}
		List<GGResource> ress = new ArrayList<GGResource>();
		ResourceContainer resources = pFile.getResources();
		Iterator<Resource> iterator = resources.iterator();
		while (iterator.hasNext()) {
			Resource r = iterator.next();
//			if (r.getCode() == null || r.getCode().equals("")) {
//				continue;
//			}
			GGResource res = new GGResource();
			res.setId(DigestUtils.md5DigestAsHex((r.getUniqueID().toString()).getBytes()));
			res.setGuid(r.getGUID().toString());
			res.setResId(r.getID());
			res.setUniqueId(r.getUniqueID());
			res.setType(r.getType().getValue() + "");
			res.setName(r.getName());
			res.setResGroup(r.getGroup());
			res.setMaterialLabel(r.getMaterialLabel());
			res.setMaxUnits(r.getMaxUnits().doubleValue());
			res.setCode(r.getCode());
			res.setNotes(r.getNotes());
			res.setOvertimeRate(r.getOvertimeRate().getAmount());
			res.setStandardRate(r.getStandardRate().getAmount());
			res.setCostPerUse(r.getCostPerUse().doubleValue());
			res.setCreateDate(r.getCreationDate());
			res.setActive(r.getActive() ? 1 : 0);
			if (r.getResourceCalendar() != null) {
				res.setCalendarUniqueId(r.getResourceCalendar().getUniqueID());
			}
			res.setBookingType(r.getBookingType().getValue() + "");
			res.setBudget(r.getBudget() ? 1 : 0);
			res.setFileCode(DigestUtils.md5DigestAsHex(m_fileName.getBytes()));
			res.setSvnLastVersion(m_svnLastVersion);
			ress.add(res);
			m_allResources.add(res);
		}
		return ress;
	}

	private String getProjectId(Task task) {
		Task t = task;
		int uniqueId = t.getUniqueID();
		String strUniqueId = "";
		if (uniqueId < 10) {
			strUniqueId = "000" + uniqueId;
		} else if (uniqueId < 100) {
			strUniqueId = "00" + uniqueId;
		} else if (uniqueId < 1000) {
			strUniqueId = "0" + uniqueId;
		} else if (uniqueId < 10000) {
			strUniqueId = "" + uniqueId;
		} else {
			strUniqueId = "0000";
		}
		StringBuilder sb = new StringBuilder("GG");
		sb.append(DateHelper.getStrDateFormat(t.getCreateDate(), "yyMM"));
		sb.append(strUniqueId);
		return sb.toString();
	}

	private boolean isValidFile(File file) {
		if (file == null) {
			return false;
		}
		String fileName = file.getName();
		String[] names = fileName.split("-");
		if (names == null || names.length <= 0) {
			return false;
		}
		String yearMonth = names[0];
		if (!DataHelper.isInteger(yearMonth)) {
			return false;
		}
		m_yearMonth = yearMonth;
		return yearMonth.length() == 6;
	}

	@Override
	public boolean saveData(String path, Long svnLastVersion) {
		if (this.addGGData(path, svnLastVersion)) {
//			String res = ggMapper.generatorTask(DigestUtils.md5DigestAsHex(m_fileName.getBytes()));
//			log.info("****** generatorTask res: " + res + " ******");
			return true;
		}
		return false;
	}

}
