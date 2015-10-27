/**
 * 
 */
package com.gome.passplatform.kubernetes;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.jackson.JacksonFeature;

import com.gome.passplatform.kubernetes.exp.ConditionDo;

/**
 * @author bailu-ds
 *
 */
public class ExpansionClient {

	private String defaultNamespaces;
	private String endpointUrl;
	
	private static final ThreadLocal<Client> tl = new ThreadLocal<Client>();
	
	public ExpansionClient() {
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
	
	public static Client getClient() {
		Client client = tl.get();
		if (null == client) {
			client = ClientBuilder.newClient();
			tl.set(client);
		}
		return client;
	}

	public ConditionDo getconditionset(String namespaces, String rcName) throws Exception {
		try {
			Client client = getClient();
			client.register(JacksonFeature.class);
			String uri = String.format(endpointUrl + "%1$s", "getconditionset/" + namespaces + "/" + rcName);
			WebTarget target = client.target(uri);
			Response response = target.request().get();
			System.out.println(response.getMetadata().toString());
			getOK("getconditionset", "", response);
			return response.readEntity(ConditionDo.class);
		} catch (Exception e) {
			String msg = "Error while retrieving getconditionset.";
			throw new Exception(msg, e);
		}
	}
	
	public boolean insertcondition(ConditionDo conditionDo) throws Exception {
		try {
			Client client = getClient();
			client.register(JacksonFeature.class);
			String uri = String.format(endpointUrl + "%1$s", "insertcondition");
			WebTarget target = client.target(uri);
			Response response = target.request().buildPost(Entity.entity(conditionDo, MediaType.APPLICATION_JSON)).invoke();
			postOK("insertcondition", conditionDo.getRcname(), response);
			return true;
		} catch (Exception e) {
			String msg = "Error while creating insertcondition: ";
			throw new Exception(msg, e);
		}
	}
	
	public boolean deletecondition(String namespaces, String rcName) throws Exception {
		try {
			Client client = getClient();
			client.register(JacksonFeature.class);
			String uri = String.format(endpointUrl + "%1$s", "deletecondition/" + namespaces + "/" + rcName);
			WebTarget target = client.target(uri);
			Response response = target.request().delete();
			deleteOK("deletecondition", "", response);
			return true;
		} catch (Exception e) {
			String msg = "Error while retrieving deletecondition.";
			throw new Exception(msg, e);
		}
	}
	
	public boolean postOK(String type, String message, Response response) throws Exception {
    	if (response == null) {
            throw new Exception(message);
        }
    	switch (response.getStatus()) {
		case 200:
			return true;
		case 409:
			return true;
		default:
			throw new Exception(response.getStatus() + "");
		}
    }
    
    public boolean getOK(String type, String message, Response response) throws Exception {
    	if (response == null) {
            throw new Exception(message);
        }
    	
    	switch (response.getStatus()) {
		case 200:
			return true;
		default:
			throw new Exception(response.getStatus() + "");
		}
    }
    
    public boolean deleteOK(String type, String message, Response response) throws Exception {
    	if (response == null) {
    		throw new Exception(message);
    	}
    	
    	switch (response.getStatus()) {
    	case 200:
    		return true;
    	case 404:
    		return true;
    	default:
    		throw new Exception(response.getStatus() + "");
    	}
    }
    
//    public static void main(String[] args) {
//    	
//    	ExpansionClient client = new ExpansionClient();
//    	client.setDefaultNamespaces("");
//    	client.setEndpointUrl("");
//    	
//    	ConditionDo conditionDo = new ConditionDo();
//    	conditionDo.setLabels("name:huang");
//    	
//    	conditionDo.setNamespace("default");
//    	conditionDo.setMaxcount(5);
//    	conditionDo.setMincount(1);
//    	
//    	conditionDo.setRcname("eatcpu");
//    	conditionDo.setAddcountpertime(1);
//    	conditionDo.setSubcountpertime(1);
//    	
//    	MemConditionDo cpucondition = new MemConditionDo();
//    	cpucondition.setMaxvalue(3.5);
//    	cpucondition.setMinvalue(1.0);
//    	MemConditionDo memcondition = new MemConditionDo();
//    	memcondition.setMaxvalue(512 * (1D * 1024 * 1024));
//    	memcondition.setMinvalue(256 * (1D * 1024 * 1024));
//    	
//    	Map<String, MemConditionDo> map = new HashMap<String, MemConditionDo>();
//    	map.put(ExpConstat.CPU_USEAGE, cpucondition);
//    	map.put(ExpConstat.MEM_USEAGE, memcondition);
////    	map.put(ExpConstat.NET_REBYTE, memorycondition);
////    	map.put(ExpConstat.NET_RXBYTE, memorycondition);
////    	map.put(ExpConstat.NET_TEBYTE, memorycondition);
////    	map.put(ExpConstat.NET_TXBYTE, memorycondition);
//    	conditionDo.setConditionmaps(map);
//    	
////    	conditionDo.setMemorycondition(memorycondition);
//    	
//    	try {
//    		
////    		ObjectMapper mapper = new ObjectMapper();
////    		mapper.writeValue(System.out, conditionDo);
//    		
////    		ExpansionClient expansionClient = new ExpansionClient();
////    		expansionClient.defaultNamespaces = "default";
////    		expansionClient.endpointUrl = "http://10.58.56.47:8082/api/v1/";
////    		expansionClient.insertcondition(conditionDo);
////    		ConditionDo cd = client.getconditionset("default", "gomeimg");
////			ExpansionClient.deletecondition("default", "gomeimg");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
    
}
