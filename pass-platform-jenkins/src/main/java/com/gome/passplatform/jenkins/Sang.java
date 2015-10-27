package com.gome.passplatform.jenkins;

import hudson.Util;
import hudson.util.IOUtils;
import hudson.util.Secret;
import hudson.util.TextFile;

import java.io.File;
import java.io.FileInputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.trilead.ssh2.crypto.Base64;


public class Sang {
	String jenkinsPath = "D:\\Program Files\\Jenkins\\secrets";
	public static void main(String[] args) throws  Exception {
		
//		  Secret pass = Secret.fromString(Scrambler.scramble("yangsj123"));
//		  System.out.println(pass.toString());
		  String key = "123456";
		  String hskey = new String(Base64.encode(key.getBytes("UTF-8")));
//		  
		  //jenkis masterkey
		  
//		  File keyFile =  new File("e:\\aaaa.KEY");
//		  System.out.println(keyFile.exists());
		  Sang y = new Sang();
		  //解密
//		  String value = "bQGBLQ5pai+PApQVbyB66J64xC72ouQV4ll4pIqQlP8=";
//		  byte[] in = Base64.decode(value.toCharArray());
////		  System.out.println(in.length);
//		  Cipher cipher = Secret.getCipher("AES");
//          cipher.init(Cipher.DECRYPT_MODE, y.getKey());
//          System.out.println("+++++++++++++++++"+in.length);
          Cipher cipher = Secret.getCipher("AES");
          cipher.init(Cipher.ENCRYPT_MODE, y.getKey());
          String v = new String ( Base64.encode(cipher.doFinal((hskey+"::::MAGIC::::").getBytes("UTF-8"))));
//          String v =   new String(Base64.encode(cipher.doFinal((new String(Base64.encode(key.getBytes("UTF-8")))+"::::MAGIC::::").getBytes("UTF-8"))));
          System.out.println(v);
//          	if(plainText.endsWith("::::MAGIC::::"))
//          		plainText =  plainText.substring(0,plainText.length()-"::::MAGIC::::".length());
//          	
          	
//          	System.out.println(new String(Base64.decode(plainText.toCharArray())));
//          String str = new String(Base64.encode(cipher.doFinal((value+MAGIC).getBytes("UTF-8"))));
//          System.out.println(str);
	}

	public SecretKey getKey() throws Exception {
		return new SecretKeySpec(getload(),0,128/8, "AES");
	}
	
	@SuppressWarnings("unused")
	public byte[] getload() throws Exception{
		 CipherInputStream cis=null;
		 FileInputStream fis=null;
		 TextFile masterSecret = new TextFile(new File(jenkinsPath + File.separator + "master.key"));
		 String masterKey = masterSecret.readTrim();
		 SecretKey hamasterKey = Util.toAes128Key(masterKey);
		 File keyFile =  new File(jenkinsPath + File.separator+"hudson.util.Secret");
		 Cipher sym = Secret.getCipher("AES");
        sym.init(Cipher.DECRYPT_MODE, hamasterKey);
        
       
        
        cis = new CipherInputStream(fis=new FileInputStream(keyFile), sym);
        byte[] bytes = IOUtils.toByteArray(cis);
//        System.out.println(bytes.length+"#####");
        return verifyMagic(bytes);
        
	}
	
	private   byte[] MAGIC = "::::MAGIC::::".getBytes();
	   private byte[] verifyMagic(byte[] payload) {
	        int payloadLen = payload.length-MAGIC.length;
	        if (payloadLen<0)   return null;    // obviously broken

	        for (int i=0; i<MAGIC.length; i++) {
	            if (payload[payloadLen+i]!=MAGIC[i])
	                return null;    // broken
	        }
	        byte[] truncated = new byte[payloadLen];
	        System.arraycopy(payload,0,truncated,0,truncated.length);
//	        System.out.println(truncated.length+"$$#$#$#");
	        return truncated;
	    }
}
