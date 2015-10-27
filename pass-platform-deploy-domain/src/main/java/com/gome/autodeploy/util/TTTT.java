/**
 * 
 */
package com.gome.autodeploy.util;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


/**
 * @author bailu-ds
 *
 */
public class TTTT {

		private static String inputStr;
		private static String name = "data.xml";

		public static  void before() {
			StringBuilder sb = new StringBuilder();
			sb.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
			sb.append("\r\n");
			sb.append("<dataGroup>");
			sb.append("\r\n\t");
			sb.append("<dataItem>");
			sb.append("\r\n\t\t");
			sb.append("<data>");
			sb.append("Test");
			sb.append("</data>");
			sb.append("\r\n\t");
			sb.append("<dataItem>");
			sb.append("\r\n");
			sb.append("</dataGroup>");

			inputStr = sb.toString();
		}

		public static void testArchiveFile() throws Exception {

			byte[] contentOfEntry = inputStr.getBytes();

			String path = "d:/" + name;

			FileOutputStream fos = new FileOutputStream(path);

			fos.write(contentOfEntry);
			fos.flush();
			fos.close();

			CompressUtil.archive(path);

			CompressUtil.dearchive(path + ".tar");

			File file = new File(path);

			FileInputStream fis = new FileInputStream(file);

			DataInputStream dis = new DataInputStream(fis);

			byte[] data = new byte[(int) file.length()];

			dis.readFully(data);

			fis.close();

//			String outputStr = new String(data);

		}

		public static void testArchiveDir() throws Exception {
			String path = "d:/fd";
			CompressUtil.archive(path);

			CompressUtil.dearchive(path + ".tar", "d:/fds");
		}
		
		public static void main(String[] args) {
			TTTT.before();
			try {
				TTTT.testArchiveDir();
				TTTT.testArchiveFile();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


}
