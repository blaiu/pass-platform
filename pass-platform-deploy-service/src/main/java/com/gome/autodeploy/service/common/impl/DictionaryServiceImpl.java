/**
 * 
 */
package com.gome.autodeploy.service.common.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.gome.autodeploy.dao.common.DictionaryDao;
import com.gome.autodeploy.domain.common.Dictionary;
import com.gome.autodeploy.service.common.DictionaryService;

/**
 * @author bailu-ds
 *
 */
public class DictionaryServiceImpl implements DictionaryService {

	@Resource
	private DictionaryDao dictionaryDao;
	
	@Override
	public List<Dictionary> getDictionarys(Map<String, Object> map) {
		return dictionaryDao.getDictionarys(map);
	}

}
