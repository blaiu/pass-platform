package com.gome.autodeploy.domain.auth;

public class SvnAuth {
	private String svnPath;
	private String svnUserName;
	private String svnPassword;
	public String getSvnPath() {
		return svnPath;
	}
	public void setSvnPath(String svnPath) {
		this.svnPath = svnPath;
	}
	public String getSvnUserName() {
		return svnUserName;
	}
	public void setSvnUserName(String svnUserName) {
		this.svnUserName = svnUserName;
	}
	public String getSvnPassword() {
		return svnPassword;
	}
	public void setSvnPassword(String svnPassword) {
		this.svnPassword = svnPassword;
	}
	@Override
	public String toString() {
		return "SvnAuth [svnPath=" + svnPath + ", svnUserName=" + svnUserName
				+ ", svnPassword=" + svnPassword + "]";
	}
	
}
