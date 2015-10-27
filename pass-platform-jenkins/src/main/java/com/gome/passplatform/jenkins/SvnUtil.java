/**
 * 
 */
package com.gome.passplatform.jenkins;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

/**
 * @author bailu-ds
 *
 */
public class SvnUtil {

	public static void setupLibrary() {  
        DAVRepositoryFactory.setup();  
        SVNRepositoryFactoryImpl.setup();  
        FSRepositoryFactory.setup();  
    }
	
	/** 
     * 验证登录svn 
	 * @throws SVNException 
     */  
    public static void authSvn(String svnRoot, String username, String password) throws SVNException {  
    	// 初始化版本库  
        setupLibrary();  
        // 创建库连接  
        SVNRepository repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(svnRoot));  
        // 身份验证  
        ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(username, password.toCharArray());
        // 创建身份验证管理器  
        repository.setAuthenticationManager(authManager);  
        repository.checkPath(svnRoot, 0);
//        DefaultSVNOptions options = SVNWCUtil.createDefaultOptions(true);  
//        SVNClientManager clientManager = SVNClientManager.newInstance(options, authManager);  
//        clientManager.getAdminClient().checkCancelled();
    } 
    
//    public static void main(String[] args) {
//    	try {
//    		SvnUtil.authSvn("http://10.58.44.86/repos/gmfs/branches/passplatform", "fangbin", "123457");
//		} catch (SVNException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
}
