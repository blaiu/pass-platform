/**
 * 
 */
package org.AutoDeploy.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author bailu-ds
 *
 */
public class SK implements Runnable {

	static Process p;
	public static void main(String[] args) {
		try {
			String[] command = { "/bin/sh", "-c", "ansible test -m command -a 'ping www.baidu.com -c 3'" };
			p = Runtime.getRuntime().exec(command);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	@Override
	public void run() {
		while (true) {
			if (p != null) {
				BufferedReader br = null;
				try {
					br = new BufferedReader(new InputStreamReader(p.getInputStream(), "GBK"));
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
			}
		}
		
	}
	
}
