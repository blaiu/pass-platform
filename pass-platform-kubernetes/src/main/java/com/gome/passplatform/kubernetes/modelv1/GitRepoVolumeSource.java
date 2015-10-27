/**
 * 
 */
package com.gome.passplatform.kubernetes.modelv1;

/**
 * @author bailu-ds
 *
 */
public class GitRepoVolumeSource {

	private String repository;
	private String revision;
	public String getRepository() {
		return repository;
	}
	public void setRepository(String repository) {
		this.repository = repository;
	}
	public String getRevision() {
		return revision;
	}
	public void setRevision(String revision) {
		this.revision = revision;
	}
	
	
	
}
