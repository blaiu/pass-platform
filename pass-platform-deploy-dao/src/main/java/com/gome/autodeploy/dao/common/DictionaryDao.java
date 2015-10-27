/**
 * 
 */
package com.gome.autodeploy.dao.common;

import java.util.List;
import java.util.Map;

import com.gome.autodeploy.domain.common.Dictionary;

/**
 * @author bailu-ds
 *
 */
public interface DictionaryDao {

	/**
	 * 字典查询
	 * @param map
	 * @return
	 */
	public List<Dictionary> getDictionarys(Map<String, Object> map);
}
