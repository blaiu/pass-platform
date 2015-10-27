package com.gome.passplatform.jenkins;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.glassfish.jersey.jackson.JacksonFeature;


public class JenkinsClient {
	
	private String endpointUrl;

	private String jenkinsSecretsPath;

	private String jenkinsJobRoot;
	
	private String mavenSettings;
	
	private static final ThreadLocal<Client> tl = new ThreadLocal<Client>();
	
	public JenkinsClient() {
	}
	
	public JenkinsClient(String endpointUrl) {
		this.endpointUrl = endpointUrl;
	}
	
	public static Client getClient() {
		Client client = tl.get();
		if (null == client) {
			client = ClientBuilder.newClient();
			tl.set(client);
		}
		return client;
	}
	
	public void createJob(String jobName, String svnurl, String svnIp, String svnPort, String svnuser, String svnpass) throws InvalidKeyException, Exception {
		SvnUtil.authSvn(svnurl, svnuser, svnpass);
		Client client = getClient();
		client.register(JacksonFeature.class);
		String uri = String.format(endpointUrl + "%1$s%2$s", "createItem?name=" + jobName, "");
		WebTarget target = client.target(uri);
		Response response = target.request().post(Entity.entity(replaceSvnAndMaven(svnurl), MediaType.TEXT_XML));
		System.out.println(response.readEntity(String.class));
		postOK("JenkinsClient : createJob", response);
		writeSvnCredential(jobName, svnIp, svnPort, svnuser, svnpass);
	}
	
	public void build(String jobName) throws Exception {
		Client client = getClient();
		client.register(JacksonFeature.class);
		String uri = String.format(endpointUrl + "job/" + "%1$s" + "/build", jobName);
		WebTarget target = client.target(uri);
		Response response = target.request().get();
		System.out.println(response.readEntity(String.class));
		postOK("JenkinsClient : build(String jobName)", response);
		boolean flag = false;
		while (!flag) {
			Thread.sleep(2);
			flag = isFinishedBuild(jobName);
		}
	}
	
	public boolean delete(String jobName) throws Exception {
		Client client = getClient();
		client.register(JacksonFeature.class);
		String uri = String.format(endpointUrl + "job/" + "%1$s" + "/doDelete", jobName);
		WebTarget target = client.target(uri);
		Response response = target.request().get();
		System.out.println(response.readEntity(String.class));
		return postOK("JenkinsClient : delete(String jobName)", response);
	}
	
	public String buildReturnLog(String jobName) throws Exception {
		Client client = getClient();
		client.register(JacksonFeature.class);
		String uri = String.format(endpointUrl + "job/" + "%1$s" + "/build", jobName);
		WebTarget target = client.target(uri);
		Response response = target.request().get();
		System.out.println(response.readEntity(String.class));
		postOK("JenkinsClient : build(String jobName)", response);
		Map<String, String> map = getLogs(jobName);
		while (!map.get("flag").equals("true")) {
			Thread.sleep(2);
			map = getLogs(jobName);
		}
		return map.get("log");
	}
	
	public void build(String jobName, String param) throws Exception {
		Client client = getClient();
		client.register(JacksonFeature.class);
		String uri = String.format(endpointUrl + "job/" + "%1$s" + "/buildWithParameters?%1$s", jobName, param);
		WebTarget target = client.target(uri);
		Response response = target.request().get();
		System.out.println(response.readEntity(String.class));
		postOK("JenkinsClient : build(String jobName, String param)", response);
		boolean flag = false;
		while (!flag) {
			Thread.sleep(2);
			flag = isFinishedBuild(jobName);
		}
	}
	
	private String replaceSvnAndMaven(String svnurl) throws IOException {
		String content = readFile("config.xml");
		content = content.replaceFirst("#svn#", svnurl);
		return content.replaceAll("#maven#", mavenSettings);
	}
	
	private void writeSvnCredential(String jobName, String svnIp, String svnPort, String user, String password) throws InvalidKeyException, Exception {
		String credential = JenkinsUtil.getSecret(password, getJenkinsSecretsPath());
		String content = readFile("subversion.credentials");
		content = content.replaceFirst("#url#", svnIp + ":" + svnPort);
		content = content.replaceFirst("#user#", user);
		content = content.replaceFirst("#password#", credential);
		writeFile(getJenkinsJobRoot() + JenkinsUtil.getFileseparator() + jobName + JenkinsUtil.getFileseparator() + "subversion.credentials", content);
	}
	
	private String readFile(String fileName) throws IOException {
		FileReader reader = null;
		BufferedReader bufferedReader = null;
		StringBuilder builder = new StringBuilder("");
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(JenkinsUtil.class.getResource(fileName).openStream()));
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				builder.append(str);
			}
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (null != bufferedReader) {
					bufferedReader.close();
				}
				if (null != reader) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return builder.toString();
	}
	
	private void writeFile(String filePath, String content) throws FileNotFoundException {
		FileOutputStream fos = null;;
		try {
			File file = new File(filePath);
			fos = new FileOutputStream(file);
			PrintWriter pw = new PrintWriter(fos);
			pw.write(content.toCharArray());
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			throw e;
		}
		finally {
			if (null != fos) {
				try {
					fos.close();
				} catch (IOException e) {
				}
			}
		}
	}
	
	public boolean postOK(String message, Response response) throws Exception {
    	if (response == null) {
            throw new Exception(message);
        }
    	switch (response.getStatus()) {
		case 200:
			return true;
		case 400:
			return true;
		case 201:
			return true;
		case 409:
			return true;
		default:
			throw new Exception(response.getStatus() + "");
		}
    }
	
	public boolean isFinishedBuild(String jobName) {
		boolean flag = false;
		String uri = String.format(endpointUrl + "job/" + "%1$s" + "/lastBuild/consoleText?", jobName);
		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(uri);  
			HttpResponse response;
			response = client.execute(httpGet);
			InputStream is = response.getEntity().getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "GBK"));
			String line = "";
			while (null != (line = br.readLine() )) {
				if (line.matches("Finished: SUCCESS") || line.matches("Finished: Failure")) {
					flag = true;
					break;
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;
	}
	public Map<String, String> getLogs(String jobName) throws ClientProtocolException, IOException {
		String uri = String.format(endpointUrl + "job/" + "%1$s" + "/lastBuild/consoleText?", jobName);
//		String url = "http://127.0.0.1:8080/jenkins/job/ccapss/lastBuild/consoleText";
		HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(uri);  
        HttpResponse response = client.execute(httpGet);
        InputStream is = response.getEntity().getContent();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "GBK"));
        Map<String, String> map = new HashMap<String, String>();
        StringBuilder sb = new StringBuilder("");
        String line = "";
        map.put("flag", "false");
        while (null != (line = br.readLine() )) {
        	if (line.matches("Finished: SUCCESS") || line.matches("Finished: Failure")) {
        		map.put("flag", "true");
        	}
        	sb.append(line +"\n");
        }
        map.put("log", sb.toString());
        return map;
	}
	
	public String copyWarTotarget(String jobName, String targetPath) throws IOException {
		File file = new File(jenkinsJobRoot + JenkinsUtil.getFileseparator() + jobName + JenkinsUtil.getFileseparator() + "workspace");
		if (!file.exists()) {
			 return null;
		}
		
		File targetFile = new File(targetPath);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		
		File srcFile = getDirectory(file);
		FileUtils.copyFile(srcFile, new File(targetPath + File.separator + srcFile.getName()));
		return srcFile.getName();
	}
	
	public File getDirectory(File file) {
		File[] fileArray = file.listFiles();
		File fl = null;
		for (File f : fileArray) {
			if (f.isDirectory()) {
				fl = getDirectory(f);
				if (null != fl) {
					break;
				}
			} else {
				if (f.getAbsoluteFile().getName().endsWith(".war")) {
					return f.getAbsoluteFile();
				}
			}
		}
		return fl;
	}
	
	public static void main(String[] args) {
//		System.out.println(JenkinsClient.class.getResource("") + "config.xml");
		JenkinsClient client = new JenkinsClient();
		client.setEndpointUrl("http://127.0.0.1:8080/jenkins/");
		client.setJenkinsJobRoot("C:\\Users\\bailu-ds\\.jenkins\\jobs");
		client.setJenkinsSecretsPath("C:\\Users\\bailu-ds\\.jenkins\\secrets");
		client.setMavenSettings("D:\\dev\\apache-maven-3.3.1\\conf\\settings.xml");
//		client.createSvn("SYSTEM", "", "");
//		String action  = "postCredential";
//		String url = "http://10.58.44.86/repos/gmfs/branches/passplatform";
//		String kind = "password";
//		String name = "fangbin";
//		String pass = "123456";
//		client.createSvn2(url, kind, name, pass);
//		String name1 = "_.scope";
//		String name2 = "_.username";
//		String name3 = "_.password";
//		String name4 = "_.description";
//		String stapler-class = "com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl";
//		String $class = "com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl";
//		System.out.println(String.format("postCredential?url=%1$s&kind=%2$s&username1=%3$s&password1=%4$s", url, kind, name, pass));
//		System.out.println(String.format("http://127.0.0.1:8080/descriptor/com.cloudbees.plugins.credentials.CredentialsSelectHelper/addCredentials?_.scope=%1$s&_.username=%2$s&_.password=%3$s&_.description=%4$s", "GLOBAL", "fangbin", "123456", ""));
		
//		System.out.println();
		
		
		
//		System.out.println(new File(JenkinsClient.class.getResource("").getFile()).getAbsoluteFile());
//		System.out.println(JenkinsClient.class.getResource("").getFile());
//		try {
//			System.out.println(client.copyWarTotarget("ccapss", "D:\\test"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		try {
//			Map<String, String> map = client.getLogs("ccapss");
//			System.out.println(map.get("log"));
//			System.out.println(map.get("flag"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		try {
			try {
				client.build("ccapss", "cleanWorkspaceRequired=true");
			} catch (Exception e) {
				e.printStackTrace();
			}
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		try {
//			client.createJob("ccapss", "http://10.58.44.86/repos/gmfs/branches/passplatform/pass-platform", "http://10.58.44.86", "80", "fangbin", "123456");
//		} catch (InvalidKeyException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		String uri = String.format("/descriptor/com.cloudbees.plugins.credentials.CredentialsSelectHelper/addCredentials?_.scope=SYSTEM&_.username=%1$s&_.username=%2$s", "bailu", "123456");
//		System.out.println(uri);
	}

//	public void createSvn2(String url, String kind, String name, String pass) {
//		Client client = ClientBuilder.newClient();
//		client.register(JacksonFeature.class);
////		String url2 = "http://127.0.0.1:8080/descriptor/com.cloudbees.plugins.credentials.CredentialsSelectHelper/addCredentials";
////		String uri = String.format("http://127.0.0.1:8080/scm/SubversionSCM/postCredential");
//		String uri = String.format("http://127.0.0.1:8080/descriptor/com.cloudbees.plugins.credentials.CredentialsSelectHelper/addCredentials?_.scope=%1$s&_.username=%2$s&_.password=%3$s&_.description=%4$s", "GLOBAL", "fangbin", "123456", "");
//		System.out.println(uri);
//		WebTarget target = client.target(uri);
//		target.queryParam("name", "cctv1");
//		target.request().header(name, value);
//		target.queryParam("url", url);
//		target.queryParam("kind", kind);
//		target.queryParam("username1", name);
//		target.queryParam("password1", pass);
//		Response response = target.request().post(Entity.entity("", MediaType.APPLICATION_XML));
//		Response response = target.request().post(Entity.entity(new HashMap<String, String>(), MediaType.TEXT_PLAIN));
//		System.out.println(response.readEntity(String.class));
//	}

	public String getEndpointUrl() {
		return endpointUrl;
	}

	public void setEndpointUrl(String endpointUrl) {
		this.endpointUrl = endpointUrl;
	}

	public String getJenkinsSecretsPath() {
		return jenkinsSecretsPath;
	}

	public void setJenkinsSecretsPath(String jenkinsSecretsPath) {
		this.jenkinsSecretsPath = jenkinsSecretsPath;
	}

	public String getJenkinsJobRoot() {
		return jenkinsJobRoot;
	}

	public void setJenkinsJobRoot(String jenkinsJobRoot) {
		this.jenkinsJobRoot = jenkinsJobRoot;
	}

	public String getMavenSettings() {
		return mavenSettings;
	}

	public void setMavenSettings(String mavenSettings) {
		this.mavenSettings = mavenSettings;
	}

    

}
