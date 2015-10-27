/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */
package com.gome.passplatform.kubernetes;





/**
 * This class is deprecated, please refer to the test cases in src/test folder.
 */
//@Deprecated
public class MainTest {

	public static void main(String[] args) throws Exception {
//		final String KUBERNETES_API_ENDPOINT = "http://10.58.56.65:8080/api/v1beta3/";
//		final String KUBERNETES_API_ENDPOINT = "http://192.168.137.152:8080/api/v1/";
//        KubernetesApiClient client = new KubernetesApiClient(KUBERNETES_API_ENDPOINT);
//        createNameSpace(client);
//        createPod(client);
//        getPod(client);
//        deletePod(client);
//        getAllPod(client);
//        getReplicationController(client);
//        getReplicationControllerList(client);
//        updateReplicationController(client);
//        createReplicationController(client);
//        deleteReplicationController(client);
//        getService(client);
//        getServiceAll(client);
//        createService(client);
        
//        Label label = new Label();
//        label.setName("fedoraapache");
//        
//        PodList podList = client.getSelectedPods("namespaces/hy/", Collections.singletonList(label));
//        printPods(podList);
        
//        createAccount(client);
//        deleteAccount(client);
	}
	
//	public static void getPod (KubernetesApiClient client) throws KubernetesClientException {
//		System.out.println(client.getPod("namespaces/default/", "lianjiatestcom3-gcj7h"));
//	}
//	public static void getAllPod (KubernetesApiClient client) throws KubernetesClientException {
//		PodList podList = client.getAllPods("namespaces/default/");
//		printPods(podList);
//	}
//	public static void createPod (KubernetesApiClient client) throws KubernetesClientException {
//		Pod pod = new Pod();
//		Metadata met = new Metadata();
//		met.setName("blaiu-apache-pod");
//		met.setNamespace("blaiu");
//		
//        Label l = new Label();
//        l.setName("blaiu-apache-pod");
//        met.setLabels(l); 
//
//        Spec spec = new Spec();
//        Container c = new Container();
//        c.setName("apache-container");
//        c.setImage("fedora/apache");
//        c.setImagePullPolicy("IfNotPresent");
//        
//        Port p = new Port();
//        p.setContainerPort(8378);
//        p.setProtocol("TCP");
//        c.setPorts(Collections.singletonList(p));
//        spec.setContainers(Collections.singletonList(c));
//        
//        Status status = new Status();
//        pod.setMetadata(met);
//        pod.setSpec(spec);
//        pod.setStatus(status);
//        
//        
////        ObjectMapper objectMapper = new ObjectMapper();
////        try {
////			objectMapper.writeValue(System.out, pod);
////		} catch (JsonMappingException e) {
////			e.printStackTrace();
////		} catch (IOException e) {
////			e.printStackTrace();
////		}
//        
//        client.createPod("namespaces/blaiu/", pod);
//	}
//	
//	public static void deletePod (KubernetesApiClient client) throws KubernetesClientException {
//		client.deletePod("namespaces/default/", "lianjiatestcom3-v2eo3");
//	}
//	
//	public static void getReplicationController (KubernetesApiClient client) throws KubernetesClientException {
//		ReplicationController controller = client.getReplicationController("", "lianjiatestcom");
//		System.out.println(controller.getMetadata().getName());
//	}
//	
//	public static void getReplicationControllerList (KubernetesApiClient client) throws KubernetesClientException {
//		ReplicationControllerList controllers = client.getAllReplicationControllers("");
//		printControllers(controllers);
//	}
//	
//	public static void createReplicationController (KubernetesApiClient client) throws KubernetesClientException {
//		ReplicationController controller = new ReplicationController();
//		
//		Metadata metadata = new Metadata();
//		metadata.setName("gome-controller");
//		
//		Label label = new Label();
//		label.setName("gome-label");
//		metadata.setLabels(label);
//		controller.setMetadata(metadata);
//		
//		RcSpec rcSpec = new RcSpec();
//		rcSpec.setReplicas(3);
//		
//		Selector selector = new Selector();
//		selector.setName("gome-label");
//		rcSpec.setSelector(selector);
//		
//		Template template = new Template();
//		Metadata metadata2 = new Metadata();
//		metadata2.setName("gome-controller-template");
//		metadata2.setLabels(label);
//		template.setMetadata(metadata);
//		
//		Spec spec = new Spec();
//		Container container = new Container();
//		container.setName("fedoraapache");
//		container.setImage("httpd");
//		container.setImagePullPolicy("IfNotPresent");
//		Port p = new Port();
//		p.setContainerPort(80);
//		container.setPorts(Collections.singletonList(p));
//		spec.setContainers(Collections.singletonList(container));
//		template.setSpec(spec);
//		rcSpec.setTemplate(template);
//		
//		controller.setSpec(rcSpec);
//		
//		RcStatus status = new RcStatus();
//		
//		controller.setStatus(status);
//		
//		client.createReplicationController("", controller);
//	}
//	
//	public static void updateReplicationController (KubernetesApiClient client) throws KubernetesClientException {
//		client.updateReplicationController("", "gome-controller", 7);
//	}
//	
//	public static void deleteReplicationController (KubernetesApiClient client) throws KubernetesClientException {
//		client.deleteReplicationController("", "gome-controller");
//	}
//	
//	public static void getService (KubernetesApiClient client) throws KubernetesClientException {
//		System.out.println(client.getService("", "liuservice"));
//	}
//	
//	public static void getServiceAll (KubernetesApiClient client) throws KubernetesClientException {
//		ServiceList services = client.getAllServices("");
//		printServices(services);
//	}
//	
//	public static void createService (KubernetesApiClient client) throws KubernetesClientException {
//		
//		Service service = new Service();
//		
////		Item item = new Item();
//		
//		Metadata metadata = new Metadata();
//		metadata.setName("gomeservice");
////		metadata.setNamespace("");
//		service.setMetadata(metadata);
//		
//		ServiceSpec spec = new ServiceSpec();
//		ServicePort port = new ServicePort();
//		port.setNodePort(31559);
////		port.setPort(221);
//		port.setProtocol("TCP");
////		port.setTargetPort(80);
//		port.setContainerPort(80);
//		
//		spec.setType("NodePort");
//		
//		spec.setPorts(Collections.singletonList(port));
//		
//		Selector selector = new Selector();
//		selector.setName("liutest");
//		spec.setSelector(selector);
////		item.setSpec(spec);
//		
////		item.setMetadata(metadata);
//		service.setSpec(spec);
////		service.setItems(Collections.singletonList(item));
//		
////		service.setId("liuservice");
////		service.setPort(222);
////		service.setContainerPort(80);
////		service.setType("NodePort");
////		service.setNodePort(9000);
////		Selector selector = new Selector();
////		selector.setName("liutest");
////		service.setSelector(selector);
////		Label l3 = new Label();
////		l3.setName("liutest");
////		service.setLabels(l3);
//      
////      objectMapper.writeValue(System.out, service);
//      
//		client.createService("", service);
//	}
//	
//	public static void createNameSpace (KubernetesApiClient client) throws KubernetesClientException {
//		Namespaces ns = new Namespaces();
//		Metadata met = new Metadata();
//		met.setName("blaiu");
//		ns.setMetadata(met);
//		client.createNamespace(ns);
//	}
//	
//	public static void deleteService (KubernetesApiClient client) throws KubernetesClientException {
//		client.deleteService("", "liuservice");
//	}
//	
//	private static void printControllers(ReplicationControllerList controllers) {
//		for (ReplicationController replicationController : controllers.getItems()) {
//			System.out.println("Replication Controller: "+replicationController.getMetadata().getName());
//			System.out.println("Replication Controller replicas: "+replicationController.getStatus().getReplicas());
//		}
//	}
//
//	private static void printPods(PodList podList) {
//		for (Pod pod2 : podList.getItems()) {
//			System.out.println("Pod : "+pod2.getMetadata().getName());
//		}
//	}
//	
//	private static void printAccounts(ServiceAccountList accountList) {
//		for (ServiceAccount account : accountList.getItems()) {
//			System.out.println("Pod : "+account.getMetadata().getName());
//		}
//	}
//
//	private static void printServices(ServiceList services) {
//		for (Service service : services.getItems()) {
//        	System.out.println("Service : "+service.getMetadata().getName());
//		}
//	}
//	
//	public static void getAccount (KubernetesApiClient client) throws KubernetesClientException {
//		System.out.println(client.getServiceAccount("namespaces/default/", "blaiu"));
//	}
//	public static void getAllAccount (KubernetesApiClient client) throws KubernetesClientException {
//		ServiceAccountList accountList = client.getAllServiceAccounts("namespaces/default/");
//		printAccounts(accountList);
//	}
//	public static void createAccount (KubernetesApiClient client) throws KubernetesClientException {
//		ServiceAccount account = new ServiceAccount();
//		ServiceAccountMetadata metadata = new ServiceAccountMetadata();
//		metadata.setName("gome");
////		metadata.setLabels("gome-labels");
//		metadata.setNamespace("blaiu");
//		ServiceAccountSecret secret = new ServiceAccountSecret();
//		secret.setName("gome");
//		secret.setNamespace("blaiu");
//		account.setMetadata(metadata);
//		account.setSecrets(Collections.singletonList(secret));
//		account.setImagePullSecrets(Collections.singletonList(new ServiceAccountImagePullSecret()));
//		client.createServiceAccount("namespaces/blaiu/", account);
//	}
//	
//	public static void deleteAccount (KubernetesApiClient client) throws KubernetesClientException {
//		client.deleteServiceAccount("namespaces/blaiu/", "gome");
//	}

}
