package com.dee.xql.proj.service;

import java.util.List;

import com.dee.xql.api.model.FrontTaskItem;
import com.dee.xql.api.model.ProjProductionPlanData;
import com.dee.xql.api.model.ProjTaskAllot;
import com.dee.xql.api.model.ProjTaskWork;
import com.dee.xql.api.model.ResourceItem;
import com.dee.xql.proj.enums.ProjectFileType;

import net.sf.mpxj.MPXJException;
import net.sf.mpxj.ProjectFile;
import net.sf.mpxj.Relation;
import net.sf.mpxj.Task;

public interface MppService {
	
	void saveData(String path,ProjectFileType type,Long svnVersion);

	ProjProductionPlanData read(String path, ProjectFileType type, Long svnVersion) throws MPXJException;

	ProjTaskWork projTaskWork(Task t, String fileName, String projectId, String strProjectId, Long summaryId,
			Long svnVersion);

	List<ProjTaskAllot> projTaskAllots(ProjectFile pFile, String fileName, String projectId, Long summaryId,
			Long svnVersion);

	FrontTaskItem frontsTaskItems(Relation r, String fileName, String projectId, Long summaryId, Long svnVersion);

	List<ResourceItem> resourceItems(ProjectFile pFile, Long summaryId, Long svnVersion);
	
	List<ProjTaskWork> findExportTasks();
	
	void exportMpp();
	
	boolean write(String path,String content);
}
