//package com.gome.autodeploy.web.svnkit;
//
//import hudson.scm.PerJobCredentialStore;
//import hudson.scm.UserProvidedCredential;
//import hudson.scm.SubversionSCM.DescriptorImpl.Credential;
//import hudson.scm.UserProvidedCredential.AuthenticationManagerImpl;
//
//import java.io.PrintWriter;
//import java.io.StringWriter;
//
//import org.tmatesoft.svn.core.SVNException;
//import org.tmatesoft.svn.core.SVNURL;
//import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
//import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
//import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
//import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
//import org.tmatesoft.svn.core.io.SVNRepository;
//import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
//
//
//public class SVNOperator {
//	  static {
//	        FSRepositoryFactory.setup();
//	        SVNRepositoryFactoryImpl.setup();
//	        DAVRepositoryFactory.setup();
//	    }
//	public static void main(String[] args) {
//		
//		StringWriter log = new StringWriter();
//        PrintWriter logWriter = new PrintWriter(log);
//        
//        
//        
//		SVNRepository repository = null;
//
//        try {
//         String url = "http://10.58.44.86/repos/gmfs/branches/imgServer";
//			repository = SVNRepositoryFactory.create(SVNURL.parseURIDecoded(url));
//			
//			 UserProvidedCredential upc = UserProvidedCredential.fromForm(req,parser);
//		     AuthenticationManagerImpl authManager = upc.new AuthenticationManagerImpl(logWriter) {
//                 @Override
//                 protected void onSuccess(String realm, Credential cred) {
//                     LOGGER.info("Persisted "+cred+" for "+realm);
//                     credentials.put(realm, cred);
//                     save();
//                     if (upc.inContextOf!=null)
//                         new PerJobCredentialStore(upc.inContextOf).acknowledgeAuthentication(realm,cred);
//
//                 }
//             };
//             
//             
//			repository.getAuthenticationManager().getFirstAuthentication(ISVNAuthenticationManager.PASSWORD, realm, url);
//        }catch (SVNException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally{
//        	if(repository != null){
//        		repository.closeSession();
//        	}
//        }
//
//	}
//
//}
