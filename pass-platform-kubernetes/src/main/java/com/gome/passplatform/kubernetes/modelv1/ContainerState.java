/**
 * 
 */
package com.gome.passplatform.kubernetes.modelv1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author blaiu
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContainerState {

 private String volumeID;
 
 private String fsType;
 
 private Integer partition;
 
 private boolean readOnly;

public String getVolumeID() {
	return volumeID;
}

public void setVolumeID(String volumeID) {
	this.volumeID = volumeID;
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

public boolean isReadOnly() {
	return readOnly;
}

public void setReadOnly(boolean readOnly) {
	this.readOnly = readOnly;
}
 
 
 
	
}
