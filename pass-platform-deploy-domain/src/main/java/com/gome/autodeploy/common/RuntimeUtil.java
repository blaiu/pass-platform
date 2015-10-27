/**
 * 
 */
package com.gome.autodeploy.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author bailu-ds
 *
 */
public class RuntimeUtil {

	public static Map<String, BufferedReader> map = new HashMap<String, BufferedReader>();
	
//	public static String runtime (String key, String cmd) {
//		String[] command = { "/bin/sh", "-c", "ansible test -m command -a 'ping www.baidu.com -c 3'" };
//		BufferedReader br = null;
//		try {
//			Process p = Runtime.getRuntime().exec(command);
//			br = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
//			String line = null;
//            while ((line = br.readLine()) != null) {
//                System.out.println(line);
//            }
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (br != null) {
//		        try {
//		            br.close();
//		        } catch (Exception e) {
//		            e.printStackTrace();
//		        }
//		    }
//		}
//		
//		return null;
//	}
	
	private static boolean runtime (String key, String[] cmd) {
//		String[] command = {"/bin/sh", "-c", cmd};
		BufferedReader br = null;
		try {
			Process p = Runtime.getRuntime().exec(cmd);
			br = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
			String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
		        try {
		            br.close();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		}
		return false;
	}
	
	public static boolean deployDownLoad (String host, String domain, String taskNo, String packageName, String url) {
		String param = " -e 'user=" + RunTimeCommand.USER + " host=" + host + " domain=" + domain + " taskNo=" + taskNo + " packageName=" + packageName + " url=" + url + "'";
		System.out.println(RunTimeCommand.DEPLOY_DOWNLOAD + param);
		String[] command = {"/bin/sh", "-c", RunTimeCommand.DEPLOY_DOWNLOAD + param };
		return runtime ("deployDownLoad-" + taskNo, command);
	}
	
	public static boolean deployBackUp (String host, String domain, String backNo, String packageName) {
		String param = " -e 'user=" + RunTimeCommand.USER + " host=" + host + " domain=" + domain + " backup=" + backNo + " package=" + packageName + "'";
		System.out.println(RunTimeCommand.DEPLOY_BACKUP + param);
		String[] command = {"/bin/sh", "-c", RunTimeCommand.DEPLOY_BACKUP + param };
		return runtime ("deployBackUp-" + backNo, command);
	}
	
	public static boolean deployDecompress (String host, String domain, String taskNo, String packageName) {
		String param = " -e 'user=" + RunTimeCommand.USER + " host=" + host + " domain=" + domain + " taskNo=" + taskNo + " file=" + packageName + "'";
		System.out.println(RunTimeCommand.DEPLOY_DECOMPRESS + param);
		String[] command = {"/bin/sh", "-c", RunTimeCommand.DEPLOY_DECOMPRESS + param };
		return runtime ("deployDecompress-" + taskNo, command);
	}
	
	public static boolean deployConfig (String host, String domain) {
		String param = " -e 'user=" + RunTimeCommand.USER + " host=" + host + " domain=" + domain + "'";
		System.out.println(RunTimeCommand.DEPLOY_CONFIG + param);
		String[] command = {"/bin/sh", "-c", RunTimeCommand.DEPLOY_CONFIG + param };
		return runtime ("deployConfig-" + domain, command);
	}

	public static boolean deployStart (String host, String domain) {
		String param = " -e 'user=" + RunTimeCommand.USER + " host=" + host + " domain=" + domain + "'";
		System.out.println(RunTimeCommand.DEPLOY_START + param);
		String[] command = {"/bin/sh", "-c", RunTimeCommand.DEPLOY_START + param };
		return runtime ("deployStart-" + domain, command);
	}
	
	public static boolean reStart (String host, String domain) {
		String param = " -e 'user=" + RunTimeCommand.USER + " host=" + host + " domain=" + domain + "'";
		System.out.println(RunTimeCommand.RESTART + param);
		String[] command = {"/bin/sh", "-c", RunTimeCommand.RESTART + param };
		return runtime ("reStart-" + domain, command);
	}
	
	public static boolean extract (String host, String domain, String packageName, String file) {
		String param = " -e 'user=" + RunTimeCommand.USER + " host=" + host + " domain=" + domain + " file=" + file + " package=" + packageName + "'";
		System.out.println(RunTimeCommand.EXTRACT + param);
		String[] command = {"/bin/sh", "-c", RunTimeCommand.EXTRACT + param };
		return runtime ("extract-" + domain, command);
	}
	
	public static boolean rollBackDownLoad (String host, String domain, String rollBackNo, String httpserver) {
		String param = " -e 'user=" + RunTimeCommand.USER + " host=" + host + " domain=" + domain + " rollBackNo=" + rollBackNo + " httpserver=" + httpserver + "'";
		System.out.println(RunTimeCommand.ROLLBACK_DOWNLOAD + param);
		String[] command = {"/bin/sh", "-c", RunTimeCommand.ROLLBACK_DOWNLOAD + param };
		return runtime ("rollBackDownLoad-" + rollBackNo, command);
	}
	
	public static boolean rollBackDecompress (String host, String domain, String rollBackNo) {
		String param = " -e 'user=" + RunTimeCommand.USER + " host=" + host + " domain=" + domain + " rollBackNo=" + rollBackNo + "'";
		System.out.println(RunTimeCommand.ROLLBACK_DECOMPRESS + param);
		String[] command = {"/bin/sh", "-c", RunTimeCommand.ROLLBACK_DECOMPRESS + param };
		return runtime ("rollBackDecompress-" + rollBackNo, command);
	}
	
	public static boolean rollBackConfig (String host, String domain, String rollBackNo) {
		String param = " -e 'user=" + RunTimeCommand.USER + " host=" + host + " domain=" + domain + " rollBackNo=" + rollBackNo + "'";
		System.out.println(RunTimeCommand.ROLLBACK_CONFIG + param);
		String[] command = {"/bin/sh", "-c", RunTimeCommand.ROLLBACK_CONFIG + param };
		return runtime ("rollBackConfig-" + rollBackNo, command);
	}

	public static boolean rollBackStart (String host, String domain) {
		String param = " -e 'user=" + RunTimeCommand.USER + " host=" + host + " domain=" + domain;
		System.out.println(RunTimeCommand.ROLLBACK_START + param);
		String[] command = {"/bin/sh", "-c", RunTimeCommand.ROLLBACK_START + param };
		return runtime ("rollBackStart-" + domain, command);
	}
	
	public static boolean upload (String host, String dir, String src, String desc) {
		String param = " -e 'user=" + RunTimeCommand.USER + " host=" + host + " src=" + src + " desc=" + desc + " dir=" + dir + "'";
		System.out.println(RunTimeCommand.ROLLBACK_START + param);
		String[] command = {"/bin/sh", "-c", RunTimeCommand.ROLLBACK_START + param };
		return runtime ("upload-" + host, command);
	}
	
	
		


	
}
