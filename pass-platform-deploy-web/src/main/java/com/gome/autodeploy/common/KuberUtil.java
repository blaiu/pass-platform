/**
 * 
 */
package com.gome.autodeploy.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gome.passplatform.kubernetes.KuberCommon;
import com.gome.passplatform.kubernetes.exp.ConditionDo;
import com.gome.passplatform.kubernetes.exp.ExpConstat;
import com.gome.passplatform.kubernetes.exp.MemConditionDo;
import com.gome.passplatform.kubernetes.modelv1.Container;
import com.gome.passplatform.kubernetes.modelv1.ContainerPort;
import com.gome.passplatform.kubernetes.modelv1.HostPathVolumeSource;
import com.gome.passplatform.kubernetes.modelv1.Limit;
import com.gome.passplatform.kubernetes.modelv1.LocalObjectReference;
import com.gome.passplatform.kubernetes.modelv1.Namespace;
import com.gome.passplatform.kubernetes.modelv1.ObjectMeta;
import com.gome.passplatform.kubernetes.modelv1.PodSpec;
import com.gome.passplatform.kubernetes.modelv1.PodTemplateSpec;
import com.gome.passplatform.kubernetes.modelv1.RBDVolumeSource;
import com.gome.passplatform.kubernetes.modelv1.ReplicationController;
import com.gome.passplatform.kubernetes.modelv1.ReplicationControllerSpec;
import com.gome.passplatform.kubernetes.modelv1.ResourceRequirements;
import com.gome.passplatform.kubernetes.modelv1.Selector;
import com.gome.passplatform.kubernetes.modelv1.Service;
import com.gome.passplatform.kubernetes.modelv1.ServicePort;
import com.gome.passplatform.kubernetes.modelv1.ServiceSpec;
import com.gome.passplatform.kubernetes.modelv1.Volume;
import com.gome.passplatform.kubernetes.modelv1.VolumeMount;

/**
 * @author bailu-ds
 *
 */
public class KuberUtil {

	
	public static ReplicationController createReplicationController(String name, String namespace, String image, String tag, String lable, String cpu, String memory, int replicas, int continerPort) {
		return createReplicationController(name, namespace, image, tag, lable, cpu, memory, replicas, continerPort, null);
	}
	
	@SuppressWarnings("unchecked")
	public static ReplicationController createReplicationController(String name, String namespace, String image, String tag, String lable, String cpu, String memory, int replicas, int continerPort, Map<String, Object> map) {
		
		ReplicationController controller = new ReplicationController();
		controller.setApiVersion(KuberCommon.version);
		controller.setKind(KuberCommon.rc_kind);
		
		ObjectMeta metadata = new ObjectMeta();
		metadata.setName(KuberCommon.rc_prefix + name);
		
		Map<String, String> labels = new HashMap<String, String>();
		labels.put("name", KuberCommon.rc_label_prefix + lable);

		metadata.setLabels(labels);
		
		ReplicationControllerSpec spec = new ReplicationControllerSpec();
		spec.setReplicas(replicas);
		
		Selector selector = new Selector();
		selector.setName(KuberCommon.rc_label_prefix + lable);
		spec.setSelector(selector);
		
		PodTemplateSpec template = new PodTemplateSpec();
		ObjectMeta templatemetadata = new ObjectMeta();
		Map<String, String> templatelabels = new HashMap<String, String>();
		templatelabels.put("name", KuberCommon.rc_label_prefix + lable);
		templatemetadata.setLabels(templatelabels);
		templatemetadata.setName(KuberCommon.template_prefix + lable);
		template.setMetadata(templatemetadata);
		
		List<VolumeMount> volumeMounts = null;
		List<Volume> volumess = null; 
		if (map != null ) {
			volumeMounts = (List<VolumeMount>) map.get("vm");
			volumess = (List<Volume>) map.get("m");
		}
		PodSpec podspec = new PodSpec();
		
		
		if (null != volumess && volumess.size() > 0) {
			podspec.setVolumes(volumess);
		} else {
			Volume volumes = new Volume();
			volumes.setName("log");
			HostPathVolumeSource hostPath = new HostPathVolumeSource();
			hostPath.setPath("/export/Logs/data/rc-lable-" + lable);
			volumes.setHostPath(hostPath);
			podspec.setVolumes(Collections.singletonList(volumes));
		}
		
		
		Container containers = new Container(); 
		containers.setName(KuberCommon.container_prefix + name);
		if (!StringUtils.isEmpty(tag)) {
			image = KuberCommon.registry + image + ":" + tag;
		} else {
			image = KuberCommon.registry + image;
		}
		containers.setImage(image);
		
		ContainerPort ports = new ContainerPort();
		ports.setProtocol("TCP");
		ports.setName("http-port");
		ports.setContainerPort(continerPort);
		containers.setPorts(Collections.singletonList(ports));
		
		if (null != volumeMounts && volumeMounts.size() > 0) {
			containers.setVolumeMounts(volumeMounts);
		}
		
		ResourceRequirements resources = new ResourceRequirements();
		Limit limits = new Limit();
		limits.setCpu(cpu);
		limits.setMemory(memory + KuberCommon.memory_unit);
		resources.setLimits(limits);
		containers.setResources(resources);
		containers.setImagePullPolicy("Always");
		
		podspec.setContainers(Collections.singletonList(containers));
		
		template.setSpec(podspec);
		spec.setTemplate(template);
		
		controller.setMetadata(metadata);
		controller.setSpec(spec);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(System.out, controller);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return controller;
	}
	
	/**
	 * 
	 * @param volumes ep: [volumesName,mountPath,image,readOnly]
	 * @param monitors
	 * @param pool
	 * @param image
	 * @param secretName
	 * @param fsType
	 * @param readOnly
	 * @return
	 */
	public static Map<String, Object> createVolumes(List<String> volumes, String monitors, String pool, String user, String keyring, String secretName, String fsType) {
		List<VolumeMount> volumeMounts = new ArrayList<VolumeMount>();
		List<Volume> volumess= new ArrayList<Volume>();
		if (null != volumes && volumes.size() > 0) {
			for (String str : volumes) {
				if (null != str) {
					String[] strs = str.split(",");
					if (strs.length == 4) {
						VolumeMount volumeMount = new VolumeMount();
						volumeMount.setMountPath(strs[1]);
						volumeMount.setName(KuberCommon.volume_prefix + strs[0]);
						volumeMount.setReadOnly(Boolean.valueOf(strs[3]));
						volumeMounts.add(volumeMount);
						Volume volume = new Volume();
						volume.setName(KuberCommon.volume_prefix + strs[0]);
						
						RBDVolumeSource rbd = new RBDVolumeSource();
						rbd.setMonitors(Arrays.asList(monitors));
						rbd.setPool(pool);
						rbd.setImage(strs[2]);
						rbd.setUser(user);
						rbd.setKeyring(keyring);
						LocalObjectReference secretRef = new LocalObjectReference();
						secretRef.setName(secretName);
						rbd.setSecretRef(secretRef);
						rbd.setFsType(fsType);
						rbd.setReadOnly(Boolean.valueOf(strs[3]));
						volume.setRbd(rbd);
						
						volumess.add(volume);
					}
				}
			} 
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("vm", volumeMounts);
		map.put("m", volumess);
		return map;
	}
	
	public static Service createService(String name, String lable, int port, int nodePort, String selectorName) {
		
		Service service = new Service();
		
		ObjectMeta meta = new ObjectMeta();
		meta.setName(KuberCommon.service_prefix + name);
		
		Map<String, String> labels = new HashMap<String, String>();
		labels.put("name", KuberCommon.service_lable_prefix + lable);
		
		meta.setLabels(labels);
		service.setMetadata(meta);
		
		ServiceSpec spec = new ServiceSpec();
		
		ServicePort ports = new ServicePort();
		ports.setProtocol("TCP");
		ports.setPort(port);
		ports.setTargetPort(port);
		if (nodePort > 30000) {
			ports.setNodePort(nodePort);
		}
		spec.setPorts(Collections.singletonList(ports));
		spec.setType("NodePort");
		
		Selector selector = new Selector();
		selector.setName(KuberCommon.selector_prefix + selectorName);
		spec.setSelector(selector);
		
		service.setSpec(spec);
		return service;
	}
	
//	public static ServiceAccount createServiceAccount() {
//		ServiceAccount serviceAccount = new ServiceAccount();
//		
//		ObjectMeta meta = new ObjectMeta();
//		meta.setName("gomeaccount");
//		
//	}
	
	public static Namespace createNamespace(String namespace) {
		Namespace namespace2 = new Namespace();
		
		ObjectMeta meta = new ObjectMeta();
		meta.setName(namespace);
		
		Map<String, String> labels = new HashMap<String, String>();
		labels.put("name", KuberCommon.namespace_lable_prefix + namespace);
		
		meta.setLabels(labels);
		namespace2.setMetadata(meta);
		return namespace2;
	}
	
	public static ConditionDo createExpansionCondition(String lables, String namespace, String rcname, Double memMin, Double memMax, Double cpuMin, Double cpuMax, int caseMin, int caseMax, int addCase, int subCase) {
		ConditionDo conditionDo = new ConditionDo();
    	conditionDo.setLabels(KuberCommon.rc_label_prefix + lables);
    	
    	conditionDo.setNamespace(namespace);
    	conditionDo.setMaxcount(caseMax);
    	conditionDo.setMincount(caseMin);
    	
    	conditionDo.setRcname(KuberCommon.rc_prefix + rcname);
    	conditionDo.setAddcountpertime(addCase);
    	conditionDo.setSubcountpertime(subCase);
    	
    	MemConditionDo memorycondition = new MemConditionDo();
    	memorycondition.setMaxvalue(memMax);
    	memorycondition.setMinvalue(memMin);
    	MemConditionDo cpuorycondition = new MemConditionDo();
    	memorycondition.setMaxvalue(cpuMax);
    	memorycondition.setMinvalue(cpuMin);
    	
    	Map<String, MemConditionDo> map = new HashMap<String, MemConditionDo>();
    	map.put(ExpConstat.CPU_USEAGE, cpuorycondition);
    	map.put(ExpConstat.MEM_USEAGE, memorycondition);
    	conditionDo.setConditionmaps(map);
    	return conditionDo;
	}
	
	
	
	
	
//	public static void main(String[] args) throws KubernetesClientException {
//		final String KUBERNETES_API_ENDPOINT = "http://10.58.56.65:8080/api/v1/";
//		KubernetesApiClient client = new KubernetesApiClient(KUBERNETES_API_ENDPOINT); 
////		String msg = "";
//		List<String> list = new ArrayList<String>();
//		list.add("/mnt/app/true");
//		Map<String, Object> map = KuberUtil.createVolumes("cctv", list, "10.58.56.33:6789", "rbd", "admin", "ceph-admin-secret", "ext4");
//		KuberUtil.createReplicationController("cctv", "default", "httpd", "", "cctv", "1", "256", 3, 80, map);
//		msg += client.createReplicationController("cctv", KuberUtil.createReplicationController("cctv94", "cctv", "httpd", null, "cctv94", "1", "64", 1));
		
//		msg = client.updateReplicationController("", "replicationcontroller-name-cctv8", 4, "registry.hub.gome.com.cn/httpd");
//		msg = client.deleteReplicationController("", "replicationcontroller-name-cctv");
		
//		client.createService("cctv", KuberUtil.createService("cctv94", "cctv94", 888, -1, "cctv94"));
		
//		client.deleteReplicationController("cctv", "cctv94");
		
//		msg = client.createNamespace(KuberUtil.createNamespace("gomeyun"));
		
//		PodList list = client.getAllPods("");
		
//		client.getRcPods("cscs", "cstp");
//		client.selectRcPods("jenkins", "jenkinscs");
		
//		msg = client.deleteReplicationController("cscs", "replicationcontroller-name-shuang");
		
		/*
		 * 
		 * 
		 * replicationcontroller-name-baidu-rea82    1/1       Running   0          1h
//replicationcontroller-name-cstp-gvz4j     0/1       Pending   0          41m
//replicationcontroller-name-cstp-rrymt     0/1       Pending   0          41m
//replicationcontroller-name-cstp-s1uuh     0/1       Pending   0          41m
//replicationcontroller-name-shuang-tdy1i   0/1       Pending   0          7m
replicationcontroller-name-tdtdtd-aeacl   1/1       Running   0          1h
replicationcontroller-name-tpfw-3qf1v     0/1       Pending   0          46m 
replicationcontroller-name-tpfw-5m0w2     0/1       Pending   0          46m
replicationcontroller-name-tpfw-qxq28     0/1       Pending   0          46m
replicationcontroller-name-tpfw-xrwsm     0/1       Pending   0          46m
replicationcontroller-name-yang-edxea
		 * 
		 * 
		 * */
		
//		msg = client.deletePod("cscs", "replicationcontroller-name-fsdf-38j44");
//		client.
		
//		System.out.println(msg);
//	}
	
}
