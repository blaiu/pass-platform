/**
 * 
 */
package com.gome.passplatform.kubernetes;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.jackson.JacksonFeature;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gome.passplatform.kubernetes.exceptions.KubernetesClientException;
import com.gome.passplatform.kubernetes.storage.Storage;

/**
 * @author bailu-ds
 *
 */
public class CephClient {

	private String endpointUrl;
	
	private static final ThreadLocal<Client> tl = new ThreadLocal<Client>();
	
	public CephClient() {
	}

	public String getEndpointUrl() {
		return endpointUrl;
	}

	public void setEndpointUrl(String endpointUrl) {
		this.endpointUrl = endpointUrl;
	}
	
	public static Client getClient() {
		Client client = tl.get();
		if (null == client) {
			client = ClientBuilder.newClient();
			tl.set(client);
		}
		return client;
	}

	public Storage getStorage(String name) throws KubernetesClientException {
		try {
			Client client = getClient();
			client.register(JacksonFeature.class);
			String uri = String.format(endpointUrl + "%1$s", "/api/images/" + name);
			WebTarget target = client.target(uri);
			Response response = target.request().get();
			System.out.println(response.getMetadata().toString());
			valStatus("getStroage", "", response);
			return response.readEntity(Storage.class);
		} catch (Exception e) {
			String msg = "Error while retrieving getStorage.";
			throw new KubernetesClientException(msg, e);
		}
	}
	
	public List<Storage> getStorages() throws KubernetesClientException {
		try {
			Client client = getClient();
			client.register(JacksonFeature.class);
			String uri = String.format(endpointUrl + "%1$s", "/api/images");
			WebTarget target = client.target(uri);
			Response response = target.request().get();
			valStatus("getStroages", "getStroages", response);
			String x = response.readEntity(String.class);
			ObjectMapper mapper = new ObjectMapper();
	    	List<Storage> list = mapper.readValue(x, new TypeReference<List<Storage>>() {});
			return list;
//			return response.readEntity(new GenericType<List<Storage>>(Storage.class) {});
		} catch (Exception e) {
			String msg = "Error while creating getStroages.";
			throw new KubernetesClientException(msg, e);
		}
	}
	public boolean createStorage(String name, String size) throws KubernetesClientException {
		try {
			return createStorage( name,  size, "ext4");
		} catch (Exception e) {
			throw new KubernetesClientException(e);
		}
	}
	public boolean createStorage(String name, String size, String type) throws KubernetesClientException {
		try {
			Client client = getClient();
			client.register(JacksonFeature.class);
			String uri = String.format(endpointUrl + "%1$s", "/api/images/" + name + "/" + size + "/" + type);
			WebTarget target = client.target(uri);
			Response response = target.request().post(Entity.entity(new HashMap<String, String>(), MediaType.APPLICATION_JSON));
			valStatus("createStroage", "", response);
			return true;
		} catch (Exception e) {
			String msg = "Error while retrieving createStorage.";
			throw new KubernetesClientException(msg, e);
		}
	}

	public boolean modifyStorage(String name, String size) throws KubernetesClientException {
		try {
			Client client = getClient();
			client.register(JacksonFeature.class);
			String uri = String.format(endpointUrl + "%1$s", "/api/images/" + name + "/resize/" + size);
			System.out.println(uri);
			WebTarget target = client.target(uri);
			Response response = target.request().put(Entity.entity(new HashMap<String, String>(), MediaType.APPLICATION_JSON));
			valStatus("modifyStroage", "", response);
			return true;
		} catch (Exception e) {
			String msg = "Error while retrieving modifyStorage.";
			throw new KubernetesClientException(msg, e);
		}
	}
	
	public boolean formatStorage(String name) throws KubernetesClientException {
		try {
			return formatStorage(name, "ext4");
		} catch (Exception e) {
			throw new KubernetesClientException(e);
		}
	}
	
	public boolean formatStorage(String name, String type) throws KubernetesClientException {
		try {
			Client client = getClient();
			client.register(JacksonFeature.class);
			String uri = String.format(endpointUrl + "%1$s", "/api/images/" + name + "/format/" + type);
			System.out.println(uri);
			WebTarget target = client.target(uri);
			Response response = target.request().put(Entity.entity(new HashMap<String, String>(), MediaType.APPLICATION_JSON));
			valStatus("modifyStroage", "", response);
			return true;
		} catch (Exception e) {
			String msg = "Error while retrieving modifyStorage.";
			throw new KubernetesClientException(msg, e);
		}
	}
	
	public boolean removeStorage(String name) throws KubernetesClientException {
		try {
			Client client = getClient();
			client.register(JacksonFeature.class);
			String uri = String.format(endpointUrl + "%1$s", "/api/images/" + name);
			WebTarget target = client.target(uri);
			Response response = target.request().delete();
			valStatus("removeStroage", "", response);
			return true;
		} catch (Exception e) {
			String msg = "Error while retrieving removeStorage.";
			throw new KubernetesClientException(msg, e);
		}
	}
	
    public boolean valStatus(String type, String message, Response response) throws Exception {
    	if (response == null) {
            throw new KubernetesClientException(message);
        }
    	
    	switch (response.getStatus()) {
		case 200:
			return true;
		default:
			throw new Exception(response.getStatus() + "");
		}
    }
	
    
//    public static void main(String[] args) throws Exception {
    	
//    	String x = "[{\"name\":\"hyimg\",\"size\":134217728,\"objects\":32,\"order\":22,\"objects_size\":0,\"block_name_prefix\":\"rb.0.138f.74b0dc51\",\"format\":1}]";
//    	ObjectMapper mapper = new ObjectMapper();
//    	mapper.readValue(System.in, valueType);
//    	List<Storage> list = mapper.readValue(x, new TypeReference<List<Storage>>() {});
//    	System.out.println(list.size());
    	
//    	CephClient client = new CephClient();
//   	client.setEndpointUrl("http://10.58.56.47:7878/api/images");
//    	client.modifyStorage("pass-1", "1024", "ext4");
//    	client.createStorage("pass-1", "512", "ext4");
//    	client.modifyStorage("ghj", "2048");
//    	client.getStorages();
//    	client.formatStorage("ghj");
//    	client.getStorages();
//    	client.removeStorage("ghj");
//    	System.out.println(client.getStorages());
//	}
    
//    public static void main(String[] args) throws InterruptedException {
//		final CephClient cephClient = new CephClient();
//		Client client = getClient();
//		System.out.println(client.hashCode());
//    	
//    	Thread thread = new Thread() {
//    		@Override
//    		public void run() {
//    			Client client = getClient();
//    			System.out.println(client.hashCode());
//    		}
//    	};
//    	thread.start();
//    	thread.join();
//    	
//    	System.out.println(client.hashCode());
//    	
//	}
    
}
