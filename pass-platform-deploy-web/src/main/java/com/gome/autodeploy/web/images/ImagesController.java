/**
 * 
 */
package com.gome.autodeploy.web.images;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gome.autodeploy.common.CosUploadUtil;
import com.gome.autodeploy.common.DictionaryType;
import com.gome.autodeploy.common.DockerFile;
import com.gome.autodeploy.common.FileUtil;
import com.gome.autodeploy.domain.app.App;
import com.gome.autodeploy.domain.auth.User;
import com.gome.autodeploy.domain.project.Project;
import com.gome.autodeploy.domain.project.ProjectPackage;
import com.gome.autodeploy.service.app.AppService;
import com.gome.autodeploy.service.project.ProjectPackageService;
import com.gome.autodeploy.service.project.ProjectService;
import com.gome.autodeploy.util.GZIPUtil;
import com.gome.autodeploy.web.BaseController;
import com.gome.pass.platform.log.bean.Message;
import com.gome.passplatform.docker.DockerImageApiClient;
import com.google.gson.Gson;

/**
 * @author bailu-ds
 *
 */
@Controller
@RequestMapping(value="/mirror")
@SuppressWarnings({"rawtypes","unchecked"})
public class ImagesController extends BaseController {
	
	@Resource
	private DockerImageApiClient dockerImageApiClient;
	
	@Resource
	private ProjectService projectService;
	
	@Resource
	private ProjectPackageService projectPackageService;
	
	@Resource
	private AppService appService;

	
	
	@RequestMapping(value="/{packageId}", method={RequestMethod.GET})
	public ModelAndView packageView(@PathVariable int packageId){
		ProjectPackage projectPackage = projectPackageService.queryProjectPackageById(packageId);
		Project project = projectService.getProject(projectPackage.getProjectId());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("project", project);
		modelAndView.addObject("projectPackage", projectPackage);
		modelAndView.setViewName("/project/mirror_view.jsp");
		return modelAndView;
	}
	
	
	@RequestMapping(value="/create/{projectId}", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView toCreate(@PathVariable String projectId) {
		ModelAndView modelAndView = new ModelAndView();
		if (StringUtils.isEmpty(projectId)) {
			return null;
		}
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("projectId", projectId);
//		map.put("userId", getUser().getUserName());
		Project project = new Project();
		project.setProjectId(Integer.valueOf(projectId));
//		List<Project> list = projectService.queryProject(map);
		List<Project> list = projectService.queryProject(project);
		if (list.size() == 0) {
			return null;
		}
		modelAndView.addObject("proj", list.get(0));
		modelAndView.addObject("mirrorTypes", getDictionarys(DictionaryType.MIRROR_TYPE.getValue()));
		modelAndView.setViewName("/project/mirror_edit.jsp");
		return modelAndView;
	}
	
	@RequestMapping(value="/to/upload/{projectId}", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView toUpload(@PathVariable String projectId, String projectName) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("projectId", projectId);
		modelAndView.addObject("projectName", projectName);
		modelAndView.setViewName("/project/upload.jsp");
		return modelAndView;
	}
	
	@RequestMapping(value="/upload", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView upload(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			String projectId = request.getParameter("projectId");
			String projectName = request.getParameter("projectName");
			String file = request.getParameter("filePath");
			if(!file.endsWith(".tar.gz")) {
				modelAndView.setViewName("redirect:/mirror/to/upload/" + projectId);
				return modelAndView;
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMDDHHmmss");
			String versionNo = format.format(new Date());
			User user = getUser();
			String packagePath = dockerImageApiClient.getPackagePath();
			File dir = new File(packagePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			String packageName = user.getUserName() + "_" + versionNo + ".tar.gz";
			String filePath = packagePath + packageName;
			CosUploadUtil.uploadPackage(packageName, packagePath, request);
			
			ProjectPackage projectPackage = new ProjectPackage();
			projectPackage.setCreateUser(getUser().getUserName());
			projectPackage.setPackageName(packageName);
			projectPackage.setPackagePath(packagePath);
			projectPackage.setProjectId(Integer.valueOf(projectId));
			projectPackage.setVersionNo(versionNo);
			projectPackage.setImageName(getUser().getUserName() + "-" + projectName);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("createUser", getUser().getUserName());
			map.put("projectId", projectId);
			projectPackageService.updateProjectPackageStatus(map);
			projectPackageService.addProjectPackage(projectPackage);
			modelAndView.addObject("packageId", projectPackage.getPackageId());
			modelAndView.addObject("filePath", filePath);
			modelAndView.addObject("packageName", packageName);
			modelAndView.setViewName("/project/upload_seccess.jsp");
		} catch(Exception ex) {
			modelAndView.setViewName("/project/upload.jsp");
		}
        return modelAndView;
	}
	
	@RequestMapping(value="/upload/delete", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			String file = request.getParameter("filePath");
			String packegeId = request.getParameter("packegeId");
			
			File file2 = new File(file);
			if (file2.exists()) {
				file2.delete();
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("packegeId", Integer.valueOf(packegeId));
			projectPackageService.removeProjectPackage(map);
		} catch(Exception ex) {
		}
		modelAndView.setViewName("/project/upload.jsp");
        return modelAndView;
	}
	
	@RequestMapping(value="/createimage/{projectId}", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String createImage(@PathVariable Integer projectId, String basicImage,String packageName,String projectSpell,String versionNo,String memo) {
//		SimpleDateFormat format = new SimpleDateFormat("yyyyMMDDHHmmss");
//		String versionNo = format.format(new Date());
		return addImage( projectPackageService, dockerImageApiClient,  projectId,  basicImage, packageName, projectSpell, versionNo, memo);
	}
	
	@RequestMapping(value="/{packageId}", method={RequestMethod.DELETE})
	@ResponseBody
	public Map delPackage(@PathVariable String packageId) {
		Map map = new HashMap();
		map.put("packageId", packageId);
		map.put("message", projectPackageService.removeProjectPackage(map));
		return map;   
	}	
	
	
	@RequestMapping(value="/projectId/{projectId}", method={RequestMethod.GET})
	@ResponseBody
	public List<ProjectPackage> images(@PathVariable String projectId) {
		Map map = new HashMap();
		map.put("projectId", projectId);
		map.put("isDel", "0");
		return  projectPackageService.queryProjectPackage(map);
	}
	
	@RequestMapping(value="/projectId/{projectId}/app", method={RequestMethod.GET})
	@ResponseBody
	public List<App> apps(@PathVariable String projectId) {
		Map map = new HashMap();
		map.put("projectId", projectId);
		return  appService.queryApp(map);
	}
	
	
	
	/** 
	* @Title: uploadWar 
	* @Description: 上传war包，
	* @param request
	* @param response
	* @throws IllegalStateException
	* @throws IOException void   
	* @throws 
	*/
	@RequestMapping(value="/uploadWar", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void uploadWar(HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException{
//		String renameFileName = ""; 
		 Map message = new HashMap();
		boolean isMultipart= ServletFileUpload.isMultipartContent(request);
		  if(isMultipart){  
	            //构造一个文件上传处理对象  
	            FileItemFactory factory = new DiskFileItemFactory();  
	            ServletFileUpload upload = new ServletFileUpload(factory);  
	            Iterator items;  
	            try{  
	                //解析表单中提交的所有文件内容  
	                items = upload.parseRequest(request).iterator();  
	                while(items.hasNext()){  
	                    FileItem item = (FileItem) items.next();  
	                    if(!item.isFormField()){  
	                        //取出上传文件的文件名称  
	                        String name = item.getName();  
	                        //取得上传文件以后的存储路径  
	                        String fileName = name.substring(name.lastIndexOf('\\') + 1, name.length());  
	                       
	                        SimpleDateFormat format = new SimpleDateFormat("yyyyMMDDHHmmss");
	            			String versionNo = format.format(new Date());
	            			 //上传文件以后的存储路径  
	                        String saveDir = dockerImageApiClient.getPackagePath() + File.separatorChar +versionNo;  
	                        if (!new File(saveDir).exists()){  
	                            new File(saveDir).mkdirs();  
	                        }  
	            			
//	            			 renameFileName = fileName.substring(0, fileName.indexOf("."))+fileName.substring(fileName.indexOf("."));
//	                        String path= saveDir + File.separatorChar +versionNo;  
	                        //上传文件  
//	                        File uploaderFile = new File(path);  
//	                        uploaderFile.mkdirs();
	                        File uploaderFile =  new File(saveDir+ File.separatorChar+ fileName);
	                        item.write(uploaderFile);  
	                        message.put("fileName", fileName);
	                        message.put("versionNo", versionNo);
	                        message.put("success", true);
	                    }  
	                }  
	            }catch(Exception e){  
                    message.put("success", false);
	            }  
		  }else{
			  message.put("success", false);
		  }
		  Gson gson = new Gson();
		  response.setContentType("text/html");
		  response.getWriter().print(gson.toJson(message)); 
	}
	
	
//	public static void main(String[] args) {
//		String dockerFile = DockerFile.getDockerFile();
//		dockerFile = String.format(dockerFile, "tomcat:8", "blaiu", "app.tar");
//		System.out.println(dockerFile);
//		File file = new File("D:\\Upload\\package\\1\\admin\\dockerFile");
		
		
//		FileUtil.writeFile("D:\\Upload\\package\\1\\admin\\dockerFile", dockerFile);
		
//		File[] sources = new File[] {new File("D:\\Upload\\package\\1\\admin\\admin_201507203162747tar.gz"), new File("D:\\Upload\\package\\1\\admin\\Dockerfile")};
//		 File target = new File("D:\\Upload\\package\\1\\admin\\app.tar.gz");
//		 GZIPUtil.compress(GZIPUtil.pack(sources, target));
//		
//		
//	}
	
	@RequestMapping(value="/ImageId/{ImageId}", method={RequestMethod.GET})
	@ResponseBody
	public List<ProjectPackage> ImageId(@PathVariable("ImageId") String ImageId) {
		Map map = new HashMap();
		map.put("versionNo", ImageId);
		map.put("isDel", "0");
		return  projectPackageService.queryProjectPackage(map);
	}
	
}
