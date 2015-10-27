/**
 * 
 */
package com.gome.autodeploy.dao.common.impl;

import java.util.List;
import java.util.Map;

import com.gome.autodeploy.dao.common.BaseDao;
import com.gome.autodeploy.dao.common.DictionaryDao;
import com.gome.autodeploy.domain.common.Dictionary;
import com.gome.autodeploy.exception.PaasDBException;

/**
 * @author bailu-ds
 *
 */
public class DictionaryDaoImpl extends BaseDao implements DictionaryDao {

	
	@Override
	public List<Dictionary> getDictionarys(Map<String, Object> map) {
		try {
			return getSqlSession().selectList(getDictionarySpaceName() + "getDictionarys", map);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
	}

}
