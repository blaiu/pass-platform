/**
 * 
 */
package com.gome.autodeploy.common;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;

/**
 * @author bailu-ds
 *
 */
public class CosUploadUtil {

	public static int fileMaxSize = 100 * 1024 * 1024;			// 文件最大容量 100M
	
	@SuppressWarnings("unused")
	public static boolean upload(String fileName, String domain, String user, String srcPath, String descPath, HttpServletRequest request) throws IOException {
	    File uploadPath = new File(srcPath);
	    if (!uploadPath.exists()) {
	        uploadPath.mkdir();
	    }
	    
	    CosFileRename rfrp = new CosFileRename();		// 重命名策略
	    rfrp.setFileName(fileName);
	    MultipartRequest mulit = new MultipartRequest(request, srcPath, fileMaxSize, "UTF-8", rfrp);// 上传文件 
//	    RuntimeUtil.upload("localhost", descPath, srcPath + rfrp.getFileName(), descPath + rfrp.getFileName());
	    return false;
	}
	
	@SuppressWarnings("unused")
	public static boolean uploadPackage(String fileName, String savePath, HttpServletRequest request) throws IOException {
		File uploadPath = new File(savePath);
		if (!uploadPath.exists()) {
			uploadPath.mkdir();
		}
		CosFileRename rfrp = new CosFileRename();		// 重命名策略
		rfrp.setFileName(fileName);
		MultipartRequest mulit = new MultipartRequest(request, savePath, fileMaxSize, "UTF-8", rfrp);// 上传文件 
		return false;
	}
	
	
}
