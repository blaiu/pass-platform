/**
 * 
 */
package com.gome.passplatform.kubernetes;

//import com.gome.passplatform.kubernetes.modelbeat.Pod;
//import com.gome.passplatform.kubernetes.modelbeat.PodList;
//import com.gome.passplatform.kubernetes.modelbeat.ReplicationController;
//import com.gome.passplatform.kubernetes.modelbeat.ReplicationControllerList;
//import com.gome.passplatform.kubernetes.modelbeat.Service;
//import com.gome.passplatform.kubernetes.modelbeat.ServiceList;

/**
 * @author bailu-ds
 *
 */
public class MainTestBeat {

	public static void main(String[] args) {
//		final String KUBERNETES_API_ENDPOINT = "http://192.168.1.100:8080/api/v1beta1/";
//
//        KubernetesApiClient client = new KubernetesApiClient(KUBERNETES_API_ENDPOINT);
//        
//        // test get pod
//        System.out.println("Test GET POD");
//        System.out.println(client.getPod("", "redis-master-2"));
//        
//        // test get all Pods
//        System.out.println("Test GET PODS");
//        PodList podList = client.getAllPods("");
//        printPods(podList);
//        
//        // test create pod
//        System.out.println("Test POST POD");
//        Pod pod = new Pod();
//        pod.setId("github-test-pod");
//        Label l = new Label();
//        l.setName("github");
//        pod.setLabels(l);
//        State desiredState = new State();
//        Manifest m = new Manifest();
//        m.setId("github-test-pod");
//        Container c = new Container();
//        c.setName("master");
//        c.setImage("busybox");
//        Port p = new Port();
//        p.setContainerPort(8379);
//        p.setHostPort(8379);
//        c.setPorts(Collections.singletonList(p));
//        m.setContainers(Collections.singletonList(c));
//        desiredState.setManifest(m);
//        pod.setDesiredState(desiredState);
//        client.createPod(pod);
//        
//        podList = client.getAllPods();
//        printPods(podList);
//        
//        // test delete Pod
//        System.out.println("Test DELETE POD");
//        client.deletePod("github-test-pod");
//        
//        podList = client.getAllPods();
//        printPods(podList);
//        
//        /* Replication Controllers */
//        // test get controller
//        System.out.println("Test GET ReplicationController");
//        ReplicationController controller = client.getReplicationController("frontendController");
//        System.out.println(controller);
//        
//        // test get all controllers
//        System.out.println("Test GET ReplicationControllers");
//        ReplicationControllerList controllers = client.getAllReplicationControllers();
//        printControllers(controllers);
//        
//        // test create controller
//        System.out.println("Test POST ReplicationController");
//        ReplicationController contr = new ReplicationController();
//        contr.setId("githubController");
//        desiredState = new State();
//        desiredState.setReplicas(3);
//        Selector selector = new Selector();
//        selector.setName("frontend");
//        desiredState.setReplicaSelector(selector);
//        
//        Pod podTemplate = new Pod();
//        State podState = new State();
//        Manifest manifest = new Manifest();
//        manifest.setId("githubfrontendController");
//        Container container = new Container();
//        container.setName("github-php-redis");
//        container.setImage("busybox");
//        p = new Port();
//        p.setContainerPort(81);
//        p.setHostPort(8001);
//        container.setPorts(Collections.singletonList(p));
//        manifest.setContainers(Collections.singletonList(container));
//        podState.setManifest(manifest);
//        podTemplate.setDesiredState(podState);
//        Label l1 = new  Label();
//        l1.setName("frontend");
//        podTemplate.setLabels(l1);
//        
//        desiredState.setPodTemplate(podTemplate);
//        contr.setDesiredState(desiredState);
//        Label l2 = new Label();
//        l2.setName("frontend");
//        contr.setLabels(l2);
//        
//        client.createReplicationController(contr);
//        
//        controllers = client.getAllReplicationControllers();
//        printControllers(controllers);
//        
//        // Test delete controller
//        System.out.println("Test DELETE ReplicationController");
//        client.deleteReplicationController("githubController");
//        
//        controllers = client.getAllReplicationControllers();
//        printControllers(controllers);
//        
//        // test get service
//        System.out.println("Test GET Service");
//        System.out.println(client.getService("redisslave"));
//        
//        // test get all services
//        System.out.println("Test GET All Services");
//        ServiceList services = client.getAllServices();
//        printServices(services);
//        
//        // test create service
//        System.out.println("Test POST Service");
//        Service service = new Service();
//        service.setId("githubfrontend");
//        service.setPort(9999);
//        selector = new Selector();
//        selector.setName("frontend");
//        service.setSelector(selector);
//        
//        client.createService(service);
//        
//        services = client.getAllServices();
//        printServices(services);
//        
//        // Test delete Service
//        System.out.println("Test DELETE Service");
//        client.deleteService("githubfrontend");
//        
//        services = client.getAllServices();
//        printServices(services);
        
	}

	
//	private static void printControllers(ReplicationControllerList controllers) {
//		for (ReplicationController replicationController : controllers.getItems()) {
//			System.out.println("Replication Controller: "+replicationController.getId());
//		}
//	}
//
//	private static void printPods(PodList podList) {
//		for (Pod pod2 : podList.getItems()) {
//			System.out.println("Pod : "+pod2.getId());
//		}
//	}
//
//	private static void printServices(ServiceList services) {
//		for (Service service : services.getItems()) {
//			
//        	System.out.println("Service : "+service.getId());
//		}
//	}
	
}
