/**
 * 
 */
package com.gome.autodeploy.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author bailu-ds
 *
 */
public class RunTimeTest {
	public static boolean runtime (String key, String cmd) {
		String[] command = {"/bin/sh", "-c", cmd};
		BufferedReader br = null;
		try {
			Process p = Runtime.getRuntime().exec(command);
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
	
	public static void main(String[] args) {
		runtime("001", "ansible-playbook /etc/ansible/yml/download.yml -e 'host=192.168.137.103 user=root package=http://192.168.137.102/Upload/package/deploy.gome.com.cn/2016/bailu-2016-1101-1299-auto-deploy-web.war domain=deploy.gome.com.cn'");
	}
}
