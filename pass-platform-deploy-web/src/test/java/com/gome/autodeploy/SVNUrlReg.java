package com.gome.autodeploy;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class SVNUrlReg {
	private final String regex="http(s?)://(.*)/";
	@Test
	public void test() {
		Pattern p=Pattern.compile(regex);
		Matcher m=p.matcher("https://www.baidu.com:8080/sjk");
		while(m.find()){
			String svnStr = m.group(2);
			if(svnStr.contains(":")){
				String[] ipAndPort = svnStr.split(":");
				System.out.println(ipAndPort[0]+"##"+ipAndPort[1]);
			}else{
				System.out.println(svnStr);
			}
		}
	}

}
