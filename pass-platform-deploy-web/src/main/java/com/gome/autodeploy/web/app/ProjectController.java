/**
 * 
 */
package com.gome.autodeploy.web.app;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.annotation.Resource;
import javax.ws.rs.core.Request;
import javax.annotation.Resource;

import org.acegisecurity.userdetails.memory.UserMap;
import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.tmatesoft.svn.core.SVNException;

import com.gome.autodeploy.common.DictionaryType;
import com.gome.autodeploy.common.UserMseeage;
import com.gome.autodeploy.domain.auth.SvnAuth;
import com.gome.autodeploy.domain.auth.User;
import com.gome.autodeploy.domain.project.Project;
import com.gome.autodeploy.domain.project.ProjectSvn;
import com.gome.autodeploy.service.project.ProjectPackageService;
import com.gome.autodeploy.service.project.ProjectService;
import com.gome.autodeploy.service.project.ProjectSvnService;
import com.gome.autodeploy.web.BaseController;
import com.gome.passplatform.docker.DockerImageApiClient;
import com.gome.passplatform.jenkins.JenkinsClient;
import com.gome.passplatform.jenkins.SvnUtil;

/**
 * @author bailu-ds
 *
 */
@Controller
@RequestMapping(value="/projects")
@SuppressWarnings({"rawtypes", "unchecked"})
public class ProjectController extends BaseController {
	private static Logger logger = Logger.getLogger(ProjectController.class);
	@Resource
	private ProjectService projectService;
	@Resource
	private ProjectPackageService projectPackageService;
	@Resource
	private ProjectSvnService projectSvnService;
	@Resource
	private JenkinsClient jenkinsClient;
	@Resource
	private DockerImageApiClient dockerImageApiClient;
	
	private final String regex="http(s?)://(.*)";  //正则不严谨，可用
	
	@RequestMapping(value="/structure/{projectId}",  method={RequestMethod.POST})
	@ResponseBody
	public Map structure(@PathVariable Integer projectId, String basicImage, String memo){
		logger.info("structure info "+projectId+":"+basicImage);
		Map<String,String> msgMap = new HashMap<String,String>();
//		basicImage = "registry.hub.gome.com.cn/tomcat";
		Project project = projectService.getProject(projectId);
		ProjectSvn projectSvn = projectSvnService.getProjectSvn(projectId);
		Map<String,String> ipAndPort = svnHost(projectSvn.getSvnAddressBranch());
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMDDHHmmss");
		String versionNo = format.format(new Date());
		StringBuilder sb = new StringBuilder();
		logger.info("structure project"+project.toString());
		logger.info("structure ipAndPort"+ipAndPort.toString());
		try {
			logger.info("createJob=======================begin");
			jenkinsClient.createJob(project.getProjectSpell(), projectSvn.getSvnAddressBranch(), ipAndPort.get("ip"), ipAndPort.get("port"), projectSvn.getSvnUserBranch(), projectSvn.getSvnPasswordBranch());
			logger.info("getCreateLog=======================begin");
			sb.append(jenkinsClient.buildReturnLog(project.getProjectSpell()));
			logger.info("copyWar=======================begin");
			String packageName = jenkinsClient.copyWarTotarget(project.getProjectSpell(), dockerImageApiClient.getPackagePath() + versionNo);
			logger.info("addImage=======================begin");
			sb.append(addImage( projectPackageService, dockerImageApiClient,  projectId,  basicImage, packageName, project.getProjectSpell(), versionNo, memo));
			msgMap.put("success", "true");
			msgMap.put("msg", sb.toString());
		} catch (Exception e) {
			msgMap.put("success", "false");
			msgMap.put("msg", "构建异常");
			e.printStackTrace();
		}
		return msgMap;
	}
	
	
	/** 
	* @Title: svnHost 
	* @Description: 根据svn地址获取ip和端口
	* @param svnPath
	* @return Map<String,String>   
	* @throws 
	*/
	private Map<String,String> svnHost(String svnPath){
		Map<String,String> svnMap = new HashMap<String,String>();
		Pattern p=Pattern.compile(regex);
		Matcher m=p.matcher(svnPath);
		while(m.find()){
			String svnStr = m.group(2);
			if(svnStr.contains(":")){
				String[] ipAndPort = svnStr.split(":");
				svnMap.put("ip", ipAndPort[0]);
				svnMap.put("port", ipAndPort[1]);
			}else{
				svnMap.put("ip", svnStr);
				svnMap.put("port", "80");
			}
		}
		return svnMap;
	}
	
	public static void main(String[] str){
		ProjectController pc = new ProjectController();
		Map map = pc.svnHost("http://10.58.44.86/repos/gmfs/branches/passplatform/pass-platform");
		System.out.println(map);
	}
	
	@RequestMapping(value="/svn",  method={RequestMethod.POST})
	@ResponseBody
	public Map checkSVN(SvnAuth svnAuth){
		logger.info("svn检测"+svnAuth.toString());
		Map<String,String> svnMap = new HashMap<String,String>();
		if(svnAuth == null || svnAuth.getSvnPath().equals("")|| svnAuth.getSvnUserName().equals("")|| svnAuth.getSvnPassword().equals("")){
			svnMap.put("msg", "svn信息不能为空");
			svnMap.put("success", "false");
		}else{
			try {
				SvnUtil.authSvn(svnAuth.getSvnPath(), svnAuth.getSvnUserName(), svnAuth.getSvnPassword());
				svnMap.put("msg", "svn验证通过");
				svnMap.put("success", "true");
			} catch (SVNException e) {
				logger.info("svn验证失败"+svnAuth.toString());
				svnMap.put("msg", "svn验证失败");
				svnMap.put("success", "false");
			}
		}
		return svnMap;
	}
	
	/** 
	* @Title: projectInit 
	* @Description:  新增项目
	* 	1、校验是否有同名或者相同简拼的项目存在，
	* 	2、如果不存在，则新增，
	* 	3、如果存在，则返回提示信息
	* @param project
	* @return ModelAndView   
	* @throws 
	*/
	@RequestMapping(method={RequestMethod.POST})
	public ModelAndView projectInit(Project project,SvnAuth svnAuth, HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		User user= UserMseeage.getUserMessages(request);
		Integer userId= user.getUserId();
		if(!projectService.checkExistProjectNameOrSpell(project)){
			project.setUpdateTime(new Date());
			project.setIsDel(0);
			project.setUserId(userId);
			project.getProjectId();
			logger.info("创建项目"+project.toString());
			projectService.addProject(project);
			if(project != null && project.getCodeSource().equals(1))
			{
			ProjectSvn projectSvn = new ProjectSvn();
			projectSvn.setProjectId(project.getProjectId());
			projectSvn.setCreateTime(new Date());
			projectSvn.setSvnAddressBranch(svnAuth.getSvnPath());
			projectSvn.setSvnAddressTrunk(svnAuth.getSvnPath());
			projectSvn.setSvnPasswordBranch(svnAuth.getSvnPassword());
			projectSvn.setSvnPasswordTrunk(svnAuth.getSvnPassword());
			projectSvn.setSvnUserBranch(svnAuth.getSvnUserName());
			projectSvn.setSvnUserTrunk(svnAuth.getSvnUserName());
			projectSvn.setUpdateTime(new Date());
			logger.info("创建svn信息"+projectSvn.toString());
			projectSvnService.addProjectSvn(projectSvn);
			modelAndView.addObject("svn", projectSvnService.getProjectSvn(project.getProjectId()));
			}
			modelAndView.addObject("project", project);
			if(project != null && project.getCodeSource().equals(1)){
				modelAndView.addObject("mirrorTypes", getDictionarys(DictionaryType.MIRROR_TYPE.getValue()));
			}
			modelAndView.setViewName("project/project_view.jsp");
		}else{
			modelAndView.addObject("project", project);
			modelAndView.addObject("msg","创建失败，存在相同名称或简拼的项目---"+project.getProjectName());
			modelAndView.setViewName("project/project_edit.jsp");
		}

		return modelAndView;

	}
	
	@RequestMapping(value="/view/{projectId}",  method={RequestMethod.GET})
	public ModelAndView projectView(@PathVariable Integer projectId){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("project", projectService.getProject(projectId));
		modelAndView.addObject("svn", projectSvnService.getProjectSvn(projectId));
		modelAndView.setViewName("project/project_edit.jsp");
		return modelAndView;
	}
	
 
	
	/** 
	* @Title: modifyProject 
	* @Description: 修改项目
	* @param project
	* @return ModelAndView   
	* @throws 
	*/
	@RequestMapping(value="/modify/{projectId}", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView modifyProject(Project project,ProjectSvn projectSvn,Integer projectId) {
		projectService.modifyProject(project);
		projectSvnService.modifyProjectSvn(projectSvn);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("svn", projectSvnService.getProjectSvn(projectId));
		modelAndView.setViewName("project/project_view.jsp");
		return modelAndView;
	}
	
	
	/**
	 * 查询项目列表
	 * */
	@RequestMapping(method={RequestMethod.GET})
	public ModelAndView projects(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		User user = UserMseeage.getUserMessages(request);
		List<Project> project = projectService.queryProjectByUser(user.getUserId());	
		modelAndView.addObject("projects", projectService.queryProjectByUser(user.getUserId()));
		modelAndView.setViewName("project/project_list.jsp");
		return modelAndView;
	}
 
	@RequestMapping(value="/{projectId}",  method={RequestMethod.GET})
	public ModelAndView projectManager(@PathVariable Integer projectId){
		ModelAndView modelAndView = new ModelAndView();
		Project project = projectService.getProject(projectId);
		ProjectSvn projectSvn=projectSvnService.getProjectSvn(projectId);
		if(project != null && project.getCodeSource().equals(1)){
			modelAndView.addObject("mirrorTypes", getDictionarys(DictionaryType.MIRROR_TYPE.getValue()));
		}
		modelAndView.addObject("project", project);
		modelAndView.addObject("svn", projectSvn);
		modelAndView.setViewName("project/project_view.jsp");
		return modelAndView;
	}
	
	 
	
	/** 
	* @Title: projectDel 
	* @Description: 删除项目
	* @param projectId
	* @return Map   
	* @throws 
	*/
	@RequestMapping(value="/{projectId}",  method={RequestMethod.DELETE})
	@ResponseBody
	public Map projectDel(@PathVariable Integer projectId){
		Map msgMap = new HashMap();
		boolean returnFlag = projectService.removeProject(projectId);
		if(returnFlag){
			msgMap.put("msg", "success");
		}else{
			msgMap.put("msg", "fail");
		}
		return msgMap;
	}
	
}
