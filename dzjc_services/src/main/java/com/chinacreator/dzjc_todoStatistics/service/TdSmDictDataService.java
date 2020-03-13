package com.chinacreator.dzjc_todoStatistics.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_todoStatistics.TdSmDictData;
import com.chinacreator.dzjc_todoStatistics.TdSmDictVo;

@Service("tdSmDictDataService")
public class TdSmDictDataService {
	
	public List<TdSmDictVo> getTdSmDictDataById(String dicttypeName){
		List<TdSmDictVo> tdSmDictVoList=new ArrayList<>();
		List<TdSmDictData> tdSmDictDataList=DaoFactory.create(TdSmDictData.class).getSession().selectList("com.chinacreator.dzjc_todoStatistics.TdSmDictDataMapper.getTdSmDictDataById", dicttypeName);
		for(TdSmDictData tdSmDictData:tdSmDictDataList){
			TdSmDictVo tdSmDictVo=new TdSmDictVo();
			tdSmDictVo.setDictdataName(tdSmDictData.getDictdataName());
			tdSmDictVo.setDictdataValue(tdSmDictData.getDictdataValue());
			tdSmDictVoList.add(tdSmDictVo);
		}
		return tdSmDictVoList;
	}
}
