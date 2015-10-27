/**
 * 
 */
package com.gome.passplatform.kubernetes.modelv1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author bailu-ds
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Volume {

	private String name;
	
	private HostPathVolumeSource hostPath;
	
	private EmptyDirVolumeSource emptyDir;
	private GCEPersistentDiskVolumeSource gcePersistentDisk;
	private AWSElasticBlockStoreVolumeSource awsElasticBlockStore;
	private GitRepoVolumeSource gitRepo;
	private SecretVolumeSource secret;
	private NFSVolumeSource nfs;
	private ISCSIVolumeSource iscsi;
	private GlusterfsVolumeSource glusterfs;
	private PersistentVolumeClaimVolumeSource persistentVolumeClaim;
	private RBDVolumeSource rbd;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public HostPathVolumeSource getHostPath() {
		return hostPath;
	}
	public void setHostPath(HostPathVolumeSource hostPath) {
		this.hostPath = hostPath;
	}
	public EmptyDirVolumeSource getEmptyDir() {
		return emptyDir;
	}
	public void setEmptyDir(EmptyDirVolumeSource emptyDir) {
		this.emptyDir = emptyDir;
	}
	public GCEPersistentDiskVolumeSource getGcePersistentDisk() {
		return gcePersistentDisk;
	}
	public void setGcePersistentDisk(GCEPersistentDiskVolumeSource gcePersistentDisk) {
		this.gcePersistentDisk = gcePersistentDisk;
	}
	public AWSElasticBlockStoreVolumeSource getAwsElasticBlockStore() {
		return awsElasticBlockStore;
	}
	public void setAwsElasticBlockStore(
			AWSElasticBlockStoreVolumeSource awsElasticBlockStore) {
		this.awsElasticBlockStore = awsElasticBlockStore;
	}
	public GitRepoVolumeSource getGitRepo() {
		return gitRepo;
	}
	public void setGitRepo(GitRepoVolumeSource gitRepo) {
		this.gitRepo = gitRepo;
	}
	public SecretVolumeSource getSecret() {
		return secret;
	}
	public void setSecret(SecretVolumeSource secret) {
		this.secret = secret;
	}
	public NFSVolumeSource getNfs() {
		return nfs;
	}
	public void setNfs(NFSVolumeSource nfs) {
		this.nfs = nfs;
	}
	public ISCSIVolumeSource getIscsi() {
		return iscsi;
	}
	public void setIscsi(ISCSIVolumeSource iscsi) {
		this.iscsi = iscsi;
	}
	public GlusterfsVolumeSource getGlusterfs() {
		return glusterfs;
	}
	public void setGlusterfs(GlusterfsVolumeSource glusterfs) {
		this.glusterfs = glusterfs;
	}
	public PersistentVolumeClaimVolumeSource getPersistentVolumeClaim() {
		return persistentVolumeClaim;
	}
	public void setPersistentVolumeClaim(
			PersistentVolumeClaimVolumeSource persistentVolumeClaim) {
		this.persistentVolumeClaim = persistentVolumeClaim;
	}
	public RBDVolumeSource getRbd() {
		return rbd;
	}
	public void setRbd(RBDVolumeSource rbd) {
		this.rbd = rbd;
	}
	
	
	
}
