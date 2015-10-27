package com.gome.autodeploy.web.volume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gome.autodeploy.common.UserMseeage;
import com.gome.autodeploy.domain.app.App;
import com.gome.autodeploy.domain.app.AppExpansion;
import com.gome.autodeploy.domain.auth.User;
import com.gome.autodeploy.domain.project.ProjectPackage;
import com.gome.autodeploy.domain.volume.Volume;
import com.gome.autodeploy.exception.PaasDBException;
import com.gome.autodeploy.service.app.AppService;
import com.gome.autodeploy.service.volume.VolumeService;
import com.gome.autodeploy.web.BaseController;
import com.gome.autodeploy.web.app.AppController;
import com.gome.passplatform.kubernetes.CephClient;
import com.gome.passplatform.kubernetes.exceptions.KubernetesClientException;

@Controller
@RequestMapping(value = "/volume")
public class VolumeController extends BaseController{
	
	private static Logger logger = Logger.getLogger(AppController.class);
	
	@Resource
	private VolumeService volumeService;
	@Resource
	private AppService appService;
	@Resource
	private CephClient cephClient;
	
	@RequestMapping(value="/add", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView addVolume(Volume volume,HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView();
		//User user= UserMseeage.getUserMessages(request);
		
		if(!volumeService.checkExistVolumeName(volume))
		{
			createVolume(volume,request);
		
        modelAndView.setViewName("/volume/volume-edit.jsp");
        
		}else{
			modelAndView.addObject("volume", volume);
			modelAndView.addObject("msg","创建失败，存在相同名称的共享磁盘---"+volume.getVolumeName());
			modelAndView.setViewName("/volume/volume-edit.jsp");
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/add/to", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Volume addToVolume(Volume volume,HttpServletRequest request) {
		
		//ModelAndView modelAndView = new ModelAndView();
		User user= UserMseeage.getUserMessages(request);
		boolean flag = true;
		if(!volumeService.checkExistVolumeName(volume))
		{
			createVolume(volume,request);
        
		}else{
			System.out.println("创建失败，存在相同名称的共享磁盘---"+volume.getVolumeName());
			flag = false;
		}
		
		//List<Volume> list=volumeService.queryVolumeNotUsed(user.getUserId());

		//modelAndView.addObject("list",list);
		//return list;
		return volume;
	
		
	}
	
	@RequestMapping(value="/list", method={RequestMethod.GET})
	public ModelAndView list(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		User user = UserMseeage.getUserMessages(request);
		
		List<Volume> volumeList = volumeService.queryVolumeByUserId(user.getUserId());
		List<App> applist = new ArrayList<App>();
		for(Volume item:volumeList){
			applist.add(appService.queryAppById(item.getAppId()));
		}
		modelAndView.addObject("list",volumeList);
		modelAndView.addObject("applist",applist);
        modelAndView.setViewName("/volume/volume-list.jsp");
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{id}", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView removeVolume(@PathVariable Integer id) {
		
		Volume volume=volumeService.getVolume(id);
		ModelAndView modelAndView = new ModelAndView();
		try {
			
			volumeService.removeVolume(id);
			
			cephClient.removeStorage(volume.getVolumeName());
			
		}catch(PaasDBException e){
			//DB异常需要回滚db操作接口
			
			logger.error(" 删除磁盘内部异常， "+e.getMessage());
			modelAndView.addObject("logMsg", " 新增磁盘内部异常 "+e.getMessage());
		}catch(KubernetesClientException e){
			recoverVolume(id);
			logger.error("  接口调用异常， "+e.getMessage());
			modelAndView.addObject("logMsg", "  接口调用异常， "+e.getMessage());
		}
		
        modelAndView.setViewName("redirect:/volume/list");
		return modelAndView;
	}
	
	@RequestMapping(value="/view/{id}", method={RequestMethod.GET})
	public ModelAndView viewVolume(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("volume",volumeService.getVolume(id));
		
		modelAndView.addObject("app",appService.queryAppById(volumeService.getVolume(id).getAppId()));
		
		modelAndView.setViewName("/volume/volume-edit.jsp");
		return modelAndView;
	}
	
	@RequestMapping(value="/modify/{id}", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView modifyVolume(Volume volume,Integer id) {
		
		Volume volume1=volumeService.getVolume(id);
		ModelAndView modelAndView = new ModelAndView();
	
			try {
				
				volumeService.modifyVolume(volume);

				cephClient.modifyStorage(volume.getVolumeName(), String.valueOf(volume.getVolumeSize()));
				
			}catch(PaasDBException e){
				//DB异常需要回滚db操作接口
				
				logger.error(" 修改磁盘内部异常， "+e.getMessage());
				modelAndView.addObject("logMsg", " 新增磁盘内部异常 "+e.getMessage());
			} catch (KubernetesClientException e) {
				
				recoverModifyVolume(volume1,id);
				logger.error("  接口调用异常， "+e.getMessage());
				modelAndView.addObject("logMsg", "  接口调用异常， "+e.getMessage());
			}
			
		
		modelAndView.setViewName("redirect:/volume/list");
		return modelAndView;
	}
	
	
	@RequestMapping(value="/format/{id}", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView formatVolume(@PathVariable Integer id) {
		
		Volume volume=volumeService.getVolume(id);
		ModelAndView modelAndView = new ModelAndView();
		try {
			
			volumeService.formatVolume(id);
			
			cephClient.formatStorage(volume.getVolumeName());
			
		}catch(PaasDBException e){
			//DB异常需要回滚db操作接口
			
			logger.error(" 格式化磁盘内部异常， "+e.getMessage());
			modelAndView.addObject("logMsg", " 格式化磁盘内部异常 "+e.getMessage());
		}catch(KubernetesClientException e){
			
			recoverFormatVolume(volume,id);
			logger.error("  接口调用异常， "+e.getMessage());
			modelAndView.addObject("logMsg", "  接口调用异常， "+e.getMessage());
		}
		
        modelAndView.setViewName("redirect:/volume/list");
		return modelAndView;
	}
	
	
	@RequestMapping(value="/volume", method={RequestMethod.GET})
	@ResponseBody
	public List<Volume> images(HttpServletRequest request) {
		
		User user= UserMseeage.getUserMessages(request);
		return volumeService.queryVolumeNotUsed(user.getUserId());  
	}
	
	
	/** 
	* @Title: removeVolumeService 
	* @Description: 清除磁盘信息
	* @param volume
	* @throws KubernetesClientException void   
	* @throws 
	*/
	public void removeVolumeService(Volume volume) throws KubernetesClientException{
		
		try {
			cephClient.removeStorage(volume.getVolumeName());
			
		} catch (Exception e) {
			throw new KubernetesClientException(e.getMessage());
		}
	}
	
	/**
	 * 删除后恢复磁盘数据库信息
	 * @param id
	 */
	public void recoverVolume(int id) {
		Volume volume=new Volume();
		volume.setIsDel(0);
		volume.setId(id);
		volumeService.updateVolumeDelFlag(volume);
	}
	
	/**
	 * 修改后恢复磁盘数据库信息
	 * @param volume
	 * @param id
	 */
	public void recoverModifyVolume(Volume volume1,Integer id)
	{
		Volume volume2=new Volume();
		volume2.setId(id);
		volume2.setVolumeName(volume1.getVolumeName());
		volume2.setVolumeSize(volume1.getVolumeSize());
		volumeService.modifyVolume(volume2);
	}
	
	/**
	 * 格式化恢复磁盘数据库信息
	 * @param volume
	 * @param id
	 */
	public void recoverFormatVolume(Volume volume,Integer id)
	{
		Volume volume1=new Volume();
		volume1.setId(id);
		volume1.setAppId(volume.getAppId());
		volumeService.mountVolume(volume1);
	}
	
	public void createVolume(Volume volume,HttpServletRequest request)
	{
		User user= UserMseeage.getUserMessages(request);
        volume.setUserId(user.getUserId());
		
		String name=volume.getVolumeName();
		String size=String.valueOf(volume.getVolumeSize());
		
		try {
			
			cephClient.createStorage(name, size);
			
			volumeService.addVolume(volume);
			
		} catch (KubernetesClientException e) {
			logger.error("  接口调用异常， "+e.getMessage());
		}catch(PaasDBException e){
			//DB异常需要回滚db操作接口
			try {
				removeVolumeService(volume);
			} catch (KubernetesClientException e1) {
				
			}
			
			logger.error(" 新增磁盘内部异常， "+e.getMessage());
	}
	
	}
	
	

}
