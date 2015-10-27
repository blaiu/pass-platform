/**
 * 
 */
package com.gome.autodeploy.service.common;

import java.util.List;
import java.util.Map;

import com.gome.autodeploy.domain.common.Dictionary;

/**
 * @author bailu-ds
 *
 */
public interface DictionaryService {

	/**
	 * 查询字典
	 * @param map
	 * @return
	 */
	public List<Dictionary> getDictionarys(Map<String, Object> map);
	
}
