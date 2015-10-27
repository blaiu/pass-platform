package com.gome.autodeploy.web.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gome.autodeploy.common.ExpansionBean;
import com.gome.autodeploy.common.KuberUtil;
import com.gome.autodeploy.common.UserMseeage;
import com.gome.autodeploy.domain.app.App;
import com.gome.autodeploy.domain.app.AppExpansion;
import com.gome.autodeploy.domain.app.ExpansionList;
import com.gome.autodeploy.domain.app.ExpansionPlan;
import com.gome.autodeploy.domain.auth.User;
import com.gome.autodeploy.domain.deploy.ContainerConfig;
import com.gome.autodeploy.domain.project.Project;
import com.gome.autodeploy.domain.project.ProjectPackage;
import com.gome.autodeploy.domain.volume.Volume;
import com.gome.autodeploy.exception.PaasDBException;
import com.gome.autodeploy.exception.PaasException;
import com.gome.autodeploy.service.app.AppExpansionService;
import com.gome.autodeploy.service.app.AppFacade;
import com.gome.autodeploy.service.app.AppService;
import com.gome.autodeploy.service.deploy.ContainerConfigService;
import com.gome.autodeploy.service.project.ProjectPackageService;
import com.gome.autodeploy.service.project.ProjectService;
import com.gome.autodeploy.service.volume.VolumeService;
import com.gome.autodeploy.web.BaseController;
import com.gome.autodeploy.web.common.PaasConstant;
import com.gome.passplatform.jenkins.JenkinsClient;
import com.gome.passplatform.kubernetes.CephClient;
import com.gome.passplatform.kubernetes.ExpansionClient;
import com.gome.passplatform.kubernetes.KuberCommon;
import com.gome.passplatform.kubernetes.KubernetesApiClient;
import com.gome.passplatform.kubernetes.exceptions.KubernetesClientException;
import com.gome.passplatform.kubernetes.exp.ConditionDo;
import com.gome.passplatform.kubernetes.exp.ExpConstat;
import com.gome.passplatform.kubernetes.exp.MemConditionDo;
import com.gome.passplatform.kubernetes.interfaces.KubernetesAPIClientInterface;
import com.gome.passplatform.kubernetes.modelv1.Pod;
import com.gome.passplatform.kubernetes.modelv1.PodList;
import com.gome.passplatform.kubernetes.modelv1.ReplicationController;
import com.gome.passplatform.kubernetes.modelv1.Service;
import com.google.gson.Gson;

/**
 * @author yangshuangjun
 *
 */
@Controller
@RequestMapping(value = "/application")
@SuppressWarnings({"unchecked", "rawtypes"})
public class AppController extends BaseController{
     
	
	private static Logger logger = Logger.getLogger(AppController.class);
	@Resource
	private AppService appService;
	@Resource
	private AppExpansionService appExpansionService;
	@Resource
	private ProjectService projectService;
	@Resource
	private ContainerConfigService containerConfigService;
	@Resource
	private VolumeService volumeService;
	@Resource
	private ProjectPackageService projectPackageService;
	@Resource
	private KubernetesApiClient kubernetesApiClient;
	@Resource
	private ExpansionClient expansionClient;
	@Resource
	private AppFacade appFacade;
	@Resource
	private JenkinsClient jenkinsClient;
	 
	
	/** 
	* @Title: toApp 
	* @Description: 新增应用初始化，
	* 	1、查询所有项目
	* 	2、查询容器配制
	* @return ModelAndView   
	* @throws 
	*/
	@RequestMapping(value="/to/add", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView toApp(HttpServletRequest request) {
		Project project = new Project();
		ModelAndView modelAndView = new ModelAndView();
		project.setIsDel(0);
		modelAndView.addObject("project", projectService.queryProject(project));
		
		Map<String, Object> map = new HashMap<String, Object>();
		modelAndView.addObject("containerconfig",containerConfigService.queryContainerConfig(map) );
		
		User user = UserMseeage.getUserMessages(request);
		modelAndView.addObject("volume",volumeService.queryVolumeNotUsed(user.getUserId()));
		
		modelAndView.setViewName("/app/app-edit.jsp");
		return modelAndView;
	}
	
	@RequestMapping(value="/projectId/{projId}", method={RequestMethod.GET})
	public ModelAndView projectToApp(@PathVariable int projId,HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		Project project = new Project();
		project.setIsDel(0);
		modelAndView.addObject("project", projectService.queryProject(project));
		
		modelAndView.addObject("curproject", projectService.getProject(projId));
		Map map = new HashMap();
		map.put("projectId", projId);
		modelAndView.addObject("projectPackges", projectPackageService.queryProjectPackage(map));
		
		Map<String, Object> configMap = new HashMap<String, Object>();
		modelAndView.addObject("containerconfig",containerConfigService.queryContainerConfig(configMap) );
		
		User user = UserMseeage.getUserMessages(request);
		modelAndView.addObject("volume",volumeService.queryVolumeNotUsed(user.getUserId()));
	
		modelAndView.setViewName("/app/app-edit.jsp");
		return modelAndView;
	}
	
	@RequestMapping(value="/projectId/{projId}/{packageId}", method={RequestMethod.GET})
	public ModelAndView projectToApp(@PathVariable int projId,@PathVariable int packageId){
		ModelAndView modelAndView = new ModelAndView();
		initApp(modelAndView,projId,packageId);
		modelAndView.setViewName("/app/app-edit.jsp");
		return modelAndView;
	}
	
	
	
	/** 
	* @Title: initApp 
	* @Description: 新建应用初始化,项目，镜像，配置
	* @param modelAndView
	* @param projId
	* @param packageId void   
	* @throws 
	*/
	public void initApp(ModelAndView modelAndView,int projId,int packageId){
		
		modelAndView.addObject("curproject", projectService.getProject(projId));
		modelAndView.addObject("curPackage", projectPackageService.queryProjectPackageById(packageId));
		
		Project project = new Project();
		project.setIsDel(0);
		modelAndView.addObject("project", projectService.queryProject(project));
		Map map = new HashMap();
		map.put("projectId", projId);
		modelAndView.addObject("projectPackges", projectPackageService.queryProjectPackage(map));
		
		Map<String, Object> configMap = new HashMap<String, Object>();
		modelAndView.addObject("containerconfig",containerConfigService.queryContainerConfig(configMap) );
	}
	/** 
	* @Title: addApp 
	* @Description: 新增应用
	* 	  1、调用kubernates接口，生成 RC对象，创建RC,创建服务，出错抛出异常
	*     2、调用新增应用和扩容计划方法
	* @param app  应用
	* @param expanlist  扩容计划
	* @return ModelAndView   
	* @throws 
	*/
	@RequestMapping(value="/add", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView addApp(App app, ExpansionList expanlist)  {
		logger.info("create app"+app.toString());
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("/app/app-edit.jsp");
		boolean createFlag = false ;    //创建应用扩展计划 标识
		Project project = projectService.getProject(app.getProjectId());
		ContainerConfig containerConfig =  containerConfigService.getContainerConfig(app.getConfigId());
		ProjectPackage proPackage = projectPackageService.queryProjectPackageById(app.getPackageId());
		if(project != null && containerConfig != null && proPackage != null){
			app.setProjectSpell(project.getProjectSpell());
			app.setImageName(proPackage.getImageName());
			
			try {
				logger.info("createContainerService---------------------------------begin");
				createContainerService(app, proPackage, containerConfig,expanlist);
				logger.info("createContainerService---------------------------------end");
				
				//创建应用，扩展计划	
				if(app.checkNull()){
					createFlag = appFacade.addApp(app, expanlist.getExpasions());
				}else{
					createFlag = false;
				}
			}catch(KubernetesClientException ecl){
				logger.error("  接口调用异常， "+ecl.getMessage());
				modelAndView.addObject("logMsg", "  接口调用异常， "+ecl.getMessage());
			}catch(PaasDBException e){
				//DB异常需要回滚db操作接口操作
				try {
					removeContainerService(app);
					
				} catch (KubernetesClientException e1) {
				}
				logger.error(" 新增应用内部异常， "+e.getMessage());
				modelAndView.addObject("logMsg", " 新增应用内部异常 "+e.getMessage());
			}catch(Exception e){
				//其他异常，报错
			}
			modelAndView.addObject("app", app);
			modelAndView.addObject("containerConfig",containerConfigService.getContainerConfig(app.getConfigId()));
			modelAndView.addObject("projectPackage",projectPackageService.queryProjectPackageById(app.getPackageId()));
			List<AppExpansion> appExpansion=appExpansionService.queryAppExpansionByAppId(app.getId());
			AppExpansion memory=appExpansion.get(0);
			AppExpansion cpu=appExpansion.get(1);
			modelAndView.addObject("memory",memory);
			modelAndView.addObject("cpu",cpu);
			
			if(app.getMountVolume()==1)
			{
			Volume volume=new Volume();
			volume.setAppId(app.getId());
			volume.setId(app.getVolumeId());
			
			Volume volume1=volumeService.getVolume(app.getVolumeId());
			String inputPath=app.getCustomPath();
			
			String customPath=null;
			if(inputPath.isEmpty())
			{
				customPath="disk/"+volume1.getVolumeName();
			}else{
				if(inputPath.endsWith("/"))
				  {
					customPath=inputPath+volume1.getVolumeName();
				  }else{
					customPath=inputPath+"/"+volume1.getVolumeName();
				  }
			}
			volume.setCustomPath(customPath);
			
			volumeService.mountVolume(volume);
			modelAndView.addObject("volume",volumeService.queryVolumeByAppId(app.getId()));
			
			}
			
			if(createFlag){
				modelAndView.setViewName("/app/app-manager.jsp");
			} 
		} 
		if(modelAndView.getViewName().equals("/app/app-edit.jsp")){
			modelAndView.addObject("errorMsg", "系统内部错误，请联系管理员");
			modelAndView.addObject("app", app);
			initApp(modelAndView,project.getProjectId(),app.getPackageId());
		}
		return modelAndView;
	}
	
	
	/** 
	* @Title: removeContainerService 
	* @Description: 清除RC和自动扩容
	* @param app
	* @throws KubernetesClientException void   
	* @throws 
	*/
	public void removeContainerService(App app) throws KubernetesClientException{
		kubernetesApiClient.deleteReplicationController(app.getProjectSpell(), app.getAppSpell());
		try {
			expansionClient.deletecondition(app.getProjectSpell(), app.getAppSpell());
		} catch (Exception e) {
			throw new KubernetesClientException(e.getMessage());
		}
	}
	
	
	/**
	 * @throws KubernetesClientException  
	* @Description: 创建RC，服务，扩容方案
	* @throws 
	*/
	public void createContainerService(App app,ProjectPackage proPackage,ContainerConfig containerConfig,ExpansionList expanlist) throws KubernetesClientException  {
		Service  s;
		logger.info("newRc");
		
		Map<String, Object> map = null;
		if(app.getMountVolume()==1){
			
			List<String> volumes=new ArrayList<String>();
			Volume v = volumeService.getVolume(app.getVolumeId());
			if(app.getCustomPath().isEmpty())
			{
				volumes.add(v.getVolumeName()+","+"disk/"+","+app.getImageName()+","+v.getReadOnlyFlag());
			}else{
				volumes.add(v.getVolumeName()+","+app.getCustomPath()+","+app.getImageName()+","+v.getReadOnlyFlag());
			}
			
			
			map=KuberUtil.createVolumes(volumes, kubernetesApiClient.getCephMonitors(), kubernetesApiClient.getCephPool(), kubernetesApiClient.getCephUser(), kubernetesApiClient.getKeyring(), kubernetesApiClient.getCephSecretRef(), "ext4");
		}
		
		
		ReplicationController rc = KuberUtil.createReplicationController(app.getAppSpell(), app.getProjectSpell(), proPackage.getImageName(),proPackage.getVersionNo(), app.getAppSpell(), String.valueOf(containerConfig.getCpu()), String.valueOf(containerConfig.getMemory()), app.getContainerCount(), app.getHostPort(),map);
		
			try {
				logger.info("createRC-----------------------begin");
				rc = kubernetesApiClient.createReplicationController(app.getProjectSpell(), rc);
				logger.info("createService----------------------begin");
				s = kubernetesApiClient.createService(app.getProjectSpell(), KuberUtil.createService(app.getAppSpell(), app.getAppSpell(), app.getHostPort(),-1, app.getAppSpell()));
				app.setHostIp(s.getSpec().getClusterIP());
				app.setServerPort(s.getSpec().getPorts().get(0).getNodePort());
			} catch (KubernetesClientException e) {
				logger.error("createContinerService 方法  创建RC，Serve失败"+e.getMessage());
				throw new KubernetesClientException(e.getMessage());
			}
		app.setServerIp(PaasConstant.SERVER_IP);
		app.setPodLabel(app.getAppSpell());
		app.setIsDel(0);
		logger.info("getExpansion----------------------begin");
		ConditionDo cd = getExpansion(app, expanlist);
		if (null != cd && 1 == app.getExpansionFlag()) {
			try {
				logger.info("create Expansion condition----------------------begin");
				expansionClient.insertcondition(cd);
			} catch (Exception e) {
				logger.error("createContinerService 方法 自动扩容接口异常"+e.getMessage());
				kubernetesApiClient.deleteReplicationController(app.getProjectSpell(), app.getAppSpell());
				throw new KubernetesClientException(e.getMessage());
			}
		}
	}
	
	
	/** 
	* @Title: delApp 
	* @Description: 删除应用，kubernates删除实例，server，
	* 		修改应用状态为删除
	* @param appId
	* @return ModelAndView   
	* @throws 
	*/
	@RequestMapping(value="/delApp/{appId}")
	public ModelAndView delApp(@PathVariable int appId)  {
		boolean delFlag = false;
		App app = appService.queryAppById(appId);
		
		List<AppExpansion> appExpansion=appExpansionService.queryAppExpansionByAppId(appId);
		ExpansionList expanlist=new ExpansionList();
		expanlist.setExpasions(appExpansion);
		ConditionDo cd = getExpansion(app, expanlist);
		
		ContainerConfig containerConfig=containerConfigService.getContainerConfig(app.getConfigId());
		ProjectPackage proPackage=projectPackageService.queryProjectPackageById(app.getPackageId());
		
		Volume volume=volumeService.queryVolumeByAppId(appId);
		List<String> volumes=new ArrayList<String>();
		if(volume!=null)
		{
		volumes.add(volume.getVolumeName()+","+volume.getCustomPath()+","+app.getImageName()+","+volume.getReadOnlyFlag());
		}
		Map<String, Object> map = null;
		map=KuberUtil.createVolumes(volumes, kubernetesApiClient.getCephMonitors(), kubernetesApiClient.getCephPool(), kubernetesApiClient.getCephUser(), kubernetesApiClient.getKeyring(), kubernetesApiClient.getCephSecretRef(), "ext4");
		ReplicationController rc = KuberUtil.createReplicationController(app.getAppSpell(), app.getProjectSpell(), proPackage.getImageName(),proPackage.getVersionNo(), app.getAppSpell(), String.valueOf(containerConfig.getCpu()), String.valueOf(containerConfig.getMemory()), app.getContainerCount(), app.getHostPort(),map);
		
		
		int i=3;
			try {
				  delFlag =  appFacade.delApp(appId);
				  
				  if(delFlag)
				  {
				     kubernetesApiClient.deleteReplicationController(app.getProjectSpell(), app.getAppSpell());
				     i--;
				     expansionClient.deletecondition(app.getProjectSpell(), app.getAppSpell());
				     i--;
				     volumeService.deleteAppVolume(appId);
				  }
				  
			}catch(Exception e){
				logger.error("删除应用异常 "+e.getMessage());
				recover(i,appId,app.getProjectSpell(),app.getAppSpell(),cd,rc);
			}
			
			ModelAndView modelAndView = new ModelAndView();
			//Map<String, Object> map1 = new HashMap<String, Object>();
			//modelAndView.addObject("app",appService.queryAppList(map1));
			List<App> appList = appService.queryForAppList();
			modelAndView.addObject("appList",appList);
			modelAndView.setViewName("/app/app-list.jsp");
			return modelAndView;
	}
	
	
	/** 
	* @Title: removeInstance 
	* @Description: 清除实例，
	* 		采用缩容方式，缩成0个实例数
	* @param projectSpell
	* @param rcName
	* @return Map   
	* @throws 
	*/
	@RequestMapping(value="/{projectSpell}/{rcName}/{packageId}", method={RequestMethod.DELETE})
	@ResponseBody
	public Map removeInstance( @PathVariable String projectSpell,@PathVariable String rcName,@PathVariable int packageId)  {
		ProjectPackage projPackage = projectPackageService.queryProjectPackageById(packageId);
			if(projPackage == null){
				throw new PaasException(PaasException.PARAM_ERROR,"packageId 不存在 "+packageId);
			}
			Gson gson = new Gson();
			Map map = new HashMap();
				//最后参数为版本号
			 ReplicationController s;
			try {
				s = kubernetesApiClient.updateReplicationController(projectSpell, rcName, 0, null,projPackage.getVersionNo());
				map.put("msg",gson.toJson(s));
			} catch (KubernetesClientException e) {
				logger.error("kubernetesApiClient.updateReplicationController 清除实例接口异常");
				map.put("msg","清除实例接口异常");
			}catch(Exception e){
				logger.error("清除实例内部异常"+e.getMessage());
				map.put("msg","清除实例内部异常");
			}
			return map;
	}
	
	
	/** 
	* @Title: removeServer 
	* @Description: 停止服务
	* 		缩容，减少实例数为0
	* @param appId
	* @return ModelAndView   
	* @throws 
	*/
	@RequestMapping(value="/server/stop/{appId}")
	public ModelAndView stopServer(@PathVariable int appId)  {
		
	 
		App app = appService.queryAppById(appId);
		if(app == null){
			throw new PaasException(PaasException.PARAM_ERROR,"停止服务 appId 不存在 "+appId);
		}
		try {
			ProjectPackage projPackage = projectPackageService.queryProjectPackageById(app.getPackageId());
			//先修改db，后执行接口
			app.setId(appId);
			app.setAppStatus(2);
			appService.updateApp(app);
			//缩容为0
			kubernetesApiClient.updateReplicationController(app.getProjectSpell(), app.getAppSpell(), 0, null,projPackage.getVersionNo());
//			kubernetesApiClient.deleteService(app.getProjectSpell(), app.getAppSpell());   删除server
		} catch (KubernetesClientException e) {
			//kubernetes异常，db操作补偿
			app.setId(appId);
			app.setAppStatus(1);
			appService.updateApp(app);
			logger.error(" kubernetesApiClient.updateReplicationController 接口调用异常 ");
			throw new PaasException(PaasException.INTERFACE_ERROR, "停止服务应用接口调用异常"+e.getMessage());
		}catch(Exception e){
			logger.error("  内部异常 "+e.getMessage());
			throw new PaasException(PaasException.INTERNAL_ERROR, "内部异常异常"+e.getMessage());
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("app",app);
        modelAndView.setViewName("/app/app-manager.jsp");
		return modelAndView;
	}
	
	/** 
	* @Title: addServer 
	* @Description: 启动服务
	* 	  创建kubernetes服务的方式启动服务
	* @param appId
	* @return ModelAndView   
	* @throws 
	*/
	@RequestMapping(value="/server/run/{appId}")
	public ModelAndView runServer( @PathVariable int appId)  {
		App app = appService.queryAppById(appId);
		if(app == null){
			throw new PaasException(PaasException.PARAM_ERROR,"启动服务 appId 不存在 "+appId);
		}
		AppExpansion appExpansion = new AppExpansion();
		appExpansion.setAppId(appId);
		try {
			ProjectPackage projPackage = projectPackageService.queryProjectPackageById(app.getPackageId());
			//先修改db，后执行接口，扩容计划设置为删除状态
			appExpansion.setIsDel(1);
			appExpansionService.updateAppExpansionDelFlag(appExpansion);
			//扩容为初始值
			kubernetesApiClient.updateReplicationController(app.getProjectSpell(), app.getAppSpell(), app.getContainerCount(), null,projPackage.getVersionNo());
		} catch (KubernetesClientException e) {
			//接口调用异常，恢复扩容计划状态
			appExpansion.setIsDel(0);
			appExpansionService.updateAppExpansionDelFlag(appExpansion);
			logger.error(" kubernetesApiClient.createService 接口调用异常 ");
			throw new PaasException(PaasException.INTERFACE_ERROR, "启动服务应用接口调用异常"+e.getMessage());
		}catch(Exception e){
			//非kubernetes接口异常
			logger.error(" 内部异常 ");
			throw new PaasException(PaasException.INTERNAL_ERROR, "内部错误"+e.getMessage());
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("app",app);
        modelAndView.setViewName("/app/app-manager.jsp");
		return modelAndView;
	}
	
	
	/** 
	* @Title: list 
	* @Description: 应用列表查询
	* @return ModelAndView   
	* @throws 
	*/
	@RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView list( ) {
		ModelAndView modelAndView = new ModelAndView();
		
		List<App> appList = appService.queryForAppList();
		modelAndView.addObject("appList",appList);
        modelAndView.setViewName("/app/app-list.jsp");
		return modelAndView;
	}
	
	/** 
	* @Title: manager 
	* @Description: 应用管理，获取某个应用
	* @param appId
	* @return ModelAndView   
	* @throws 
	*/
	@RequestMapping(value="/{appId}", method={RequestMethod.GET})
	public ModelAndView manager(@PathVariable Integer appId) {
		ModelAndView modelAndView = new ModelAndView();
		App app = appService.queryAppById(appId);
		app.setAppStatus(this.getAppStatus(app.getProjectSpell(), app.getAppSpell()));
		modelAndView.addObject("app",app);
		modelAndView.addObject("nameSpaces", app.getProjectSpell());
		modelAndView.addObject("contianerName", KuberCommon.container_prefix +app.getAppSpell());
		modelAndView.addObject("rcLabel", KuberCommon.rc_label_prefix +app.getAppSpell());
		Volume volume=volumeService.queryVolumeByAppId(appId);
		modelAndView.addObject("volume",volume);
		ProjectPackage projectPackage=projectPackageService.queryProjectPackageById(app.getPackageId());
		modelAndView.addObject("projectPackage",projectPackage);
		ContainerConfig containerConfig=containerConfigService.getContainerConfig(app.getConfigId());
		modelAndView.addObject("containerConfig",containerConfig);
		
		List<AppExpansion> appExpansion=appExpansionService.queryAppExpansionByAppId(app.getId());
		AppExpansion memory=appExpansion.get(0);
		AppExpansion cpu=appExpansion.get(1);
		modelAndView.addObject("memory",memory);
		modelAndView.addObject("cpu",cpu);
		
        modelAndView.setViewName("/app/app-manager.jsp");
		return modelAndView;
	}
	
	
	/** 
	* @Title: manager 
	* @Description: 获取应用容器实例信息
	* @param projectSpell
	* @param rcName
	* @return
	* @throws KubernetesClientException List<Map>   
	* @throws 
	*/
	@RequestMapping(value="/{projectSpell}/{rcName}", method={RequestMethod.GET})
	@ResponseBody
	public List<Map> appPods(@PathVariable String projectSpell,@PathVariable String rcName) {
		if(StringUtils.isNotEmpty(rcName) && StringUtils.isNotEmpty(projectSpell)){
			PodList pods;
			try {
				pods = kubernetesApiClient.selectRcPods(projectSpell, rcName);
				List<Map> listMap = new ArrayList<Map>();
				for(Pod pod : pods.getItems()){
					try{
						Map map  = new HashMap();
						try{
							map.put("continerName", pod.getStatus().getContainerStatuses().get(0).getContainerID());
						}catch(NullPointerException es){
							map.put("continerName", pod.getSpec().getContainers().get(0).getName());
						}
						map.put("podName", pod.getMetadata().getName());
						map.put("imagName", pod.getSpec().getContainers().get(0).getImage());
						map.put("status", pod.getStatus().getPhase());
						map.put("podIp", pod.getStatus().getPodIP());
						map.put("nameSpaces", projectSpell);
						map.put("rcLabel", KuberCommon.selector_prefix +rcName);
						listMap.add(map);
					}catch(Exception e){
						logger.error("查询pods信息出错"+pod.toString()+e.getMessage());
						continue;
					}
				}
//				if(listMap != null){
					return listMap;
//				}
			} catch (KubernetesClientException e1) {
				logger.error("  kubernetesApiClient.selectRcPods 接口调用异常 ");
				throw new PaasException(PaasException.INTERFACE_ERROR, " 查询pods接口调用异常"+e1.getMessage());
			}
		}else{
			logger.error("  参数不能为空 ");
			throw new PaasException(PaasException.PARAM_ERROR, String.format("projectSpell:%s-rcName:%s 参数不能为空", projectSpell,rcName) );
		
		}
//		return null;
	}
	
	/** 
	* @Title: expansion 
	* @Description: 扩容
	* @param expansion
	* @return
	* @throws  Map   
	* @throws 
	*/
	@RequestMapping(value="/expansion", method={RequestMethod.POST})
	@ResponseBody
	public Map expansion( ExpansionBean expansion)  {
		Gson gson = new Gson();
		Map map = new HashMap();
		if(expansion != null){
			//最后参数为版本号
			ReplicationController s;
			try {
				s = kubernetesApiClient.updateReplicationController(expansion.getProjectSpell(), expansion.getAppSpell(), expansion.getExpansionCount(), null,"");
				 map.put("msg",gson.toJson(s));
			} catch (KubernetesClientException e) {
				logger.error("  kubernetesApiClient.updateReplicationController 接口调用异常 ，扩容失败"+e.getMessage());
				map.put("msg", "内部异常扩容失败");
			}
		}else{
			map.put("msg", "没有扩容参数");
		}
		return map;
	}
	
	private ConditionDo getExpansion(App app, ExpansionList expanlist) {
		ConditionDo conditionDo = null;
		if (null != expanlist && null != expanlist.getExpasions()) {
			conditionDo = new ConditionDo();
			conditionDo.setLabels("name:" + KuberCommon.rc_label_prefix + app.getAppSpell());
			conditionDo.setRcname(KuberCommon.rc_prefix + app.getAppSpell());
			conditionDo.setNamespace(app.getProjectSpell());
			Map<String, MemConditionDo> map = new HashMap<String, MemConditionDo>();
			for (AppExpansion ae : expanlist.getExpasions()) {
				if (1 == ae.getExpansionIndex()) {
					conditionDo.setMaxcount(ae.getMaxInstance());
					conditionDo.setMincount(ae.getMinInstance());
					conditionDo.setAddcountpertime(ae.getAddCount());
					conditionDo.setSubcountpertime(ae.getReduceCount());
					MemConditionDo mem = new MemConditionDo();
					mem.setMaxvalue(ae.getMaxValue() * 1D);
					mem.setMinvalue(ae.getMinValue() * 1D);
					map.put(ExpConstat.CPU_USEAGE, mem);
					conditionDo.setConditionmaps(map);
				} else {
					conditionDo.setMaxcount(ae.getMaxInstance());
					conditionDo.setMincount(ae.getMinInstance());
					conditionDo.setAddcountpertime(ae.getAddCount());
					conditionDo.setSubcountpertime(ae.getReduceCount());
					MemConditionDo mem = new MemConditionDo();
					mem.setMaxvalue(ae.getMaxValue() * (1D * 1024 * 1024));
					mem.setMinvalue(ae.getMinValue() * (1D * 1024 * 1024));
					map.put(ExpConstat.MEM_USEAGE, mem);
				}
			}
			conditionDo.setConditionmaps(map);
		}
		return conditionDo;
	}
	
	
	@RequestMapping(value="/modify/expansion/{appId}", method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public void modifyExpansion(@PathVariable Integer appId,ExpansionPlan expansionPlan) {
		
		//ModelAndView modelAndView = new ModelAndView();
		App app=appService.queryAppById(appId);
	
		appExpansionService.updateAppExpansionPlan(appId);
		
		AppExpansion appExpansion1=new AppExpansion();
		AppExpansion appExpansion2=new AppExpansion();
		
		appExpansion1.setAppId(appId);
		appExpansion1.setIsDel(0);
		appExpansion1.setPackageId(app.getPackageId());
		appExpansion1.setExpansionIndex(expansionPlan.getMemoryExpansionIndex());
		appExpansion1.setMaxValue(expansionPlan.getMemoryMaxValue());
		appExpansion1.setGetValue(expansionPlan.getMemoryGetValue());
		appExpansion1.setMinValue(expansionPlan.getMemoryMinValue());
		appExpansion1.setMaxInstance(expansionPlan.getMaxInstance());
		appExpansion1.setMinInstance(expansionPlan.getMinInstance());
		appExpansion1.setAddCount(expansionPlan.getAddCount());
		appExpansion1.setReduceCount(expansionPlan.getReduceCount());
		
		appExpansion2.setAppId(appId);
		appExpansion2.setIsDel(0);
		appExpansion2.setPackageId(app.getPackageId());
		appExpansion2.setExpansionIndex(expansionPlan.getCpuExpansionIndex());
		appExpansion2.setMaxValue(expansionPlan.getCpuMaxValue());
		appExpansion2.setGetValue(expansionPlan.getCpuGetValue());
		appExpansion2.setMinValue(expansionPlan.getCpuMinValue());
		appExpansion2.setMaxInstance(expansionPlan.getMaxInstance());
		appExpansion2.setMinInstance(expansionPlan.getMinInstance());
		appExpansion2.setAddCount(expansionPlan.getAddCount());
		appExpansion2.setReduceCount(expansionPlan.getReduceCount());
		
		appExpansionService.addAppExpansion(appExpansion1);
		appExpansionService.addAppExpansion(appExpansion2);
		
		
		//modelAndView.setViewName("/app/app-manager.jsp");
	    //return modelAndView;
	}
	
	
	/**
	 * 获取应用的状态
	 * @param projectSpell
	 * @param appSell
	 * @return
	 */
	private int getAppStatus(String projectSpell,String appSell){
		try {
			PodList pods = kubernetesApiClient.selectRcPods(projectSpell, appSell);
			if(pods!=null&&pods.getItems().size()>0){
				return 1;
			}else{
				return 2;
			}
		} catch (KubernetesClientException e) {
			return 2;
		}
	}
	/**
	 * 
	 * @param projId
	 * @param versionNo
	 * @return
	 */
	@RequestMapping(value="/versionNo/{projId}/{versionNo}", method={RequestMethod.GET})
	public ModelAndView projectToAppEdit(@PathVariable int projId,@PathVariable String versionNo){
		ModelAndView modelAndView = new ModelAndView();
		Project project = new Project();
		project.setIsDel(0);
		modelAndView.addObject("project", projectService.queryProject(project));
		modelAndView.addObject("curproject", projectService.getProject(projId));
		Map map = new HashMap();
		map.put("projectId", projId);
		int packageId=projectPackageService.queryProjectPackageByVersionNo(versionNo);
		modelAndView.addObject("projectPackges", projectPackageService.queryProjectPackage(map));
		modelAndView.addObject("curPackage", projectPackageService.queryProjectPackageById(packageId));

		Map<String, Object> configMap = new HashMap<String, Object>();
		modelAndView.addObject("containerconfig",containerConfigService.queryContainerConfig(configMap) );

		modelAndView.setViewName("/app/app-edit.jsp");
		return modelAndView;
	}
	
	@RequestMapping(value="/versionNo/{projId}", method={RequestMethod.GET})
	public ModelAndView projectToAppEditAnother(@PathVariable int projId){
		ModelAndView modelAndView = new ModelAndView();
		Project project = new Project();
		project.setIsDel(0);
		modelAndView.addObject("project", projectService.queryProject(project));
		modelAndView.addObject("curproject", projectService.getProject(projId));
		Map map = new HashMap();
		map.put("projectId", projId);
		modelAndView.addObject("projectPackges", projectPackageService.queryProjectPackage(map));
		Map<String, Object> configMap = new HashMap<String, Object>();
		modelAndView.addObject("containerconfig",containerConfigService.queryContainerConfig(configMap) );

		modelAndView.setViewName("/app/app-edit.jsp");
		return modelAndView;
	}
	
	/**
	 * 可能删除异常，需要定时清理
	 * @param i
	 * @param appId
	 * @param projectSpell
	 * @param appSpell
	 * @param cd
	 * @param rc
	 */
	public void recover(int i,int appId,String projectSpell,String appSpell,ConditionDo cd,ReplicationController rc)
	{
		switch(i) 
		{     
		    case 1:
		       try {
					expansionClient.insertcondition(cd);
				  } catch (Exception e) {
					e.printStackTrace();
				  }   
		    case 2:
		       try {
					kubernetesApiClient.createReplicationController(projectSpell, rc);
				   } catch (KubernetesClientException e) {
					e.printStackTrace();
				   } 
		    case 3:
		       appFacade.recoverApp(appId); 
			   break;   	  
		 } 
	}
	
	
}
