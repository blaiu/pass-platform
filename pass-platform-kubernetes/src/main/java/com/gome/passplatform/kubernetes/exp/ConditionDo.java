/**
 * 
 */
package com.gome.passplatform.kubernetes.exp;

import java.io.Serializable;
import java.util.Map;

/**
 * @author bailu-ds
 *
 */
@SuppressWarnings("serial")
public class ConditionDo implements Serializable {

	private String namespace;
	private String labels;
	private String rcname;
	
	private Integer maxcount;
	private Integer mincount;
	private Integer addcountpertime;
	private Integer subcountpertime;
	
	private Map<String, MemConditionDo>  conditionmaps;
	
//	private MemConditionDo memorycondition;
	
	public String getNamespace() {
		return namespace;
	}
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	public String getLabels() {
		return labels;
	}
	public void setLabels(String labels) {
		this.labels = labels;
	}
	public String getRcname() {
		return rcname;
	}
	public void setRcname(String rcname) {
		this.rcname = rcname;
	}

	public Integer getAddcountpertime() {
		return addcountpertime;
	}
	public void setAddcountpertime(Integer addcountpertime) {
		this.addcountpertime = addcountpertime;
	}
	public Integer getSubcountpertime() {
		return subcountpertime;
	}
	public void setSubcountpertime(Integer subcountpertime) {
		this.subcountpertime = subcountpertime;
	}
//	public MemConditionDo getMemorycondition() {
//		return memorycondition;
//	}
//	public void setMemorycondition(MemConditionDo memorycondition) {
//		this.memorycondition = memorycondition;
//	}
	public Integer getMaxcount() {
		return maxcount;
	}
	public void setMaxcount(Integer maxcount) {
		this.maxcount = maxcount;
	}
	public Integer getMincount() {
		return mincount;
	}
	public void setMincount(Integer mincount) {
		this.mincount = mincount;
	}
	public Map<String, MemConditionDo> getConditionmaps() {
		return conditionmaps;
	}
	public void setConditionmaps(Map<String, MemConditionDo> conditionmaps) {
		this.conditionmaps = conditionmaps;
	}

	
	
	
}
