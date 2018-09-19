package com.dee.xql.proj.service;

import com.dee.xql.api.model.LampsData;
import com.dee.xql.api.model.SAPGoods;

public interface LampsService {

	boolean saveData(String path, Long svnLastVersion);

	boolean addLampsData(String path, Long svnLastVersion);

	LampsData readLampsMpp(String path, Long svnLastVersion);

	boolean lampsGoodsChanges(SAPGoods[] goods);

	void uptLampsResource();
}