/**
 * 
 */
package com.gome.autodeploy.domain.app;

import java.io.Serializable;
import java.util.Date;

/**
 * @author bailu-ds
 *
 */
public class Application implements Serializable {

	private static final long serialVersionUID = 3976548337322840886L;
	/** 应用ID */
	private Integer id;
	/** 应用名称 */
	private String name;
	/** 应用中文名称 */
	private String chineseName;
	/** 应用类型 */
	private Integer appType;
	/** 应用级别 */
	private Integer appLevel;
	/** 应用负责人 */
	private String appChief;
	/** 应用成员 */
	private String appMembers;
	
	private String projectManager;
	
	/** 域名类型 */
	private Integer domainType;
	/** 域名 */
	private String domain;
	
	/** 系统ID */
	private Integer systemId;
	/** 系统名称 */
	private String systemName;
	/** 系统负责人 */
	private String systemChief;
	/** 部门 */
	private String department;
	
	/** 部门负责人 */
	private String departmentChief;
	
	/** 部署类型 */
	private Integer deployType;
	/** 部署路径*/
	private String deployPath;
	
	/** 编译类型 */
	private Integer compileType;
	
	/** 状态 */
	private Integer status;
	
	/** 创建人 */
	private String createUser;
	/** 创建时间 */
	private Date createTime;
	/** 更新人 */
	private String updateUser;
	/** 更新时间 */
	private Date updateTime;
	
	/** 删除标识 */
	private Integer isDel;
	
	/** 审批原因 */
	private String reason;
	
	/** 备注 */
	private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	public Integer getAppType() {
		return appType;
	}

	public void setAppType(Integer appType) {
		this.appType = appType;
	}

	public Integer getAppLevel() {
		return appLevel;
	}

	public void setAppLevel(Integer appLevel) {
		this.appLevel = appLevel;
	}

	public Integer getDomainType() {
		return domainType;
	}

	public void setDomainType(Integer domainType) {
		this.domainType = domainType;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Integer getSystemId() {
		return systemId;
	}

	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getSystemChief() {
		return systemChief;
	}

	public void setSystemChief(String systemChief) {
		this.systemChief = systemChief;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Integer getDeployType() {
		return deployType;
	}

	public void setDeployType(Integer deployType) {
		this.deployType = deployType;
	}

	public String getDeployPath() {
		return deployPath;
	}

	public void setDeployPath(String deployPath) {
		this.deployPath = deployPath;
	}

	public Integer getCompileType() {
		return compileType;
	}

	public void setCompileType(Integer compileType) {
		this.compileType = compileType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public String getAppChief() {
		return appChief;
	}

	public void setAppChief(String appChief) {
		this.appChief = appChief;
	}

	public String getAppMembers() {
		return appMembers;
	}

	public void setAppMembers(String appMembers) {
		this.appMembers = appMembers;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}

	public String getDepartmentChief() {
		return departmentChief;
	}

	public void setDepartmentChief(String departmentChief) {
		this.departmentChief = departmentChief;
	}
	
	
	
	
}
