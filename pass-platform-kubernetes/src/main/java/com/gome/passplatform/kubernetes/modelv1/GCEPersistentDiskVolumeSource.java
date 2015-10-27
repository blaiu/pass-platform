/**
 * 
 */
package com.gome.passplatform.kubernetes.modelv1;

/**
 * @author bailu-ds
 *
 */
public class GCEPersistentDiskVolumeSource {

	private String pdName;
	
	private String fsType;
	
	private Integer partition;
	
	private Boolean readOnly;

	public String getPdName() {
		return pdName;
	}

	public void setPdName(String pdName) {
		this.pdName = pdName;
	}

	public String getFsType() {
		return fsType;
	}

	public void setFsType(String fsType) {
		this.fsType = fsType;
	}

	public Integer getPartition() {
		return partition;
	}

	public void setPartition(Integer partition) {
		this.partition = partition;
	}

	public Boolean getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
	}
	
	
	
}
