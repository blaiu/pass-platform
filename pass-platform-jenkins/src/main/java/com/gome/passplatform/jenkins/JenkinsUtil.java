/**
 * 
 */
package com.gome.passplatform.jenkins;

import hudson.Util;
import hudson.util.IOUtils;
import hudson.util.Secret;
import hudson.util.TextFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.InvalidKeyException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.trilead.ssh2.crypto.Base64;

/**
 * @author bailu-ds
 *
 */
public class JenkinsUtil {

//	public static String jenkinsPath = "C:\\Users\\bailu-ds\\.jenkins\\secrets";
//	public static String jenkinsRoot = "C:\\Users\\bailu-ds\\.jenkins\\jobs";
	
	
	public static String getSecret(String password, String jenkinsSecretsPath) throws InvalidKeyException, Exception {
		String hskey = new String(Base64.encode(password.getBytes("UTF-8")));
		Cipher cipher = Secret.getCipher("AES");
        cipher.init(Cipher.ENCRYPT_MODE, getKey(jenkinsSecretsPath));
        String v = new String ( Base64.encode(cipher.doFinal((hskey+"::::MAGIC::::").getBytes("UTF-8"))));
        return v;
	}
	
	private static SecretKey getKey(String jenkinsSecretsPath) throws Exception {
		return new SecretKeySpec(getload(jenkinsSecretsPath),0,128/8, "AES");
	}
	
	@SuppressWarnings("unused")
	private static byte[] getload(String jenkinsSecretsPath) throws Exception{
		CipherInputStream cis = null;
		FileInputStream fis = null;
		TextFile masterSecret = new TextFile(new File(jenkinsSecretsPath + getFileseparator() + "master.key"));
		String masterKey = masterSecret.readTrim();
//		String masterKey = readTrim(new File(jenkinsSecretsPath + getFileseparator() + "master.key"));
		SecretKey hamasterKey = Util.toAes128Key(masterKey);
		File keyFile =  new File(jenkinsSecretsPath + getFileseparator() + "hudson.util.Secret");
		Cipher sym = Secret.getCipher("AES");
        sym.init(Cipher.DECRYPT_MODE, hamasterKey);
        cis = new CipherInputStream(fis = new FileInputStream(keyFile), sym);
        byte[] bytes = IOUtils.toByteArray(cis);
        return verifyMagic(bytes);
	}
	
	public static String read(File file) throws IOException {
        StringWriter out = new StringWriter();
        PrintWriter w = new PrintWriter(out);
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
        try {
            String line;
            while((line=in.readLine())!=null)
                w.println(line);
        } finally{
            in.close();
        }
        return out.toString();
    }
	 
	public static String readTrim(File file) throws IOException {
		return read(file).trim();
	}
	
	private static byte[] MAGIC = "::::MAGIC::::".getBytes();
	
	private static byte[] verifyMagic(byte[] payload) {
        int payloadLen = payload.length - MAGIC.length;
        if (payloadLen < 0)   return null;

        for (int i=0; i<MAGIC.length; i++) {
            if (payload[payloadLen+i] != MAGIC[i])
                return null;
        }
        byte[] truncated = new byte[payloadLen];
        System.arraycopy(payload, 0, truncated, 0, truncated.length);
        return truncated;
	} 
	
	public static String getFileseparator() {
		
		return File.separator;
//		return "/";
	}
	
}
