/**
 * 
 */
package com.gome.autodeploy.common;

/**
 * @author bailu-ds
 *
 */
public class DockerFile {

	public static String getDockerFile() {
		StringBuilder builder = new StringBuilder();
		builder.append("FROM %1$s\n");
		builder.append("MAINTAINER %2$s\n");
		builder.append("COPY %3$s  %4$s  \n");
		return builder.toString();
	}
	

	
}
