package com.gome.autodeploy.common;



import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtil {
	
	public static void main(String[] args){
		EncryptUtil eu = new EncryptUtil();
		try {
			System.out.println(eu.Md5("123456"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	
	public  String Md5(String plainText) throws NoSuchAlgorithmException{
		
		
		MessageDigest md=MessageDigest.getInstance("MD5");
		md.update(plainText.getBytes());
		
		byte b[]=md.digest();

		int i;
		

		StringBuffer buf=new StringBuffer("");
		for(int offset=0;offset<b.length;offset++){
		i=b[offset];
		if(i<0)i+=256;
		if(i<16)
		buf.append("0");
		buf.append(Integer.toHexString(i));
		}

		return buf.toString();
		
		}


}