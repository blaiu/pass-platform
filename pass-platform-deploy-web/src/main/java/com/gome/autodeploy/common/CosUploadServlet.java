/**
 * 
 */
package com.gome.autodeploy.common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

/**
 * @author bailu-ds
 *
 */
public class CosUploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;  
	  
    public CosUploadServlet() {  
        super();  
    }  
   
    public void destroy() {  
        super.destroy();    
    }  
   
    @SuppressWarnings("unused")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String file = request.getParameter("filePath");
    	SimpleDateFormat format = new SimpleDateFormat("yyyyMMDDHHmmss");
    	String date = format.format(new Date());
    	if(!file.endsWith(".tar.gz")) {
    		response.getWriter().print("只支持 .tar.gz后缀的文件");
    		response.sendRedirect(getServletContext().getContextPath()+"/views/jsp/upload.jsp");
    		return;
    	}
    	
    	String domain = request.getParameter("domain");
    	String user = request.getParameter("user");
    	  
        String filePath = "/export/Upload/" + domain + "/" + user +"/";	// 存相对路径
        String descPath = "/export/Upload/package/" + domain + "/" + date + "/";
        File uploadPath = new File(filePath);
        if (!uploadPath.exists()) {
            uploadPath.mkdir();
        }
        
        int fileMaxSize = 100 * 1024 * 1024;			// 文件最大容量 100M
//        String[] fileDiscription = { null, null };	// 存放文件描述
//        int fileCount = 0;							// 上传文件数
        CosFileRename rfrp = new CosFileRename();		// 重命名策略
        rfrp.setFileName(user + "_" + format.format(new Date()));
        MultipartRequest mulit = new MultipartRequest(request, filePath, fileMaxSize, "UTF-8", rfrp);// 上传文件  
  
        RuntimeUtil.upload("localhost", descPath, filePath + rfrp.getFileName(), descPath + rfrp.getFileName());
        response.sendRedirect(getServletContext()+"/views/jsp/upload.jsp");
//        String userName = mulit.getParameter("userName");  
  
//        Enumeration filesname = mulit.getFileNames();//取得上传的所有文件(相当于标识)   
//        while (filesname.hasMoreElements()) {  
//            String name = (String) filesname.nextElement();//标识  
//            fileName = mulit.getFilesystemName(name); //取得文件名  
//            String contentType = mulit.getContentType(name);//工具标识取得的文件类型  
//            if (fileName != null) {  
//                fileCount++;  
//            }  
//        }  
    }  
   
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   
        this.doGet(request, response);  
    }   
      
    public void init() throws ServletException {  
    }
}
