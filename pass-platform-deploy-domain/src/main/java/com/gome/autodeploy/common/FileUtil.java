/**
 * 
 */
package com.gome.autodeploy.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author blaiu
 *
 */
public class FileUtil {

	/**
	 * 读取文件
	 * @param filePath
	 * @return
	 */
	public static String readFile(String filePath) {
		BufferedReader reader = null;
		try {
			StringBuilder builder = new StringBuilder();
			String temp = null;
			URI uri = new URI(filePath);
			File file = new File(uri);
			reader = new BufferedReader(new FileReader(file));
			while (null != (temp = reader.readLine())) {
				builder.append(temp);
			}
			return builder.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			 if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
		}
		return null;
	} 
	
	/**
	 * 写入文件
	 * @param filePath
	 * @param content
	 * @return
	 */
	public static boolean writeFile(String filePath, String content) {
		FileOutputStream fos = null;;
		try {
			File file = new File(filePath);
			fos = new FileOutputStream(file);
			PrintWriter pw = new PrintWriter(fos);
			pw.write(content.toCharArray());
			pw.flush();
			pw.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			if (null != fos) {
				try {
					fos.close();
				} catch (IOException e) {
				}
			}
		}
		return false;
	}
	
}
