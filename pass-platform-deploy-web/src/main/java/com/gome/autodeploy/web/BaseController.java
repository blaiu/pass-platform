/**
 * 
 */
package com.gome.autodeploy.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.PathVariable;

import com.gome.autodeploy.common.DockerFile;
import com.gome.autodeploy.common.FileUtil;
import com.gome.autodeploy.domain.auth.User;
import com.gome.autodeploy.domain.common.Dictionary;
import com.gome.autodeploy.domain.project.ProjectPackage;
import com.gome.autodeploy.service.common.DictionaryService;
import com.gome.autodeploy.service.project.ProjectPackageService;
import com.gome.autodeploy.util.GZIPUtil;
import com.gome.passplatform.docker.DockerImageApiClient;

/**
 * @author bailu-ds
 *
 */
public class BaseController {

	@Resource
	protected MessageSource messageSource;
	
	@Resource
	protected DictionaryService dictionaryService;
	
	
//	public String packagePath = "D:/";
	
	protected List<Dictionary> getDictionarys(int type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		return dictionaryService.getDictionarys(map);
	}
	
	protected Dictionary getDictionary(String key, String value, Integer type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("key", key);
		map.put("type", type);
		map.put("value", value);
		List<Dictionary> list = dictionaryService.getDictionarys(map);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	protected List<Dictionary> getDictionaryList(Map<String, Object> map) {
		return dictionaryService.getDictionarys(map);
	}
	
	static User user = new User();
	
	protected User getUser() {
		user.setUserId(100000); 
		user.setUserName("admin");
		return user;
	}
	
	public String addImage(ProjectPackageService projectPackageService, DockerImageApiClient dockerImageApiClient, Integer projectId, String basicImage,String packageName,String projectSpell,String versionNo,String memo) {
//		SimpleDateFormat format = new SimpleDateFormat("yyyyMMDDHHmmss");
//		String versionNo = format.format(new Date());
		String packPath = dockerImageApiClient.getPackagePath() + File.separator + versionNo + File.separator;
		
		ProjectPackage projectPackage = new ProjectPackage();
		projectPackage.setCreateUser(getUser().getUserName());
		projectPackage.setPackageName(packageName);
		projectPackage.setPackagePath(packPath);
		projectPackage.setProjectId(projectId);
		projectPackage.setBasicImage(basicImage);
		projectPackage.setVersionNo(versionNo);
		projectPackage.setImageName(getUser().getUserName() + "_" + projectSpell);
		projectPackage.setIsDel(0);
		projectPackage.setMemo(memo);
		String dockerFile = DockerFile.getDockerFile();
		dockerFile = String.format(dockerFile, basicImage,  getUser().getUserName(), packageName, dockerImageApiClient.getWebRoot() + packageName);
		FileUtil.writeFile(packPath + "Dockerfile", dockerFile);
		
		File[] sources = new File[] {new File(packPath + packageName), new File(packPath + "Dockerfile")};
		File target = new File(packPath + projectPackage.getImageName() + ".tar.gz");
		GZIPUtil.compress(GZIPUtil.pack(sources, target));
		String returnMsg = "";
		try {
			projectPackageService.addProjectPackage(projectPackage);
			returnMsg = dockerImageApiClient.createImages(projectPackage.getImageName(), target, projectPackage.getVersionNo());
			returnMsg += dockerImageApiClient.pushImages(projectPackage.getImageName(), projectPackage.getVersionNo());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			returnMsg = "Dockerfile 未找到";
			//接口失败，项目包 镜像 设置为删除状态
			Map map = new HashMap();
			map.put("packageId", projectPackage.getPackageId());
			projectPackageService.removeProjectPackage(map);
		} catch(Exception e){
			returnMsg = "操作失败";
		}
		return returnMsg;
	}
	
}
