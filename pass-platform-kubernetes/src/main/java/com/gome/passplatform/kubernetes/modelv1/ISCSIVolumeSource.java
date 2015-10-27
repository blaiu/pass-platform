/**
 * 
 */
package com.gome.passplatform.kubernetes.modelv1;

/**
 * @author bailu-ds
 *
 */
public class ISCSIVolumeSource {

	private String targetPortal;
	private String iqn;
	private Integer lun;
	private String fsType;
	private Boolean readOnly;
	public String getTargetPortal() {
		return targetPortal;
	}
	public void setTargetPortal(String targetPortal) {
		this.targetPortal = targetPortal;
	}
	public String getIqn() {
		return iqn;
	}
	public void setIqn(String iqn) {
		this.iqn = iqn;
	}
	public Integer getLun() {
		return lun;
	}
	public void setLun(Integer lun) {
		this.lun = lun;
	}
	public String getFsType() {
		return fsType;
	}
	public void setFsType(String fsType) {
		this.fsType = fsType;
	}
	public Boolean getReadOnly() {
		return readOnly;
	}
	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
	}
	
	
	
	
}
