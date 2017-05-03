package com.yuhi.util;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

public class MD5Test {
	
	public static void main(String[] args) {
		
		//原始 密码 
		String source = "htb1admin888";
		//盐
		String salt = "test";
		//散列次数
		int hashIterations = 1;
		//上边散列1次：48474f975022f960bc2afbe49be581e8
		//上边散列2次：13f79dafcbbedc313273e2b891ac84d3
		
		
		//构造方法中：
		//第一个参数：明文，原始密码 
		//第二个参数：盐，通过使用随机数
		//第三个参数：散列的次数，比如散列两次，相当 于md5(md5(''))
		Md5Hash md5Hash = new Md5Hash(source, salt, hashIterations);
		
		String password_md5 =  md5Hash.toString();
		System.out.println(password_md5);
		//第一个参数：散列算法 
		SimpleHash simpleHash = new SimpleHash("md5", source, salt, hashIterations);
		System.out.println(simpleHash.toString());
		
		
		
	}

}
