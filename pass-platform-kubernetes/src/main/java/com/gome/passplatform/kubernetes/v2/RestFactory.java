package com.gome.passplatform.kubernetes.v2;
//package com.gome.kub.v2;
//
//import java.net.URI;
//import java.net.URISyntaxException;
//
////import javax.ws.rs.client.Client;
////import javax.ws.rs.client.ClientBuilder;
////
////import org.apache.http.auth.AuthScope;
////import org.apache.http.auth.UsernamePasswordCredentials;
////import org.apache.http.impl.client.DefaultHttpClient;
////import org.apache.http.auth.AuthScope;
////import org.apache.http.auth.UsernamePasswordCredentials;
////import org.apache.http.impl.client.DefaultHttpClient;
////import org.apache.commons.httpclient.*;
////import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
////import org.glassfish.jersey.client.JerseyClient;
////import org.glassfish.jersey.client.JerseyClientBuilder;
////import org.jboss.resteasy.client.jaxrs.ProxyBuilder;
////import org.jboss.resteasy.client.jaxrs.ResteasyClient;
////import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
////import org.jboss.resteasy.client.jaxrs.engines.ApacheHttpClient4Engine;
//
//public class RestFactory {
//
//    private final ClassLoader classLoader;
//
//    public RestFactory() {
//        classLoader = null;
//    }
//
//    public RestFactory(ClassLoader classLoader) {
//        this.classLoader = classLoader;
//    }
//
//    public KubernetesAPI createAPI(URI uri, String userName, String password) {
//        
//    	
//    	
////    	DefaultHttpClient httpClient = new DefaultHttpClient();
////        httpClient.getCredentialsProvider().setCredentials(new AuthScope(uri.getHost(), uri.getPort()), new UsernamePasswordCredentials(userName, password));
////
////        ApacheHttpClient4Engine engine = new ApacheHttpClient4Engine(httpClient);
////        
////        JerseyClient client = new JerseyClientBuilder().register(engine);
////        
//////        ResteasyClient client = new ResteasyClientBuilder().httpEngine(engine).build();
////        client.register(JacksonJaxbJsonProvider.class);
////        ProxyBuilder<KubernetesAPI> proxyBuilder = client.target(uri).proxyBuilder(KubernetesAPI.class);
////        if (classLoader != null) {
////            proxyBuilder = proxyBuilder.classloader(classLoader);
////        }
////        return proxyBuilder.build();
//    	return null;
//    }
//
//    public KubernetesAPI createAPI(String url, String userName, String password) throws URISyntaxException {
//        URI uri = new URI(url);
//        return createAPI(uri, userName, password);
//    }
//}
