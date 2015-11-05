package com.gome.passplatform.kubernetes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.jackson.JacksonFeature;

import com.gome.passplatform.kubernetes.exceptions.KubernetesClientException;
import com.gome.passplatform.kubernetes.interfaces.KubernetesAPIClientInterface;
import com.gome.passplatform.kubernetes.modelv1.Data;
import com.gome.passplatform.kubernetes.modelv1.Namespace;
import com.gome.passplatform.kubernetes.modelv1.ObjectMeta;
import com.gome.passplatform.kubernetes.modelv1.Pod;
import com.gome.passplatform.kubernetes.modelv1.PodList;
import com.gome.passplatform.kubernetes.modelv1.ReplicationController;
import com.gome.passplatform.kubernetes.modelv1.ReplicationControllerList;
import com.gome.passplatform.kubernetes.modelv1.Secret;
import com.gome.passplatform.kubernetes.modelv1.Service;
import com.gome.passplatform.kubernetes.modelv1.ServiceAccount;
import com.gome.passplatform.kubernetes.modelv1.ServiceAccountList;
import com.gome.passplatform.kubernetes.modelv1.ServiceList;


public class KubernetesApiClient implements KubernetesAPIClientInterface {
	
	private String defaultNamespaces = "namespaces/default/";
	private String endpointUrl;
	private String registryDomain;
	private String cephMonitors;
	private String cephPool;
	private String cephUser;
	private String cephSecretRef;
	private String keyring;
	private String cephKey;
	

	public String getCephKey() {
		return cephKey;
	}

	public void setCephKey(String cephKey) {
		this.cephKey = cephKey;
	}

	private static final ThreadLocal<Client> tl = new ThreadLocal<Client>();
	
	public KubernetesApiClient() {
	}
	
	public KubernetesApiClient(String endpointUrl) {
		this.endpointUrl = endpointUrl;
	}
	
	private String getNamespaces(String namespaces) {
		if (StringUtils.isEmpty(namespaces)) {
			namespaces = defaultNamespaces;
		} else {
			if(!namespaces.contains("namespaces/")) {
				namespaces = "namespaces/" + namespaces +"/";
			}
		}
		return namespaces;
	}
	
	@Override
	public boolean createSecret(String namespaces) throws KubernetesClientException {
		Secret secret = new Secret();
		secret.setKind("Secret");
		secret.setType("Opaque");
		ObjectMeta metadata = new ObjectMeta();
		metadata.setName("ceph-admin-secret");
		metadata.setNamespace(namespaces);
		
		secret.setMetadata(metadata);
		Data data = new Data();
		data.setKey(cephKey);
		secret.setData(data);
		
		try {
			Client client = getClient();
			client.register(JacksonFeature.class);
			String uri = String.format(endpointUrl + "%1$s", "namespaces/"+namespaces+"/secrets");
			WebTarget target = client.target(uri);
			Response response = target.request().buildPost(Entity.entity(secret, MediaType.APPLICATION_JSON)).invoke();
			return postOK("namespaces", secret.getMetadata().getName(), response);
		} catch (KubernetesClientException e) {
			throw e;
		} catch (Exception e) {
			String msg = "Error while creating the Secret: "+ secret.getMetadata().getName();
			throw new KubernetesClientException(msg, e);
		}
	}
	
	public static Client getClient() {
		Client client = tl.get();
		if (null == client) {
			client = ClientBuilder.newClient();
			tl.set(client);
		}
		return client;
	}
	
	@Override
	public boolean createNamespace(Namespace namespaces) throws KubernetesClientException {
		try {
			Client client = getClient();
			client.register(JacksonFeature.class);
			String uri = String.format(endpointUrl + "%1$s%2$s", "namespaces/", "");
			WebTarget target = client.target(uri);
			Response response = target.request().buildPost(Entity.entity(namespaces, MediaType.APPLICATION_JSON)).invoke();
			postOK("namespaces", namespaces.getMetadata().getName(), response);
			return createSecret(namespaces.getMetadata().getName());
		} catch (KubernetesClientException e) {
			throw e;
		} catch (Exception e) {
			String msg = "Error while creating the Service: "+ namespaces.getMetadata().getName();
			throw new KubernetesClientException(msg, e);
		}
	}
	
	public Pod getPod(String namespaces, String podId) throws KubernetesClientException{
		try {
			podId = KuberCommon.pod_prefix + podId;
			Client client = getClient();
			client.register(JacksonFeature.class);
			String uri = String.format(endpointUrl + "%1$s%2$s%3$s", getNamespaces(namespaces), "pods/", podId);
			WebTarget target = client.target(uri);
			Response response = target.request().get();
			getOK("Pod", podId, response);
			return response.readEntity(Pod.class);
		} catch (KubernetesClientException e) {
            throw e;
        } catch (Exception e) {
			String msg = "Error while retrieving Pod info with Pod ID: " +podId;
			throw new KubernetesClientException(msg, e);
		}
	}
	
	public PodList getAllPods(String namespaces) throws KubernetesClientException {
		try {
			Client client = getClient();
			client.register(JacksonFeature.class);
			String uri = String.format(endpointUrl + "%1$s%2$s", getNamespaces(namespaces), "pods/");
			WebTarget target = client.target(uri);
			Response response = target.request().get();
			getOK("Pod", "", response);
			return response.readEntity(new GenericType<PodList>(PodList.class) {});
		} catch (Exception e) {
			String msg = "Error while retrieving Pods.";
			throw new KubernetesClientException(msg, e);
		}
	}
	
	public Pod createPod(String namespaces, Pod pod) throws KubernetesClientException {
		try {
			Client client = getClient();
			client.register(JacksonFeature.class);
			String uri = String.format(endpointUrl + "%1$s%2$s", getNamespaces(namespaces), "pods/");
			WebTarget target = client.target(uri);
			Response response = target.request().buildPost(Entity.entity(pod, MediaType.APPLICATION_JSON)).invoke();
			postOK("Pod", pod.getMetadata().getName(), response);
			return response.readEntity(Pod.class);
		} catch (KubernetesClientException e) {
			throw e;
		} catch (Exception e) {
			String msg = "Error while creating Pod: "+pod;
			throw new KubernetesClientException(msg, e);
		}
	}
	
	public String deletePod(String namespaces, String podId) throws KubernetesClientException {
		try {
			podId = KuberCommon.pod_prefix + podId;
			Client client = getClient();
			client.register(JacksonFeature.class);
			String uri = String.format(endpointUrl + "%1$s%2$s%3$s", getNamespaces(namespaces), "pods/", podId);
			WebTarget target = client.target(uri);
			Response response = target.request().delete();
			getOK("Pod", podId, response);
			return getResult(response);
		} catch (KubernetesClientException e) {
			throw e;
		} catch (Exception e) {
			String msg = "Error while retrieving Pod info of Pod ID: "+podId;
			throw new KubernetesClientException(msg, e);
		}
	}
	
	private String deletePods(String namespaces, String podId) throws KubernetesClientException {
		try {
			Client client = getClient();
			client.register(JacksonFeature.class);
			String uri = String.format(endpointUrl + "%1$s%2$s%3$s", getNamespaces(namespaces), "pods/", podId);
			WebTarget target = client.target(uri);
			Response response = target.request().delete();
			getOK("Pod", podId, response);
			return getResult(response);
		} catch (KubernetesClientException e) {
			throw e;
		} catch (Exception e) {
			String msg = "Error while retrieving Pod info of Pod ID: " + podId;
			throw new KubernetesClientException(msg, e);
		}
	}
	
	public ReplicationController getReplicationController(String namespaces, String controllerId) throws KubernetesClientException{
		try {
			controllerId = KuberCommon.rc_prefix + controllerId;
			Client client = getClient();
			client.register(JacksonFeature.class);
			String uri = String.format(endpointUrl + "%1$s%2$s%3$s", getNamespaces(namespaces), "replicationcontrollers/", controllerId);
			WebTarget target = client.target(uri);
			Response response = target.request().get();
			getOK("Replication Controller", controllerId, response);
			return response.readEntity(ReplicationController.class);
		} catch (KubernetesClientException e) {
            throw e;
        } catch (Exception e) {
        	String msg = "Error while retrieving Replication Controller info with ID: "+controllerId;
			throw new KubernetesClientException(msg, e);
		}
	}

	public ReplicationControllerList getAllReplicationControllers(String namespaces) throws KubernetesClientException {
		try {
			Client client = getClient();
			client.register(JacksonFeature.class);
			String uri = String.format(endpointUrl + "%1$s%2$s", getNamespaces(namespaces), "replicationcontrollers/");
			WebTarget target = client.target(uri);
			Response response = target.request().get();
			getOK("Replication Controller", "", response);
			return response.readEntity(new GenericType<ReplicationControllerList>(ReplicationControllerList.class) {});
		} catch (Exception e) {
			String msg = "Error while retrieving Replication Controllers.";
			throw new KubernetesClientException(msg, e);
		}
	}
	
	public ReplicationController createReplicationController(String namespaces, ReplicationController controller) throws KubernetesClientException {
		try {
			if (createNamespace(createNamespaces(namespaces))) {
				Client client = getClient();
				client.register(JacksonFeature.class);
				String uri = String.format(endpointUrl + "%1$s%2$s", getNamespaces(namespaces), "replicationcontrollers");
				WebTarget target = client.target(uri);
				Response response = target.request().buildPost(Entity.entity(controller, MediaType.APPLICATION_JSON)).invoke();
				postOK("Replication Controller", controller.getMetadata().getName(), response);
				return response.readEntity(ReplicationController.class);
			}
			throw new KubernetesClientException("create Replication Controller error: namespace [" + namespaces + "] not found");
		} catch (KubernetesClientException e) {
			throw e;
		} catch (Exception e) {
			String msg = "Error while creating Replication Controller: " + controller;
			throw new KubernetesClientException(msg, e);
		}
	}
	
    public ReplicationController updateReplicationController(String namespaces, String controllerId, int replicas, String imageName, String tag) throws KubernetesClientException {
    	ReplicationController controller = getReplicationController(getNamespaces(namespaces), controllerId);
    	controllerId = KuberCommon.rc_prefix + controllerId;
    	try {
    		if (replicas >= 0) {
    			controller.getSpec().setReplicas(replicas);
    		}
    		if (!StringUtils.isEmpty(imageName)) {
    			imageName = KuberCommon.registry + imageName;
    			if (!StringUtils.isEmpty(tag)) {
    				imageName = imageName + ":" + tag;
    				controller.getSpec().getTemplate().getSpec().getContainers().get(0).setImage(imageName);
    			} else {
    				controller.getSpec().getTemplate().getSpec().getContainers().get(0).setImage(imageName);
    			}
    		}
    		Client client = getClient();
			client.register(JacksonFeature.class);
			String uri = String.format(endpointUrl + "%1$s%2$s", getNamespaces(namespaces), "replicationcontrollers/" + controllerId);
			WebTarget target = client.target(uri);
			Response response = target.request().put(Entity.entity(controller, MediaType.APPLICATION_JSON));
			getOK("Replication Controller", controller.getMetadata().getName(), response);
			return response.readEntity(ReplicationController.class);
    	} catch (KubernetesClientException e) {
    		throw e;
    	} catch (Exception e) {
    		String msg = "Error while updating Replication Controller: " + controller;
    		throw new KubernetesClientException(msg, e);
    	}
    }
	
	public String deleteReplicationController(String namespaces, String controllerId) throws KubernetesClientException {
		try {
			String selectorName = KuberCommon.selector_prefix + controllerId;
			String serviceName = KuberCommon.service_prefix + controllerId;
			controllerId = KuberCommon.rc_prefix + controllerId;
			PodList pods = selectPodsBySelectorName(namespaces, selectorName);
			Client client = getClient();
			client.register(JacksonFeature.class);
			String uri = String.format(endpointUrl + "%1$s%2$s%3$s", getNamespaces(namespaces), "replicationcontrollers/", controllerId);
			WebTarget target = client.target(uri);
			Response response = target.request().delete();
			getOK("Replication Controller", controllerId, response);
			String msg = getResult(response);
			
			if (null != pods && pods.getItems().size() > 0) {
				for (Pod pod : pods.getItems()) {
					deletePods(namespaces, pod.getMetadata().getName());
				}
			}
			deleteServices(namespaces, serviceName);
			return msg;
		} catch (KubernetesClientException e) {
			throw e;
		} catch (Exception e) {
			String msg = "Error while retrieving Replication Controller info of Controller ID: "+controllerId;
			throw new KubernetesClientException(msg, e);
		}
	}
	
	public Service getService(String namespaces, String serviceId) throws KubernetesClientException {
		try {
			serviceId = KuberCommon.service_prefix + serviceId;
			Client client = getClient();
			client.register(JacksonFeature.class);
			String uri = String.format(endpointUrl + "%1$s%2$s%3$s", getNamespaces(namespaces), "services/", serviceId);
			WebTarget target = client.target(uri);
			Response response = target.request().get();
			getOK("Service", serviceId, response);
			return response.readEntity(Service.class);
		} catch (KubernetesClientException e) {
			throw e;
		} catch (Exception e) {
			String msg = "Error while retrieving Service info with Service ID: "+serviceId;
			throw new KubernetesClientException(msg, e);
		}
	}
	
	public ServiceList getAllServices(String namespaces) throws KubernetesClientException {
		try {
			Client client = getClient();
			client.register(JacksonFeature.class);
			String uri = String.format(endpointUrl + "%1$s%2$s", getNamespaces(namespaces), "services/");
			WebTarget target = client.target(uri);
			Response response = target.request().get();
			getOK("Service", "", response);
			return response.readEntity(new GenericType<ServiceList>(ServiceList.class) {});
		} catch (Exception e) {
			String msg = "Error while retrieving Services.";
			throw new KubernetesClientException(msg, e);
		}
	}
	
	public Service createService(String namespaces, Service service) throws KubernetesClientException {
		try {
			Client client = getClient();
			client.register(JacksonFeature.class);
			String uri = String.format(endpointUrl + "%1$s%2$s", getNamespaces(namespaces), "services/");
			WebTarget target = client.target(uri);
			Response response = target.request().buildPost(Entity.entity(service, MediaType.APPLICATION_JSON)).invoke();
			postOK("service", service.getMetadata().getName(), response);
			return response.readEntity(Service.class);
		} catch (KubernetesClientException e) {
			throw e;
		} catch (Exception e) {
			String msg = "Error while creating the Service: "+service;
			throw new KubernetesClientException(msg, e);
		}
	}
	
	public String deleteService(String namespaces, String serviceId) throws KubernetesClientException {
		try {
			serviceId = KuberCommon.service_prefix + serviceId;
			Client client = getClient();
			client.register(JacksonFeature.class);
			String uri = String.format(endpointUrl + "%1$s%2$s%3$s", getNamespaces(namespaces), "services/", serviceId);
			WebTarget target = client.target(uri);
			Response response = target.request().delete();
			getOK ("Service", serviceId, response);
			return getResult(response);
		} catch (KubernetesClientException e) {
			throw e;
		} catch (Exception e) {
			String msg = "Error while retrieving Service info of Service ID: "+serviceId;
			throw new KubernetesClientException(msg, e);
		}
	}
	
	private String deleteServices(String namespaces, String serviceId) throws KubernetesClientException {
		try {
			Client client = getClient();
			client.register(JacksonFeature.class);
			String uri = String.format(endpointUrl + "%1$s%2$s%3$s", getNamespaces(namespaces), "services/", serviceId);
			WebTarget target = client.target(uri);
			Response response = target.request().delete();
			getOK ("Service", serviceId, response);
			return getResult(response);
		} catch (KubernetesClientException e) {
			throw e;
		} catch (Exception e) {
			String msg = "Error while retrieving Service info of Service ID: "+serviceId;
			throw new KubernetesClientException(msg, e);
		}
	}
    
    public PodList getSelectedPods(String namespaces, Map<String, String> label) throws KubernetesClientException {
    	try {
    		String labelQuery = getLabelQuery(label);
    		Client client = getClient();
    		client.register(JacksonFeature.class);
    		String uri = String.format(endpointUrl + "%1$s%2$s%3$s", getNamespaces(namespaces), "pods/", "");
    		WebTarget target = client.target(uri).queryParam("labels", labelQuery);
    		Response response = target.request().get();
    		return response.readEntity(new GenericType<PodList>(PodList.class) {});
    	} catch (Exception e) {
    		throw new KubernetesClientException(e);
    	}
    }
    
    private String getLabelQuery(Map<String, String> label) {
        String query = "";
        if (null != label) {
        	
        	Iterator<Map.Entry<String, String>> it = label.entrySet().iterator();
        	while(it.hasNext()) {
        		Map.Entry<String, String> me = it.next();
        		query = query.concat(me.getKey() + "=" + me.getValue());
        	}
        }
//        
//        for (Label l : label) {
//            query = query.concat("name="+l.getName()+",");
//        }
        return query.endsWith(",") ? query.substring(0, query.length()-1) : query;
    }
    
    private String getResult(Response response) {
    	StringBuilder stringBuilder = new StringBuilder();
    	stringBuilder.append("Response Status: " + response.getStatus() + "\n");
    	stringBuilder.append("Response StatusInfo: " + response.getStatusInfo().toString()+ "\n");
    	return stringBuilder.toString();
    }
    
    public boolean postOK(String type, String message, Response response) throws KubernetesClientException {
    	if (response == null) {
            throw new KubernetesClientException(message);
        }
    	switch (response.getStatus()) {
		case 201:
			return true;
		case 409:
			return true;
		default:
			throw new KubernetesClientException(response.getStatus() + "");
		}
    }
    
    public boolean getOK(String type, String message, Response response) throws KubernetesClientException {
    	if (response == null) {
            throw new KubernetesClientException(message);
        }
    	
    	switch (response.getStatus()) {
		case 200:
			return true;
		default:
			throw new KubernetesClientException(response.getStatus() + "");
		}
    }

	@Override
	public ServiceAccount getServiceAccount(String namespaces, String serviceaccountId) throws KubernetesClientException {
		try {
			serviceaccountId = KuberCommon.serviceaccount_prefix + serviceaccountId;
			Client client = getClient();
			client.register(JacksonFeature.class);
			String uri = String.format(endpointUrl + "%1$s%2$s%3$s", getNamespaces(namespaces), "serviceaccounts/", serviceaccountId);
			System.out.println(uri);
			WebTarget target = client.target(uri);
			Response response = target.request().get();
			getOK("Serviceaccount", serviceaccountId, response);
			return response.readEntity(ServiceAccount.class);
		} catch (KubernetesClientException e) {
            throw e;
        } catch (Exception e) {
			String msg = "Error while retrieving Serviceaccount info with Serviceaccount ID: " + serviceaccountId;
			throw new KubernetesClientException(msg, e);
		}
	}

	@Override
	public ServiceAccountList getAllServiceAccounts(String namespaces) throws KubernetesClientException {
		try {
			Client client = getClient();
			client.register(JacksonFeature.class);
			String uri = String.format(endpointUrl + "%1$s%2$s", getNamespaces(namespaces), "serviceaccounts/");
			WebTarget target = client.target(uri);
			Response response = target.request().get();
			getOK("Serviceaccount", "", response);
			return response.readEntity(new GenericType<ServiceAccountList>(ServiceAccountList.class) {});
		} catch (Exception e) {
			String msg = "Error while retrieving Serviceaccount.";
			throw new KubernetesClientException(msg, e);
		}
	}

	@Override
	public ServiceAccount createServiceAccount(String namespaces, ServiceAccount account) throws KubernetesClientException {
		try {
			Client client = getClient();
			client.register(JacksonFeature.class);
			String uri = String.format(endpointUrl + "%1$s%2$s", getNamespaces(namespaces), "serviceaccounts/");
			WebTarget target = client.target(uri);
			Response response = target.request().buildPost(Entity.entity(account, MediaType.APPLICATION_JSON)).invoke();
			postOK("account", account.getMetadata().getName(), response);
			return response.readEntity(ServiceAccount.class);
		} catch (KubernetesClientException e) {
			throw e;
		} catch (Exception e) {
			String msg = "Error while creating ServiceAccount: " + account;
			throw new KubernetesClientException(msg, e);
		}
	}

	@Override
	public String deleteServiceAccount(String namespaces, String accountId) throws KubernetesClientException {
		try {
			accountId = KuberCommon.serviceaccount_prefix + accountId;
			Client client = getClient();
			client.register(JacksonFeature.class);
			String uri = String.format(endpointUrl + "%1$s%2$s%3$s", getNamespaces(namespaces), "serviceaccounts/", accountId);
			WebTarget target = client.target(uri);
			Response response = target.request().delete();
			getOK("account", accountId, response);
			return getResult(response);
		} catch (KubernetesClientException e) {
			throw e;
		} catch (Exception e) {
			String msg = "Error while retrieving ServiceAccount info of Pod ID: " + accountId;
			throw new KubernetesClientException(msg, e);
		}
	}

	@Override
	public PodList getRcPods(String namespaces, String rcName) throws KubernetesClientException {
		rcName = KuberCommon.rc_prefix + rcName;
		PodList list = getAllPods(namespaces);
		if (null != list && list.getItems().size() > 0) {
			List<Pod> listPod = new ArrayList<Pod>();
			for (Pod pod : list.getItems()) {
				if (pod.getMetadata().getName().startsWith(rcName)) {
					listPod.add(pod);
				}
			}
			list.setItems(listPod);
		}
		return list;
	}

	@Override
	public PodList selectRcPods(String namespaces, String selectorName) throws KubernetesClientException {
		try {
			selectorName = KuberCommon.selector_prefix + selectorName;
			Client client = getClient();
			client.register(JacksonFeature.class);
			String uri = String.format(endpointUrl + "%1$s%2$s", getNamespaces(namespaces), "pods?labelSelector=name=" + selectorName);
			WebTarget target = client.target(uri);
			Response response = target.request().get();
			getOK("Pod", "", response);
			return response.readEntity(new GenericType<PodList>(PodList.class) {});
		} catch (Exception e) {
			String msg = "Error while retrieving Pods.";
			throw new KubernetesClientException(msg, e);
		}
	}
	

	private PodList selectPodsBySelectorName(String namespaces, String selectorName) throws KubernetesClientException {
		try {
			Client client = getClient();
			client.register(JacksonFeature.class);
			String uri = String.format(endpointUrl + "%1$s%2$s", getNamespaces(namespaces), "pods?labelSelector=name=" + selectorName);
			WebTarget target = client.target(uri);
			Response response = target.request().get();
			getOK("Pod", "", response);
			return response.readEntity(new GenericType<PodList>(PodList.class) {});
		} catch (Exception e) {
			String msg = "Error while retrieving Pods.";
			throw new KubernetesClientException(msg, e);
		}
	}
	
	public Namespace createNamespaces(String namespace) {
		Namespace namespace2 = new Namespace();
		
		ObjectMeta meta = new ObjectMeta();
		meta.setName(namespace);
		
		Map<String, String> label = new HashMap<String, String>();
		label.put("name", "namespace-" + namespace);
//		Label label = new Label();
//		label.setName("namespace-" + namespace);
		meta.setLabels(label);
		namespace2.setMetadata(meta);
		return namespace2;
	}
	
	public String getDefaultNamespaces() {
		return defaultNamespaces;
	}

	public void setDefaultNamespaces(String defaultNamespaces) {
		this.defaultNamespaces = defaultNamespaces;
	}

	public String getEndpointUrl() {
		return endpointUrl;
	}

	public void setEndpointUrl(String endpointUrl) {
		this.endpointUrl = endpointUrl;
	}

	public String getRegistryDomain() {
		return registryDomain;
	}

	public void setRegistryDomain(String registryDomain) {
		this.registryDomain = registryDomain;
	}
	
	

//	private void ss() {
//      HttpClient httpClient = new HttpClient();  
//      //HttpClient  
//      CloseableHttpClient closeableHttpClient = httpClientBuilder.build();  
//
//      HttpGet httpGet = new HttpGet("http://www.gxnu.edu.cn/default.html");  
//      System.out.println(httpGet.getRequestLine());  
//      try {  
//          //执行get请求  
//          HttpResponse httpResponse = closeableHttpClient.execute(httpGet);  
//          //获取响应消息实体  
//          HttpEntity entity = httpResponse.getEntity();  
//          //响应状态  
//          System.out.println("status:" + httpResponse.getStatusLine());  
//          //判断响应实体是否为空  
//          if (entity != null) {  
//              System.out.println("contentEncoding:" + entity.getContentEncoding());  
//              System.out.println("response content:" + EntityUtils.toString(entity));  
//          }  
//      } catch (IOException e) {  
//          e.printStackTrace();  
//      } finally {  
//          try {      //关闭流并释放资源  
//              closeableHttpClient.close();  
//          } catch (IOException e) {  
//              e.printStackTrace();  
//          }  
//      }  
//  }  
		
		
		
		
		
		
		
		
		
		
		
		
//		try {
//			Client client = ClientBuilder.newClient();
//			client.register(JacksonFeature.class);
//			WebTarget target = client.target(s);
//			Response response = target.request().get();
//			System.out.println(response.accepted(String.class));
//		} catch (Exception e) {
//          throw e;
//      } 
//	}
	
	public String getCephMonitors() {
		return cephMonitors;
	}

	public void setCephMonitors(String cephMonitors) {
		this.cephMonitors = cephMonitors;
	}

	public String getCephPool() {
		return cephPool;
	}

	public void setCephPool(String cephPool) {
		this.cephPool = cephPool;
	}

	public String getCephUser() {
		return cephUser;
	}

	public void setCephUser(String cephUser) {
		this.cephUser = cephUser;
	}

	public String getCephSecretRef() {
		return cephSecretRef;
	}

	public void setCephSecretRef(String cephSecretRef) {
		this.cephSecretRef = cephSecretRef;
	}

	public String getKeyring() {
		return keyring;
	}

	public void setKeyring(String keyring) {
		this.keyring = keyring;
	}



	

//	public static void main(String[] args) throws Exception {
////		String url = "http://10.58.56.47:2375/containers/5d20bd7f31511950e5893e65826f1f8c45549e462aed8fb0cb876de4858895b9/logs?stderr=1&stdout=1&timestamps=1&follow=1";
//		String url = "http://127.0.0.1:8080/jenkins/job/ccapss/lastBuild/consoleText";
//		
//		HttpClient client = new DefaultHttpClient();
//        HttpGet httpGet = new HttpGet(url);  
//        
//        
//        HttpResponse response = client.execute(httpGet);
//        
//		
//        InputStream is = response.getEntity().getContent();
//        
//        BufferedReader br = new BufferedReader(new InputStreamReader(is, "GBK"));
//        
//        
//        String line = "";
//        while (null != (line = br.readLine())) {
//        	System.out.println(line);
//        }
//        
//        
//
//	}
	
	
}
