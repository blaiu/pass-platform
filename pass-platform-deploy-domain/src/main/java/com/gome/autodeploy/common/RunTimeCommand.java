/**
 * 
 */
package com.gome.autodeploy.common;

/**
 * @author bailu-ds
 *
 */
public class RunTimeCommand {
	
	public final static String USER = "root";

	public final static String DEPLOY_DOWNLOAD = "ansible-playbook /etc/ansible/yml/deploy/download.yml";
	
	public final static String DEPLOY_BACKUP = "ansible-playbook /etc/ansible/yml/deploy/backup.yml";
	
	public final static String DEPLOY_DECOMPRESS = "ansible-playbook /etc/ansible/yml/deploy/decompress.yml";
	
	public final static String DEPLOY_CONFIG = "ansible-playbook /etc/ansible/yml/deploy/config.yml";
	
	public final static String DEPLOY_START = "ansible-playbook /etc/ansible/yml/deploy/deploy.yml";
	
	
	public final static String RESTART = "ansible-playbook /etc/ansible/yml/restart/restart.yml";
	
	public final static String EXTRACT = "ansible-playbook /etc/ansible/yml/extract/extract.yml";
	
	
	public final static String ROLLBACK_DOWNLOAD = "ansible-playbook /etc/ansible/yml/rollback/download.yml";
	
	public final static String ROLLBACK_DECOMPRESS = "ansible-playbook /etc/ansible/yml/rollback/decompress.yml";
	
	public final static String ROLLBACK_CONFIG = "ansible-playbook /etc/ansible/yml/rollback/config.yml";
	
	public final static String ROLLBACK_START = "ansible-playbook /etc/ansible/yml/rollback/deploy.yml";
	
	
	public final static String UPLOAD = "ansible-playbook /etc/ansible/yml/upload/upload.yml";
	
	
}
