/**
 * 
 */
package com.gome.passplatform.docker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



/**
 * @author bailu-ds
 *
 */
public class DockerImageApiClient {
	
	private String dockerPath;
	private String registryPath;
	private String registryDomain;
	private String webRoot;
	private String packagePath;
	
	private static final ThreadLocal<Client> tl = new ThreadLocal<Client>();
	
	public static Client getClient() {
		Client client = tl.get();
		if (null == client) {
			client = ClientBuilder.newClient();
			tl.set(client);
		}
		return client;
	}
	
	private String getDockerUrl () {
		return dockerPath + "/build?t=" + registryDomain + "/%1$s:%2$s";
	}
	
	private String getRegistryUrl () {
		return dockerPath + "/images/" + registryDomain + "/%1$s/push?tag=%2$s";
	}

	public String createImages(String imagesName, String path, String version) throws FileNotFoundException {
		File file = new File(path);
		return createImages(imagesName, file, version);
	}
	
	public String createImages(String imagesName, File file, String version) throws FileNotFoundException {
		FileInputStream fileInputStream;
		fileInputStream = new FileInputStream(file);
		return createImages(imagesName, fileInputStream, version);
	}
	
	public String createImages(String imagesName, FileInputStream fileInputStream, String version) throws FileNotFoundException {
		String url = String.format(getDockerUrl(), imagesName, version);
		Client client = getClient();
		WebTarget target = client.target(url);
		Response response = target.request().buildPost(Entity.entity(fileInputStream, MediaType.APPLICATION_OCTET_STREAM)).invoke();
		return response.readEntity(String.class);
	}
	
	public String pushImages(String imagesName,String version) throws FileNotFoundException {
		String url = String.format(getRegistryUrl(), imagesName, version);
		Client client = getClient();
		WebTarget target = client.target(url);
		target.request().header("X-Registry-Auth", "");
		Response response = target.request().post(Entity.entity(new HashMap<String, String>(), MediaType.APPLICATION_JSON));
		return response.readEntity(String.class);
	}
	
//	public static void main(String[] args) {
//		SimpleDateFormat format = new SimpleDateFormat("yyyyMMDDHHmmss");
//		String versionNo = format.format(new Date());
//
//		String returnMsg = "";
//		try {
//			System.out.println("jenkins" + versionNo);
//			returnMsg = createImages("jenkins", "D:/usr/local/tomcat/webapps/webapps.tar.gz", versionNo);
//			returnMsg += pushImages("jenkins", versionNo);
//			System.out.println(returnMsg);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
	
	public String getDockerFile() {
		StringBuilder builder = new StringBuilder(); 
		builder.append("FROM %1$s\n");
		builder.append("MAINTAINER %2$s\n");
		builder.append("ADD %3$s %4$s \n");
		return builder.toString();
	}
	
	public String getDockerPath() {
		return dockerPath;
	}

	public void setDockerPath(String dockerPath) {
		this.dockerPath = dockerPath;
	}

	public String getRegistryPath() {
		return registryPath;
	}

	public void setRegistryPath(String registryPath) {
		this.registryPath = registryPath;
	}

	public String getWebRoot() {
		return webRoot;
	}

	public void setWebRoot(String webRoot) {
		this.webRoot = webRoot;
	}

	public String getRegistryDomain() {
		return registryDomain;
	}

	public void setRegistryDomain(String registryDomain) {
		this.registryDomain = registryDomain;
	}

	public String getPackagePath() {
		return packagePath;
	}

	public void setPackagePath(String packagePath) {
		this.packagePath = packagePath;
	}
	
	
}
