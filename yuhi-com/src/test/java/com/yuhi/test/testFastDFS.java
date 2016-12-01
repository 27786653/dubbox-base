package com.yuhi.test;

import java.io.File;

import com.yuhi.util.FastDFSClientUtils;

public class testFastDFS {

	
	public static void main(String[] args) {
		String fileId = FastDFSClientUtils.upload(new File("C:\\Users\\李森林\\Pictures\\Saved Pictures\\20161021155749.jpg"),".jpg");
		System.out.println(fileId);
		
	}
	//FastDFSClientUtils.delete("group1", fileId);
}
