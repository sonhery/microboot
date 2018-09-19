package com.dee.xql.proj.service;

import com.dee.xql.api.model.GGData;

public interface GGService {

	boolean saveData(String path,Long svnLastVersion);
	
	boolean addGGData(String path, Long svnLastVersion);

	GGData readGGMpp(String path, Long svnLastVersion);
}
